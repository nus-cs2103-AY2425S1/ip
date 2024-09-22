package screwllum.exception;

public class EmptyDescriptionException extends ScrewllumException {
    public EmptyDescriptionException() {
        super("Description is empty! Please name your task something!");
    }
}
