import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Justbot {
    public static void main(String[] args) throws JustbotException {
        final String chatbotName = "JustBot";
        final String filePath = "/Users/justinyeo/Desktop/CS2103T-ip/data/justbottask.txt";

        Scanner scanner = new Scanner(System.in);
        String input = "";
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getArrListTask();

        TaskFileHandler taskFileHandler = new TaskFileHandler(filePath);
        taskFileHandler.createFileIfDoesNotExist();
        taskFileHandler.loadTasks(taskList);
        Commands.botIntro(chatbotName);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            CommandType command = parser.commandTypeParser(input);
            switch(command) {
                case BYE:
                    System.out.println("------------------------------------------");
                    Commands.bye();
                    scanner.close();
                    return;
                case LIST:
                    try {
                        if (tasks.isEmpty()) {
                            throw new JustbotException("Hey man you have no tasks in your list!");
                        }
                        Commands.returnTaskList(tasks);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case MARK:
                    try {
                        int markNumber = parser.extractTaskNumber(input);
                        taskList.validateMarkTaskNumber(markNumber);
                        Commands.markTask(taskList, markNumber);
                        taskFileHandler.saveTasks(taskList);
                    } catch (JustbotException | IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case UNMARK:
                    try {
                        int unmarkNumber = parser.extractTaskNumber(input);
                        taskList.validateUnmarkTaskNumber(unmarkNumber);
                        Commands.unmarkTask(taskList, unmarkNumber);
                        taskFileHandler.saveTasks(taskList);
                    } catch (JustbotException | IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case DEADLINE:
                    try {
                        String deadlineDescription = parser.extractDeadlineDescription(input);
                        LocalDateTime deadlineDateTime = parser.extractDeadlineDateTime(input);
                        Commands.addTask(tasks, new Deadline(deadlineDescription, deadlineDateTime));
                        taskFileHandler.saveTasks(taskList);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case EVENT:
                    try {
                        String eventDescription = parser.extractEventDescription(input);
                        String[] eventTimings = parser.extractEventTimings(input);
                        LocalDateTime startDateTime = parser.parseEventDateTime(eventTimings[0]);
                        LocalDateTime endDateTime = parser.parseEventDateTime(eventTimings[1]);

                        parser.validateEventTimings(startDateTime, endDateTime);

                        Commands.addTask(tasks, new Event(eventDescription, startDateTime, endDateTime));
                        taskFileHandler.saveTasks(taskList);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case TODO:
                    try {
                        String description = parser.extractTodoDescription(input);
                        Commands.addTask(tasks, new Todo(description));
                        taskFileHandler.saveTasks(taskList);
                    } catch (JustbotException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                case DELETE:
                    try {
                        int deleteNumber = parser.extractDeleteTaskNumber(input);
                        taskList.validateDeleteTaskNumber(deleteNumber);
                        Commands.deleteTask(tasks, deleteNumber);
                        taskFileHandler.saveTasks(taskList);
                    } catch (JustbotException | IndexOutOfBoundsException e) {
                        System.out.println("------------------------------------------");
                        System.out.println(e.getMessage());
                        System.out.println("------------------------------------------");
                    }
                    break;
                default:
                    System.out.println("------------------------------------------");
                    System.out.println("Hey man please use one of these valid commands\n" + "list\n" + "delete [task number]\n" + "mark [task number]\n" + "unmark [task number]\n" + "todo [description]\n" + "deadline [description] /by DD/MM/YYYY HHmm\n" + "event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n");
                    System.out.println("------------------------------------------");
            }
        }
    }
}
