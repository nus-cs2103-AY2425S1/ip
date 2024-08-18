public class MissingFieldException extends BrunoException {
    public MissingFieldException() {
        super("Some information needed is missing in your command.");
    }
}
