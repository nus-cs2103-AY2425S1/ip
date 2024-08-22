public class Grok {

    private static void padMessage(String msg) {
        String horizontalLine = "_".repeat(80);
        System.out.println(horizontalLine);
        System.out.println(msg);
        System.out.println("\n".concat(horizontalLine));
    }
    public static void main(String[] args) {
        padMessage("Hello! I'm Grok\nWhat can I do for you?");
        padMessage("Bye. Hope to see you again soon!");
    }
}
