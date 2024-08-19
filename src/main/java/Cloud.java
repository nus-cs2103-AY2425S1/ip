public class Cloud {

    private static void greet() {
        System.out.println(
            "Hello! I'm Cloud\n" +
            "What can I do for you?"
        );
    }

    private static void printHorizLine() {
        System.out.println(
            "____________________________________________________________"
        );
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        printHorizLine();
        greet();
        printHorizLine();
        exit();
        printHorizLine();
    }
}
