package jane.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    // Common deadline for testing: Oct 10, 2023, 6:00 PM
    private final LocalDateTime testTime = LocalDateTime.of(2023, 10, 10, 18, 0);

    @Test
    public void toString_noTags_correctFormat() {
        Deadline d = new Deadline("return book", testTime);
        // [D][ ] description (by: MMM d yyyy H:mm)
        assertEquals("[D][ ] return book (by: Oct 10 2023 18:00)", d.toString());
    }

    @Test
    public void toString_withTags_correctFormat() {
        // Assuming your TagList can be constructed or added to
        Deadline d = new Deadline("return book", testTime);
        d.addTag(new Tag("urgent"));

        // Note where the tag appears: Task.toString() puts it after description
        assertEquals("[D][ ] return book #urgent (by: Oct 10 2023 18:00)", d.toString());
    }

    @Test
    public void format_unmarkedNoTags_correctStorageFormat() {
        Deadline d = new Deadline("submit project", testTime);
        // Based on Task.format: D,isDone,description,tags,by
        // If tags is empty, result should be "D,0,submit project,,2023-10-10T18:00"
        assertEquals("D,0,submit project,,2023-10-10T18:00", d.format());
    }

    @Test
    public void format_markedWithTags_correctStorageFormat() {
        Deadline d = new Deadline(true, "submit project", testTime, new TagList());
        d.addTag(new Tag("school"));

        // D,1,submit project, #school,2023-10-10T18:00
        assertEquals("D,1,submit project, #school,2023-10-10T18:00", d.format());
    }

    @Test
    public void loadDeadline_validData_restoresCorrectly() {
        // Format: [type, isDone, description, byTime, tags]
        String[] data = {"D", "1", "read chapter", "2023-10-10T18:00", "#study #fun #cs2103"};

        // Ensure TagList.loadTags("study") works correctly
        Deadline d = Deadline.loadDeadline(data);

        assertEquals("[D][X] read chapter #study #fun #cs2103 (by: Oct 10 2023 18:00)", d.toString());
    }
}