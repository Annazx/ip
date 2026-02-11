package jane.task;

public class Tag {
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format("#%s", tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) o;
        return tag.equals(other.tag);
    }

    @Override
    public int hashCode() {
        // Important: Objects that are .equals() MUST have the same hashCode
        return java.util.Objects.hash(tag);
    }
}
