package jane.task;

/**
 * Represents a to-do task without any associated date or time.
 */
public class Todo extends Task {

    /**
     * Creates a new to-do task that is initially not done.
     *
     * @param description Description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a to-do task with the specified completion status.
     *
     * @param isDone      {@code true} if the task is completed
     * @param description Description of the to-do task
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a user-friendly string representation of the to-do task.
     *
     * @return A formatted string prefixed with {@code [T]}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task suitable for file storage.
     *
     * @return A formatted string prefixed with {@code "T,"}
     */
    public String format() {
        return "T," + super.format();
    }

    /**
     * Creates a {@code Todo} object from stored data.
     *
     * @param a An array containing task data loaded from storage
     * @return A {@code Todo} reconstructed from the given data
     */
    public static Todo loadTodo(String[] a) {
        return new Todo(a[1].equals("1"), a[2]);
    }
}