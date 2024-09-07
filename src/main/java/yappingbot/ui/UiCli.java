package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;

/**
 * User-interface class for mainly outputting different texts and decorating them to represent
 * the bot talking.
 */
public class UiCli implements Ui {
    private static final String PREFIX = "|  ";
    private static final String PREFIX_EMPTY = "|";

    @Override
    public void printError(YappingBotException e) {
        printError(e.getErrorMessage());
    }

    @Override
    public void printError(String msg) {
        System.out.print(quoteMultilineText(msg));
    }

    @Override
    public boolean hasNextLine() {
        return false;
    }

    @Override
    public String getNextLine() throws YappingBotIoException {
        return "";
    }

    @Override
    public void print(String msg) {
        System.out.print(quoteMultilineText(msg));
    }

    @Override
    public void println(String msg) {
        System.out.print(quoteSinglelineText(msg));
    }

    @Override
    public void printf(String formattedString, Object... o) {
        System.out.print(quoteMultilineText(String.format(formattedString, o)));
    }

    /**
     * Decorates the given string, to denote that it is the bot's output.
     *
     * @param line the message to be decorated.
     * @return String of the newly decorated message.
     */
    private String quoteSinglelineText(String line) {
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
    private void quoteSinglelineText(String line, StringBuilder sb) {
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
    private String quoteMultilineText(String text) {
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
}

