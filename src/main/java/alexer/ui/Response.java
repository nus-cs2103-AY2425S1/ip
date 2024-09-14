package alexer.ui;

public class Response {
    private String response;

    public Response(String response) {
        this.response = response;
    }

    /**
     * Updates the response if needed
     * @param response the new response string
     */
    public void setResponse(String response) {
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

    /**
     * Returns the string form of the response
     * @return the response string
     */
    @Override
    public String toString() {
        return response;
    }
}
