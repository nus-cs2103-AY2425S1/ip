package ui;

public interface Ui {
    // Inspired by https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/project.html
    public void showWelcome();
    public Message readMessage();
    public void showError(String message);
    public void showGoodbye();
}
