public class InputLog {
    private String[] log;
    private int numInputs;

    InputLog(String[] newLog) {
        this.log = newLog;
        this.numInputs = 0;
    }

    InputLog() {
        this(new String[100]);
    }

    public void addInput(String input) {
        this.log[this.numInputs] = input;
        this.numInputs++;
        if (this.numInputs == this.log.length) {
            this.doubleLogSize();
        }
    }

    private void doubleLogSize() {
        int newLogSize = 2 * this.log.length;
        String[] newLog = new String[newLogSize];
        System.arraycopy(this.log, 0, newLog, 0, this.log.length);
        this.log = newLog;
    }

    private String getInput(int pos) {
        if (pos < 0 || pos > (this.numInputs - 1)) {
            return "";
        }
        return this.log[pos];
    }

    public String lastInput() {
        return this.getInput(this.numInputs - 1);
    }

    protected String[] getLog() {
        String[] truncLog = new String[this.numInputs];
        for (int i = 0; i < this.numInputs; i++) {
            truncLog[i] = this.getInput(i);
        }
        return truncLog;
    }
}
