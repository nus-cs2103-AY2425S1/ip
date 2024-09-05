package mortalreminder.tasks;

import mortalreminder.errorhandling.MortalReminderException;

public class ToDoStub extends ToDo {

    public ToDoStub(String description) throws MortalReminderException {
        super(description);
    }

    @Override
    public String convertToFileFormat() {
        return description;
    }
}
