package jane;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jane.task.Tag;
import org.junit.jupiter.api.Test;

public class TagTest {
    @Test
    public void testToString_validString_prefixedWithHash() {
        Tag tag = new Tag("fun");
        assertEquals("#fun", tag.toString());
    }
}