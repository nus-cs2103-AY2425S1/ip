package meeju;

/**
 * Main class of the program. It serves as the entry point to the program.
 */
public class Meeju {

    private static final String LINE_BREAK = "____________________________________________________________";
    public static void main(String[] args) {
        UserInteraction userInteraction = new UserInteraction();


        String logo = " __  __ _____ _____    _ _   _\n"
                + "|  \\/  | ____| ____|  | | | | |\n"
                + "| |\\/| |  _| |  _| _  | | | | |\n"
                + "| |  | | |___| |__| |_| | |_| |\n"
                + "|_|  |_|_____|_____\\___/ \\___/";



        System.out.println(logo + "\n\t Meow!");
        System.out.println(LINE_BREAK);
        System.out.println(" Hello! I'm Meeju\n"
                + " What can I do for you?");
        System.out.println(LINE_BREAK);
        userInteraction.interact();
        GoodByeMessage.goodByeMessage();
        System.out.println(Meeju.LINE_BREAK);


        //REMOVE ALL THE LINE BREAK,... Initialize all objects that was initialised in userinteraction clas
        // add a getResponse method method should get input, pass to parse and get back a string
        // -> an instance of meeju will be passed to the GUI part
    }

    public String getResponse(String input) {
        return "HI";
    }
}
