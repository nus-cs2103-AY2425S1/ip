public class MissingDeadlineException extends Exception {
    private static String horizontalLine = "\n-------------------------------------------------";

    public MissingDeadlineException() {
        super("Nimbus noticed that you did not include a deadline! Use /by!" + horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
