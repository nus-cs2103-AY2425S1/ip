public class Ui {
    private final int lineLength = 50;
    private final String horizontalLine = "\t" + "-".repeat(this.lineLength);

    public void printHorizontalLine() {
        System.out.println(this.horizontalLine);
    }


    public void greet(String name) {
        this.printHorizontalLine();
        System.out.println("\t" + "Hello, I'm " + name);
        System.out.println("\t" + "What can I do for you?");
        this.printHorizontalLine();
    }

    public void exit() {
        this.printHorizontalLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        this.printHorizontalLine();
    }

    public void echo(String msg) {
        this.printHorizontalLine();
        System.out.println("\t" + msg);
        this.printHorizontalLine();
    }
}