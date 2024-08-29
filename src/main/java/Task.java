abstract public class Task {
    String description;
    boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public boolean markAsDone() {
        if (this.status) {
            return false;

        } else {
            this.status = true;
            return true;
        }
    }

    public boolean unmarkAsUndone() {
        if (!this.status) {
            return false;

        } else {
            this.status = false;
            return true;
        }
    }

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public abstract  String toFileString();
}
