import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    public static Deadline loadDeadline(String[] s) {
        return new Deadline(s[1].equals("1"), s[2], LocalDateTime.parse(s[3]));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"))+ ")";
    }

    public String format() {
        return "D," + super.format() + "," + by;
    }
}