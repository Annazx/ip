package jane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles loading tasks from disk and saving tasks to disk.
 * The data is stored in a text file specified by the given file path.
 */
public class Storage {

    /** The file used for persistent storage of tasks. */
    private final File file;

    /**
     * Creates a {@code Storage} object using the given file path.
     *
     * @param filepath Path to the file used for storing task data
     */
    Storage(String filepath) {
        // "./data/janeeyre.txt"
        this.file = new File(filepath);
    }

    /**
     * Overwrites the storage file with the current list of tasks.
     *
     * @param tasks The {@code TaskList} containing tasks to be saved
     * @throws JaneException If an I/O error occurs while writing to the file
     */
    public void updateData(TaskList tasks) throws JaneException {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks.getTasks()) {
                fw.write(task.format() + "\n");
            }
            //fw.close();
        } catch (IOException e) {
            throw new JaneException("The file could not be updated\n");
        }

    }

    /**
     * Appends a single task to the storage file.
     *
     * @param task  The task to be stored
     * @param tasks The current task list (not modified by this method)
     * @throws RuntimeException If an I/O error occurs while writing to the file
     */
    public void storeTask(Task task, TaskList tasks) throws RuntimeException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write(task.format() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads tasks from the storage file into the given {@code TaskList}.
     * Each line in the file is parsed and converted into a corresponding task.
     *
     * @param arr The {@code TaskList} to load tasks into
     * @throws JaneException If the file format is invalid or the file cannot be read
     */
    public void loadTaskArray(TaskList arr) throws JaneException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] argsArr = s.nextLine().split(",");
                switch (argsArr[0]) {
                    case "T":
                        arr.addTask(Todo.loadTodo(argsArr));
                        break;
                    case "E":
                        arr.addTask(Event.loadEvent(argsArr));
                        break;
                    case "D":
                        arr.addTask(Deadline.loadDeadline(argsArr));
                        break;
                    default:
                        throw new JaneException("Oh dear, an error has occurred\n");
                }
            }
        } catch (FileNotFoundException e){
            throw new JaneException("Oh dear, an error has occurred\n");
        }
    }

    /**
     * Loads tasks from disk and returns them as a {@code TaskList}.
     * <p>
     * If the parent directory does not exist, it is created.
     * If the file does not exist, an empty file is created.
     *
     * @return A {@code TaskList} containing all loaded tasks
     * @throws JaneException If an I/O error occurs while loading data
     */
    public TaskList loadData() throws JaneException {
        TaskList tasks = new TaskList();
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();   // creates data/ if missing
            }

            if (file.exists()) {
                loadTaskArray(tasks);
            } else {
                file.createNewFile();
            }

            return tasks;
        } catch (IOException e) {
            throw new JaneException("An error occurred while loading data\n");
        }
    }
}
