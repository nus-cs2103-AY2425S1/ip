package yihuibot;

/**
 * Responds to the user's dialog.
 *
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // Format for date time of task
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * Generates a response to the user based on what was sent.
     *
     * @param input the user's input.
     * @return a response.
     */
    public String getResponse(String input) {
        return "YihuiBot heard: " + input;
    }
}
