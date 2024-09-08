package yappingbot.ui;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotIoException;

public class UiGui implements Ui {
    @Override
    public void print(String s) {

    }

    @Override
    public void println(String s) {

    }

    @Override
    public void printf(String formattedString, Object... o) {

    }

    @Override
    public void printError(String e) {

    }

    @Override
    public void printError(YappingBotException e) {

    }

    @Override
    public void printfError(String formattedString, Object... o) {

    }

    @Override
    public boolean hasNextLine() {
        return false;
    }

    @Override
    public String getNextLine() throws YappingBotIoException {
        return "";
    }
}
