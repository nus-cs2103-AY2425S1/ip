package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.PotongException;


public class WrongCommand extends Command {

    private static final String OUTPUT_STRING = String.format("Sorry, that is not a command.\nTo write a command," +
                    " try:\nlist\ntodo [task]\ndeadline [task] /by [deadline]\n event [task] /from [start]" +
                    "/to [end]\nmark/unmark/delete [index]\nfind [keyword]\ntag [index] [tags]\nbye");
    /**
     * Initialise the WrongCommand class
     *
     * @param command The general description of the wrong command.
     */
    public WrongCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return WrongCommand.OUTPUT_STRING;
    }
}
