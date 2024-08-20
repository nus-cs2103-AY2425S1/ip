public class Henry {

    /**
     * Prints greetings
     *
     */
    public static void greetings() {
        String greetings = "Hello! I'm Henry\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    /**
     * Prints exit
     *
     */
    public static void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }
    public static void main(String[] args) {
        greetings();
        bye();
    }
}
