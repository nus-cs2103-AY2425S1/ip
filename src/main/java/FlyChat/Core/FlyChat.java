package flychat.core;

import java.util.InputMismatchException;

/**
 * Contains the main methods and components for FlyChat.
 */
public class FlyChat {

    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser();

    public FlyChat() {
        assert ui != null : "Ui object is not initialized";
        assert storage != null : "Storage object is not initialized";
        assert taskList != null : "TaskList object is not initialized";
        assert parser != null : "Parser object is not initialized";
    }

    public void startUp() {
        storage.findSaveFile("./data/save.txt");
        storage.loadSaveFile(taskList);
    }

    public static String greetUser() {
        return Ui.greetUser();
    }

    public String shutDown() {
        return ui.bye();
    }

    public String getResponse(String inputString) {
        assert inputString != null && !inputString.isEmpty() : "Input string is null or empty";

        try {
            return processCommands(inputString);
        } catch (InputMismatchException e) {
            return ui.announceString("I'm not sure what task you want me to do :((");
        }

    }

    private String processCommands(String inputString) throws InputMismatchException {
        assert inputString != null && !inputString.isEmpty() : "Input string is null or empty";
        String finalString;

        //when user types "list", list of tasks is returned
        if (parser.parseCommand(inputString).equals("list")) {
            finalString = ui.announceString(taskList.announceItems());
        //when user types "mark [num]", the task with index num is marked as done
        } else if (parser.parseCommand(inputString).equals("mark")) {
            try {
                finalString = ui.announceString(taskList.mark(parser.getTargetTaskIndex(inputString)));
            } catch (IndexOutOfBoundsException e) {
                finalString = ui.announceString("Please ensure that you typed the correct task number");
            }
        //when user types "unmark [num]", the task with index num is marked as not done
        } else if (parser.parseCommand(inputString).equals("unmark")) {
            try {
                finalString = ui.announceString(taskList.unmark(parser.getTargetTaskIndex(inputString)));
            } catch (IndexOutOfBoundsException e) {
                finalString = ui.announceString("Please ensure that you typed the correct task number");
            }
        //when user types todo/deadline/event at the start, a new corresponding task is created
        } else if (parser.parseCommand(inputString).equals("todo")
                || parser.parseCommand(inputString).equals("deadline")
                || parser.parseCommand(inputString).equals("event")) {
            finalString = ui.announceString(taskList.addTask(inputString));
        //when user types "delete [num]", the task with the index num is removed
        } else if (parser.parseCommand(inputString).equals("delete")) {
            try {
                finalString = ui.announceString(taskList.deleteTask(parser.getTargetTaskIndex(inputString)));
            } catch (IndexOutOfBoundsException e) {
                finalString = ui.announceString("Please ensure that you typed the correct task number");
            }
        } else if (parser.parseCommand(inputString).equals("find")) {
            finalString = ui.announceString(taskList.find(parser.getKeyWord(inputString)));
        } else if (parser.parseCommand(inputString).equals("bye")) {
            finalString = ui.bye();
        } else {
            throw new InputMismatchException("Invalid input command");
        }
        storage.writeToSave(taskList.formatSaveString());
        return finalString;
    }

}
