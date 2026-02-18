package jane;
import jane.exception.JaneException;
import jane.parser.Parser;
import jane.storage.Storage;
import jane.task.TaskList;
import jane.ui.Ui;

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
     * Parses the response from the user and has JaneBot respond accordingly
     *
     * @param input User input String
     */
    public String getResponse(String input) {
        Parser parser = new Parser(storage, tasks);
        String[] parts = input.split(" ");
        assert parts.length >= 1;
        String commandWord = input.split(" ")[0];
        try {
            switch (commandWord) {
            case "bye":
                return ui.printBye();
            case "list":
                return ui.printTaskList(tasks);
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
        } catch(JaneException e) {
            return ui.printUserError(e.getMessage());
        }
    }

    /**
     * Launches the JaneEyre application.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
    }
}