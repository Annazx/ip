public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    public static Event loadEvent(String[] s) {
        return new Event(s[1].equals("1"), s[2], s[3], s[4]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }

    public String format() {
        return "E," + super.format() + "," + from + "," + to;
    }
}