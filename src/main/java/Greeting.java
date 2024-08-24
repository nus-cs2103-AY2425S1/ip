public class Greeting {
    public static void greetingMsg() {
        String init = SigmaBot.HR_LINE_IN
                + "\t" + "Hello! I'm SigmaBot\n"
                + "\t" + "What can I do for you?\n"
                + SigmaBot.HR_LINE;
        System.out.println(init);
    }

    public static void byeMsg() {
        String msg = SigmaBot.HR_LINE
                + "\t" + "Bye. Hope to see you again soon!\n"
                + SigmaBot.HR_LINE_OUT;
        System.out.println(msg);
    }
}
