package jane;
import java.util.Scanner;

public class JaneEyre {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    JaneEyre(String filePath) {
        storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            tasks = storage.loadData();
        } catch (JaneException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(storage, tasks);
        boolean isDone = false;
        ui.printGreet();
        while (!isDone) {
            String input = scanner.nextLine().trim();
            String commandWord = input.split(" ")[0];
            try {
                switch (commandWord) {
                    case "bye":
                        ui.printBye();
                        isDone = true;
                        break;
                    case "list":
                        ui.taskListPrinter(tasks);
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
                        ui.printCannotUnderstand();
                }
            }
            catch(JaneException e) {
                ui.printUserError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JaneEyre("data/janeeyre.txt").run();
    }
}