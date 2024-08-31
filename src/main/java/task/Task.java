package task;

import java.util.Arrays;
import java.util.HashSet;

import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import task.tasktype.Deadline;
import task.tasktype.Event;
import task.tasktype.TaskType;
import task.tasktype.Todo;

/**
 * Represents a general task. This class is a base class for specific task types such as
 * to-dos, deadlines, and events.
 */
public class Task {
    protected static HashSet<String> taskNames = new HashSet<>(Arrays.asList("todo", "deadline", "event"));

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a new Task with the specified type and description.
     *
     * @param type The type of the task.
     * @param description The description of the task.
     */
    protected Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
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
            throws InvalidTaskException, NoTaskDescriptionException {
        String strippedDescription = description.trim().toLowerCase();
        String[] words = description.split(" ");
        if (!taskNames.contains(words[0])) {
            throw new InvalidTaskException();
        }
        if (words.length == 1 || words[1].equals("/from") || words[1].equals("/by")) {
            throw new NoTaskDescriptionException();
        }

        TaskType taskType = determineTaskType(strippedDescription);

        if (taskType == TaskType.TODO) {
            return new Todo(description);
        } else if (taskType == TaskType.DEADLINE) {
            int firstSlashIndex = description.indexOf("/");
            String des = description.substring(0, firstSlashIndex).trim();
            String deadline = description.substring(description.indexOf("/by") + 4);
            return new Deadline(des, deadline);
        } else {
            int firstSlashIndex = description.indexOf("/");
            String des = description.substring(0, firstSlashIndex).trim();
            String timeDetails = description.substring(firstSlashIndex);

            String startDateTime = timeDetails.substring(timeDetails.indexOf("/from") + 6,
                    timeDetails.indexOf("/to")).trim();
            String endDateTime = timeDetails.substring(timeDetails.indexOf("/to") + 4).trim();
            return new Event(des, startDateTime, endDateTime);
        }
    }

    /**
     * Determines the type of the task based on the description provided.
     *
     * @param description The task description.
     * @return The TaskType (TODO, DEADLINE, or EVENT) based on the description.
     */
    public static TaskType determineTaskType(String description) {
        if (description.startsWith("todo")) {
            return TaskType.TODO;
        } else if (description.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else {
            return TaskType.EVENT;
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

    @Override
    public String toString() {
        int firstSpaceIndex = this.description.indexOf(' ');
        String removeFirstWord = this.description.substring(firstSpaceIndex + 1);
        return "[" + this.getStatusIcon() + "] " + removeFirstWord;
    }
}
