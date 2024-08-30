package wenjieBot.exceptions;

public class NoNumberInputtedException extends DukeException {
    public NoNumberInputtedException() {

        super("test");
    }

    @Override
    public String getMessage() {

        return "wah shag bro, you forgot to add a number after your mark/unmark/delete command";
    }
}
