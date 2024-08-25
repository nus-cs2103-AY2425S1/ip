public class MissingStartEndTimeException extends Exception {
    private static String horizontalLine = "\n-------------------------------------------------";
    private String missing;

    public MissingStartEndTimeException(String missing) {
        super("Nimbus noticed that you did not include the " + missing + horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
