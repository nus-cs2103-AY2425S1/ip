public class YapBotException extends Exception {
    private static final String PREFIXLINE = "\n-------------------------------------------\n";
    private static final String POSTFIXLINE = "\n-------------------------------------------\n";

    public YapBotException(String description) {
        super(PREFIXLINE + description + POSTFIXLINE);
    }

}
