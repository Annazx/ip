public class Ui {
    private static final String LINE =
            "____________________________________________________________\n";

    public void taskListPrinter(TaskList tasks) throws JaneException {
        System.out.print(LINE);
        if (tasks.isEmpty()) {
            throw new JaneException("Your task list is currently empty\n");
        }
        System.out.print(tasks);
        System.out.println(LINE);
    }

    public void printGreet() {
        System.out.print(LINE + "Hello, I'm Jane Eyre\n");
        System.out.print("What can I do for you?\n" + LINE);
    }

    public void printCannotUnderstand() {
        System.out.print(LINE + "\nTo speak truth, sir, I don’t understand you at all:\n " +
                "I cannot keep up the conversation, because it has got out of my depth.\n" + LINE);
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
    }

    public void printUserError() {
        System.out.print(LINE + "User input error\n" + LINE);
    }

    public void printMark(Task task) {
        System.out.print(LINE + "Nice! I've marked this task as done:\n  "
                + task + "\n" + LINE);
    }

    public void printUnmark(Task task) {
        System.out.print(LINE + "OK, I've marked this task as not done yet:\n  "
                + task + "\n" + LINE);
    }

    public void printAdd(Task task, int i) {
        System.out.print(LINE + "Got it. I've added this task:\n  "
                + task
                + "\n" + String.format("Now you have %d tasks in the list", i) + "\n"
                + LINE);
    }

    public void printRemove(Task task, int i) {
        System.out.print(LINE + " Noted. I've removed this task\n  "
                + task + "\n" + String.format("Now you have %d tasks in the list.\n", i)+ LINE);
    }
}
