package task;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import task.tasktype.Deadline;
import task.tasktype.Event;
import task.tasktype.TaskType;
import task.tasktype.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a general task. This class is a base class for specific task types such as
 * to-dos, deadlines, and events.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected ArrayList<Tag> tagsLst = new ArrayList<>();

    /**
     * Constructs a new Task with the specified type and description.
     *
     * @param type The type of the task.
     * @param description The description of the task.
     */
    protected Task(TaskType type, String description, String... tags) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        if (tags.length != 0) {
            Arrays.stream(tags).forEach(tag -> tagsLst.add(TagManager.addTag(tag)));
        }
    }
    /**
     * Factory method: Creates a new task based on the description provided. The task type is determined from
     * the description, and the appropriate task object is created.
     *
     * @param description A string containing the task description and any associated time details.
     * @return A new Task object of the appropriate type (Todo, Deadline, or Event).
     * @throws InvalidTaskException if the task type is invalid.
     * @throws NoTaskDescriptionException if the task description is missing
     */
    public static Task createTask(String description)
            throws InvalidTaskException, NoTaskDescriptionException, InvalidDateException {
        String strippedDescription = description.trim().toLowerCase();
        String[] words = description.split(" ");
        if (words.length == 1 || words[1].equals("/from") || words[1].equals("/by")) {
            throw new NoTaskDescriptionException();
        }

        TaskType taskType = determineTaskType(strippedDescription);

        try {
            if (taskType == TaskType.TODO) {
                return Task.createTodo(description);
            } else if (taskType == TaskType.DEADLINE) {
                return Task.createDeadline(description);
            } else {
                return Task.createEvent(description);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private static String[] extractTags(String description) {
        int tagIndex = description.indexOf("-t");
        if (tagIndex == -1) {
            return new String[]{description, ""};
        }

        String pureDescription = description.substring(0, tagIndex);
        String tagString = description.substring(tagIndex + 2).trim();
        return new String[]{pureDescription, tagString};
    }

    private static Todo createTodo(String description) {
        String[] descTagArr = extractTags(description);
        String[] tagArr = descTagArr[1].split(" ");
        return new Todo(descTagArr[0], tagArr);
    }

    private static Event createEvent(String description) throws DateTimeParseException {
        String[] descTagArr = extractTags(description);
        String[] tagArr = descTagArr[1].split(" ");
        String pureDescription = descTagArr[0];

        int firstSlashIndex = pureDescription.indexOf("/");
        String des = pureDescription.substring(0, firstSlashIndex).trim();
        String timeDetails = pureDescription.substring(firstSlashIndex);

        String startDateTime = timeDetails.substring(timeDetails.indexOf("/from") + 6,
                timeDetails.indexOf("/to")).trim();
        String endDateTime = timeDetails.substring(timeDetails.indexOf("/to") + 4).trim();
        return new Event(des, startDateTime, endDateTime, tagArr);
    }

    private static Deadline createDeadline(String description) throws DateTimeParseException {
        String[] descTagArr = extractTags(description);
        String[] tagArr = descTagArr[1].split(" ");
        String pureDescription = descTagArr[0];

        int firstSlashIndex = pureDescription.indexOf("/");
        String des = pureDescription.substring(0, firstSlashIndex).trim();
        String deadline = pureDescription.substring(description.indexOf("/by") + 4);
        return new Deadline(des, deadline, tagArr);
    }

    /**
     * Determines the type of the task based on the description provided.
     *
     * @param description The task description.
     * @return The TaskType (TODO, DEADLINE, or EVENT) based on the description.
     */
    public static TaskType determineTaskType(String description) throws InvalidTaskException{
        if (description.startsWith("todo")) {
            return TaskType.TODO;
        } else if (description.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (description.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            throw new InvalidTaskException();
        }
    }




    /**
     * Changes the status of the task to done or not done.
     *
     * @param newIsDone The new status of the task (true if done, false otherwise).
     */
    public void changeStatus(boolean newIsDone) {
        this.isDone = newIsDone;
    }

    /**
     * Returns the status icon of the task ("X" if done, otherwise " ").
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getTagsAsString() {
        StringBuilder result = new StringBuilder();
        tagsLst.forEach(tag -> result.append(tag.toString())
                .append(" "));
        return result.toString();
    }

    @Override
    public String toString() {
        int firstSpaceIndex = this.description.indexOf(' ');
        String removeFirstWord = this.description.substring(firstSpaceIndex + 1);
        return "[" + this.getStatusIcon() + "] " + removeFirstWord;
    }
}
