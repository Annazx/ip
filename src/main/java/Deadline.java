public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    public static Deadline loadDeadline(String[] s) {
        return new Deadline(s[1].equals("1"), s[2], s[3]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String format() {
        return "D," + super.format() + "," + by;
    }
}