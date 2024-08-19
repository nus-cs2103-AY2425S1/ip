public class Janet {
    private static final String horizontalLine = "____________________________________________________________";

    /**
     * Level 0 - greets the user
     * @return a String message to greet the user.
     */
    public String greet() {
        return horizontalLine + "\nHello! I'm Janet\n" + "What can I do for you?\n" + horizontalLine;
    }


    /**
     * Level 0 - say bye to user
     * @return a String message to say bye to the user and exit the program.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n" + horizontalLine;
    }


    /**
     * Level 1 - echoes the message
     * @param message a String message to capture what the user typed into the command line.
     * @return the exact same String provided by the user
     */
    public String echo(String message) {
        return horizontalLine + "\n" + message + "\n" + horizontalLine;
    }
}
