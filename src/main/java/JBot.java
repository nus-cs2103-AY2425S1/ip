public class JBot {

    private static void hLine() {
        System.out.println("___________________________________________");
    }
    private static void greetUser() {
        hLine();
        System.out.println("Hello! I'm JBot");
        System.out.println("What can I do for you?");
    }
    private static void endSession() {
        hLine();
        System.out.println("Bye. Hope to see you again soon!");
        hLine();
    }
    public static void main(String[] args) {
        greetUser();
        endSession();
    }
}
