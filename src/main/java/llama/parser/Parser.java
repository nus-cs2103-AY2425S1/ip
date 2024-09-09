package llama.parser;

import llama.commands.AddCommand;
import llama.commands.Command;
import llama.commands.DeleteCommand;
import llama.commands.EditCommand;
import llama.commands.ExitCommand;
import llama.commands.ListCommand;
import llama.exceptions.LlamaException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Method to interpret user's input
     *
     * @param input user input to be interpreted
     * @return Command that program can execute based on user input
     */
    public static Command parse(String input) {
        // Split input into command and remaining
        String command = input;
        String remaining = "";
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
            remaining = input.substring(input.indexOf(" ") + 1);
        }

        if (command.equals("todo")) {
            return new AddCommand(remaining, AddCommand.TaskType.TODO);
        } else if (command.equals("deadline")) {
            return new AddCommand(remaining, AddCommand.TaskType.DEADLINE);
        } else if (command.equals("event")) {
            return new AddCommand(remaining, AddCommand.TaskType.EVENT);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("mark")) {
            return new EditCommand(remaining, EditCommand.Instruction.MARK);
        } else if (command.equals("unmark")) {
            return new EditCommand(remaining, EditCommand.Instruction.UNMARK);
        } else if (command.equals("delete")) {
            return new DeleteCommand(remaining);
        } else if (command.equals("find")) {
            return new ListCommand(remaining);
        }

        throw new LlamaException("Command not found, try again.");
    }
}
