public class Ui {
    public static final String START_DIVIDER = "____________________________________________________________";
    public static final String END_DIVIDER = "____________________________________________________________\n";
    private final static String CHAT_NAME = "Snah";

    public Ui() {
    }

    public void greet() {
        start();
        print(String.format("Hello! I'm %s", CHAT_NAME));
        print("What can I do for you?");
        end();
    }

    public void start() {
        print(START_DIVIDER);
    }

    public void end() {
        print(END_DIVIDER);
    }

    public void printf(String message, Object... args) {
        print(String.format(message, args));
    }

    public void print(String message) {
        System.out.printf("\t%s\n", message);
    }

}
