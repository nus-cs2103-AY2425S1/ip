public class Echo {
    public static void echoMsg(String input) {
        String msg = SigmaBot.HR_LINE_IN
                + "\t" + "Echoing:" + input
                + "\n"
                + SigmaBot.HR_LINE_OUT;
        System.out.println(msg);
    }
}
