package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;

public class Ui {
    private static final String PREFIX = "|  ";
    private static final String PREFIX_EMPTY = "|";
    private static String quoteSinglelineText(String line) {
        if (line == null || line.trim().isEmpty()) {
            return PREFIX_EMPTY + "\n";
        } else {
            return PREFIX + line.replaceAll("\n","") + "\n";
        }
    }
    public static void quoteSinglelineText(String line, StringBuilder sb) {
        if (line.trim().isEmpty()) {
            sb.append(PREFIX_EMPTY);
        } else {
            sb.append(PREFIX);
            sb.append(line.replaceAll("\n", ""));
        }
        sb.append("\n");
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
        return sb.toString();
    }

    public static void printError(YappingBotException e) {
        printError(e.getErrorMessage());
    }
    public static void printError(String msg) {
        System.out.print(Ui.quoteMultilineText(msg));
    }

    public static void print(String msg) {
        System.out.print(Ui.quoteMultilineText(msg));
    }
    public static void println(String msg) {
        System.out.print(Ui.quoteSinglelineText(msg));
    }

}

