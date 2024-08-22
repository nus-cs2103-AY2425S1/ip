public abstract class Task {
    private final String description;
    protected enum Status {
        DONE,NOT_DONE,URGENT
    }
    private Status status;

    /** Private constructor for a Task */
    public Task(String description) {
        this.description = description;
        this.status = Status.NOT_DONE;
    }
    protected void updateStatus(Task.Status input) {
        StringBuilder changelog = new StringBuilder();
        changelog.append("Status updated successfully: \nOld: ");
        changelog.append(this);
        switch(input) {
            case DONE -> status = Status.DONE;
            case NOT_DONE -> status = Status.NOT_DONE;
            case URGENT -> status = Status.URGENT;
        }
        changelog.append("\nNew: ");
        changelog.append(this);
        System.out.println(changelog);
    }

    private char getStatusIcon() {
        return switch (status) {
            case DONE -> 'X';
            case NOT_DONE -> ' ';
            case URGENT -> '!';
        };
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("[%c] %s", getStatusIcon(), description));
        return str.toString();
    }
}
