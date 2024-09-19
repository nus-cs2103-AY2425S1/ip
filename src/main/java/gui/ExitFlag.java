package gui;

public class ExitFlag {
    private boolean flag;

    public ExitFlag() {
        this.flag = false;
    }

    public void raise() {
        this.flag = true;
    }

    public boolean getFlag() {
        return this.flag;
    }
}
