package alice.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alice.parser.TaskParser;

/** Represents tasks. */
public abstract class Task {
    protected String description;
    protected boolean isCompleted;
    protected List<String> tags;

    /** Types of tasks. */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a task and marks it as incomplete.
     *
     * @throws InvalidTaskException if the input line is invalid
     */
    public Task(String line) throws InvalidTaskException {
        this.description = TaskParser.parseDescription(line);
        this.isCompleted = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Sets the completion status of a task.
     *
     * @param isCompleted whether the task should be set as complete or incomplete
     */
    public void setCompletion(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Sets the completion status of a task.
     *
     * @param tagDescription thedescription of the tag
     */
    public void addTag(String tagDescription) {
        tags.add(tagDescription);
    }


    /**
     * Parses a JSON string into a task.
     *
     * @param  jsonString the JSON string representing a task
     * @return            the parsed task
     */
    public static Task fromJsonString(String jsonString) throws InvalidTaskException {
        Map<String, String> arguments = TaskParser.parseJsonString(jsonString);
        assert arguments.containsKey("taskType");

        switch (TaskType.valueOf(arguments.get("taskType").toUpperCase())) {
        case TODO:
            return ToDo.fromJsonString(jsonString);
        case DEADLINE:
            return Deadline.fromJsonString(jsonString);
        case EVENT:
            return Event.fromJsonString(jsonString);
        default:
            throw new InvalidTaskException();
        }
    }

    /**
     * Checks if the task description contains a given keyword.
     *
     * @param  keyword the keyword to search for
     * @return         whether the description contains the keyword
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        String tagsString = tags.isEmpty() ? "" : String.format(" (%s)", String.join(",", tags));
        return String.format("[%s] %s%s", isCompleted ? "X" : " ", description, tagsString);
    }

    /**
     * Converts a task into a JSON string for storage.
     * The implementation is handled in the subclasses.
     *
     * @return the parsed task
     */
    public abstract String toJsonString();
}
