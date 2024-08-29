package src;

public class NoFileException extends DukeException {
    NoFileException() {
        super("test");
    }

    @Override
    public String toString() {
        return "eh siao liao, i cant find the file to put my lobang in sia";
    }
}
