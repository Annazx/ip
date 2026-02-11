package jane.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date-time.
 */
public class Event extends Task {

    /** Start date and time of the event */
    private LocalDateTime from;

    /** End date and time of the event */
    private LocalDateTime to;

    /**
     * Constructs an Event task with the given description and time range.
     *
     * @param description Description of the event
     * @param from        Start date and time of the event
     * @param to          End date and time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with an explicit completion status.
     *
     * @param isDone      Completion status of the task
     * @param description Description of the event
     * @param from        Start date and time of the event
     * @param to          End date and time of the event
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with an explicit completion status.
     *
     * @param isDone      Completion status of the task
     * @param description Description of the event
     * @param from        Start date and time of the event
     * @param to          End date and time of the event
     * @param tags        List of tags
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to, TagList tags) {
        super(isDone, description, tags);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with an explicit completion status.
     *
     * @param description Description of the event
     * @param from        Start date and time of the event
     * @param to          End date and time of the event
     * @param tags        List of tags
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, TagList tags) {
        super(description, tags);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event object from a formatted storage representation.
     *
     * @param a Array containing the stored event fields
     * @return Reconstructed Event object
     */
    public static Event loadEvent(String[] a) {
        assert a.length > 4;
        boolean isDone = a[1].equals("1");
        String desc = a[2];
        TagList tags;
        String fromStr;
        String toStr;

        if (a.length > 5) {
            tags = TagList.loadTags(a[3]);
            fromStr = a[4];
            toStr = a[5];
        } else {
            tags = new TagList();
            fromStr = a[3];
            toStr = a[4];
        }
        return new Event(isDone, desc, LocalDateTime.parse(fromStr), LocalDateTime.parse(toStr), tags);
    }

    /**
     * Returns a user-friendly string representation of the event.
     *
     * @return Formatted string describing the event
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy H:mm");
        return "[E]" + super.toString()
                + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a storage-friendly string representation of the event.
     *
     * @return Formatted string suitable for file storage
     */
    public String format() {
        return "E," + super.format() + "," + from + "," + to;
    }
}