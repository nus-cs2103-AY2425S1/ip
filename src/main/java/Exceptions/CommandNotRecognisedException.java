package Exceptions;

public class CommandNotRecognisedException extends TestamentException {

    public CommandNotRecognisedException() {
        super("Apologies, but I do not understand that command");
    }

    public CommandNotRecognisedException(String s) {
        super(s);
    }

}
