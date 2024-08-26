package ip.derrick ;
public class CommandHandler {

    private Commands command;
    private boolean isExit;
    public CommandHandler(Commands command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * Checks whether the user would like to exit the program.
     *
     * @return A Boolean value indicating whether the user would like to exit the program.
     */
    public boolean checkExit() {
        return this.isExit;
    }

    /**
     * Execute the command given by the user.
     *
     * @param input The string input given by the user.
     * @param tasks The TaskList containing all the tasks.
     * @param storage The storage in charge of saving and loading the TaskList.
     * @param ui The Ui in charge of user interactions
     */
    public void execute(String input, TaskList tasks,Storage storage, Ui ui) {
        switch (command) {
            case BYE:
                this.isExit = true;
                break;
            case LIST:
                tasks.list();
                break;
            case MARK:
                try {
                    tasks.markItem(input);
                } catch (MissingPositionException | MissingItemException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case UNMARK: {
                try {
                    tasks.unmarkItem(input);

                } catch (MissingPositionException | MissingItemException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case DELETE: {
                try {
                    tasks.delete(input);
                } catch (MissingItemException | MissingPositionException | EmptyListException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case TODO: {
                try {
                    tasks.addTodo(input);
                } catch (InvalidDescriptionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case DEADLINE: {
                try {
                    tasks.addDeadline(input);

                } catch (InvalidDescriptionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case EVENT: {
                try {
                    tasks.addEvent(input);

                } catch (InvalidDescriptionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case UNKNOWN: {
                System.out.println("Please specify the type of item that you wish to add ( Todo / Event / Deadline )");
                break;
            }
        }
    }
}
