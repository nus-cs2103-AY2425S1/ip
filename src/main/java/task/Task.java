package task;

import java.util.Arrays;
import java.util.HashSet;

import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;
import task.tasktype.Deadline;
import task.tasktype.Event;
import task.tasktype.TaskType;
import task.tasktype.Todo;

public class Task {
    protected static HashSet<String> taskNames = new HashSet<>(Arrays.asList("todo", "deadline", "event"));

    protected String description;
    protected boolean isDone;
    protected TaskType type;

    protected Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

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

    public static TaskType determineTaskType(String description) {
        if (description.startsWith("todo")) {
            return TaskType.TODO;
        } else if (description.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else {
            return TaskType.EVENT;
        }
    }

    public void changeStatus(boolean newIsDone) {
        this.isDone = newIsDone;
    }

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
