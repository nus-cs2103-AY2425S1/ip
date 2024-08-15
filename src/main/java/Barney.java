public class Barney {
    private static String LONG_LINE = "_________________________________________________";

    public static void main(String[] args) {
        welcomeText();
        endingText();
    }

    public static void welcomeText() {
        String text = "Hello, I am Barney <RAWR>, what can I do for you?";
        System.out.println(text);
        System.out.println(LONG_LINE);
    }

    public static void endingText() {
        String text = "Goodbye, I am Barney <RAWR>, see you next time!";
        System.out.println(text);
        System.out.println(LONG_LINE);
    }

}
