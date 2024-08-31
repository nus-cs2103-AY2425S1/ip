package tars;

import tars.storage.Storage;
import tars.tasks.TaskList;
import java.util.Scanner;
public class Tars {
    public void run() {

        Storage storage = new Storage();
        Response response = new Response();
        TaskList tasks = new TaskList(storage.updateTasks());

        response.intro();

        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;

        while (!isExit) {
            System.out.print(">>>");
            String userInput = scanner.nextLine().trim();

            switch (userInput) {
            case "bye":
                response.outro();
                scanner.close();
                isExit = true;

                break;

            case "list":
                response.showList(tasks);
                break;

            default:
                response.generateResponse(userInput, tasks);
                break;
            }
        }

        storage.saveTasks(tasks);



    }

    public static void main(String[] args) {

        Tars tars = new Tars();
        tars.run();
    }
}
