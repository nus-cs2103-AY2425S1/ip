package nathanbot.ui;

import java.util.Scanner;

import nathanbot.commands.CommandHandler;
import nathanbot.commands.CommandType;
import nathanbot.tasks.TaskListStore;

/**
 * Handles the user interface for NathanBot, including reading user input and executing commands.
 * Javadocs using Copilot
 */
public class UserInterface {
    private final TaskListStore taskList;

    /**
     * Constructs a UserInterface with the specified TaskList.
     *
     * @param taskList The TaskList to be managed by the UserInterface.
     */
    public UserInterface(TaskListStore taskList) {
        this.taskList = taskList;
    }

    /**
     * Starts the user interface, greeting the user and processing commands in a loop.
     */
    public void start() {
        CommandHandler.handleGreet();

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                CommandType commandType = CommandType.fromInput(input);

                switch (commandType) {
                    case BREAK -> {
                        CommandHandler.handleExit();
                        return;
                    }
                    case DISPLAY_LIST -> CommandHandler.handleDisplayList(taskList);
                    case MARK_DONE -> CommandHandler.handleMarkCommand(input, "mark ", taskList, true);
                    case MARK_UNDONE -> CommandHandler.handleMarkCommand(input, "unmark ", taskList, false);
                    case TODO -> CommandHandler.handleTodoCommand(input, taskList);
                    case DEADLINE -> CommandHandler.handleDeadlineCommand(input, taskList);
                    case EVENT -> CommandHandler.handleEventCommand(input, taskList);
                    case DELETE -> CommandHandler.handleDeleteCommand(input, taskList);
                    default -> CommandHandler.handleUnknownCommand();
                }
            }
        }
    }    
}