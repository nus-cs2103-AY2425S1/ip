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
                try {
                    validateCommand(command, taskList);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(taskList.markTask(taskNum));
            }
            else if(command.startsWith("unmark")) {
                try {
                    validateCommand(command, taskList);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(taskList.unmarkTask(taskNum));
            }
            else if(command.startsWith("delete")) {
                try {
                    validateCommand(command, taskList);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(taskList.deleteTask(taskNum));
            }
            else {
                try {
                    validateCommand(command, taskList);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                if(command.startsWith("todo")) {
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

    public static void validateCommand(String command, TaskList taskList) throws NebulaException {
        Parser parser = new Parser();
        Ui ui = new Ui();

        if (command.isEmpty()) {
            throw new NebulaException("Please enter a command!");
        } else if (!(command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event") || command.startsWith("mark")
                || command.startsWith("unmark") || command.startsWith("delete")
                || command.startsWith("list") || command.startsWith("bye"))) {
            throw new NebulaException(ui.displayUnknownCommandException());
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            String[] parts = command.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new NebulaException(ui.displayUnknownTaskNumberException());
            }
            try {
                int taskIndex = Integer.parseInt(parts[1].trim()) - 1; // Convert to 0-based index
                if (taskIndex < 0 || taskIndex >= TaskList.getTaskListLength()) {
                    throw new NebulaException(ui.displayNonexistentTaskNumberException());
                }
            } catch (NumberFormatException e) {
                throw new NebulaException(ui.displayUnknownTaskNumberException());
            }
        } else {
            String[] parts = command.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new NebulaException(ui.displayUnknownMessageException());
            }

            String description = parts[1].trim();

            if (command.startsWith("deadline")) {
                if (!description.contains("/by")) {
                    throw new NebulaException(ui.displayUnknownDeadlineException());
                }
            } else if (command.startsWith("event")) {
                if (!description.contains("/from") || !description.contains("/to")) {
                    throw new NebulaException(ui.displayUnknownEventTimingException());
                }
            }
        }
    }




}
