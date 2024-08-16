public class WheelyBigCheese {
    private static final String greeting = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ending = "Bye. Hope to see you again soon!";

    /**
     * Method to format the output of the bot
     * @param s string to say
     */
    private static void Say(String s){
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        WheelyBigCheese.Say(greeting);
        WheelyBigCheese.Say(ending);
    }
}
