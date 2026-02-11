package jane;

public class Ui {

    public String printTaskList(TaskList tasks) throws JaneException {
        if (tasks.isEmpty()) {
            throw new JaneException("Your task list is currently empty\n");
        }
        return tasks.toString();
    }

    public String printGreet() {
        return "Hello, I'm Jane Eyre\n"
                + "What can I do for you?\n";
    }

    public String printCannotUnderstand() {
        return  "To speak truth, sir, I don't understand you at all:\n "
                + "I cannot keep up the conversation, because it has got out of my depth.\n";
    }

    public String printBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String printUserError(String e) {
        return e;
    }

    public String printMark(Task task) {
        return "Nice! I've marked this task as done:\n  "
                + task + "\n";
    }

    public String printUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n  "
                + task + "\n";
    }

    public String printAdd(Task task, int i) {
        assert i > -1;
        return "Got it. I've added this task:\n  "
                + task + "\n"
                + String.format("Now you have %d tasks in the list", i) + "\n";
    }

    public String printRemove(Task task, int i) {
        assert i > -1;
        return "Noted. I've removed this task\n  "
                + task + "\n"
                + String.format("Now you have %d tasks in the list.\n", i);
    }

    public String printFind(String list) {
        return "Here are the matching tasks in your list:\n"
                + list;
    }
}