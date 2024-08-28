public class Ui {
    private static final String NAME_OF_CHATBOT = "Stobberi";
    private static final String HELLO_GREETING =
            "Hello! I'm " + NAME_OF_CHATBOT + ".\n"
                    + "What can I do for you?";
    private static final String GOODBYE_GREETING = "Bye. Hope to see you again soon! :)\n";
    public static void displayForm(String phrase) {
        System.out.println("_________________________________________________________\n"
                        + phrase
                        + "\n_________________________________________________________\n");
    }

    public Ui() {}

    public void greet() {
        Ui.displayForm(HELLO_GREETING);
    }

    public void goodbye() {
        Ui.displayForm(GOODBYE_GREETING);
    }
}