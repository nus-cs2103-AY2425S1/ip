class Todo extends Task {
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toSaveFormat() {
        return "T | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | ";
    }
}
