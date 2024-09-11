package commands;

import exceptions.ErrorMessageHandler;
import exceptions.InvalidTagNameException;
import exceptions.NoTaskDescriptionException;
import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class FilterByTagsCommand extends FindCommand {
    private String[] tagsToFilterBy;
    public FilterByTagsCommand(String... tagsToFilterBy) {
        this.tagsToFilterBy = tagsToFilterBy;
    }
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
