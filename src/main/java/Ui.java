public class Ui {
    private final static String logo = " _   _                 __\n"
            + "| \\ | |  ___  _ ___   |_  \\ \n"
            + "|  \\| | / _ \\| '__ |    ) |\n"
            + "| |\\  ||  __/| | | |   / /_ \n"
            + "|_| \\_| \\___||_| |_|  |____|\n";
    private static final String separator = "--------------------------------------------";

    public Ui() {

    }

    public void showError(Exception e) {
        System.out.println(e);
    }

    /**
     * Greets user by printing out logo and greeting messages
     */
    public void greet() {
        System.out.println(separator);
        System.out.println(logo + "Hello! I'm Nen2 \nWhat can I do for you?");
        System.out.println(separator);
    }

    /**
     * End the conversation with ending messages
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }

    public void separate() {
        System.out.println(separator);
    }

    public void print(String react) {
        separate();
        System.out.print(react);
        separate();
    }

}
