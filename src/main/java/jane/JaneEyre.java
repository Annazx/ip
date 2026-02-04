package jane;
import java.util.Scanner;

/**
 * Main entry point of the JaneEyre task management application.
 * Handles program initialization and the command execution loop.
 */
public class JaneEyre {

    /** Storage handler for loading and saving tasks */
    private final Storage storage;

    /** List of tasks currently managed by the application */
    private TaskList tasks;

    /** User interface for interacting with the user */
    private final Ui ui;

    /**
     * Constructs the JaneEyre application using the given file path.
     * Loads existing tasks from storage if available.
     *
     * @param filePath Path to the data file used for storage
     */
    JaneEyre(String filePath) {
        storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            tasks = storage.loadData();
        } catch (JaneException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the application and processes user commands until exit.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(storage, tasks);
        boolean isDone = false;
        ui.printGreet();
        while (!isDone) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
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
                    case "find":
                        parser.handleFind(parts);
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

    public String getResponse(String input) {
        Parser parser = new Parser(storage, tasks);
        String[] parts = input.split(" ");
        String commandWord = input.split(" ")[0];
        try {
            switch (commandWord) {
            case "bye":
                return ui.printBye();
            case "list":
                return ui.taskListPrinter(tasks);
            case "mark":
                return parser.handleMark(input);
            case "unmark":
                return parser.handleUnmark(input);
            case "todo":
                return parser.handleTodo(input);
            case "deadline":
                return parser.handleDeadline(input);
            case "event":
                return parser.handleEvent(input);
            case "delete":
                return parser.handleRemove(input);
            case "find":
                return parser.handleFind(parts);
            default:
                return ui.printCannotUnderstand();
            }
        }
        catch(JaneException e) {
            return ui.printUserError(e.getMessage());
        }
    }
    /**
     * Launches the JaneEyre application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new JaneEyre("data/janeeyre.txt").run();
    }
}