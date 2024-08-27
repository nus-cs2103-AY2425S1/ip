import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Justbot {
    private Ui ui;
    private TaskList taskList;
    public static void main(String[] args) throws JustbotException {
        final String chatbotName = "JustBot";
        final String filePath = "/Users/justinyeo/Desktop/CS2103T-ip/data/justbottask.txt";

        Scanner scanner = new Scanner(System.in);
        String input = "";
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        Storage storage = new Storage(filePath);
        storage.createFileIfDoesNotExist();
        storage.loadTasks(taskList);
        ui.botIntro(chatbotName);

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            CommandType command = parser.commandTypeParser(input);
            switch(command) {
                case BYE:
                    ui.byeMessage();
                    scanner.close();
                    return;
                case LIST:
                    try {
                        taskList.validateNotEmpty();
                        ui.listMessage(taskList);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
                    }
                    break;
                case MARK:
                    try {
                        int markNumber = parser.extractTaskNumber(input);
                        taskList.validateMarkTaskNumber(markNumber);
                        Commands.markTask(taskList, markNumber);
                        ui.markMessage(taskList, markNumber);
                        storage.saveTasks(taskList);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
                    }
                    break;
                case UNMARK:
                    try {
                        int unmarkNumber = parser.extractTaskNumber(input);
                        taskList.validateUnmarkTaskNumber(unmarkNumber);
                        Commands.unmarkTask(taskList, unmarkNumber);
                        ui.unmarkMessage(taskList, unmarkNumber);
                        storage.saveTasks(taskList);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
                    }
                    break;
                case DEADLINE:
                    try {
                        Task deadlineTask = parser.parseDeadlineTask(input);
                        Commands.addTask(taskList, deadlineTask);
                        ui.addTaskMessage(taskList, deadlineTask);
                        storage.saveTasks(taskList);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
                    }
                    break;
                case EVENT:
                    try {
                        Task eventTask = parser.parseEventTask(input);
                        Commands.addTask(taskList, eventTask);
                        ui.addTaskMessage(taskList, eventTask);
                        storage.saveTasks(taskList);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
                    }
                    break;
                case TODO:
                    try {
                        Task todoTask = parser.parseTodoTask(input);
                        Commands.addTask(taskList, todoTask);
                        storage.saveTasks(taskList);
                        ui.addTaskMessage(taskList, todoTask);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
                    }
                    break;
                case DELETE:
                    try {
                        int deleteNumber = parser.extractDeleteTaskNumber(input);
                        taskList.validateDeleteTaskNumber(deleteNumber);
                        ui.deleteTaskMessage(taskList, deleteNumber);
                        Commands.deleteTask(taskList, deleteNumber);
                        storage.saveTasks(taskList);
                    } catch (JustbotException e) {
                        ui.getJustBotExceptionMessage(e);
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
