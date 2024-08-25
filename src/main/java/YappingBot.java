import java.util.Arrays;
import java.util.stream.Collectors;

public class YappingBot {
    // Text strings
    private static final String BOT_NAME = "YappingBot";
    private static final String GREETING_TEXT = String.format(
            "Hello! I'm %s\nWhat can I do for you?",
            BOT_NAME
    );
    private static final String EXIT_TEXT = "Bye. Hope to see you again soon!";
    // End of text strings

    private static String quoteText(String text) {
        // annotates text with pipe to denote speech from bot
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            sb.append("\n |  ");
            sb.append(l);
        }
        sb.append("\n"); // pad the end with another newline
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(quoteText(GREETING_TEXT));
        System.out.println(quoteText(EXIT_TEXT));
    }
}
