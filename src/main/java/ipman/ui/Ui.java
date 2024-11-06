package ipman.ui;

/**
 * Represents a particular user interface which this program can display
 * messages to.
 */
public interface Ui {
    // Inspired by https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/project.html
    void showMessage(String message);
    void showMessageFormat(String message, Object... args);
    void showError(String message);
}
