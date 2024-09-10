package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;

/**
 * Abstract user-interface class for mainly outputting different texts and decorating them to
 * represet the bot talking.
 */
public interface Ui {

    /**
     * Prints a String to output.
     *
     * @param s String to be outputted.
     */
    void print(String s);

    /**
     * Prints a String line to output, followed by a newline.
     *
     * @param s String to be outputted as single line.
     */
    void println(String s);

    /**
     * Prints a String line, with formatting support.
     *
     * @param formattedString String that uses `String.format()` style formatting.
     * @param o Objects that will be formatted into the format string before printing.
     */
    void printf(String formattedString, Object ... o);

    /**
     * Prints a String error to output.
     *
     * @param e String error to be outputted.
     */
    void printError(String e);

    /**
     * Prints an error to output.
     *
     * @param e YappingBotException error to be outputted.
     */
    void printError(YappingBotException e);

    /**
     * Prints a String error, with formatting support.
     *
     * @param formattedString String that uses `String.format()` style formatting.
     * @param o Objects that will be formatted into the format string before printing.
     */
    void printfError(String formattedString, Object ... o);


    /**
     * Peeks into input buffer and returns true if a next line is available, or <b>blocks</b>
     * until a line is present in the input buffer, or the input stream is closed.
     *
     * @return boolean true if line is available, or false if input is closed.
     */
    boolean hasInputLines();


    /**
     * Pops the next line that is available in input buffer, or else throws exception.
     *
     * @return String of next line in input buffer.
     */
    String getNextInputLine();


    /**
     * Returns the output lines in the buffer to be printed.
     *
     * @return String to be outputted or printed:
     */
    String getNextOutputLine() throws YappingBotIoException;

    /**
     * Peeks into output buffer and returns true if a next line is available, or <b>blocks</b>
     * until a line is present in the output buffer, or the output stream is closed.
     *
     * @return boolean true if line is available, or false if output is closed.
     */
    boolean hasOutputLines();
}

