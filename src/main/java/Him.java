public class Him{
    private static final String DIVIDER = "\n____________________________________________________________\n";
    private static void greet() {
        System.out.print(DIVIDER + " Hello! I'm Him\n What can I do for you?" + DIVIDER);
    }

    private static void exit() {
        System.out.print(" Bye. Hope to see you again soon!" + DIVIDER);
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}
