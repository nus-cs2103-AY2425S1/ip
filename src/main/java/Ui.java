public class Ui {
    private static final String NAME_OF_CHATBOT = "Stobberi";
    private static final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "What can I do for you?";
    private static final String GOODBYE_GREETING = "Bye. Hope to see you again soon! :)\n";

    public Ui() {

    }

    public void greet() {
        System.out.println(HELLO_GREETING);
    }

    public void goodbye() {
        System.out.println(GOODBYE_GREETING);
    }
}