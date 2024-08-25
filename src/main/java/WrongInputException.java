public class WrongInputException extends Exception {
    private static String horizontalLine = "\n-------------------------------------------------";
    public WrongInputException() {
        super("Sorry Nimbus don't understand what you are saying QwQ \n" +
                "Try using todo, deadline or event!" + horizontalLine);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
