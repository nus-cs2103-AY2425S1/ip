package juno.command;

import juno.ui.JunoUi;

/**
 * A class to exit the Juno chat bot. When executed, close the chat bot for user.
 * Subclass of Command class.
 */
public class ExitCommand extends Command {
    private JunoUi junoUi;

    /**
     * Constructs an ExitCommand instance that takes in a JunoUi instance, subsequently using it
     * to display exit message.
     *
     * @param junoUi The JunoUi instance to display the farewell message.
     */
    public ExitCommand(JunoUi junoUi) {
        this.junoUi = junoUi;
    }

    /**
     * Executes the command to exit the chat bot.
     * Displays the farewell message to the user by calling the <code>displayFarewellMessage()</code> method using
     * the JunoUi instance.
     */
    @Override
    public String runCommand() {
        return this.junoUi.displayFarewellMessage();
    }

    /**
     * Returns false as the user executes the exitCommand, so it returns false to exit the while loop in Juno class.
     *
     * @return False, indicating the command is not in a while loop.
     */
    @Override
    public boolean isInWhileLoop() {
        return false;
    }
}
