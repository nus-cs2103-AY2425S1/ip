public class SecondMind {
    private static final String line = "____________________________________________________________";

    private static void printLineSeparator() {
        System.out.println(line);
    }

    private static void greetUser() {
        printLineSeparator();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void main(String[] args) {
        String logo = "SecondMind";
    }
}
