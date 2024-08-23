public class Message {
    private static final String HELLO = "Hello, I'm Talk-a-Bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void Hello() {
        System.out.println(HELLO);
    }

    public static void Goodbye() {
        System.out.println(GOODBYE);
    }

    public static void Echo(String input) {
        System.out.println(String.format("%s????? What does that mean?", input));
    }
}
