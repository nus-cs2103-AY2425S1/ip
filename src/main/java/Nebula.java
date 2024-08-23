import java.util.Scanner;

public class Nebula {
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        System.out.println(ui.greeting());

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String command = sc.nextLine();

            if(command.equals("bye")) {
                System.out.println(ui.goodbye());
                break;
            }
            else if(command.equals("list")) {
                System.out.println(ui.displayList());
            }
            else if(command.startsWith("mark")) {
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(taskList.markTask(taskNum));
            }
            else if(command.startsWith("unmark")) {
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(taskList.unmarkTask(taskNum));
            }
            else {
                Task newTask = new Task(command);
                taskList.addTask(newTask);

                String addedTask = ui.displayAddedTask(command);
                System.out.println(addedTask);
            }

        }


    }
}
