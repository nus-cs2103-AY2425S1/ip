package echoa.exception;

public class NoUpdateException extends UpdateException {
    public NoUpdateException() {
        super();
    }

    @Override
    public String getMessage() {
        return "No updates done.\n" +
                "Please input updates and check validity of date and time\n";
    }
}
