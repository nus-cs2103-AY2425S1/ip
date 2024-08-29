public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toStore(){
        return "T/" + super.getStatus() + "/" + description;
    }
}
