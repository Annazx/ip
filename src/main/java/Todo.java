public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String format() {
        return "T," + super.format();
    }

    public static Todo loadTodo(String[] a) {
        return new Todo(a[1].equals("1"), a[2]);
    }
}