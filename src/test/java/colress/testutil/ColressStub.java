package colress.testutil;

import colress.Chatbot;

/**
 * A stub class for the Colress class for testing purposes.
 */
public final class ColressStub extends Chatbot {

    public ColressStub() {
        super(new StorageStub(), new TaskListStub());
    }

    public ColressStub(boolean hasError, boolean isBeginnerMode) {
        super(new StorageStub(), new TaskListStub(), hasError, isBeginnerMode);
    }

    @Override
    public String loadTasks() {
        return "This method should not be called";
    }

    @Override
    public String getResponse(String input) {
        return "This method should not be called";
    }
}
