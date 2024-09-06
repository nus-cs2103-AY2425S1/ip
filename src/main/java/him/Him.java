package him;

import command.Command;
import exceptions.HimException;
import task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point for Him chatbot.
 *
 * @author IsaacPangTH
 */
public class Him {

    private TaskList list = new TaskList();
    private final Storage storage = new Storage();

    public String getResponse(String input) {
        try {
            list = storage.loadTaskList();
        } catch (FileNotFoundException e) {
            try {
                storage.initStorage();
            } catch (IOException ex) {
                return him.Ui.say("Error initializing storage");
            }
        } catch (HimException e) {
            return him.Ui.sayLoadingFailure();
        }

        try {
            Command c = Parser.parseUserInput(input);
            String message = c.execute(list);
            storage.saveTaskList(list);
            return message;
        } catch (IOException e) {
            return him.Ui.showSaveFailure();
        } catch (HimException e) {
            return him.Ui.say(e.getMessage().split("\n"));
        }

    }
}
