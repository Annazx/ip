import java.util.ArrayList;
import java.util.Scanner;
public class JaneEyre {
    private static final String LINE =
            "____________________________________________________________\n";
    public static void taskListPrinter(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        System.out.println(LINE);
    }

    public static void handleMark(String input, ArrayList<Task> tasks) {
        int i = Integer.valueOf(input.substring(4).trim()) - 1;
        // what if it is an error?
        tasks.set(i, tasks.get(i).mark(true));
        System.out.println(LINE + "Nice! I've marked this task as done:\n  "
                + tasks.get(i) + "\n" + LINE);
    }

    public static void handleUnmark(String input, ArrayList<Task> tasks) {
        int i = Integer.valueOf(input.substring(6).trim()) - 1;
        // need exception handling here
        tasks.set(i, tasks.get(i).mark(false));
        System.out.println(LINE + "OK, I've marked this task as not done yet:\n  "
                + tasks.get(i) + "\n" + LINE);
    }

    public static void handleAdd(String input, ArrayList<Task> tasks) {
        System.out.println(LINE + "added: " + input + "\n" + LINE);
        if (input.contains("/from") && input.contains("/to")) {
            int start = input.indexOf("/from");
            int end = input.indexOf("/to");
            //System.out.println(String.format("%d %d", start, end));
            String des = input.substring(0, start).trim();
            String from = input.substring(5 + start, end).trim();
            String to = input.substring(end + 3).trim();
            tasks.add(new Event(des, from, to));
        } else if (input.contains("/by")) {
            int start = input.indexOf("/by");
            String des = input.substring(0, start).trim();
            String by = input.substring(start + 3).trim();
            tasks.add(new Deadline(des, by));
        } else {
            tasks.add(new Todo(input));
        }
    }

    public static void main(String[] args) {
        System.out.println(LINE + "Hello, I'm Jane Eyre\n");
        System.out.println("What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(100);
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                isDone = true;
            } else if (input.equals("list")) {
                taskListPrinter(tasks);
            } else if (input.startsWith("mark")) {
                handleMark(input, tasks);
            } else if (input.startsWith("unmark")) {
                handleUnmark(input, tasks);
            } else {
                handleAdd(input, tasks);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}
