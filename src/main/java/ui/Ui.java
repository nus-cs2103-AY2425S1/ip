package ui;

import commands.Command;

public interface Ui {
    // Inspired by https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/project.html
    void showWelcome();
    Command readMessage();
    void showError(String message);
    void showGoodbye();
}
