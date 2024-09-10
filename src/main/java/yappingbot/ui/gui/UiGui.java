package yappingbot.ui.gui;

import java.util.ArrayDeque;
import java.util.Deque;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;
import yappingbot.ui.Ui;

/**
 * UI implementation for javafx GUI.
 * Abides by the UI contract that YappingBot uses.
 */
public class UiGui implements Ui {

    ArrayDeque<String> inputBuffer = new ArrayDeque<>();
    ArrayDeque<String> outputBuffer = new ArrayDeque<>();
    private boolean programClose = false;

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
    public boolean hasInputLines() {
        while (inputBuffer.isEmpty()) {
            Thread.onSpinWait();
            if (programClose) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getNextInputLine() throws YappingBotIoException {
        try {
            return inputBuffer.poll();
        } catch (Exception e) {
            throw new YappingBotIoException(e.getMessage());
        }
    }

    @Override
    public boolean hasOutputLines() {
        while (outputBuffer.isEmpty()) {
            Thread.onSpinWait();
            if (programClose) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getNextOutputLine() throws YappingBotIoException {
        try {
            return outputBuffer.poll();
        } catch (Exception e) {
            throw new YappingBotIoException(e.getMessage());
        }
    }

    /**
     * Pushes String input into the input buffer to be processed by Yappingbot.
     *
     * @param input String to be inputted
     */
    public void pushInputLine(String input) {
        inputBuffer.add(input);
    }


    /**
     * Signals UiGui to close all inputs and outputs.
     *
     * @param programClose Boolean of whether the programme has ended
     */
    public void setProgramClose(boolean programClose) {
        this.programClose = programClose;
    }
}
