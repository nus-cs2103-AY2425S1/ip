import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Elysia {
    private static ArrayList<Task> arrayLists;
    private static String folderName = "data";
    private static String fileName = "elysia.txt";
    private static String filePath = "./data/elysia.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        arrayLists = new ArrayList<>();
        Storage storage = new Storage(arrayLists);
        storage.scanFileContents(filePath);
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        ui.showWelcomeMessage();


        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                ui.showExitMessage();
                storage.handleExit(folderName, fileName, filePath);
                break;
            } else if (input.equals("list")) {
                ui.printList(arrayLists);
            } else if (input.startsWith("mark ")) {
                taskList.markAsDone(arrayLists, input);
            } else if (input.startsWith("unmark ")) {
                taskList.unmarkAsDone(arrayLists, input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {

                if (input.startsWith("todo")) {
                    taskList.addToDos(arrayLists, input.substring(4).trim());
                } else if (input.startsWith("deadline")) {
                    taskList.addDeadline(arrayLists, input.substring(8).trim());
                } else {
                    taskList.addEvent(arrayLists, input.substring(5).trim());
                }

            } else if (input.startsWith("delete")) {
                taskList.deleteTask(arrayLists, input);
            } else {
                ui.showFailMessage();
            }
        }
    }
}