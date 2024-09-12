package terminator.cli;

/**
 * Class responsible for displaying UI interface.
 */
public class TerminalUi {

    private static final String HLINE = "____________________________________________________________\n";

    private static final String LOGO =
            """
                               <((((((\\\\\\
                               /      . }\\
                               ;--..--._|}
            (\\                 '--/\\--'  )
             \\\\                | '-'  :'|
              \\\\               . -==- .-|
               \\\\               \\.__.'   \\--._
               [\\\\          __.--|       //  _/'--.
               \\ \\\\       .'-._ ('-----'/ __/      \\
                \\ \\\\     /   __>|      | '--.       |
                 \\ \\\\   |   \\   |     /    /       /
                  \\ '\\ /     \\  |     |  _/       /
                   \\  \\       \\ |     | /        /
                    \\  \\      \\        /
            """;

    public TerminalUi() {
    }

    /**
     * Prints a horizontal line to standard output.
     */
    public void printHorizontalLine() {
        System.out.print(HLINE);
    }

    /**
     * Prints a basic greeting message.
     */
    public void greet() {
        String msg = HLINE + LOGO + "Device booted successfully. State your request.\n" + HLINE;
        System.out.print(msg);
    }

    /**
     * Prints an exit message when the chatbot closes.
     */
    public void showExitMsg() {
        String exitMsg = HLINE + "Connection terminated. I will be back...\n" + HLINE;
        System.out.print(exitMsg);
    }

    /**
     * Prints an error message indicating that an error occurred but was not handled.
     */
    public void showErrorMsg() {
        System.out.println(HLINE + "Unexpected error, terminating early..." + HLINE);
    }

}
