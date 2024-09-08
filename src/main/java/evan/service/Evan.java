package evan.service;

import evan.command.Command;
import evan.exception.FileCreationException;
import evan.exception.InvalidUserInputException;
import evan.exception.LoadingException;
import evan.exception.NoSuchTaskException;
import evan.exception.SavingException;

/**
 * The main class for the Evan chatbot.
 */
public class Evan {
    private static final String COMMAND_LIST = """
            Commands supported by Evan:
            - list
            - todo <description>
            - deadline <description> /by <when>
            - event <description> /from <start> /to <end>
            - mark <task_number>
            - unmark <task_number>
            - delete <task_number>
            - find <description>""";
    private final UserInputParser userInputParser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiates an Evan object.
     *
     * @param filePath File path of the .txt file that will store Evan's data.
     */
    public Evan(String filePath) {
        userInputParser = new UserInputParser();

        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (FileCreationException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (LoadingException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input User's chat message.
     * @return Evan's response.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command command = userInputParser.parse(input);
            response = command.execute(taskList);
            storage.save(taskList); // Save the task list after every update
        } catch (InvalidUserInputException e) {
            response = e.getMessage() + "\n" + COMMAND_LIST;
        } catch (NoSuchTaskException | SavingException e) {
            response = e.getMessage();
        }
        return response;
    }
}
