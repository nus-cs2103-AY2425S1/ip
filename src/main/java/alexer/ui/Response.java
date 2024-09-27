package alexer.ui;

/**
 * Represents a response message from the chatbot.
 *
 * @author sayomaki
 */
public class Response {
    public final String response;
    public final ResponseType type;

    public Response(String response) {
        this(response, ResponseType.SUCCESS);
    }

    public Response(String response, ResponseType type) {
        this.response = response;
        this.type = type;
    }

    /**
     * Prints the response to console output
     */
    public void printToConsole() {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }
    
    public enum ResponseType {
        SUCCESS,
        ERROR
    }
}
