public class Janet {
    private static final String horizontalLine = "____________________________________________________________";

    /**
     * @return a String message to greet the user
     */
    public String greet() {
        return horizontalLine + "\nHello! I'm Janet\n" + "What can I do for you?\n" + horizontalLine;
    }


    /**
     * @return a String message to say bye to the user and exit the program
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n" + horizontalLine;
    }
}
