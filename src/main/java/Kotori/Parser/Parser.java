package Kotori.Parser;

import Kotori.Command.*;
import Kotori.Storage.Storage;
import Kotori.TaskList.*;


public class Parser {
    private Storage storage;
    private TaskList list;


    public Parser (Storage storage, TaskList list) {
        this.storage = storage;
        this.list = list;
    }

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

        } else if (input.startsWith("find ")) {
            return new FindCommand(list,input.substring(5));
        } else {
            return new AddCommand(storage, list, input);
            }
        }
}
