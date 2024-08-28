package Task;

import Task.TaskType.Deadline;
import Task.TaskType.TaskType;
import Task.TaskType.Todo;
import Task.TaskType.Event;
import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.util.Arrays;
import java.util.HashSet;

public class Task {
    protected String description;
    protected  boolean isDone;
    protected TaskType type;
    protected static HashSet<String> taskNames = new HashSet<>(Arrays.asList("todo", "deadline", "event"));

    public static Task createTask(String description)
            throws NoTaskDescriptionException {
        String strippedDescription = description.trim().toLowerCase();
        String[] words = description.split(" ");

        if (words.length == 1) {
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

            String startDateTime = timeDetails.substring(timeDetails.indexOf("/from") + 6, timeDetails.indexOf("/to")).trim();
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

    protected Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }


    public void changeStatus(boolean newIsDone) {
       this.isDone = newIsDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void printDate() {

    }

    public TaskType getTaskType(){
        return this.type;
    }

    @Override
    public String toString() {
        int firstSpaceIndex = this.description.indexOf(' ');
        String removeFirstWord = this.description.substring(firstSpaceIndex + 1);
        return "[" + this.getStatusIcon() + "] " + removeFirstWord;
    }
}
