public class Alexer {
    private static final String GOODBYE = "Goodbye! If you ever want to chat again, I'll be here.\nHave a great day!";
    private static final String BREAK = "____________________________________________________________";

    public static final String NAME = "Alexer";
    public static void main(String[] args) {
        String logo = """
                     .     .                           \s
                    /|     |     ___  _  .-   ___  .___\s
                   /  \\    |   .'   `  \\,'  .'   ` /   \\
                  /---'\\   |   |----'  /\\   |----' |   '
                ,'      \\ /\\__ `.___, /  \\  `.___, /   \s""";

        System.out.println(BREAK);
        System.out.println(logo);
        System.out.println(BREAK);
        System.out.printf("Hello from %s, what can I do for you today?\n", NAME);
        System.out.println(BREAK);
        System.out.println(GOODBYE);
        System.out.println(BREAK);
    }
}
