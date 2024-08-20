import java.util.Scanner;

public class EchoBot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskList taskList = new TaskList();
        UI ui = new UI();
        ui.greeting();

        while (true) {
            String command = scan.nextLine();
            String[] commandSplit = command.split(" ");

            if ("bye".equals(command)) {
                ui.exit();
                break;
            } else if ("list".equals(command)) {
                ui.printTaskList(taskList.getTaskList());
            } else if (commandSplit.length == 2 && "mark".equals(commandSplit[0])) {
                int taskIndex = Integer.parseInt(commandSplit[1]);
                ToDo task = (ToDo) taskList.getTaskByIndex(taskIndex);
                task.markDone();
                ui.printTaskMarkedDone(task);
            } else if (commandSplit.length == 2 && "unmark".equals(commandSplit[0])) {
                int taskIndex = Integer.parseInt(commandSplit[1]);
                ToDo task = (ToDo) taskList.getTaskByIndex(taskIndex);
                task.markUndone();
                ui.printTaskMarkedUndone(task);
            } else {
                Task task = new ToDo(command);
                taskList.addTask(task);
                ui.printAddTaskFeedback(task);
            }
        }
    }
}
