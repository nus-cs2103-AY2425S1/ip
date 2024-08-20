import java.util.Scanner;

public class EchoBot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskList taskList = new TaskList();
        UI ui = new UI();
        ui.greeting();


        while (true) {
            String command = scan.nextLine();
            if ("bye".equals(command)) {
                ui.exit();
                break;
            }
            else if ("list".equals(command)) {
                ui.printTaskList(taskList.getTaskList());
            } else {
                taskList.addTask(new Task(command));
                ui.printAddTaskFeedback(command);
            }
        }
    }
}
