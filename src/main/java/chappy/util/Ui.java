package chappy.util;

import java.util.Scanner;

public class Ui {
    Scanner scanner;
    String logo =
            """
                                :..
                               #@@@@#+:
                              -@@@@@@@@@*-
                             .@@@@@@@@@@@@@#-
                             %@@@@@@@@@@@@@@@%=
                      -==:. *#@@@@@@@@@@@@@@@@@@.
                     .@@@@@%=--*@@@@@@@@%%%%@@%-
                      .#@@@#-::::=#@@@@%%%@@%-
                        :#@@@#-::...:+#%@@%=
                          .+%@@%*-     :*=
                             :+%@@@*=-=%%=
                                :=#@@@@@@@%.
                                    :=*#@@@*
                     .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.
                    | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
                    | |     ______   | || |  ____  ____  | || |      __      | || |   ______     | || |   ______     | || |  ____  ____  | |
                    | |   .' ___  |  | || | |_   ||   _| | || |     /  \\     | || |  |_   __ \\   | || |  |_   __ \\   | || | |_  _||_  _| | |
                    | |  / .'   \\_|  | || |   | |__| |   | || |    / /\\ \\    | || |    | |__) |  | || |    | |__) |  | || |   \\ \\  / /   | |
                    | |  | |         | || |   |  __  |   | || |   / ____ \\   | || |    |  ___/   | || |    |  ___/   | || |    \\ \\/ /    | |
                    | |  \\ `.___.'\\  | || |  _| |  | |_  | || | _/ /    \\ \\_ | || |   _| |_      | || |   _| |_      | || |    _|  |_    | |
                    | |   `._____.'  | || | |____||____| | || ||____|  |____|| || |  |_____|     | || |  |_____|     | || |   |______|   | |
                    | |              | || |              | || |              | || |              | || |              | || |              | |
                    | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
                     '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
                     """;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns whether there is new input from user.
     *
     * @return boolean value.
     */
    public boolean hasNextUserInput() {
        return scanner.hasNext();
    }

    /**
     * Returns space-trimmed new input from user.
     *
     * @return String.
     */
    public String loadNextUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints String representing an ASCII line to separate input and responses.
     *
     */
    public void printLineSpacer() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints Strings containing ASCII logo and introduction.
     *
     */
    public void printIntro() {
        System.out.println("Good day sir! \n" + logo + "\nat your service!");
        System.out.println("I shall await your next request.");
    }

    /**
     * Prints String stating user's input is not a valid Command.
     *
     */
    public void printUnknownCommand() {
        System.out.println("Oh SIR! I can't understand what you are saying!");
    }


}
