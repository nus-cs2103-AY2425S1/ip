package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand(String input) {
        this.input = input;
    }
    private enum MessageType {
        LIST("Type `list` to display all your Tasks"),
        BYE("Type `bye` to save and exit the application"),
        HELP("""
                Commands:
                -TODO
                -DEADLINE
                -EVENT
                -LIST
                -MARK
                -UNMARK
                -BYE
                -HELP

                Type `Help [Command]` for more info on each command. (E.g `help list`)"""),
        TODO("Type `todo [Description]` to save a new Todo task."),
        DEADLINE("Type `deadline [Description] /by [dd-mm-yyyy]` to save a new task " +
                "with a deadline\n(E.g `deadline task1 /by 11-09-2001`)"),
        EVENT("Type `event [Description] /from [dd-mm-yyyy] /to [dd-mm-yyyy]` to save a new event " +
                "with given start date after /from and given end date after /by.\n" +
                "(E.g `event exam /from 11-09-2001 /to 12-09-2001`)"),
        MARK("Type `mark [index]` to mark the task as complete.\n" +
                "Can Mark multiple tasks by spacing the index with a space\n" +
                "(E.g `mark 1 3` will mark the first and third item from the task list)"),
        UNMARK("Type `unmark [index]` to mark the task as incomplete.\n" +
                "(E.g `unmark  1 3` will unmark the first and third item from the task list)"),
        DELETE("Type `delete [index]` to delete the task as incomplete.\n" +
                "(E.g `delete 1 3` will remove the first and third item from the task list)"),
        FIND("Type `find [prompt]` to search for all tasks that contains" +
                " the prompt in its description");
        private final String message;
        MessageType(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }


    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (input == null){
            helpResponse(MessageType.HELP.getMessage());
            return;
        }
        try {
            Command.Types t = Command.checkType(input);
            switch (t) {
            case LIST -> helpResponse(MessageType.LIST.getMessage());
            case BYE -> helpResponse(MessageType.BYE.getMessage());
            case HELP -> helpResponse(MessageType.HELP.getMessage());
            case TODO -> helpResponse(MessageType.TODO.getMessage());
            case DEADLINE -> helpResponse(MessageType.DEADLINE.getMessage());
            case EVENT -> helpResponse(MessageType.EVENT.getMessage());
            case MARK -> helpResponse(MessageType.MARK.getMessage());
            case UNMARK -> helpResponse(MessageType.UNMARK.getMessage());
            case DELETE -> helpResponse(MessageType.DELETE.getMessage());
            case FIND -> helpResponse(MessageType.FIND.getMessage());
            }
        } catch (RuntimeException e) {
            helpResponse(MessageType.HELP.getMessage());
        }

    }

    private void helpResponse(String response) {
        setResponse(response);
    }

}
