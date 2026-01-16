import java.util.Scanner;
public class JaneEyre {
    private static final String LINE = "____________________________________________________________\n";
    public static void taskListPrinter(String[] tasks, int numTasks) {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks[i]));
        }
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        System.out.println(LINE + "Hello, I'm Jane Eyre\n");
        System.out.println("What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int idx = 0;
        boolean isDone = false;
        while (!isDone) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                isDone = true;
            } else if (input.equals("list")) {
                taskListPrinter(tasks, idx);
            } else {
                System.out.println(LINE + "added: " + input + "\n" + LINE);
                tasks[idx] = input;
                idx += 1;
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }
}
