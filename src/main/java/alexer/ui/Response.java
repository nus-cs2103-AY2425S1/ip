package alexer.ui;

/**
 * Represents a response message from the chatbot.
 *
 * @author sayomaki
 */
public class Response {
    public final String response;

    public Response(String response) {
        this.response = response;
    }

    /**
     * Prints the response to console output
     */
    public void printToConsole() {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }
}
