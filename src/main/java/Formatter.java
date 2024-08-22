public class Formatter {
    public static final String DIVIDER = "____________________________________________________________\n";
    public static String formatBotMessage(String msg) {
        return (DIVIDER + msg + "\n" + DIVIDER).indent(4);
    }
}
