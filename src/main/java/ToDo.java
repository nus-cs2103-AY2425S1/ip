public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[T]%s",super.toString());
    }

    @Override
    public String toSaveString() {
        if (isDone) {
            return String.format("T | %d | %s",1,this.getDescription());
        } else {
            return String.format("T | %d | %s",0,this.getDescription());
        }
    }
}
