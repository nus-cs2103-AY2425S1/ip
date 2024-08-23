package exceptions;

public class ArgumentNotFoundException extends BottyException {
    public ArgumentNotFoundException(String argument) {
        super("The following argument is not found: " + argument);
    }
}
