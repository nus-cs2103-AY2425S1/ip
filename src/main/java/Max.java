public class Max {
    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    public void runMax() {
        printLine();
        printMessage("Hello! I'm Max\nWhat can I do for you?");
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printMessage(String message) {
        System.out.println(message);
        printLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}
