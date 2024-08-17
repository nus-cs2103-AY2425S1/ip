public class Todo extends Task {
    private String type = "[T]";

    private static String modifyDescription(String des) throws TaskException {
        if (des.length() == 0) {
            throw new TaskException("OH NO!!! The description of Todo cannot be empty!");
        }
        return des;
    }

    public Todo(String description) throws TaskException {
        super(modifyDescription(description));
    }

    @Override
    public String getType() {
        return this.type;
    }
}
