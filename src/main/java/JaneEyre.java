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
    private Storage storage;
    private TaskList tasks;
    private static final String LINE =
            "____________________________________________________________\n";

    JaneEyre(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = storage.loadData();
        } catch (JaneException e) {
            tasks = new TaskList();
        }
    }

    public void taskListPrinter(TaskList tasks) throws JaneException {
        System.out.print(LINE);
        if (tasks.isEmpty()) {
            throw new JaneException("Your task list is currently empty\n");
        }
        System.out.print(tasks);
        System.out.println(LINE);
    }

    public void run() {
        System.out.print(LINE + "Hello, I'm Jane Eyre\n");
        System.out.print("What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        try {
            tasks = storage.loadData(); //LOOK AT ME
        }
        catch (JaneException j) {
            System.out.println("error loading tasks\n");
            j.printStackTrace();
        }
        Parser parser = new Parser(storage, tasks);
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
                        parser.handleMark(input);
                        break;
                    case "unmark":
                        parser.handleUnmark(input);
                        break;
                    case "todo":
                        parser.handleTodo(input);
                        break;
                    case "deadline":
                        parser.handleDeadline(input);
                        break;
                    case "event":
                        parser.handleEvent(input);
                        break;
                    case "delete":
                        parser.handleRemove(input);
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

    public static void main(String[] args) {
        new JaneEyre("data/janeeyre.txt").run();
    }
}