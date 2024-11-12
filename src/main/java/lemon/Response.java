package lemon;

import lemon.gui.LemonFxWindow;

/**
 * Class used to handle the response of {@link lemon.gui.LemonFx}
 */
public class Response {
    private LemonFxWindow window = null;
    private StringBuilder output = new StringBuilder();

    /**
     * Add String to the output to be printed
     * @param output String that is to be printed
     */
    public void addOutput(String output) {
        this.output.append(output);
    }

    /**
     * Add String to the output to be printed along with a newline character
     * @param output String that is to be printed
     */
    public void addOutputln(String output) {
        this.output.append(output).append("\n");
    }

    /**
     * Print out the string as a {@link lemon.gui.DialogBox}
     * Clears the output String afterward
     */
    public void print() {
        assert window != null;
        window.addLemonDialogBox(output.toString());
        output = new StringBuilder();
    }

    /**
     * Set {@link LemonFxWindow} that the response needs a ref to
     * Required for initialization
     * @param window {@link LemonFxWindow} reference
     */
    public void setWindow(LemonFxWindow window) {
        this.window = window;
    }

    /**
     * Disable {@link LemonFxWindow} window
     */
    public void disableWindow() {
        this.window.disable();
    }
}
