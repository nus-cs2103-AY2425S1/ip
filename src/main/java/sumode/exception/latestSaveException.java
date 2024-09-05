package sumode.exception;

public class latestSaveException extends SumoDException {
    public latestSaveException() {
        super("Sumo cannot save latest change.");
    }
}
