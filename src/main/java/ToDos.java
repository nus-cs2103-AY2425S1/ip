public class ToDos extends Tasks {
    public ToDos(String description) {
        super(description);
    }


    @Override
    protected String typeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return typeIcon() + super.toString();
    }

}
