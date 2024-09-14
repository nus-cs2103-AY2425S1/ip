import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Nebula {
    /**
     * Entry point of the application. Initializes the UI, task list, and parser,
     * then processes user commands in a loop until "bye" is entered
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        File currentDirectory = new File("./");

        System.out.println(ui.greeting());

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String command = sc.nextLine();

            if(command.equals("bye")) {
                System.out.println(ui.goodbye());
                saveTaskListToTextFile(TaskList.getTaskList());
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

                TaskType taskType = parseTaskType(command);

                switch (taskType) {
                    case TODO:
                        String taskDescription = parser.splitCommandAndTaskDescription(command);
                        Task newTodo = new Todo(taskDescription);
                        String addedTodo = taskList.addTask(newTodo);
                        System.out.println(addedTodo);
                        break;

                    case DEADLINE:
                        String taskInformation = parser.splitCommandAndTaskDescription(command);
                        String taskDescriptionDeadline = parser.splitDeadlineCommand(taskInformation)[0];
                        String taskDeadline = parser.splitDeadlineCommand(taskInformation)[1];
                        Task newDeadline = new Deadline(taskDescriptionDeadline, taskDeadline);
                        String addedDeadline = taskList.addTask(newDeadline);
                        System.out.println(addedDeadline);
                        break;

                    case EVENT:
                        String taskInfo = parser.splitCommandAndTaskDescription(command);
                        String taskDescriptionEvent = parser.splitEventCommand(taskInfo)[0];
                        String startInfo = parser.splitEventCommand(taskInfo)[1];
                        String endInfo = parser.splitEventCommand(taskInfo)[2];

                        String taskStart = parser.splitCommandAndTaskDescription(startInfo);
                        String taskEnd = parser.splitCommandAndTaskDescription(endInfo);

                        Task newEvent = new Event(taskDescriptionEvent, taskStart, taskEnd);
                        String addedEvent = taskList.addTask(newEvent);
                        System.out.println(addedEvent);
                        break;

                    case UNKNOWN:
                        System.out.println("Unknown command type.");
                        break;
                }

            }

        }


    }

    /**
     * Validates the user's input for the correct format and content
     *
     * @param command  the user input command
     * @param taskList the current list of tasks
     * @throws NebulaException if the command is invalid or improperly formatted
     */
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

    /**
     * Determines the TaskType based on the command prefix
     *
     * @param command the input command string
     * @return the corresponding TaskType, or unknown TaskType if unrecognized
     */
    public static TaskType parseTaskType(String command) {
        if (command.startsWith("todo")) {
            return TaskType.TODO;
        } else if (command.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (command.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }

    public static void saveTaskListToTextFile(ArrayList<Task> listOfTasks) throws IOException {
        FileWriter fw = new FileWriter("nebulaTaskList.txt");
        for (Task task : listOfTasks) {

            String isMarked = task.isDone() ? "1" : "0";
            String taskSymbol = task.getTaskSymbol();
            String taskDescription = task.getDescription();

            String taskData = isMarked + " | " + taskSymbol + " | " + taskDescription;

            if(task instanceof Deadline) {
                taskData += " | " + ((Deadline) task).getDeadline();
            } else if (task instanceof Event) {
                taskData += " | " + ((Event) task).getStart() + "-" + ((Event) task).getEnd();
            }

            fw.write(taskData + "\n");
        }

        fw.close();
    }





}
