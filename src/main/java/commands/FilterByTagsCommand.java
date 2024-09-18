package commands;

import exceptions.ErrorMessageHandler;
import exceptions.InvalidTagNameException;
import exceptions.NoTaskDescriptionException;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to filter tasks by their associated tags.
 * Extends the {@code FindCommand} class and provides functionality to search for tasks
 * that match the specified tags.
 */
public class FilterByTagsCommand extends FindCommand {
    private String[] tagsToFilterBy;

    /**
     * Constructs a {@code FilterByTagsCommand} with the specified tags to filter by.
     *
     * @param tagsToFilterBy An array of tags used to filter tasks.
     */
    public FilterByTagsCommand(String... tagsToFilterBy) {
        this.tagsToFilterBy = tagsToFilterBy;
    }

    /**
     * Executes the filter command, searching for tasks in the task list that contain all the specified tags.
     * Returns a string representation of the matched tasks.
     *
     * @param taskList The task list to search through for matching tasks.
     * @return A string representation of all tasks that match the specified tags, or an error message if no valid tags are found.
     */
    @Override
    public String execute(TaskList taskList) {
        StringBuilder resultString = new StringBuilder();

        try {
            ArrayList<Task> matchedTasks = taskList.filterTaskByTags(tagsToFilterBy);

            for (int i = 0; i < matchedTasks.size(); i++) {
                resultString.append(i + 1)
                        .append(". ")
                        .append(matchedTasks.get(i))
                        .append("\n");
            }
            return resultString.toString();
        } catch (InvalidTagNameException e) {
            return ErrorMessageHandler.getInvalidTagNameMessage();
        }
    }
}
