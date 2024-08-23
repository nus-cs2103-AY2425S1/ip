public class Elliot {
    public static void main(String[] args) {
        IntroSay();
        ByeSay();
    }

    private static void Say(String textToSay) {
        String line = "____________________________________________________________\n";
        System.out.print(textToSay);
        System.out.print(line);
    }

    private static void IntroSay() {
        String logo = "    ________    __    ________  ______\n"
                + "   / ____/ /   / /   /  _/ __ \\/_  __/\n"
                + "  / __/ / /   / /    / // / / / / /\n"
                + " / /___/ /___/ /____/ // /_/ / / /\n"
                + "/_____/_____/_____/___/\\____/ /_/\n";
        String intro = " Hello! I'm\n" + logo
                + " What can I do for you?\n";
        Say("");
        Say(intro);
    }

    private static void ByeSay() {
        String bye = " Bye. Hope to see you again soon!\n";
        Say(bye);
    }
}
