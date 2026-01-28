package jane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = new Ui();
    }

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

    public void handleMark(String input) throws JaneException {
        int i = parseIndex(input.substring(4).trim(), tasks.size());
        tasks.mark(i);
        ui.printMark(tasks.get(i));
        storage.updateData(tasks);
    }

    public void handleUnmark(String input) throws JaneException {

        int i = parseIndex(input.substring(6).trim(), tasks.size());
        tasks.unMark(i);
        ui.printUnmark(tasks.get(i));
        storage.updateData(tasks);
    }

    public void addTask(Task task) {
        tasks.addTask(task);
        storage.storeTask(task, tasks);
        ui.printAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    public void handleEvent(String input) throws JaneException {
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
                LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
                LocalDateTime to = LocalDateTime.parse(toStr, formatter);
                addTask(new Event(des, from, to));
            } catch (DateTimeParseException e) {
                throw new JaneException("Invalid Input\nUsage: [day]/[month]/[year] [time (24 hour clock)]\n");
            }
        } else {
            throw new JaneException("Usage: event [task] /from [start] /to [end]\n");
        }
    }

    public void handleDeadline(String input) throws JaneException {
        if (input.contains("/by")) {
            int start = input.indexOf("/by");
            String des = input.substring(8, start).trim();
            if (des.isEmpty()) {
                throw new JaneException("Empty field\nUsage: deadline [task] /by [time]\n");
            }
            String byStr = input.substring(start + 3).trim();
            try {
                LocalDateTime by = LocalDateTime.parse(byStr, formatter);
                addTask(new Deadline(des, by));
            } catch (DateTimeParseException e) {
                throw new JaneException("Invalid Input\nUsage: [day]/[month]/[year] [time (24 hour clock)]\n");
            }
        } else {
            throw new JaneException("Usage: deadline [task] /by [time]\n");
        }
    }

    public void handleTodo(String input) throws JaneException {
        if (input.substring(4).trim().isEmpty()) {
            throw new JaneException("Usage: todo [task]\n");
        }
        addTask(new Todo(input.substring(4).trim()));
    }

    public void handleRemove(String input) throws JaneException {
        int i = parseIndex(input.substring(6).trim(), tasks.size());
        Task temp = tasks.get(i);
        tasks.removeTask(i);
        ui.printRemove(temp, tasks.size());
        storage.updateData(tasks);
    }
}
