package jane.task;

import java.util.Collections;
import java.util.Set;

/**
 * Represents a generic task with a description and completion status.
 * This class serves as a base class for more specific task types.
 */
public class Task {

    /** Description of the task. */
    protected String description;

    /** Indicates whether the task has been completed. */
    private boolean isDone;

    /** List of tags attached to task **/
    private TagList tags;

    /**
     * Creates a new task that is initially not done.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new TagList();
    }

    /**
     * Creates a task with the specified completion status.
     *
     * @param isDone      {@code true} if the task is completed
     * @param description Description of the task
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new TagList();
    }

    public Task(boolean isDone, String description, TagList tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    public Task(String description, TagList tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
    }
    /**
     * Returns a status icon representing whether the task is completed.
     *
     * @return {@code "X"} if the task is done, otherwise a blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done or not done.
     *
     * @param newDone {@code true} to mark the task as completed,
     *                {@code false} to mark it as not completed
     */
    public void mark(boolean newDone) {
        this.isDone = newDone;
    }

    /**
     * Adds tag to tagList
     *
     * @param newTag  New tag to be added to tagList
     *
     */
    public void addTag(Tag newTag) {
        tags.addTag(newTag);
    }


    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return A formatted string showing task status and description
     */
    public String toString() {
        assert description != null;
        return String.format("[%s] %s%s", getStatusIcon(), description, tags.toString());
    }

    /**
     * Returns a string representation suitable for file storage.
     *
     * @return A formatted string containing completion status and description
     */
    public String format() {
        assert description != null;
        return String.format("%d,%s,%s", isDone ? 1 : 0, description, tags);
    }
}
