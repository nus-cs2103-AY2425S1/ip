import java.util.ArrayList;
import java.util.Scanner;

public class Pikappi {
    static Scanner reader = new Scanner(System.in);
    static Ui ui = new Ui();
    static String command;
    static Storage storage = new Storage("data/pikappi.txt");
    static TaskList tasks = new TaskList();
    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) throws PikappiException {
        tasks = storage.load();
        ui.greet();

        while (true) {
            command = reader.nextLine();
            ui.showLine();
            if (command.equals("bye")) {
                storage.save(tasks);
                ui.goodbye();
                return;
            } else if (command.equals("list")) {
                tasks.listTasks();
            } else if (command.startsWith("mark")) {
               tasks.markTask(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("unmark")) {
                tasks.unmarkTask(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("todo")) {
                try {
                    tasks.addTask(command, TaskType.TODO);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("deadline")) {
                try {
                    tasks.addTask(command, TaskType.DEADLINE);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("event")) {
                try {
                    tasks.addTask(command, TaskType.EVENT);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("delete")) {
                try {
                    tasks.deleteTask(command);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new PikappiException("Pi-ka..?? I don't understand..");
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            }
            ui.showLine();
        }
    }
}
