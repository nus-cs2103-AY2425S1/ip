package wenjieBot.exceptions;

public class OutOfBoundsException extends DukeException {
    public OutOfBoundsException() {
        super("test");
    }

    @Override
    public String getMessage() {
        return "wah shag bro, you input a list number that hasn't been added to the list yet ";
    }
}
