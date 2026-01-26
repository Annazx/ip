import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class JaneEyre {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd HHmm");
    private static final String LINE =
            "____________________________________________________________\n";
    public static void taskListPrinter(TaskList tasks) throws JaneException {
        System.out.print(LINE);
        if (tasks.isEmpty()) {
            throw new JaneException("Your task list is currently empty\n");
        }
        System.out.print(tasks);
        System.out.println(LINE);
    }

    public static void updateData(TaskList tasks) throws JaneException {
        File f = new File("./data/janeeyre.txt");
        try (FileWriter fw = new FileWriter(f)) {
           for (Task task : tasks.getTasks()) {
               fw.write(task.format() + "\n");
           }
           //fw.close();
        } catch (IOException e) {
            throw new JaneException("The file could not be updated\n");
        }

    }

    public static int parseIndex(String input, int n) throws JaneException {
        try {
            int i = Integer.parseInt(input) - 1;
            if (i < 0 || i >= n) {
                throw new JaneException("Please use a valid index\n");
            }
            return i;
        } catch (NumberFormatException nfe) {
            throw new JaneException("Please enter a valid index\n");
        }
    }

    public static void handleMark(String input, TaskList tasks) throws JaneException {
        int i = parseIndex(input.substring(4).trim(), tasks.size());
        tasks.mark(i);
        System.out.print(LINE + "Nice! I've marked this task as done:\n  "
                + tasks.get(i) + "\n" + LINE);
        updateData(tasks);
    }

    public static void handleUnmark(String input, TaskList tasks) throws JaneException {

        int i = parseIndex(input.substring(6).trim(), tasks.size());
        tasks.unMark(i);
        System.out.print(LINE + "OK, I've marked this task as not done yet:\n  "
                + tasks.get(i) + "\n" + LINE);
        updateData(tasks);
    }

    public static void addTask(Task task, TaskList tasks) {
        tasks.addTask(task);
        FileWriter fw = null;
        try {
            fw = new FileWriter("data/janeeyre.txt", true);
            fw.write(task.format() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print(LINE + "Got it. I've added this task:\n  "
                + tasks.get(tasks.size() - 1).toString()
                + "\n" + String.format("Now you have %d tasks in the list", tasks.size()) + "\n"
                + LINE);
    }

    public static void handleEvent(String input, TaskList tasks) throws JaneException {
        if (input.contains("/from") && input.contains("/to")) {
            int start = input.indexOf("/from");
            int end = input.indexOf("/to");
            String des = input.substring(5, start).trim();
            String fromStr = input.substring(5 + start, end).trim();
            String toStr = input.substring(end + 3).trim();
            if (des.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                throw new JaneException("Empty field\nUsage: event [task] /from [start] /to [end]\n");
            }
            try {
                LocalDateTime from = LocalDateTime.parse(fromStr, formatter);
                LocalDateTime to = LocalDateTime.parse(toStr, formatter);
                addTask(new Event(des, from, to), tasks);
            } catch (DateTimeParseException e) {
                throw new JaneException("Invalid Input\nUsage: [day]/[month]/[year] [time (24 hour clock)]\n");
            }
        } else {
            throw new JaneException("Usage: event [task] /from [start] /to [end]\n");
        }
    }

    public static void handleDeadline(String input, TaskList tasks) throws JaneException {
        if (input.contains("/by")) {
            int start = input.indexOf("/by");
            String des = input.substring(8, start).trim();
            if (des.isEmpty()) {
                throw new JaneException("Empty field\nUsage: deadline [task] /by [time]\n");
            }
            String byStr = input.substring(start + 3).trim();
            try {
                LocalDateTime by = LocalDateTime.parse(byStr, formatter);
                addTask(new Deadline(des, by), tasks);
            } catch (DateTimeParseException e) {
                throw new JaneException("Invalid Input\nUsage: [day]/[month]/[year] [time (24 hour clock)]\n");
            }
        } else {
            throw new JaneException("Usage: deadline [task] /by [time]\n");
        }
    }

    public static void handleTodo(String input, TaskList tasks) throws JaneException {
        if (input.substring(4).trim().isEmpty()) {
            throw new JaneException("Usage: todo [task]\n");
        }
        addTask(new Todo(input.substring(4).trim()), tasks);
    }

    public static void handleRemove(String input, TaskList tasks) throws JaneException {
        int i = parseIndex(input.substring(6).trim(), tasks.size());
        Task temp = tasks.get(i);
        tasks.removeTask(i);
        System.out.print(LINE + " Noted. I've removed this task\n  "
                + temp + "\n" + String.format("Now you have %d tasks in the list.\n", tasks.size())+ LINE);
        updateData(tasks);
    }

    public static void loadTaskArray(TaskList arr) throws JaneException {
        File f = new File("./data/janeeyre.txt");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] argsArr = s.nextLine().split(",");
                switch (argsArr[0]) {
                    case "T":
                        arr.addTask(Todo.loadTodo(argsArr));
                        break;
                    case "E":
                        arr.addTask(Event.loadEvent(argsArr));
                        break;
                    case "D":
                        arr.addTask(Deadline.loadDeadline(argsArr));
                        break;
                    default:
                        throw new JaneException("Oh dear, an error has occurred\n");
                }
            }
        } catch (FileNotFoundException e){
            throw new JaneException("Oh dear, an error has occurred\n");
        }
    }

    public static TaskList loadData() throws JaneException {
        File f = new File("data/janeeyre.txt");
        TaskList tasks = new TaskList();
        try {
            if (!f.createNewFile()) {
                loadTaskArray(tasks);
            }
            return tasks;
        } catch (IOException e) {
            throw new JaneException("An error occurred\n");
        }
    }

    public static void main(String[] args) throws JaneException {
        System.out.print(LINE + "Hello, I'm Jane Eyre\n");
        System.out.print("What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        try {
            tasks = loadData();
        }
        catch (JaneException j) {
            throw new JaneException("help");
        }
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine().trim();
            String commandWord = input.split(" ")[0];
            try {
                switch (commandWord) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
                        isDone = true;
                        break;
                    case "list":
                        taskListPrinter(tasks);
                        break;
                    case "mark":
                        handleMark(input, tasks);
                        break;
                    case "unmark":
                        handleUnmark(input, tasks);
                        break;
                    case "todo":
                        handleTodo(input, tasks);
                        break;
                    case "deadline":
                        handleDeadline(input, tasks);
                        break;
                    case "event":
                        handleEvent(input, tasks);
                        break;
                    case "delete":
                        handleRemove(input, tasks);
                        break;
                    default:
                        System.out.print(LINE + "\nTo speak truth, sir, I don’t understand you at all:\n " +
                                "I cannot keep up the conversation, because it has got out of my depth.\n" + LINE);
                }
            }
            catch(JaneException e) {
                System.out.print(LINE + "User input error\n" + e.getMessage() + LINE);
            }
        }
    }
}