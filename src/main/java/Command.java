public class Command {
    private String[] inputParts;
    private String command;
    public Command(String[] inputParts) {
        this.inputParts = inputParts;
        this.command = inputParts[0];
    }

    public void run() {
        label:
        switch (this.command) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println(" Bye! Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break label;
            case "list":
                listTasks();
                break;
            case "mark": {
                handleMarkCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "unmark": {
                handleUnmarkCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "todo": {
                handleTodoCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "deadline": {
                try {
                    handleDeadlineCommand(inputParts);
                    Storage.saveTasksToFile(tasks);
                } catch (IllegalArgumentException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println(" Please enter date in the format dd/MM/yyyy?");
                    System.out.println("____________________________________________________________");
                }
                break;
            }
            case "event": {
                try {
                    handleEventCommand(inputParts);
                    Storage.saveTasksToFile(tasks);
                } catch (IllegalArgumentException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println(" Please enter date in the format dd/MM/yyyy?");
                    System.out.println("____________________________________________________________");
                }
                break;
            }
            case "delete": {
                handleDeleteCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "findbydate":
                handleFindByDateCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            default:
                System.out.println("____________________________________________________________");
                System.out.println(" I'm sorry, I don't recognize that command.");
                System.out.println("____________________________________________________________");
                break;
        }
    }
}
