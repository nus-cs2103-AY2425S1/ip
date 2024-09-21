package commands;

import java.util.Random;

import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Abstract class that defines a template for user commands.
 */
public abstract class Command {
    private final String command;

    private static final String[] quirkyResponses = {
            "\nHEHEHEHA!",
            "\nOH MAH GAWD!",
            "\nBING CHILLING!",
            "\nZHONG XINA!",
            "\nAMAZINGG OwO!",
            "\nGOOD JOB OwO!",
            "\nLAO GAN MA!"
    };

    /**
     * Stores the command string associated with user commands.
     *
     * @param command Command string.
     */
    protected Command(String command) {
        this.command = command;
    }

    /**
     * Breaks the command string down into words.
     *
     * @return Command words.
     */
    protected String[] processCommand() {
        return this.command.split(" ");
    }

    /**
     * Fetches the stored command string.
     * To be examined within the user command subclass.
     *
     * @return Command string.
     */
    protected String getCommand() {
        return this.command;
    }

    /**
     * Runs the user command.
     *
     * @param taskStorage {@code TaskStorage} object that creates and interfaces with save file.
     * @param tasks {@code TaskList} object that stores the current tasks in an {@code ArrayList}.
     * @return Response string after executing the command.
     * @throws BrockException If there are any issues with running the command.
     */
    public abstract String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks)
            throws BrockException;

    /**
     * Gets the type associated with the command.
     *
     * @return Command type.
     */
    public abstract String getCommandType();

    /**
     * Gets a randomly selected quirky response.
     *      To append to back of generated response for each command.
     *
     * @return Quirky response selected.
     */
    public String getQuirkyResponse() {
        Random random = new Random();
        int randomIndex = random.nextInt(quirkyResponses.length);
        return quirkyResponses[randomIndex];
    }
}
