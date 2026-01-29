package jane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A <code>Deadline</code> object contains a description and a date/time by which the task must be completed.
 */
public class Deadline extends Task {
    /** The date and time by which this task must be finished. */
    private LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified description and deadline time.
     *
     * @param description The description of the task.
     * @param by The date and time deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the specified completion status, description, and deadline time.
     * This is typically used when loading tasks from a data file.
     *
     * @param isDone The completion status of the task.
     * @param description The description of the task.
     * @param by The date and time deadline for the task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Creates a Deadline task from an array of strings representing task data.
     * Expected array format: [type, isDone, description, byTime]
     *
     * @param s An array of strings where index 1 is status ("1" for done),
     *          index 2 is description, and index 3 is the ISO-8601 formatted date string.
     * @return A new Deadline object initialized with the provided data.
     */
    public static Deadline loadDeadline(String[] s) {
        return new Deadline(s[1].equals("1"), s[2], LocalDateTime.parse(s[3]));
    }


    /**
     * Returns a string representation of the Deadline task, including its type [D],
     * status icon, description, and formatted deadline time.
     *
     * @return A formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"))+ ")";
    }

    /**
     * Formats the Deadline task into a machine-readable string for file storage.
     * The format used is "D,isDone,description,byTime".
     *
     * @return A CSV-style string representation of the task for saving to a file.
     */
    public String format() {
        return "D," + super.format() + "," + by;
    }
}