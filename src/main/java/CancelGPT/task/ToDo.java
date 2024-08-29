package CancelGPT.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSavedDataString() {
        return "T" + " | " + super.getSavedDataString();
    }

    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) {
        return new ToDo(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2]);
    }
}
