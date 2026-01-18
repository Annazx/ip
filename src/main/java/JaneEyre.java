import java.util.Scanner;
public class JaneEyre {
    private static final String LINE = "____________________________________________________________\n";
    public static void taskListPrinter(Task[] tasks, int numTasks) {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks[i]));
        }
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        System.out.println(LINE + "Hello, I'm Jane Eyre\n");
        System.out.println("What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int idx = 0;
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                isDone = true;
            } else if (input.equals("list")) {
                taskListPrinter(tasks, idx);
            } else if (input.indexOf("mark") == 0) {
                int i = Integer.valueOf(input.substring(4).trim()) - 1;
                // what if it is an error?
                tasks[i] = tasks[i].mark(true);
                System.out.println(LINE + "Nice! I've marked this task as done:\n  "
                        + tasks[i] + "\n" + LINE);
            } else if (input.indexOf("unmark") == 0) {
                int i = Integer.valueOf(input.substring(6).trim()) - 1;
                // need exception handling here
                tasks[i] = tasks[i].mark(false);
                System.out.println(LINE + "OK, I've marked this task as not done yet:\n  "
                        + tasks[i] + "\n" + LINE);
            } else {
                System.out.println(LINE + "added: " + input + "\n" + LINE);
                tasks[idx] = new Task(input);
                idx += 1;
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}
