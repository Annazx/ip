package jane;

public class Ui {
    private static final String LINE =
            "____________________________________________________________\n";

    public String taskListPrinter(TaskList tasks) throws JaneException {
        if (tasks.isEmpty()) {
            throw new JaneException("Your task list is currently empty\n");
        }
        return LINE + tasks + LINE;
    }

    public String printGreet() {
        return LINE + "Hello, I'm Jane Eyre\n"
                + "What can I do for you?\n" + LINE;
    }

    public String printCannotUnderstand() {
        return LINE + "\nTo speak truth, sir, I don’t understand you at all:\n "
                + "I cannot keep up the conversation, because it has got out of my depth.\n"
                + LINE;
    }

    public String printBye() {
        return LINE + "Bye. Hope to see you again soon!\n" + LINE;
    }

    public String printUserError(String e) {
        return LINE + e + LINE;
    }

    public String printMark(Task task) {
        return LINE + "Nice! I've marked this task as done:\n  "
                + task + "\n" + LINE;
    }

    public String printUnmark(Task task) {
        return LINE + "OK, I've marked this task as not done yet:\n  "
                + task + "\n" + LINE;
    }

    public String printAdd(Task task, int i) {
        return LINE + "Got it. I've added this task:\n  "
                + task + "\n"
                + String.format("Now you have %d tasks in the list", i) + "\n"
                + LINE;
    }

    public String printRemove(Task task, int i) {
        return LINE + "Noted. I've removed this task\n  "
                + task + "\n"
                + String.format("Now you have %d tasks in the list.\n", i)
                + LINE;
    }

    public String printFind(String list) {
        return LINE + "Here are the matching tasks in your list:\n"
                + list + LINE;
    }
}