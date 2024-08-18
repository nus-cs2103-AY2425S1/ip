import java.util.Arrays;

public class CommandExecutor {

    public enum CommandType {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        UNKNOWN
    }

    public boolean HandleCommand(Command command, TaskList taskList, boolean continueScanning) {
        CommandType commandType = CommandType.values()[command.commandCode()];

        switch (commandType) {
            case LIST:
                FormattedPrinting.printList(taskList);
                break;

            case MARK:
                try {
                    int index = Integer.parseInt(command.commandDetails()[0]) - 1;
                    Task taskToMark = taskList.getTask(index);
                    taskToMark.markDone();
                    FormattedPrinting.printMarked(taskToMark);
                } catch (Exception e) {
                    FormattedPrinting.FormatPrint("Invalid task number!");
                }
                break;

            case UNMARK:
                try {
                    int index = Integer.parseInt(command.commandDetails()[0]) - 1;
                    Task taskToUnmark = taskList.getTask(index);
                    taskToUnmark.markUndone();
                    FormattedPrinting.printUnmarked(taskToUnmark);
                } catch (Exception e) {
                    FormattedPrinting.FormatPrint("Invalid task number!");
                }
                break;

            case TODO:
                String commandDetails = Arrays.stream(command.commandDetails())
                        .reduce((accumulator, element) -> accumulator + " " + element)
                        .orElse("");
                Task newTask = new Task(commandDetails);
                taskList.addTask(newTask);
                FormattedPrinting.addTask(newTask, taskList);
                break;

            case DEADLINE:

            case EVENT:

                break;

            case UNKNOWN:
                FormattedPrinting.FormatPrint("I do not recognise this command, please check again!");
                break;

            case BYE:
                continueScanning = false;
                break;
        }

        return continueScanning;
    }
}
