import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Luna {

    private Storage storage;
    private TaskList tasks;

    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.loadTasks());
        boolean isRunning = true;

        while (isRunning) {
            String input = scanner.nextLine();

            try {
                Command command = Parser.parse(input);
                command.execute(tasks, storage);
                if (command instanceof ExitCommand) {
                    isRunning = false;
                }
            } catch (LunaException e) {
                System.out.println(e.getMessage());
            } finally {
                storage.saveTasks(tasks.getTasks());
            }
        }
        scanner.close();
    }
}
