public class UI {
    private static String welcomeMessage = LineWrapper.wrap("Hello! I'm dudu\n"
            + "What can I do for you?");
    private static String goodbyeMessage = LineWrapper.wrap("Bye. Hope to see you again soon!");

    public void welcomeMessage() {
        System.out.println(UI.welcomeMessage);
    }

    public void goodbyeMessage() {
        System.out.println(goodbyeMessage);
    }

    public void output(String message) {
        System.out.println(message);
    }
}
