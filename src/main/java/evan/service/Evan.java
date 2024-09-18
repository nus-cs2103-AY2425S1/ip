package evan.service;

import evan.command.Command;
import evan.command.HelpCommand;
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
            - find <description>
            - help""";
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

            // Attempt to create a TaskList with the currently saved tasks
            taskList = new TaskList(storage.load());
        } catch (FileCreationException e) {
            System.out.println(e.getMessage());

            // A FileCreationException means that the tasks.txt save file could not be created
            // This is a fatal error since no tasks can be created and saved
            // Hence, the program should be terminated
            System.exit(1);
        } catch (LoadingException e) {
            System.out.println(e.getMessage());

            // Create a new, empty TaskList since an error occurred while trying to load the saved tasks
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

        assert (userInputParser != null) : "userInputParser should be non-null";
        assert (storage != null) : "storage should be non-null";
        assert (taskList != null) : "taskList should be non-null";

        try {
            Command command = userInputParser.parse(input);
            if (command instanceof HelpCommand) {
                response = COMMAND_LIST;
            } else {
                response = command.execute(taskList);
                storage.save(taskList); // Save the task list after every update
            }
        } catch (InvalidUserInputException e) {
            response = e.getMessage() + "\n" + COMMAND_LIST;
        } catch (NoSuchTaskException | SavingException e) {
            response = e.getMessage();
        }
        return response;
    }
}
