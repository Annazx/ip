package jane;

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
    public void parseIndex_exception_whenInvalidIndex() {
        JaneException tooBig = assertThrows(JaneException.class, () -> {
            Parser parser = new Parser(new Storage("string"), new TaskList());
            parser.parseIndex("    19   ", 5);
        });
        JaneException tooSmall = assertThrows(JaneException.class, () -> {
            Parser parser = new Parser(new Storage("string"), new TaskList());
            parser.parseIndex("    -1", 5);
        });
    }

    @Test
    public void parseEvent_testInvalidInputFromTo() {
        Parser parser = new Parser(new Storage("string"), new TaskList());
        assertDoesNotThrow(() -> parser.handleEvent("event class /to 2026"));
    }
}
