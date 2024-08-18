public class Jeff {
    // String for horizontal line
    private static final String HORIZONTAL = "____________________________________________";

    // String for greetings
    private static String greeting() {
        return HORIZONTAL +
                "\n Hello! I'm Jeff." +
                "\n What can I do for you?\n";
    }

    // String for farewell
    private static String farewell() {
        return HORIZONTAL +
                "\n Bye. Hope to see you again soon!\n";
    }

    public static void main(String[] args) {
        System.out.println(greeting() + farewell() + HORIZONTAL);
    }
}
