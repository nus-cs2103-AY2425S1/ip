public class Bocchi {
    /**
     * The name of this bot.
     */
    static private final String name = "Bocchi";

    /**
     * Constructor.
     */
    public Bocchi() {

    }

    /**
     * Print a greeting with an ASCII art.
     */
    private void printLogo() {
        String logo = """
                 ____                 _     _\s
                |  _ \\               | |   (_)
                | |_) | ___   ___ ___| |__  _\s
                |  _ < / _ \\ / __/ __| '_ \\| |
                | |_) | (_) | (_| (__| | | | |
                |____/ \\___/ \\___\\___|_| |_|_|
                                             \s
                                             \s""";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Print a horizontal line.
     */
    private void printSeparator() {
        System.out.println("_________________________________________");
    }

    /**
     * End the conversation.
     */
    private void exit() {
        System.out.println("Oh you are leaving.. It was a great time talking to you (>_<)");
    }

    /**
     * Greet the user.
     */
    private void greet() {
        System.out.println("Hi! I'm " + name + "...");
        System.out.println("Wha..what can I do for you today? o(*//▽//*)q");
    }

    /**
     * Start a conversation.
     */
    public void start() {
        printSeparator();
        greet();
        printSeparator();
        exit();
        printSeparator();
    }

    public static void main(String[] args) {
        new Bocchi().start();
    }
}
