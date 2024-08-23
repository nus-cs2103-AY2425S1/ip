import java.util.Scanner;

public class Nebula {
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();

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
            else {
                Task newTask = new Task(command);
                taskList.addTask(newTask);

                String echoCommand = ui.echo(command);
                System.out.println(echoCommand);
            }

        }


    }
}
