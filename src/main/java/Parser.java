import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
    private static final String LINE =
            "____________________________________________________________\n";
    private Storage storage;
    private TaskList tasks;

    Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    public int parseIndex(String input, int n) throws JaneException {
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

    public void handleMark(String input) throws JaneException {
        int i = parseIndex(input.substring(4).trim(), tasks.size());
        tasks.mark(i);
        System.out.print(LINE + "Nice! I've marked this task as done:\n  "
                + tasks.get(i) + "\n" + LINE);
        storage.updateData(tasks);
    }

    public void handleUnmark(String input) throws JaneException {

        int i = parseIndex(input.substring(6).trim(), tasks.size());
        tasks.unMark(i);
        System.out.print(LINE + "OK, I've marked this task as not done yet:\n  "
                + tasks.get(i) + "\n" + LINE);
        storage.updateData(tasks);
    }

    public void addTask(Task task) {
        tasks.addTask(task);
        storage.storeTask(task, tasks);
        System.out.print(LINE + "Got it. I've added this task:\n  "
                + tasks.get(tasks.size() - 1).toString()
                + "\n" + String.format("Now you have %d tasks in the list", tasks.size()) + "\n"
                + LINE);
    }

    public void handleEvent(String input) throws JaneException {
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
        System.out.print(LINE + " Noted. I've removed this task\n  "
                + temp + "\n" + String.format("Now you have %d tasks in the list.\n", tasks.size())+ LINE);
        storage.updateData(tasks);
    }
}
