package utility;

public class Ui {

    public Ui() {}

    public static void say(String textToSay) {
        String line = "____________________________________________________________\n";
        System.out.print(textToSay);
        System.out.print(line);
    }

    public static void introSay() {
        String logo = "    ________    __    ________  ______\n"
                + "   / ____/ /   / /   /  _/ __ \\/_  __/\n"
                + "  / __/ / /   / /    / // / / / / /\n"
                + " / /___/ /___/ /____/ // /_/ / / /\n"
                + "/_____/_____/_____/___/\\____/ /_/\n";
        String intro = "Hello! I'm\n" + logo
                + "What can I do for you?\n";
        say("");
        say(intro);
    }

}
