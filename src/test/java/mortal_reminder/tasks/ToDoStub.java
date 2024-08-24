package mortal_reminder.tasks;

public class ToDoStub extends ToDo {

    public ToDoStub(String description) {
        super(description);
    }

    @Override
    public String convertToFileFormat() {
        return description;
    }
}
