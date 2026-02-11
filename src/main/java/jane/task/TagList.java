package jane.task;

import java.util.Set;

public class TagList {
    private final Set<Tag> tags;

    public TagList() {
        tags = new java.util.LinkedHashSet<>();
    }

    public TagList(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag newTag) {
        tags.add(newTag);
    }

    @Override
    public String toString() {
        return tags.stream()
                .map(tag -> tag.toString())
                .reduce("", (accumulator, element) -> accumulator + " " + element);
    }

    public static TagList loadTags(String tagStr) {
        TagList tagList = new TagList();
        if (tagStr == null || tagStr.trim().isEmpty()) {
            return tagList;
        }

        String[] tags = tagStr.trim().split("\\s+");
        for (String t : tags) {
            if (t.startsWith("#") && t.length() > 1) {
                tagList.addTag(new Tag(t.substring(1)));
            }
        }
        return tagList;
    }
}
