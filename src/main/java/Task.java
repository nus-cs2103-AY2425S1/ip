class Task {
    private boolean completed = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void setCompleted(boolean status) {
        this.completed = status;
    }

    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + description;
    }
}
