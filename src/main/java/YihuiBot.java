public class YihuiBot {
    public static final String NAME = "YihuiBot";
    public static final String HORIZONTAL_LINE = "-----------------------------------------------------";

    public static void main(String[] args) {
        printGreetings();
        printExit();
    }

    private static void printGreetings() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(String.format("Hello! I am %s!", NAME));
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
