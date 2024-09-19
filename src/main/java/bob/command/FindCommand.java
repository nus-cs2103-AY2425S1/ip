package bob.command;

import java.util.ArrayList;

import bob.exception.InvalidTaskException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;


/**
 * ListCommand class executes list command.
 */
public class FindCommand extends Command {

    /**
     * Constructor to initalise FindCommand
     *
     * @param input
     */
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = this.getInput();
        try {
            verifyOnly1Keyword(input);
            String searchKeyword = Parser.parseSearchKeywordFromInput(input);
            ArrayList<Task> matchingRecords = taskList.searchKeywordInRecords(searchKeyword);
            String matchingRecordsString = taskList.getMatchingRecordsString(matchingRecords);
            Ui.showFindResults(matchingRecordsString);
            return matchingRecordsString;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Verifies that only 1 keyword is being searched for.
     *
     * @param input Input by user.
     * @throws InvalidTaskException
     */
    private void verifyOnly1Keyword(String input) throws InvalidTaskException {
        String[] inputArray = Parser.parseInputIntoStringArray(input);
        if (inputArray.length > 2) {
            throw new InvalidTaskException("find command can only be used to find 1 keyword.");
        }
        if (inputArray.length <= 1) {
            throw new InvalidTaskException("Please enter the exact keyword you want to find.");
        }
    }
}
