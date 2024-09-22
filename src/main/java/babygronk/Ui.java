package babygronk;

/**
 * Contains functions that prints customized messages.
 */
public class Ui {
    private static final String SEPARATOR = "=========================================================\n";

    /**
     * Prints goodbye message and exits with status 0.
     */
    protected void logOff() {
        String goodByeMessage = SEPARATOR
                + """
                Going back to my skibidi toilet.
                """
                + SEPARATOR;
        //System.out.println(goodByeMessage);
        System.exit(0);
    }

    /**
     * Prints welcome message
     */
    protected void greet() {
        String logo = """
                """;
        System.out.println("Up your rizz\n" + logo);
        String welcomeMessage = SEPARATOR
                + """
                What's up ohio? I'm BabyGronk, let's see how sigma you are
                Gyatt Damn
                """
                + SEPARATOR;
        System.out.println(welcomeMessage);
    }

    protected static String sayHi() {
        return ("""
                Hello there, have you been practising your mewing?
                """);
    }

    protected static String sayBye() {
        return ("""
                Smell you later
                """);
    }

    public void printMessage(String message) {
        System.out.println(SEPARATOR + message + SEPARATOR);
    }
}
