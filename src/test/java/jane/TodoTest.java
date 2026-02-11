package jane;

import jane.task.Todo;
import jane.task.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_noTags_correctFormat() {
        Todo todo = new Todo("borrow books");
        // tags.toString() returns "", so result is "[T][ ] borrow books"
        assertEquals("[T][ ] borrow books", todo.toString());
    }

    @Test
    public void toString_withTags_includesHashtags() {
        Todo todo = new Todo("borrow books");
        todo.addTag(new Tag("fun"));
        todo.addTag(new Tag("urgent"));
        // tags.toString() returns " #fun #urgent"
        assertEquals("[T][ ] borrow books #fun #urgent", todo.toString());
    }

    @Test
    public void format_noTags_correctStorageFormat() {
        Todo todo = new Todo("do homework");
        // Result: "0,do homework," (assuming tags.toString() is empty)
        // Adjust the commas based on your exact storage logic
        assertEquals("T,0,do homework,", todo.format());
    }

    @Test
    public void format_withTags_correctStorageFormat() {
        Todo todo = new Todo(true, "do homework");
        todo.addTag(new Tag("school"));
        // Notice the space before the tag because of your TagList reduce logic
        assertEquals("T,1,do homework, #school", todo.format());
    }

    @Test
    public void loadTodo_withTags_restoresCorrectly() {
        // Your loadTodo logic needs to handle 4 parts now: Type, Status, Desc, Tags
        // Example: T, 1, read book, #fun
        String[] data = {"T", "1", "read book", "#fun"};

        // Ensure your Todo.loadTodo(data) is updated to handle data.length == 4
        Todo todo = Todo.loadTodo(data);

        assertEquals("[T][X] read book #fun", todo.toString());
    }
}
