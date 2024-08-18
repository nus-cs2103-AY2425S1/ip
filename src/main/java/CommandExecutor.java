import java.util.Arrays;

public class CommandExecutor {

    public boolean HandleCommand(Command command, TaskList taskList, boolean continueScanning) {
        CommandTypes commandType = command.commandType();

        String commandDetails = Arrays.stream(command.commandDetails())
                .reduce((accumulator, element) -> accumulator + " " + element)
                .orElse("");

        Task newTask;
        int index;

        switch (commandType) {
            case LIST:
                FormattedPrinting.printList(taskList);
                break;

            // the following code was slightly optimised using ChatGPT
            case MARK:
            case UNMARK:
            case DELETE:
                try {
                    index = Integer.parseInt(commandDetails) - 1;
                    newTask = taskList.getTask(index);
                    if (newTask != null) {
                        if (commandType == CommandTypes.MARK) {
                            newTask.markDone();
                        } else if (commandType == CommandTypes.UNMARK) {
                            newTask.markUndone();
                        } else {
                            taskList.deleteTask(newTask);
                        }
                    }
                } catch (NumberFormatException e) {
                    FormattedPrinting.unknownNumber();
                }
                break;

            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    if (commandType == CommandTypes.TODO) {
                        newTask = new ToDo(commandDetails);
                    } else if (commandType == CommandTypes.DEADLINE) {
                        newTask = new Deadline(commandDetails);
                    } else {
                        newTask = new Events(commandDetails);
                    }
                    taskList.addTask(newTask);
                } catch (ArrayIndexOutOfBoundsException e) {
                    FormattedPrinting.invalidNumberOfDetails();
                }
                break;

            case UNKNOWN:
                FormattedPrinting.unknownCommand();
                break;

            case BYE:
                continueScanning = false;
                break;
        }

        return continueScanning;
    }
}
