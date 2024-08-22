import java.util.List;

public class Formatter {
    public static final String DIVIDER = "____________________________________________________________\n";
    public static String formatBotMessage(String msg) {
        return (DIVIDER + msg + "\n" + DIVIDER).indent(4);
    }
    public static String formatList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            String s = (i + 1) + ". " + list.get(i) + "\n";
            sb.append(s);
        }
        return sb.toString();
    }
}
