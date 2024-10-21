package duke;

public class ArgumentNotFoundException extends Exception {
    public ArgumentNotFoundException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return this.msg;
    }

    public String msg;
}
