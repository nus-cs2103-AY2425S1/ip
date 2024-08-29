package rex;

import rex.command.Command;
import rex.exception.InvalidInputException;
import rex.task.Deadline;
import rex.task.Event;
import rex.task.Task;
import rex.task.TaskList;
import rex.task.ToDo;
import rex.util.Parser;
import rex.util.Storage;
import rex.util.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Rex {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Rex() {
        try {
            this.storage = new Storage();
            this.taskList = new TaskList(storage);
            this.ui = new Ui();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
    }

    public void run() {
        ui.greeting();

        while (true) {
            Command command = null;
            try {
                String input = ui.readInput();
                String[] inputTokens = Parser.parseInput(input);

                // Process user command
                command = Command.inputToCommand(inputTokens[0]);
                switch (command) {
                case HELP:
                    ui.help();
                    break;
                case TODO:
                    String argument = inputTokens[1];
                    ToDo toDo = taskList.addToDo(argument);
                    ui.addTask(toDo);
                    break;
                case DEADLINE:
                    argument = inputTokens[1];
                    Deadline deadline = taskList.addDeadline(argument);
                    ui.addTask(deadline);
                    break;
                case EVENT:
                    argument = inputTokens[1];
                    Event event = taskList.addEvent(argument);
                    ui.addTask(event);
                    break;
                case LIST:
                    String output = taskList.getListDisplay();
                    ui.displayList(output);
                    break;
                case FIND:
                    argument = inputTokens[1];
                    output = taskList.findTasks(argument);
                    ui.findTask(output);
                    break;
                case MARK:
                    argument = inputTokens[1];
                    Task marked = taskList.markTask(argument);
                    ui.markTask(marked);
                    break;
                case UNMARK:
                    argument = inputTokens[1];
                    Task unmarked = taskList.unmarkTask(argument);
                    ui.unmarkTask(unmarked);
                    break;
                case DELETE:
                    argument = inputTokens[1];
                    Task deleted = taskList.deleteTask(argument);
                    ui.deleteTask(deleted);
                    break;
                case RAWR:
                    ui.rawr();
                    break;
                case BYE:
                    ui.goodbye();
                    return;
                }
            } catch (InvalidInputException e) {
                if (command != null) {
                    String usageMessage = Command.usageMessage(command);
                    ui.errorMessage(e.getMessage() + "\nUsage: " + usageMessage);
                } else {
                    ui.errorMessage(e);
                }
            } catch (IOException e) {
                System.out.println("An error has occurred.");
                throw new RuntimeException(e);
            } catch (DateTimeParseException e) {
                String usageMessage = Command.usageMessage(command);
                ui.errorMessage("Wrong date/time format!\nUsage: " + usageMessage);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                String usageMessage = Command.usageMessage(command);
                ui.errorMessage("Invalid task number!\nUsage: " + usageMessage);
            }
        }
    }

    public static void main(String[] args) {
        Rex rex = new Rex();
        rex.run();
    }
}
