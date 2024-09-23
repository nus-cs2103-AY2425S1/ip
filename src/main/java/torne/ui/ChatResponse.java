package torne.ui;

public class ChatResponse {
    private final String title;

    private final boolean isError;

    private ChatResponse(String title, boolean isError) {
        this.title = title;
        this.isError = isError;
    }

    /**
     * Creates a new normal chat response object.
     *
     * @param title title of response.
     * @return {@link ChatResponse} object.
     */
    public static ChatResponse response(String title) {
        return new ChatResponse(title, false);
    }

    /**
     * Creates a new error chat response object.
     *
     * @param title title of error response.
     * @return {@link ChatResponse} object.
     */
    public static ChatResponse error(String title) {
        return new ChatResponse(title, true);
    }

    public String getTitle() {
        return title;
    }

    public boolean isErrorResponse() {
        return isError;
    }
}
