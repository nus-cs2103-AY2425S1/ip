class ToDo extends Task {
    ToDo(String taskString) {
        super(taskString);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
