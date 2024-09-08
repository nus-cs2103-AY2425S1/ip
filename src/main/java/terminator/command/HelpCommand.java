package terminator.command;

import java.util.ArrayList;

import terminator.task.Task;

public class HelpCommand extends Command {

    private static final String ERR_MSG = """
            help command takes no arguments.\n
            Usage: help""";

    /**
     * Returns a new instance of a HelpCommand.
     *
     * @param input The user string input.
     */
    public HelpCommand(String input) {
        super(input);
    }

    /**
     * Prints out a list of commands that the user can give.
     *
     * @param todoList The task list.
     * @throws TerminatorException
     */
    @Override
    public String execute(ArrayList<Task> todoList) throws TerminatorException {
        if (!(input == null)) {
            throw new TerminatorException(ERR_MSG);
        }
        String response = """
                The list of available commands are:   
                
                • help
                • list
                • todo <description>
                • deadline /by dd/MM/yyyy HHmm
                • event <description> /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm
                • mark <index>
                • unmark <index>
                • delete <index> 
                • find <description>
                • bye
                
                Note that the items in the task list are 1-indexed.""";
        return response;
    }
}
