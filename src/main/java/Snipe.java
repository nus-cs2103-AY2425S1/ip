public class Snipe {
    private static final String NAME = "Snipe";
    private static final String LOGO = "  _________      .__               \n"
            + " /   _____/ ____ |__|_____   ____  \n"
            + " \\_____  \\ /    \\|  \\____ \\_/ __ \\ \n"
            + " /        \\   |  \\  |  |_> >  ___/ \n"
            + "/_______  /___|  /__|   __/ \\___  >\n"
            + "        \\/     \\/   |__|        \\/ \n";
    private static final String HORIZONTAL_LINE = "_".repeat(60);
    public void initChat() {
        greetUser();
    }

    private void greetUser() {
        String OPENING_MESSAGE = "Hello! I'm\n" + LOGO +"\nWhat can I do for you?";
        String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
        System.out.println(HORIZONTAL_LINE);
        System.out.println(OPENING_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
        System.out.println(CLOSING_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }
    private void printWithLines(String message) {

        System.out.println(HORIZONTAL_LINE);
    }
    public static void main(String[] args) {
        Snipe snipe = new Snipe();
        snipe.initChat();
    }
}
