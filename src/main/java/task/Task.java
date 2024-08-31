package task;
import exception.InputFormatException;
public abstract class Task {
    private final String description;
    private boolean done;

    public Task (String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Checks if the task's description contains the specified target word.
     *
     * @param targetWord The word to search for within the task's description.
     * @return true if the description contains the target word, false otherwise.
     */
    public boolean find(String targetWord) {
        return description.contains(targetWord);
    }


    public String toFileFormatString() {
        return String.format("%d | %s", done? 1 : 0, description);
    }

    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), description);
    }

    public void markAsDone() {
        done = true;
    }

    public void markAsUndone() {
        done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public static int matchesUnmark(String input) throws InputFormatException{
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            throw new InputFormatException("Please specify a number after \"unmark\"");
        }
        try {
            return Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number after \"unmark\"");
        }
    }

    //if returns 0 means no matches
    public static int matchesMark(String input) throws InputFormatException{
        String[] inputArr = input.split(" ");
        if (inputArr.length != 2) {
            throw new InputFormatException("Please specify a number after \"mark\"");
        }
        try {
            return Integer.parseInt(inputArr[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number after \"mark\"");
        }
    }
}
