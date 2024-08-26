package infinity;

import infinity.infinityexception.InfinityException;
import infinity.task.Deadline;
import infinity.storage.Storage;
import infinity.task.Event;
import infinity.task.Task;
import infinity.task.ToDos;
import infinity.tasklist.TaskList;
import infinity.ui.Ui;

import java.io.IOException;

/**
 * This is the Infinity Bot that will run the program. Create a new instance and it will run.
 * 
 * @param <T> Type of Task that extends Task. Examples include ToDos, Events and Deadline.
 */
public class Infinity <T extends Task> {
    
    private enum KnownCommands {
        BYE, 
        DEADLINE, 
        DELETE,
        EVENT, 
        FIND,
        LIST, 
        MARK, 
        SAVE,
        TODO
    }

    private Ui botUI = new Ui();
    private Storage storage = new Storage(botUI);
    private TaskList botTasks;

    /**
     * Constructor for the Infinity class.
     * Initialises the bot and runs infinitely until "bye" command is given
     */
    public Infinity() {

        this.botTasks = new TaskList(storage.readFile(), botUI);

        while (true) {
            String currentInput = botUI.nextInput();

            try {

                if (currentInput.equals(KnownCommands.BYE.toString().toLowerCase())) {

                    botUI.endBot();

                } else if (currentInput.equals(KnownCommands.LIST.toString().toLowerCase())) {

                    botUI.listTasks(this.botTasks);

                } else if (currentInput.startsWith(KnownCommands.MARK.toString().toLowerCase()) 
                        && currentInput.length() > 5) {

                    this.botTasks.markTask(currentInput);

                } else if (currentInput.startsWith(KnownCommands.TODO.toString().toLowerCase()) 
                        && currentInput.length() > 5) {

                    this.botTasks.addTask(new ToDos(currentInput.substring(5)));

                } else if (currentInput.startsWith(KnownCommands.DEADLINE.toString().toLowerCase()) 
                        && currentInput.length() > 9) {

                    this.botTasks.addTask(new Deadline(currentInput.substring(9)));

                } else if (currentInput.startsWith(KnownCommands.EVENT.toString().toLowerCase()) 
                        && currentInput.length() > 6) {

                    this.botTasks.addTask(new Event(currentInput.substring(6)));

                } else if (currentInput.startsWith(KnownCommands.DELETE.toString().toLowerCase()) 
                        && currentInput.length() > 7) {

                    this.botTasks.deleteTask(currentInput.substring(7));

                } else if (currentInput.startsWith(KnownCommands.FIND.toString().toLowerCase())
                        && currentInput.length() > 5) {

                    this.botTasks.findTasks(currentInput.substring(5));

                } else if (currentInput.startsWith(KnownCommands.SAVE.toString().toLowerCase())) {
                    
                    try {
                        storage.saveFile(this.botTasks.getTasks());
                    } catch (IOException e) {
                        botUI.botSays(
                                "I'm sorry, I'm a noob at this, I can't save the file, can you help me debug? " 
                                + e.getMessage());
                    }

                } else {

                    botUI.echo(currentInput);

                }

            } catch (InfinityException e) {

                botUI.botSays(e.getMessage());

            }
        }
    }

    /**
     * Main method to run the Infinity bot.
     * 
     * @param args The arguments to run the bot.
     */
    public static void main(String[] args) {
        Infinity infinityBot = new Infinity();
    }

}
