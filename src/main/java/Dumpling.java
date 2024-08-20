public class Dumpling {

    public static final String DIVIDER = "____________________________________________________________";

    public static void greet() {
        String greetingMessage = "Hello! I'm Dumpling\n" +
                "What can I do for you?\n" +
                DIVIDER;
        System.out.println(greetingMessage);
    }

    public static void exit() {
        String exitMessage = "Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exitMessage);
    }

    public static void main(String[] args) {
        System.out.println(DIVIDER);
        greet();
        exit();
    }
}
