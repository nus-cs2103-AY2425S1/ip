package babygronk;

/**
 * Contains functions that prints customized messages.
 */
public class Ui {
    private final static String SEPARATOR =  "💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬💬\n";

    Ui() {
    }

    /**
     * Prints goodbye message and exits with status 0.
     */
    public void logOff() {
        String goodByeMessage = SEPARATOR +
                """
                Going back to my skibidi toilet.
                """ +
                SEPARATOR;
        System.out.println(goodByeMessage);
        System.exit(0);
    }

    public void    greet() {
        String welcomeMessage = SEPARATOR +
                """ 
                What's up ohio? I'm BabyGronk, let's see how sigma you are
                Gyatt Damn
                """ +
                SEPARATOR;
        System.out.println(welcomeMessage);
    }

    public void    mascot() {
        String logo = """
                ⣠⣀⣤⣶⣶⣶⣶⣤⣤⣤⣤⣄⡀⠀⠀⠀⢀⣀⣀⣤⣤⣤⣶⣶⣶⣶⣬⣒⢦⡀
                ⡾⠛⠉⠉⢀⣀⣈⣉⣉⣉⣻⠛⠁⠀⠀⠀⠀⠙⢛⣛⣉⣉⣉⣉⣀⠀⠉⠙⠻⢮
                ⠀⠀⣀⠴⢲⣶⣶⣶⠶⡦⠄⢷⡄⠀⠀⠀⠀⣼⠃⠴⡶⢶⣶⣶⢶⠲⢤⡀⠀⠀
                ⠀⠘⠓⠤⠼⠿⠿⠿⠥⠽⠄⠘⠀⠀⠀⠀⠀⠘⠂⠼⠥⠽⠿⠿⠿⠤⠖⠛⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠀⠀⡞⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣤⣀⣀⣀⣀⣀⣀⣀⣀⣀⣠⡤⠤⠶⠞⠋⠁⠀⠀⣸⠁⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀""";
        System.out.println("Up your rizz\n" + logo);
    }

    public void printMessage(String message) {
        System.out.println(SEPARATOR + message + SEPARATOR);
    }
}
