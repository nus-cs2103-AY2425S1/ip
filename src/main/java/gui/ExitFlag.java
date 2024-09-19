package gui;

public class ExitFlag {
    private boolean isExit;

    public ExitFlag() {
        this.isExit = false;
    }

    public void raise() {
        this.isExit = true;
    }

    public boolean getFlag() {
        return this.isExit;
    }
}
