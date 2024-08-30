package task;

import java.util.Arrays;

public class Todo extends Task {
    public Todo() {
        super("", "T");
    }

    public void convertStringToTask(String[] slicedStr) {
        String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
        this.description = String.join(" ", task);
    }

    public void convertSavedDataToTask(String[] dataArr) {
        this.setMarkStatus(dataArr[1].equals("1"));
        this.description = dataArr[2];
    }
}
