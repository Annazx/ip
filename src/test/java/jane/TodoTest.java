package jane;

import jane.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_unmarkedTodo_correctFormat() {
        Todo todo = new Todo("borrow books");
        assertEquals("[T][ ] borrow books", todo.toString());
    }

    @Test
    public void toString_markedTodo_correctFormat() {
        Todo todo = new Todo(true, "wash dishes");
        assertEquals("[T][X] wash dishes", todo.toString());
    }

    @Test
    public void format_unmarkedTodo_correctStorageFormat() {
        Todo todo = new Todo("do homework");
        assertEquals("T,0,do homework", todo.format());
    }

    @Test
    public void format_markedTodo_correctStorageFormat() {
        Todo todo = new Todo(true, "do homework");
        assertEquals("T,1,do homework", todo.format());
    }

    @Test
    public void loadTodo_markedTodo_restoresCorrectly() {
        String[] data = {"T", "1", "read book"};
        Todo todo = Todo.loadTodo(data);

        assertEquals("[T][X] read book", todo.toString());
    }

    @Test
    public void loadTodo_unmarkedTodo_restoresCorrectly() {
        String[] data = {"T", "0", "read book"};
        Todo todo = Todo.loadTodo(data);

        assertEquals("[T][ ] read book", todo.toString());
    }
}


