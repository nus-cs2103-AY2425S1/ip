package yappingbot.ui.gui;

import java.util.ArrayDeque;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;
import yappingbot.ui.Ui;

/**
 * UI implementation for javafx GUI.
 * Abides by the UI contract that YappingBot uses.
 */
public class UiGui implements Ui {

    final ArrayDeque<String> inputBuffer = new ArrayDeque<>();
    final ArrayDeque<String> outputBuffer = new ArrayDeque<>();
    private boolean streamsClosed = false; // When programme ends, this will be set true.

    // OUTPUT methods

    @Override
    public void print(String s) {
        outputBuffer.add(s);
    }

    @Override
    public void println(String s) {
        outputBuffer.add(s);
    }

    @Override
    public void printf(String formattedString, Object... o) {
        outputBuffer.add(String.format(formattedString, o));
    }

    @Override
    public void printError(String e) {
        println(e);
    }

    @Override
    public void printError(YappingBotException e) {
        println(e.getErrorMessage());
    }

    @Override
    public void printfError(String formattedString, Object... o) {
        printf(formattedString, o);
    }

    @Override
    public boolean hasOutputLines() {
        // busywait until there is output to be shown to the user
        while (outputBuffer.isEmpty()) {
            Thread.onSpinWait();
            if (streamsClosed) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getNextOutputLine() throws YappingBotIoException {
        // get output to be shown to the user
        try {
            return outputBuffer.poll();
        } catch (Exception e) {
            throw new YappingBotIoException(e.getMessage());
        }
    }

    // INPUT methods

    /**
     * Pushes String input into the input buffer to be processed by Yappingbot.
     *
     * @param input String to be inputted
     */
    @Override
    public void pushInputLine(String input) {
        // get output to be shown to the user
        inputBuffer.add(input);
    }

    @Override
    public boolean hasInputLines() {
        // busywait until there is input from user to be processed
        while (inputBuffer.isEmpty()) {
            Thread.onSpinWait();
            if (streamsClosed) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getNextInputLine() throws YappingBotIoException {
        // get input from user to be processed
        try {
            return inputBuffer.poll();
        } catch (Exception e) {
            throw new YappingBotIoException(e.getMessage());
        }
    }

    // HELPER methods

    /**
     * Signals UiGui to close all inputs and outputs.
     *
     * @param streamsClosed Boolean of whether the programme has ended
     */
    public void setProgramClose(boolean streamsClosed) {
        this.streamsClosed = streamsClosed;
    }
}
