public class Ui {

    private static final String LINE_BREAK = "____________________________________";
    private boolean isTerminated;

    public Ui() {
        this.isTerminated = false;
    }

    public static void printText(String text) {
        System.out.println(LINE_BREAK);
        System.out.println(text);
        System.out.println(LINE_BREAK + "\n");
    }

    public static void printList(TaskList tasks) {
        printText(tasks.toString());
    }

    public static void printGreeting() {
        printText("Hello, I'm Rasputin!\nWhat can I do for you?");
    }

    public static void printFarewell() {
        printText("Bye. See you later!");
    }

    public static void printInvalidCommand() {
        printText("I'm sorry, I don't understand that command.");
    }

    public static void printError(String error) {
        printText(error);
    }
}

