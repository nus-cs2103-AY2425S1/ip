package yappingbot.ui.cli;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;
import yappingbot.ui.Ui;

/**
 * User-interface class for mainly outputting different texts and decorating them to represent
 * the bot talking.
 */
public class UiCli implements Ui {
    private static final String PREFIX = " |  ";
    private static final String PREFIX_EMPTY = " |";
    private static final int MAX_STRING_LENGTH = 72;
    private static final int MIN_SINGLE_STRING_LEN = MAX_STRING_LENGTH * 2 / 3;
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
        assert false : "NOT IMPLEMENTED";
        return "";
    }

    @Override
    public boolean hasOutputLines() {
        // System.out.print directly interfaces with Java's STDOUT.
        // We do not need to implement any system to collect and output any STDOUT streams.
        assert false : "NOT IMPLEMENTED";
        return false;
    }

    // INPUT methods

    @Override
    public void pushInputLine(String input) {
        // Scanner with System.in directly interfaces with Java's STDIN
        // We do not need to implement any system to collect inputs and stream to bot.
        assert false : "NOT IMPLEMENTED";
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
     * Only for single-line strings. All newlines in passed String will be stripped.
     * Use {@link UiCli#quoteMultilineText} for decorating multiline text.
     *
     * @param line the message to be decorated.
     * @return String of the newly decorated message.
     */
    private String quoteSinglelineText(String line) {
        // handle empty
        if (line == null || line.trim().isEmpty()) {
            return PREFIX_EMPTY + "\n";
        }

        // remove all newlines
        String escapedLine = line.replaceAll("\n", "").trim();

        // handle normal strings
        if (escapedLine.length() <= MAX_STRING_LENGTH) {
            return PREFIX + escapedLine + "\n";
        }

        // handle strings too long
        int softWrapIndex = escapedLine.lastIndexOf(" ", MAX_STRING_LENGTH);
        // (1) space found and the resulting broken line is not too short
        // (2) space found but resulting broken line is very short
        // (3) no space found
        if (softWrapIndex < MIN_SINGLE_STRING_LEN) {
            softWrapIndex = MAX_STRING_LENGTH;
        }

        return quoteSinglelineText(escapedLine.substring(0, softWrapIndex))
               + quoteSinglelineText(escapedLine.substring(softWrapIndex));
    }

    /**
     * Decorates the given string, to denote that it is the bot's output.
     * This accepts multiple lines.
     *
     * @param text String message to be decorated.
     * @return String of the newly decorated message.
     */
    private String quoteMultilineText(String text) {
        return text == null ? quoteSinglelineText("")
                            : Arrays.stream(text.split("\n"))
                                    .map(this::quoteSinglelineText)
                                    .collect(Collectors.joining());
    }
}

