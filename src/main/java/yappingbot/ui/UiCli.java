package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;

/**
 * User-interface class for mainly outputting different texts and decorating them to represent
 * the bot talking.
 */
public class UiCli extends Ui {
    private static final String PREFIX = "|  ";
    private static final String PREFIX_EMPTY = "|";


    /**
     * Decorates the given string, to denote that it is the bot's output.
     *
     * @param line the message to be decorated.
     * @return String of the newly decorated message.
     */
    private static String quoteSinglelineText(String line) {
        if (line == null || line.trim().isEmpty()) {
            return PREFIX_EMPTY + "\n";
        } else {
            return PREFIX + line.replaceAll("\n", "") + "\n";
        }
    }

    /**
     * Decorates the given string, to denote that it is the bot's output.
     * This accepts a StringBuilder for efficiently dealing with multiple lines.
     *
     * @param line String to be decorated.
     * @param sb StringBuilder that the decorated line will be appended to.
     */
    protected static void quoteSinglelineText(String line, StringBuilder sb) {
        if (line.trim().isEmpty()) {
            sb.append(PREFIX_EMPTY);
        } else {
            sb.append(PREFIX);
            sb.append(line.replaceAll("\n", ""));
        }
        sb.append("\n");
    }


    /**
     * Decorates the given string, to denote that it is the bot's output.
     * This accepts multiple lines.
     *
     * @param text String message to be decorated.
     * @return String of the newly decorated message.
     */
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

    /**
     * Prints error message from a YappingBotException with decorations denoting output is from bot.
     *
     * @param e YappingBotException or its derived classes with an embedded message.
     */
    public static void printError(YappingBotException e) {
        printError(e.getErrorMessage());
    }


    /**
     * Prints error message from a YappingBotException with decorations denoting output is from bot.
     *
     * @param msg String error message to be outputted.
     */
    public static void printError(String msg) {
        System.out.print(quoteMultilineText(msg));
    }

    /**
     * Prints any String, with decorations denoting output is from bot.
     *
     * @param msg String message to be printed.
     */
    public static void print(String msg) {
        System.out.print(quoteMultilineText(msg));
    }


    /**
     * Prints any String in a line, with decorations denoting output is from bot.
     * Any newlines will be removed, rendering the message in a single line
     *
     * @param msg String message to be printed.
     */
    public static void println(String msg) {
        System.out.print(quoteSinglelineText(msg));
    }

}

