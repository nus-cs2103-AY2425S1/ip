package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;

/**
 * Abstract user-interface class for mainly outputting different texts and decorating them to
 * represet the bot talking.
 */
public abstract class Ui {

    /**
     * Prints a String to output.
     *
     * @param s String to be outputted.
     */
    public abstract void print(String s);

    /**
     * Prints a String line to output, followed by a newline.
     *
     * @param s String to be outputted as single line.
     */
    public abstract void println(String s);

    /**
     * Prints a String error to output.
     *
     * @param e String error to be outputted.
     */
    public abstract void printError(String e);

    /**
     * Prints an error to output.
     *
     * @param e Exception error to be outputted.
     */
    public abstract void printError(Exception e);
}

