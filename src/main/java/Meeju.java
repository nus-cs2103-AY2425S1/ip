
public class Meeju {

    public static String LINE_BREAK = "____________________________________________________________";
    public static void main(String[] args) {
        UserInteraction userInteraction = new UserInteraction();


        String LOGO = " __  __ _____ _____    _ _   _\n" +
                "|  \\/  | ____| ____|  | | | | |\n" +
                "| |\\/| |  _| |  _| _  | | | | |\n" +
                "| |  | | |___| |__| |_| | |_| |\n" +
                "|_|  |_|_____|_____\\___/ \\___/";



        System.out.println(LOGO + "\n\t Meow!");
        System.out.println(LINE_BREAK);
        System.out.println(" Hello! I'm Meeju\n" +
                " What can I do for you?");
        System.out.println(LINE_BREAK);
        userInteraction.interact();
        GoodByeMessage.goodByeMessage();
        System.out.println(Meeju.LINE_BREAK);
    }
}
