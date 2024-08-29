package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;

public class UnrecognisedCommand extends Commands {

        public UnrecognisedCommand(String[] split) {
            super(split);
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
            ui.throwUnrecognisedError();
        }

}
