package jane;

import jane.exception.JaneException;
import jane.parser.Parser;
import jane.storage.Storage;
import jane.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseIndex_exception_whenNotNumber() {
        JaneException exception = assertThrows(JaneException.class, () -> {
            Parser parser = new Parser(new Storage("string"), new TaskList());
            parser.parseIndex("hello", 5);
        });
        String expectedMessage = "Please use a valid index\n";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseIndex_validInput_returnsCorrectIndex() throws JaneException {
        // Assume task list size is 5, input "3" should return index 2
        Parser parser = new Parser(new Storage("mock"), new TaskList());
        int index = parser.parseIndex("3", 5);
        assertEquals(2, index);
    }

    @Test
    public void parseIndex_exception_indexTooBig() {
        JaneException tooBig = assertThrows(JaneException.class, () -> {
            Parser parser = new Parser(new Storage("string"), new TaskList());
            parser.parseIndex("    19   ", 5);
        });
    }

    @Test
    public void parseIndex_exception_indexTooSmall() {
        JaneException tooSmall = assertThrows(JaneException.class, () -> {
            Parser parser = new Parser(new Storage("string"), new TaskList());
            parser.parseIndex("    -1", 5);
        });
    }
}
