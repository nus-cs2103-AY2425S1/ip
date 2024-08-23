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
            else if(command.startsWith("todo")) {
                String taskDescription = parser.splitCommandAndTaskDescription(command);
                Task newTask = new Todo(taskDescription);
                String addedTask = taskList.addTask(newTask);
                System.out.println(addedTask);
            }
            else if(command.startsWith("deadline")) {
                String taskInformation = parser.splitCommandAndTaskDescription(command);
                String taskDescription = parser.splitDeadlineCommand(taskInformation)[0];
                String taskDeadline = parser.splitDeadlineCommand(taskInformation)[1];
                Task newTask = new Deadline(taskDescription, taskDeadline);
                String addedTask = taskList.addTask(newTask);
                System.out.println(addedTask);
            }
            else if(command.startsWith("event")) {
                String taskInformation = parser.splitCommandAndTaskDescription(command);

                String taskDescription = parser.splitEventCommand(taskInformation)[0];

                String startInfo = parser.splitEventCommand(taskInformation)[1];
                String endInfo = parser.splitEventCommand(taskInformation)[2];

                String taskStart = parser.splitCommandAndTaskDescription(startInfo);
                String taskEnd = parser.splitCommandAndTaskDescription(endInfo);


                Task newTask = new Event(taskDescription, taskStart, taskEnd);
                String addedTask = taskList.addTask(newTask);
                System.out.println(addedTask);
            }

        }


    }
}
