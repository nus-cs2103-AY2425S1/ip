package sentinel.ui;

public class Ui {
    public static String MESSAGE_LINE = "---------------------------------------";

    public void output(String message) {
        System.out.println(MESSAGE_LINE);
        System.out.println(message);
        System.out.println(MESSAGE_LINE);
    }

    public void showError(String errorMessage) {
        output("ERROR: " + errorMessage);
    }
}
