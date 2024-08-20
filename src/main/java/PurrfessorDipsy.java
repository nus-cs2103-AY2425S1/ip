import java.util.Scanner;
public class PurrfessorDipsy {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        printWelcomeMessage();
        while (true) {
            String userInput = inputScanner.nextLine();
            doAction(userInput);
        }
    }

    private static void printTerminalLine() {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(TERMINAL_LINE);
    }

    private static void printWelcomeMessage() {
        printTerminalLine();
        System.out.println("Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\nHow can I purrvide assistance? " +
                "Purrhaps I can lend a paw!");
        printTerminalLine();
    }

    private static void printExitMessage() {
        System.out.println("Fur-well friend, stay paw-sitive!");
        printTerminalLine();
    }

    private static void echoUserInput(String userInput) {
        printTerminalLine();
        System.out.println(userInput);
        printTerminalLine();
    }

    private static void doAction(String userInput) {
        String trimmedInput = userInput.trim().toLowerCase();

        switch (trimmedInput) {
            case "":
                // do nothing
                break;
            case "bye":
                printExitMessage();
                System.exit(0);
                break;
            default:
                echoUserInput(userInput);
                break;
        }
    }

}
