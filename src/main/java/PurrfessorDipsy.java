public class PurrfessorDipsy {
    public static void main(String[] args) {
        printWelcomeMessage();
        printExitMessage();
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
}
