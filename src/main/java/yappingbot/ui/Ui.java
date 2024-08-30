package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;

public class Ui {
    private static String quoteSinglelineText(String line) {
        if (line == null || line.trim().isEmpty()) {
            return "\n |";
        } else {
            return String.format("\n |  %s\n", line);
        }
    }
    public static void quoteSinglelineText(String line, StringBuilder sb) {
        String prefix = "\n|  ";
        if (sb.isEmpty()) {
            prefix = "|  ";
        }
        if (line.trim().isEmpty()) {
            sb.append(prefix.replaceAll(" ",""));
        } else {
            sb.append(prefix);
            sb.append(line.replaceAll("\n", ""));
        }
    }
    private static String quoteMultilineText(String text) {
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

    public static void printError(YappingBotException e) {
        printError(e.getErrorMessage());
    }
    public static void printError(String msg) {
        System.out.println(Ui.quoteMultilineText(msg));
    }

    public static void print(String msg) {
        System.out.println(Ui.quoteMultilineText(msg));
    }
    public static void println(String msg) {
        System.out.println(Ui.quoteSinglelineText(msg));
    }

}

