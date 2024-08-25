public class MissingDescriptionException extends Exception {
    private static String horizontalLine = "\n-------------------------------------------------";
    public MissingDescriptionException(String taskName) {
        super("Oh noo, you cant leave the description of " + taskName +
                " empty!!" + horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
