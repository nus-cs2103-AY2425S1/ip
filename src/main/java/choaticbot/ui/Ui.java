package choaticbot.ui;

public class Ui {
    public static void printText(String text) {
        String textToPrint = "____________________________________________________________\n"
                + text
                + "____________________________________________________________\n";

        System.out.println(textToPrint);
    }

    public static void printLine() {
        String line = "____________________________________________________________\n";
        System.out.println(line);
    }

    public static void printWelcomeMsg() {
        String welcomeMsg = "Hello! I'm ChoaticBot\n" + "What can I do for you?\n";
        printText(welcomeMsg);
    }

    public static void printByeMsg() {
        String byeMsg = "Bye. Hope to see you again soon!\n";
        printText(byeMsg);
    }

}
