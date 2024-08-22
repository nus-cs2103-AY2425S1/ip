public class ChadGPT {
    public static void main(String[] args) {
        printBotMessage("Hello! I'm ChadGPT. What can I do for you?");
        printBotMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted bot message
     *
     * @param msg the string message to be printed
     */
    public static void printBotMessage(String msg) {
        final String divider = "____________________________________________________________";

        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }
}
