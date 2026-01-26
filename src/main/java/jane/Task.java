package jane;

public class Task {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark(boolean newDone) {
        this.isDone = newDone;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String format() {
        return String.format("%d,%s", isDone ? 1 : 0, description);
    }
}
