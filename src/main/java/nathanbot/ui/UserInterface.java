package ui;

import java.util.Scanner;

import commands.CommandHandler;
import commands.CommandType;
import tasks.TaskList;

public class UserInterface {
    private final TaskList taskList;

    public UserInterface(TaskList taskList) {
        this.taskList = taskList;
    }

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
