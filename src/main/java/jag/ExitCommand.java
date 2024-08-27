package jag;

/**
 * Represents and instance of a ExitCommand so the
 * right Ui message can be displayed and the while loop in Jag.run()
 * can be broken and ended.
 */
public class ExitCommand extends Command {

    /**
     * Calls upon the Ui instance to display the bye message and close the
     * scanner instance
     *
     * @param tasks Not needed here but included as it is an abstract method
     * @param ui Ui instance so that the exirReponse() method can be called upon
     *           to display the right message and end the conversation
     * @param storage Not needed here but included as it is an abstract method
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitResponse();
    }

    /**
     * Returns true so that Jag.java does exit for loop
     *
     * @return a default true so the run() in Jag.java does exit
     * the while loop
     */
    @Override
    public Boolean isExit() {
        return true;
    }
}
