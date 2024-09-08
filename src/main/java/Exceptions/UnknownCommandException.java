package Exceptions;

public class UnknownCommandException extends BonnieException {

    public UnknownCommandException(String command) {
        super(String.format("Hey there, Bonnie does not understand what you mean by \"%s\"!", command));
    }
}
