package shnoop.exceptions;
public class UnmarkableArrayException extends Exception {

    public UnmarkableArrayException(String message) {
        super(message);
    }

    public UnmarkableArrayException() {
        super("✿ Shnoop ✿: Don't break your neck tryna creep a little sneak mark, there's no task with that number.");
    }
}
