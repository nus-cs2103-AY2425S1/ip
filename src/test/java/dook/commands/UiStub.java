package dook.commands;

import dook.ui.Ui;

public class UiStub extends Ui {
    public UiStub() {

    }
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String EXIT = "Bye. Hope to see you again soon!\n" + SEPARATOR;
    private static final String GREETING = "Hello! I'm Dook.Dook\nWhat can I do for you?\n" + SEPARATOR;

    public void separate() {
        System.out.println(SEPARATOR);
    }

    public void greet() {
        System.out.println(SEPARATOR);
        System.out.println(GREETING);
    }

    public void exit() {
        System.out.println(SEPARATOR);
        System.out.println(EXIT);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void errorMessage(String errorMessage) {
        System.out.println(SEPARATOR);
        System.out.println(errorMessage);
        System.out.println(SEPARATOR);
    }
}
