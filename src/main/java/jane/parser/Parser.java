package jane.parser;
import jane.ui.Ui;
import jane.exception.JaneException;
import jane.storage.Storage;
import jane.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user commands and executes the corresponding task operations.
 * Responsible for input validation and task creation.
 */
public class Parser {

    /** Formatter used for parsing date-time input */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser with the required application components.
     *
     * @param storage Storage handler for persisting tasks
     * @param tasks   Task list managed by the application
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Parses a 1-based index from user input and converts it to 0-based indexing.
     *
     * @param input Index provided by the user
     * @param n     Total number of tasks
     * @return Parsed 0-based index
     * @throws JaneException If the index is invalid or out of range
     */
    public int parseIndex(String input, int n) throws JaneException {
        try {
            int i = Integer.parseInt(input) - 1;
            if (i < 0 || i >= n) {
                throw new JaneException("Please use a valid index\n");
            }
            return i;
        } catch (NumberFormatException nfe) {
            throw new JaneException("Please use a valid index\n");
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param input Full user command containing the task index
     * @throws JaneException If the index is invalid
     */
    public String handleMark(String input) throws JaneException {
        int i = parseIndex(input.substring(4).trim(), tasks.getSize());
        assert i > 0 && i < tasks.getSize();
        tasks.mark(i);
        storage.updateData(tasks);
        return ui.printMark(tasks.get(i));
    }

    /**
     * Unmarks a completed task.
     *
     * @param input Full user command containing the task index
     * @throws JaneException If the index is invalid
     */
    public String handleUnmark(String input) throws JaneException {

        int i = parseIndex(input.substring(6).trim(), tasks.getSize());
        tasks.unMark(i);

        storage.updateData(tasks);
        return ui.printUnmark(tasks.get(i));
    }

    /**
     * Adds a task to the task list and updates persistent storage.
     *
     * @param task Task to be added
     */
    public String addTask(Task task) {
        tasks.addTask(task);
        storage.storeTask(task, tasks);
        return ui.printAdd(tasks.get(tasks.getSize() - 1), tasks.getSize());
    }

    /**
     * Handles creation of an Event task.
     *
     * @param input Full user command for creating an event
     * @throws JaneException If required fields are missing or date format is invalid
     */
    public String handleEvent(String input) throws JaneException {
        if (input.contains("/from") && input.contains("/to")) {
            String[] parts = input.substring(5).split("/from");
            if (parts.length < 2) {
                throw new JaneException("Missing field Error\nUsage: event [task] /from [start] /to [end]\n");
            }
            String[] dates = parts[1].split("/to");
            if (dates.length < 2) {
                    throw new JaneException("Empty field\nUsage: event [task] /from [start] /to [end]\n");
            }
            String des = parts[0].trim();
            String fromStr = dates[0].trim();
            String toStr = dates[1].trim();
            if (des.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                throw new JaneException("Empty field\nUsage: event [task] /from [start] /to [end]\n");
            }
            try {
                LocalDateTime from = LocalDateTime.parse(fromStr, FORMATTER);
                LocalDateTime to = LocalDateTime.parse(toStr, FORMATTER);
                return addTask(new Event(des, from, to));
            } catch (DateTimeParseException e) {
                throw new JaneException("Invalid Input\nUsage: [day]/[month]/[year] [time (24 hour clock)]\n");
            }
        } else {
            throw new JaneException("Usage: event [task] /from [start] /to [end]\n");
        }
    }

    /**
     * Handles creation of a Deadline task.
     *
     * @param input Full user command for creating a deadline
     * @throws JaneException If required fields are missing or date format is invalid
     */
    public String handleDeadline(String input) throws JaneException {
        if (input.contains("/by")) {
            int start = input.indexOf("/by");
            String des = input.substring(8, start).trim();
            if (des.isEmpty()) {
                throw new JaneException("Empty field\nUsage: deadline [task] /by [time]\n");
            }
            String byStr = input.substring(start + 3).trim();
            try {
                LocalDateTime by = LocalDateTime.parse(byStr, FORMATTER);
                return addTask(new Deadline(des, by));
            } catch (DateTimeParseException e) {
                throw new JaneException("Invalid Input\nUsage: [day]/[month]/[year] [time (24 hour clock)]\n");
            }
        } else {
            throw new JaneException("Usage: deadline [task] /by [time]\n");
        }
    }

    /**
     * Handles creation of a Todo task.
     *
     * @param input Full user command for creating a todo
     * @throws JaneException If the task description is empty
     */
    public String handleTodo(String input) throws JaneException {
        if (input.substring(4).trim().isEmpty()) {
            throw new JaneException("Usage: todo [task]\n");
        }
        return addTask(new Todo(input.substring(4).trim()));
    }

    /**
     * Removes a task from the task list.
     *
     * @param input Full user command containing the task index
     * @throws JaneException If the index is invalid
     */
    public String handleRemove(String input) throws JaneException {
        int i = parseIndex(input.substring(6).trim(), tasks.getSize());
        assert i > 0 && i < tasks.getSize();
        Task temp = tasks.get(i);
        tasks.removeTask(i);

        storage.updateData(tasks);
        return ui.printRemove(temp, tasks.getSize());
    }

    /**
     * Finds tasks whose string representations contain the given keyword(s).
     *
     * @param parts Tokenized user input
     * @throws JaneException If no keyword is provided
     */
    public String handleFind(String[] parts) throws JaneException {
        if (parts.length < 2) {
            throw new JaneException("No keyword given\n");
        }
        String list = "";
        for (int i = 1; i < parts.length; i++) {
            for (int j = 0; j < tasks.getSize(); j++) {
                if (tasks.get(j).toString().contains(parts[i])) {
                    list += tasks.get(j).toString() + "\n";
                }
            }
        }
        return ui.printFind(list);
    }
}
