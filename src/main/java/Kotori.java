public class Kotori {
    public static void main(String[] args) {
        printLine();
        printGreeting();
        printLine();
        printExit();
        printLine();
    }

    public static void printLine() {
        System.out.println("___________________________________________");
    }

    public static void printGreeting() {
        System.out.println("Hello! I'm Kotori.\n What can I do for you?");
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
