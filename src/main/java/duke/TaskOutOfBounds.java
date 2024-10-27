package duke;

public class TaskOutOfBounds extends Exception {
    public TaskOutOfBounds(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return this.msg;
    }

    public String msg;
}
