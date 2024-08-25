class Todo extends Task {
    Todo(String task) {
        super(task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}