package oyster;

public class Ui {
    private int LINE_LENGTH = 50;

    /**
     * Displays a given Message.
     *
     * @param message The String message to be displayed on the terminal.
     */
    public void output(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Displays a given Message array.
     *
     * @param messages An array of String to be displayed on the terminal in its own lines.
     */
    public void output(String[] messages) {
        drawLine();
        for (String message : messages) {
            System.out.println(message);
        }
        drawLine();
    }

    /**
     * Displays a horizontal line across in the terminal.
     */
    public void drawLine() {
        System.out.println("_".repeat(LINE_LENGTH));
    }
}
