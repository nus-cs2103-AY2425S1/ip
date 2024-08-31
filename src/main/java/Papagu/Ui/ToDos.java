package Papagu.Ui;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toFile() {
        return "T | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
