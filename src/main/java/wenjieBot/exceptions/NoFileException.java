package wenjieBot.exceptions;

public class NoFileException extends DukeException {
    public NoFileException() {
        super("test");
    }

    @Override
    public String getMessage() {
        return "eh siao liao, i cant find the file to put my lobang in sia";
    }
}
