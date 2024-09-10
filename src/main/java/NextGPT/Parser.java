package nextgpt;

import nextgpt.command.*;
import nextgpt.task.Deadline;
import nextgpt.task.Event;
import nextgpt.task.Todo;

/**
 * Class to understand User Input when using bot.
 */
public class Parser {
    /**
     * Attempt to understand user input.
     *
     * @param fullCommand User input.
     * @return Command that contains the properties of user input.
     * @throws NextGPTException If user input is invalid.
     */
    static Command parse(String fullCommand) throws NextGPTException{
        String[] split = fullCommand.trim().split(" ",2);
        String keyword = split[0];

        switch(keyword.toLowerCase()) {
        case "todo":
            if (split.length != 2) {
                throw new NextGPTException("Task name missing! Please include the task to be done! \n" +
                        "ie Todo <task name>\n");
            }
            return new AddCommand(new Todo(split[1].trim()));
        case "deadline":
            if (split.length != 2) {
                throw new NextGPTException("Please provide your Deadline task in the following format: \n"
                        + "Deadline <description> /by <yyyy-mm-dd>\n"
                        + "Example: Deadline do homework /by 2024-09-01\n");
            }
            String[] split1 = split[1].split("/by");
            if (split1.length != 2) {
                throw new NextGPTException("Please provide your Deadline task in the following format: \n"
                        + "Deadline <description> /by <yyyy-mm-dd>\n"
                        + "Example: Deadline do homework /by 2024-09-01\n");
            }
            String desc = split1[0].trim();
            String by = split1[1].trim();
            if (desc.length() == 0 || by.length() == 0) {
                throw new NextGPTException("Please provide your Deadline task in the following format: \n"
                        + "Deadline <description> /by <yyyy-mm-dd>\n"
                        + "Example: Deadline do homework /by 2024-09-01\n");
            }
            return new AddCommand(new Deadline(desc, by));
        case "event":
            String[] split2 = split[1].split("/from");
            if (split2.length != 2) {
                throw new NextGPTException("Please provide your Event task in the following format: \n"
                        + "Event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + "Example: event do homework /from 2024-08-01 /to 2024-08-03\n");
            }
            String[] split3 = split2[1].split("/to");
            if (split3.length != 2) {
                throw new NextGPTException("Please provide your Event task in the following format: \n"
                        + "Event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + "Example: event do homework /from 2024-08-01 /to 2024-08-03\n");
            }
            desc = split2[0].trim();
            String from = split3[0].trim();
            String to = split3[1].trim();
            if (desc.length() == 0 || from.length() == 0 || to.length() == 0) {
                throw new NextGPTException("Please provide your Event task in the following format: \n"
                        + "Event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + "Example: event do homework /from 2024-08-01 /to 2024-08-03\n");
            }
            return new AddCommand(new Event(desc, from, to));
        case "delete":
            return new DeleteCommand(Integer.parseInt(split[1].trim()) - 1);
        case "list":
            return new ListCommand();
        case "mark":
            return new EditCommand(true, Integer.parseInt(split[1].trim()) - 1);
        case "unmark":
            return new EditCommand(false, Integer.parseInt(split[1].trim()) - 1);
        case "bye":
            return new ExitCommand();
        case "find":
            if (split.length != 2) {
                throw new NextGPTException("Please input the keyword for me to find!");
            }
            return new FindCommand(split[1].trim());
        }
        throw new NextGPTException("Sorry, I do not understand what that means.");
    }
}



