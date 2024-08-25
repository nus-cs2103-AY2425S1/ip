public class Ui {

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

    public Ui() {
    }

    public void printHorizontalLine() {
        System.out.print(HLINE);
    }

    public void greet() {
        String msg = HLINE + LOGO + "Device booted successfully. State your request.\n" + HLINE;
        System.out.print(msg);
    }

    public void showExitMsg() {
        String exitMsg = HLINE + "Connection terminated. I will be back...\n" + HLINE;
        System.out.print(exitMsg);
    }

    public void showErrorMsg() {
        System.out.println(HLINE + "Unexpected error, terminating early..." + HLINE);
    }

}
