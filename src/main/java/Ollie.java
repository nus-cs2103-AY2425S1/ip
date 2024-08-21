public class Ollie {

    // Private Types
    private static final String NAME = "Ollie";
    private static final String DIVIDER = "____________________________________________________________\n";

    public static void main(String[] args) {
        String greet = String.format("Hello! I'm %s!\n" + "What can I do for you?\n", Ollie.NAME);
        String exit = "Bye. Hope to see you again soon!\n";

        System.out.println(Ollie.DIVIDER + greet + Ollie.DIVIDER + exit + Ollie.DIVIDER);
    }

}
