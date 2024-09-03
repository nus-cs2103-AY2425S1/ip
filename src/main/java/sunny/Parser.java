package sunny;
/**
 * Breaks down user messages for other classes
 */
public class Parser {
    private String firstHalf;
    private String secondHalf;

    public Parser(String message) {
        String[] words = message.split("\\s+", 2);
        firstHalf = words[0];
        if (words.length > 1) {
            secondHalf = words[1];
        }
    }

    public Parser(String message, String parseBy) {
        String[] words = message.split(parseBy, 2);
        firstHalf = words[0];
        if (words.length > 1) {
            secondHalf = words[1];
        }
    }

    public String getFirstHalf() {
        return firstHalf;
    }

    public String getSecondHalf() {
        return secondHalf;
    }
}
