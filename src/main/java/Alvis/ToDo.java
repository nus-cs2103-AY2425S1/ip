package Alvis;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }
    
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
    
    public String toSaveFormat() {
        return String.format("T_%s_%s", isDone ? "1" : "0", getDesc());
    }
}
