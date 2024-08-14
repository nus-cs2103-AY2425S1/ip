public class YihuiBot {
    private static final String NAME = "YihuiBot";

    public static void main(String[] args) {
        greetings();
        exit();
    }

    private static String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
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
