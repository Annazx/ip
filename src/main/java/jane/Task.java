package jane;

/**
 * Represents a generic task with a description and completion status.
 * This class serves as a base class for more specific task types.
 */
public class Task {

    /** Description of the task. */
    protected String description;

    /** Indicates whether the task has been completed. */
    private boolean isDone;

    /**
     * Creates a new task that is initially not done.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task with the specified completion status.
     *
     * @param isDone      {@code true} if the task is completed
     * @param description Description of the task
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon representing whether the task is completed.
     *
     * @return {@code "X"} if the task is done, otherwise a blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done or not done.
     *
     * @param newDone {@code true} to mark the task as completed,
     *                {@code false} to mark it as not completed
     */
    public void mark(boolean newDone) {
        this.isDone = newDone;
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return A formatted string showing task status and description
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a string representation suitable for file storage.
     *
     * @return A formatted string containing completion status and description
     */
    public String format() {
        return String.format("%d,%s", isDone ? 1 : 0, description);
    }
}
