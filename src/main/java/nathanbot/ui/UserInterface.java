package nathanbot.ui;

import java.util.Scanner;

import nathanbot.commands.CommandHandler;
import nathanbot.commands.CommandType;
import nathanbot.tasks.TaskListStore;

/**
 * Cleaned up using Copilot to follow Google's Java Style Guide,
 * while keeping indentations to be 4 spaces.
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
                    case BREAK:
                        CommandHandler.handleExit();
                        return;
                    case DISPLAY_LIST:
                        CommandHandler.handleDisplayList(taskList);
                        break;
                    case MARK_DONE:
                        CommandHandler.handleMarkCommand(input, "mark ", taskList, true);
                        break;
                    case MARK_UNDONE:
                        CommandHandler.handleMarkCommand(input, "unmark ", taskList, false);
                        break;
                    case TODO:
                        CommandHandler.handleTodoCommand(input, taskList);
                        break;
                    case DEADLINE:
                        CommandHandler.handleDeadlineCommand(input, taskList);
                        break;
                    case EVENT:
                        CommandHandler.handleEventCommand(input, taskList);
                        break;
                    case DELETE:
                        CommandHandler.handleDeleteCommand(input, taskList);
                        break;
                    case FIND:
                        CommandHandler.handleFindCommand(input, taskList);
                        break;
                    default:
                        CommandHandler.handleUnknownCommand();
                        break;
                }
            }
        }
    }
}