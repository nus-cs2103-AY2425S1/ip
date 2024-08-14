public class YihuiBot {
    private static final String NAME = "YihuiBot";
    private static final String HORIZONTAL_LINE = "-----------------------------------------------------";

    public static void main(String[] args) {
        greetings();
        exit();
    }

    private static String wrapStringWithHorizontalLines(String s) {
        return HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE;
    }

    private static void greetings() {
        String s = String.format(
            "Hello! I am %s!\nWhat can I do for you?",
            NAME
        );
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void exit() {
        String s = "Bye. Hope to see you again soon!";
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }
}
