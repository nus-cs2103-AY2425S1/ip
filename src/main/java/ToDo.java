class ToDo extends Task {
    ToDo(String taskString) {
        super(taskString);
    }

    @Override
    public String toFileString()  {
        return "T | " + (done ? "1": "0") + " | " + taskString;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
