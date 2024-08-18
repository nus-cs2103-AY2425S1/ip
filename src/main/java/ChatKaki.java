public class ChatKaki {
    private static final String CHATBOT_NAME = "ChatKaki";

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + CHATBOT_NAME);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Exit message
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();
    }
}
