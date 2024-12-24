package colress.testutil;

import colress.Chatbot;
import colress.UiBeginner;
import colress.tasklist.TaskList;


/**
 * A stub class for the UiBeginner class for testing purposes.
 */
public class UiBeginnerStub extends UiBeginner {

    public UiBeginnerStub() {
        super(new ColressStub());
    }

    public UiBeginnerStub(Chatbot colress) {
        super(colress);
    }

    @Override
    public String processInput(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public String processCommand(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public String processKeyword(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public String processTaskType(String input) {
        return "This method should not be called";
    }

    @Override
    public String processDescription(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public String processDate(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public String processStartTime(String input) {
        return "This method should not be called";
    }

    @Override
    public String processEndTime(String input, TaskList taskList) {
        return "This method should not be called";
    }

    @Override
    public String processTaskNumber(String input, TaskList taskList) {
        return "This method should not be called";
    }
}
