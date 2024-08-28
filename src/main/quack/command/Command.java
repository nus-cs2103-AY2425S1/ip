package quack.command;

/**
 * This is an abstract class to define a command object.
 */
public abstract class Command {

    /**
     * Executes the logic based on the specfic command.
     * @param quack The chatbot object quack.
     * @param taskList A list that stores all the tasks tracked by Quack. 
     * @param storage Storage object to save and load data from the save file.
     * @param ui The ui object that handles user interface requests.
     */
    public abstract void execute(Quack quack, TaskList taskList, Storage storage, Ui ui);
}
