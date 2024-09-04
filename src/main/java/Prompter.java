public class Prompter {
    private static final String BREAK = "____________________________________________________________";

    public void printLogo(String logo) {
        printResponse(logo);
    }

    public void printGreeting() {
        printResponse(String.format("Hello from %s, what can I do for you today?", Alexer.NAME));
    }

    public void printGoodbye() {
        printResponse("Goodbye! If you ever want to chat again, I'll be here.\nHave a great day!");
    }

    /**
     * Prints the chatbot response with break lines
     * @param response The message to be printed
     */
    public void printResponse(String response) {
        System.out.println(BREAK);
        System.out.println(response);
        System.out.println(BREAK);
    }
}
