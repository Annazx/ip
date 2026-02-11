package jane;
import java.util.ArrayList;

/**
 * Represents a list of tasks managed by the application.
 * Provides basic operations for adding, removing, and accessing tasks.
 */
public class TaskList {
    /** Internal list storing all tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns a formatted string representation of all tasks.
     *
     * @return Numbered list of tasks as a string
     */
    @Override
    public String toString() {
        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            formatBuilder.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return formatBuilder.toString();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param i Index of the task to remove (0-based)
     */
    public void removeTask(int i) {
        assert i > 0 && i < tasks.size();
        tasks.remove(i);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param i Index of the task to mark (0-based)
     */
    public void mark(int i) {
        assert i > 0 && i < tasks.size();
        tasks.get(i).mark(true);
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param i Index of the task to unmark (0-based)
     */
    public void unMark(int i) {
        assert i > 0 && i < tasks.size();
        tasks.get(i).mark(false);
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return List containing all tasks
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null;
        return tasks;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i Index of the task to retrieve (0-based)
     * @return Task at the given index
     */
    public Task get(int i) {
        assert i > 0 && i < tasks.size();
        return tasks.get(i);
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return {@code true} if the list contains no tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }
}
