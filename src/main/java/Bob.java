public class Bob {
    private static final String line = "____________________________________________________________\n";
    private static String greeting() {
        return Bob.line +
                " Hello! I'm [YOUR CHATBOT NAME]\n" +
                " What can I do for you?\n" +
                Bob.line +
                " Bye. Hope to see you again soon!\n" +
                Bob.line;
    }
    public static void main(String[] args) {
        System.out.println(Bob.greeting());
    }
}
