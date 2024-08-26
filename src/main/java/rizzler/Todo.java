package rizzler;

class Todo extends Task {
    Todo(String name) {
        super(name);
    }
    Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T][" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    @Override
    String toSaveState() {
        return "T" + "/" + (this.isDone ? "1" : "0") + "/" + this.name + "\n";
    }
}
