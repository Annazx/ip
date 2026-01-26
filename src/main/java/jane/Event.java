package jane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    public static Event loadEvent(String[] s) {
        return new Event(s[1].equals("1"), s[2], LocalDateTime.parse(s[3]), LocalDateTime.parse(s[4]));
    }

    @Override
    public String toString() {
        //by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy H:mm");
        return "[E]" + super.toString()
                + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    public String format() {
        return "E," + super.format() + "," + from + "," + to;
    }
}