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
        CommandType commandType = CommandType.values()[command.getCommandCode()];

        switch (commandType) {
            case LIST:
                System.out.println(taskList.printList());
                break;

            case MARK:
                try {
                    int index = Integer.parseInt(command.getCommandDetails()) - 1;
                    Task taskToMark = taskList.getTask(index);
                    System.out.println(taskToMark.markDone());
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
                break;

            case UNMARK:
                try {
                    int index = Integer.parseInt(command.getCommandDetails()) - 1;
                    Task taskToUnmark = taskList.getTask(index);
                    System.out.println(taskToUnmark.markUndone());
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
                break;

            case TODO:

            case DEADLINE:

            case EVENT:
                Task newTask = new Task(command.getCommandDetails());
                taskList.addTask(newTask);
                System.out.println(newTask.printTask());
                break;

            case UNKNOWN:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;

            case BYE:
                continueScanning = false;
                break;
        }

        return continueScanning;
    }
}
