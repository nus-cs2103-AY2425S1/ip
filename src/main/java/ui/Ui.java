package ui;

public class Ui {
    public static String quoteSinglelineText(String line) {
        if (line == null || line.trim().isEmpty()) {
            return "\n |";
        } else {
            return String.format("\n |  %s\n", line);
        }
    }
    public static void quoteSinglelineText(String line, StringBuilder sb) {
        if (line.trim().isEmpty()) {
            sb.append("\n |");
        } else {
            sb.append("\n |  ");
            sb.append(line);
        }
    }
    public static String quoteMultilineText(String text) {
        // annotates text with pipe to denote speech from bot
        if (text == null) {
            return quoteSinglelineText("");
        }
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            quoteSinglelineText(l, sb);
        }
        sb.append("\n"); // pad the end with another newline
        return sb.toString();
    }
}
