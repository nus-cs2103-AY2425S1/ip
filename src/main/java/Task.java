import exceptions.InvalidTaskException;
import exceptions.NoTaskDescriptionException;

import java.util.Arrays;
import java.util.HashSet;

public class Task {
    protected String description;
    protected  boolean isDone;
    protected static HashSet<String> taskNames = new HashSet<>(Arrays.asList("todo", "deadline", "event"));

    public static Task createTask(String description)
            throws InvalidTaskException, NoTaskDescriptionException {
        String strippedDescription = description.trim().toLowerCase();
        String[] words = description.split(" ");
        if (!taskNames.contains(words[0])) {
            throw new InvalidTaskException();
        }
        if (words.length == 1) {
            throw new NoTaskDescriptionException();
        }

        if (strippedDescription.startsWith("todo")) {
            return new Todo(description);
        } else if(strippedDescription.startsWith("deadline")) {
            String[] splitStr = description.split("/");
            String des = splitStr[0].trim();
            String deadline = splitStr[1].split(" ")[1];
            return new Deadline(des, deadline);
        } else {
            String[] splitStr = description.split("/");
            String des = splitStr[0].trim();
            String start = splitStr[1].replace("from ", "").trim();
            String end = splitStr[2].replace("to ", "").trim();
            return new Event(des, start, end);
        }
    }

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
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
