package yappingbot.ui;

import java.util.Scanner;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;

/**
 * User-interface class for mainly outputting different texts and decorating them to represent
 * the bot talking.
 */
public class UiCli implements Ui {
    private static final String PREFIX = " |  ";
    private static final String PREFIX_EMPTY = " |";
    private static final int MAX_STRING_LENGTH = 72;
    private final Scanner scanner;

    public UiCli() {
        this.scanner = new Scanner(System.in);
    }

    // OUTPUT methods

    @Override
    public void print(String msg) {
        System.out.print(quoteMultilineText(msg) + "\n");
    }

    @Override
    public void println(String msg) {
        System.out.println(quoteSinglelineText(msg));
    }

    @Override
    public void printf(String formattedString, Object... o) {
        print(String.format(formattedString, o));
    }

    @Override
    public void printError(YappingBotException e) {
        printError(e.getErrorMessage());
    }

    @Override
    public void printError(String msg) {
        // TODO: make different from stdout
        System.out.print(quoteMultilineText(msg));
    }

    @Override
    public void printfError(String formattedString, Object... o) {
        printError(String.format(formattedString, o));
    }

    @Override
    public String getNextOutputLine() {
        // System.out.print directly interfaces with Java's STDOUT.
        // We do not need to implement any system to collect and output any STDOUT streams.
        throw new YappingBotIoException("NOT IMPLEMENTED");
    }

    @Override
    public boolean hasOutputLines() {
        // System.out.print directly interfaces with Java's STDOUT.
        // We do not need to implement any system to collect and output any STDOUT streams.
        throw new YappingBotIoException("NOT IMPLEMENTED");
    }

    // INPUT methods

    @Override
    public void pushInputLine(String input) {
        // Scanner with System.in directly interfaces with Java's STDIN
        // We do not need to implement any system to collect inputs and stream to bot.
        throw new YappingBotIoException("NOT IMPLEMENTED");
    }

    @Override
    public boolean hasInputLines() {
        return scanner.hasNext();
    }

    @Override
    public String getNextInputLine() throws YappingBotIoException {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw new YappingBotIoException(e.getMessage());
        }
    }

    // HELPER methods

    /**
     * Decorates the given string, to denote that it is the bot's output.
     *
     * @param line the message to be decorated.
     * @return String of the newly decorated message.
     */
    private String quoteSinglelineText(String line) {
        if (line == null || line.trim().isEmpty()) {
            return PREFIX_EMPTY + "\n";
        } else if (line.length() >= MAX_STRING_LENGTH) {
            StringBuilder sb = new StringBuilder();
            sb.append(PREFIX);
            int p = 0;
            int nextBoundary = 0;
            while (p < line.length()) {
                nextBoundary = line.indexOf(' ', p + 1);
                if (nextBoundary < 0) {
                    nextBoundary = line.length();
                }

                if ((p % MAX_STRING_LENGTH) + (nextBoundary - p) > MAX_STRING_LENGTH) {
                    nextBoundary = p + (MAX_STRING_LENGTH - (p % MAX_STRING_LENGTH));
                }

                if (nextBoundary < p) {
                    break;
                }

                sb.append(line, p, nextBoundary);
                p = nextBoundary;
                if (p % MAX_STRING_LENGTH == 0) {
                    sb.append("\n").append(PREFIX);
                }
            }
            return sb.toString();
        } else {
            return PREFIX + line.replaceAll("\n", "") + "\n";
        }
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
            sb.append(quoteSinglelineText(l));
        }
        return sb.toString();
    }
}

