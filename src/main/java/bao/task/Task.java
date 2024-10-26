package bao.task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import bao.main.Bao;

/**
 * The Task class represents a task with a description and a completion status.
 * It is an abstract class that provides methods applicable for all types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a new Task with the given description and sets it as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Tags the Task object with a string input.
     *
     * @param tag String to tag the task object with.
     */
    public void addTag(String tag) {
        assert tag != null : "Tag should not be null";
        String trimmedTag = tag.trim();
        tags.add(trimmedTag);
    }

    /**
     * Removes the tag on the Task object.
     *
     * @param tag String of the target tag to be removed from the task object.
     */
    public boolean removeTag(String tag) {
        assert tag != null : "Tag should not be null";
        String trimmedTag = tag.trim();
        return tags.remove(trimmedTag);
    }

    public Set<String> getTags() {
        return this.tags;
    }

    /**
     * Creates a Task object from a string input.
     * The string should be formatted in a specific way to be parsed and for the task to be created correctly.
     *
     * @param string String representation of the task.
     * @return Task object created from the string.
     * @throws IllegalArgumentException Ff the string format is invalid or unknown task type is provided.
     */
    public static Task fromString(String string) {
        assert string != null : "Task string should not be null";
        String[] parts = string.split(" \\| ");
        assert parts.length >= 3 : "Invalid task format";

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;

        switch (type) {
        case "T" -> {
            task = new ToDo(description);
            if (parts.length >= 4 && parts[3].contains("Tags:")) {
                String[] tags = parts[3].replace("Tags: ", "").split(", ");
                for (String tag : tags) {
                    task.addTag(tag);
                }
            }
        }
        case "D" -> {
            assert parts.length >= 4 : "Invalid deadline task format";
            LocalDateTime dateAndTime = LocalDateTime.parse(parts[3].trim(), Bao.getFileDateFormat());
            task = new Deadline(description, dateAndTime);
            if (parts.length >= 5 && parts[4].contains("Tags:")) {
                String[] tags = parts[4].replace("Tags: ", "").split(", ");
                for (String tag : tags) {
                    task.addTag(tag);
                }
            }
        }
        case "E" -> {
            assert parts.length >= 4 : "Invalid event task format";
            String[] duration = parts[3].split(" - ");
            assert duration.length == 2 : "Invalid event duration format";
            LocalDateTime from = LocalDateTime.parse(duration[0].trim(), Bao.getFileDateFormat());
            LocalDateTime to = LocalDateTime.parse(duration[1].trim(), Bao.getFileDateFormat());
            task = new Event(description, from, to);
            if (parts.length >= 5 && parts[4].contains("Tags:")) {
                String[] tags = parts[4].replace("Tags: ", "").split(", ");
                for (String tag : tags) {
                    task.addTag(tag);
                }
            }
        }
        default -> {
            throw new IllegalArgumentException("Bao doesn't know what this task type is");
        }
        }

        if (isDone) {
            task.mark();
        }
        return task;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Un-marks the task.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the tags on the task.
     *
     * @return String representation of the tags on the task.
     */
    protected String getTagsAsString() {
        if (tags.isEmpty()) {
            return "";
        } else {
            return " | Tags: " + String.join(", ", tags);
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return (isDone ? "1 | " : "0 | ") + description.trim();
    }

    /**
     * Returns the string representation of the task to be saved to a file.
     * This method must be implemented by all subclasses.
     *
     * @return String representation of the task to be saved to a file.
     */
    public abstract String toFileString();
}
