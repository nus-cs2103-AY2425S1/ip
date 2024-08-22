public class Salutations {
    public void greet() {
        line();
        System.out.println("Hello! I'm Tanjiro!");
        System.out.println("What can I do for you?");
        line();
    }

    public void goodbye() {
        System.out.println("Bye! Hope to see you again!");
        line();
    }

    private void line() {
        System.out.println("========================================");
    }
}
