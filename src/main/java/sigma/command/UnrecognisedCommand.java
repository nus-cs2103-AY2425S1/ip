package sigma.command;

import sigma.Storage;
import sigma.TaskList;
import sigma.Ui;
import sigma.exception.SigmaException;

/**
 * Represents the command to execute the user's input.
 */
public class UnrecognisedCommand extends Commands {

        public UnrecognisedCommand(String[] split) {
            super(split);
        }

        /**
         * Throws an unrecognised error.
         * @param tasks
         * @param ui
         * @param storage
         * @throws SigmaException
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
            ui.throwUnrecognisedError();
        }
}
