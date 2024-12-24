package colress.testutil;

import colress.Chatbot;

/**
 * A stub class for the Colress class for testing purposes.
 */
public final class ColressStub extends Chatbot {

    public ColressStub() {
        super("");
    }

    public ColressStub(boolean hasError, boolean isBeginnerMode) {
        super(new CorrectFormatStorageStub(), new CorrectFormatTaskListStub(), hasError, isBeginnerMode);
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
