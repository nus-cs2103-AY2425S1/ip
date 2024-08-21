public class Megamind {
    private static final String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = """
                  _____  \s
                 /     \\ \s
                | () () |\s
                 \\  ^  / \s
                  |||||  \s
                  |||||  \s
                """;
        System.out.println(horizontalLine);
        System.out.println("Hello from\n" + logo);
        greet();
        exit();
    }

    public static void greet() {
        System.out.println(horizontalLine);
        System.out.println("Whats good, I'm Megamind.\nWhat can I do for you? \n");
    }

    public static void exit() {
        System.out.println(horizontalLine);
        System.out.println("See you around! \n");
        System.out.println(horizontalLine);
    }
}
