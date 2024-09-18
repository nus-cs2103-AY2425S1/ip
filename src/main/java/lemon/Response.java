package lemon;

import lemon.gui.LemonFxWindow;

public class Response {
    private LemonFxWindow window = null;
    private StringBuilder output = new StringBuilder();
    public void addOutput(String output) {
        this.output.append(output);
    }
    public void addOutputln(String output) {
        this.output.append(output).append("\n");
    }

    public void print() {
        //assert window != null;
        window.addResponse(output.toString());
        output = new StringBuilder();
    }

    public void setWindow(LemonFxWindow window) {
        this.window = window;
    }

    public void disableWindow() {
        this.window.disable();
    }
}
