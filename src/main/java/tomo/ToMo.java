package tomo;

import command.Command;
import exception.ToMoException;
import tasklist.TaskList;

/**
 * Handles everything about the conversation with user
 */
public class ToMo {
    private Parser parser;
    private TaskList tasks;

    public String getResponse(String cmdline) {
        try {
            Command command = parser.parse(cmdline);
            return command.getResponse(tasks);
        } catch (ToMoException e) {
            return e.toString();
        }
    }
}
