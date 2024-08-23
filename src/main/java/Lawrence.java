public class Lawrence {
    private static final String NAME = "Lawrence";
    public static void main(String[] args) {
        greetUser();
        exit();
    }

    private static void greetUser() {
        String greeting = String.format("Hello! I'm %s and I'm here to steal your credit card information.\n"
                + "What can I do for you?", NAME);
        System.out.println(greeting);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
