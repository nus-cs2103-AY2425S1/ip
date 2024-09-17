package snah.commands;

import snah.TaskList;
import snah.task.Deadline;
import snah.task.Event;
import snah.task.ToDo;
import snah.util.Parser;
import snah.util.Storage;

/**
 * Help command to display and explain the commands available
 */
public class Help extends Command {

    public Help(String input) {
        super("");
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = "Here are the lists of commands available:\n";
        String[] commandList = Parser.getCommandList();

        for (int i = 0; i < commandList.length; i++) {
            response += String.format("%d. %s\n", i + 1, commandList[i]);

            switch (commandList[i]) {
            case "HELP":
                response += String.format("Usage: %s\n", "help");
                response += "Description: Displays the list of commands available\n";
                break;
            case "LIST":
                response += String.format("Usage: %s\n", "list");
                response += "Description: Lists all the tasks in the task list\n";
                break;
            case "CLEAR":
                response += String.format("Usage: %s\n", "clear");
                response += "Description: Clears all the tasks in the task list\n";
                break;
            case "MARK":
                response += String.format("Usage: %s\n", "mark <task number>");
                response += "Description: Marks the task as done\n";
                break;
            case "UNMARK":
                response += String.format("Usage: %s\n", "unmark <task number>");
                response += "Description: Unmarks the task as done\n";
                break;
            case "TODO":
                response += String.format("Usage: %s\n", ToDo.getFormatDescription());
                response += "Description: Adds a todo task to the task list\n";
                break;
            case "DELETE":
                response += String.format("Usage: %s\n", "delete <task number>");
                response += "Description: Deletes the task from the task list\n";
                break;
            case "FIND":
                response += String.format("Usage: %s\n", "find <keyword>");
                response += "Description: Finds the tasks with the keyword\n";
                break;
            case "DEADLINE":
                response += String.format("Usage: %s\n", Deadline.getFormatDescription());
                response += "Description: Adds a deadline task to the task list\n";
                break;
            case "EVENT":
                response += String.format("Usage: %s\n", Event.getFormatDescription());
                response += "Description: Adds an event task to the task list\n";
                break;
            case "BYE":
                response += String.format("Usage: %s\n", "bye");
                response += "Description: Exits the chatbot\n";
                break;
            default:
                break;
            }

            response += "\n";
        }
        return response;
    }

}
