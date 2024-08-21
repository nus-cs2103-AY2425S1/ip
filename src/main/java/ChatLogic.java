public class ChatLogic {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String BYE_COMMAND = "bye";
    static final String LIST_COMMAND = "list";

    private final String name;

    public ChatLogic(String name) {
        this.name = name;
    }

    public void processInput(String input) {
        switch (input) {
            case BYE_COMMAND:
                printBye();
                System.exit(0);
            default:
                printInputEcho(input);
        }
    }

    public void printInputEcho(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" " + input);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGreeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm " + name + ".");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
