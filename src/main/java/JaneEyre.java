import java.util.ArrayList;
import java.util.Scanner;
public class JaneEyre {
    private static final String LINE =
            "____________________________________________________________\n";
    public static void taskListPrinter(ArrayList<Task> tasks) throws JaneException {
        System.out.println(LINE);
        if (tasks.size() == 0) {
            throw new JaneException("Your task list is currently empty\n");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        System.out.println(LINE);
    }

    public static void handleMark(String input, ArrayList<Task> tasks) throws JaneException {
        int i = 0;
        try {
            i = Integer.valueOf(input.substring(4).trim()) - 1;
        } catch (NumberFormatException nfe) {
            throw new JaneException("Please enter a valid index\n");
        }
        if (i < 0 || i >= tasks.size()) {
            throw new JaneException("Please use a valid index\n");
        }
        tasks.get(i).mark(true);
        System.out.println(LINE + "Nice! I've marked this task as done:\n  "
                + tasks.get(i) + "\n" + LINE);
    }

    public static void handleUnmark(String input, ArrayList<Task> tasks) throws JaneException {
        int i = 0;
        try {
            i = Integer.valueOf(input.substring(6).trim()) - 1;
        } catch (NumberFormatException nfe) {
            throw new JaneException("Please enter a valid index\n");
        }
        if (i < 0 || i >= tasks.size()) {
            throw new JaneException("Please use a valid index\n");
        }
        tasks.get(i).mark(false);
        System.out.println(LINE + "OK, I've marked this task as not done yet:\n  "
                + tasks.get(i) + "\n" + LINE);
    }

    public static void handleAdd(String input, ArrayList<Task> tasks) throws JaneException {
        boolean isValid = true;
        if (input.startsWith("event")) {
            if (input.contains("/from") && input.contains("/to")) {
                int start = input.indexOf("/from");
                int end = input.indexOf("/to");
                //System.out.println(String.format("%d %d", start, end));
                String des = input.substring(5, start).trim();
                String from = input.substring(5 + start, end).trim();
                String to = input.substring(end + 3).trim();
                if (des.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new JaneException("Empty task\n");
                }
                tasks.add(new Event(des, from, to));
            } else {
                throw new JaneException("Usage: event [task] /from [start] /to [end]\n");
            }
        } else if (input.startsWith("deadline")) {
            if (input.contains("/by")) {
                int start = input.indexOf("/by");
                String des = input.substring(8, start).trim();
                String by = input.substring(start + 3).trim();
                if (des.isEmpty() || by.isEmpty()) {
                    throw new JaneException("Empty task\n");
                }
                tasks.add(new Deadline(des, by));
            } else {
                throw new JaneException("Usage: deadline [task] /by [time]\n");
            }
        }
        else {
            if (input.substring(4).trim().isEmpty()) {
                throw new JaneException("Usage: todo [task]\n");
            }
            tasks.add(new Todo(input.substring(4).trim()));
        }
        System.out.println(LINE + "Got it. I've added this task:\n  "
                    + tasks.get(tasks.size() - 1).toString()
                    + "\n" + String.format("Now you have %d tasks in the list", tasks.size()) + "\n"
                    + LINE);
    }

    public static void handleRemove(String input, ArrayList<Task> tasks) throws JaneException {
        int i = 0;
        try {
            i = Integer.valueOf(input.substring(6).trim()) - 1;
        } catch (NumberFormatException nfe) {
            throw new JaneException("Please enter a valid index\n");
        }
        if (i < 0 || i >= tasks.size()) {
            throw new JaneException("Please use a valid index\n");
        }
        Task temp = tasks.get(i);
        tasks.remove(i);
        System.out.println(LINE + " Noted. I've removed this task\n  "
                + temp + "\n" + String.format("Now you have %d tasks in the list.\n", tasks.size())+ LINE);
    }

    public static void main(String[] args) {
        System.out.println(LINE + "Hello, I'm Jane Eyre\n");
        System.out.println("What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(100);
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    isDone = true;
                } else if (input.equals("list")) {
                    taskListPrinter(tasks);
                } else if (input.startsWith("mark")) {
                    handleMark(input, tasks);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input, tasks);
                } else if (input.startsWith("todo")
                        || input.startsWith("deadline")
                        || input.startsWith("event")) {
                    handleAdd(input, tasks);
                } else if (input.startsWith("delete")) {
                    handleRemove(input, tasks);
                } else {
                    throw new JaneException("Sorry, I don't understand!\n");
                }
            }
            catch(JaneException e) {
                System.out.println(LINE + "User input error\n" + e.getMessage() + LINE);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}
