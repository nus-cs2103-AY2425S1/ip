package llama.parser;

import llama.commands.AddCommand;
import llama.commands.Command;
import llama.commands.CreateTagCommand;
import llama.commands.DeleteCommand;
import llama.commands.EditCommand;
import llama.commands.ExitCommand;
import llama.commands.ListCommand;
import llama.commands.TagTaskCommand;
import llama.commands.UntagTaskCommand;
import llama.exceptions.LlamaException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    /**
     * Inteprets the user's input and returns a command based on input
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

        switch (command.toLowerCase()) {
        case "todo":
            return new AddCommand(remaining, AddCommand.TaskType.TODO);
        case "deadline":
            return new AddCommand(remaining, AddCommand.TaskType.DEADLINE);
        case "event":
            return new AddCommand(remaining, AddCommand.TaskType.EVENT);
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new EditCommand(remaining, EditCommand.Instruction.MARK);
        case "unmark":
            return new EditCommand(remaining, EditCommand.Instruction.UNMARK);
        case "delete":
            return new DeleteCommand(remaining);
        case "find":
            return new ListCommand(remaining);
        case "create":
            return new CreateTagCommand(remaining);
        case "tag":
            return new TagTaskCommand(remaining);
        case "untag":
            return new UntagTaskCommand(remaining);
        default:
            throw new LlamaException("Command not found, try again.");
        }
    }
}
