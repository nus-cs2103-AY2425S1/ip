package kotori.parser;

import kotori.command.*;
import kotori.storage.Storage;
import kotori.taskList.*;

/**
 * This class represents a parser that will
 * parse a String of command line into a command Object
 */

public class Parser {
    private Storage storage;
    private TaskList list;


    public Parser (Storage storage, TaskList list) {
        this.storage = storage;
        this.list = list;
    }

    /**
     * Parses a string and produce a command Object.
     *
     * @param input the input command line.
     * @return the command going to be execute later
     * */
    public Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand(list);
        } else if (input.equals("list")) {
            return new PrintListCommand(list);
        } else if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(storage, list, index);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new UnmarkCommand(storage, list, index);
        } else if (input.startsWith("delete ")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(storage, list, index);

        } else if (input.startsWith("search ")) {
            return new searchCommand(list,input.substring(5));
        } else {
            return new AddCommand(storage, list, input);
            }
        }
}
