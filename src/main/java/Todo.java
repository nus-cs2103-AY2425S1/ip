class Todo extends Task {
    Todo(String task) {
        super(task);
    }

    Todo(int status, String task) {
        super(status, task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    String toSaveAsString() {
        return "T/" + super.toSaveAsString();
    }
}