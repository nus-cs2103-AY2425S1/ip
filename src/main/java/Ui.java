public class Ui {
    public Ui() {

    }

    public static void print(String str) {
        System.out.print("    ");
        System.out.println(str);
    }

    public void showWelcome() {
        this.printHorizontalBar();
        Ui.print("Hello! I'm Friendly Bot");
        Ui.print("What can I do for you?");
        this.printHorizontalBar();
    }

    public void exitMessage() {
        Ui.print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal bar for visual separation in the console output.
     */
    public void printHorizontalBar() {
        Ui.print("____________________________________________________________");
    }

    public void readCommand() {

    }
}
