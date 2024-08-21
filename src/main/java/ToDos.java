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

    protected static ToDos createToDo(String text) throws NoDescriptionException {
        String description = text.substring(4).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }
        return new ToDos(description);
    }
}
