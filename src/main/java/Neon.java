public class Neon {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";

    private static void greeting_line() {
        System.out.println(DASH_BREAK);
        String greeting = "Hello I'm " + NAME + "!\n"
                + "What can I help you with?\n";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    private static void closing_line() {
        System.out.println(DASH_BREAK);
        String closing = "Byeee! Nice to meet you!\n";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    public static void main(String[] args) {
        greeting_line();
        closing_line();
    }
}
