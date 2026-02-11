package jane;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jane.task.Tag;
import jane.task.TagList;
import org.junit.jupiter.api.Test;

public class TagListTest {

    @Test
    public void toString_emptyList_returnsEmptyString() {
        TagList tagList = new TagList();
        assertEquals("", tagList.toString());
    }

    @Test
    public void toString_singleTag_returnsLeadingSpaceAndHash() {
        TagList tagList = new TagList();
        tagList.addTag(new Tag("fun"));
        assertEquals(" #fun", tagList.toString());
    }

    @Test
    public void toString_multipleTags_returnsFormattedString() {
        TagList tagList = new TagList();
        tagList.addTag(new Tag("fun"));
        tagList.addTag(new Tag("urgent"));

        // Note: Set order depends on implementation.
        // If using LinkedHashSet, it will be " #fun #urgent"
        assertEquals(" #fun #urgent", tagList.toString());
    }

    @Test
    public void toString_duplicatedTags_returnsFormattedString() {
        TagList tagList = new TagList();
        tagList.addTag(new Tag("fun"));
        tagList.addTag(new Tag("urgent"));
        tagList.addTag(new Tag("fun"));

        // Note: Set order depends on implementation.
        // If using LinkedHashSet, it will be " #fun #urgent"
        assertEquals(" #fun #urgent", tagList.toString());
    }
}