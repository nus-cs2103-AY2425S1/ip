import java.util.Scanner;


public class Tars {
    public void run() {

        Storage storage = new Storage();
        Response response = new Response();
        TaskList tasks = new TaskList(storage.updateTasks());

        response.intro();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.print(">>>");
            String userInput = scanner.nextLine().trim();

            switch (userInput) {
                case "bye":
                    response.outro();
                    scanner.close();
                    exit = true;

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
