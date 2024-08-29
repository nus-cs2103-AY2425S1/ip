import java.util.Scanner;

public class Meep {
    public static void main(String[] args) {
        FormattedPrint.greeting();
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = FileUtils.loadTasks();
        boolean isDone = false;
        // keep scanning for user input
        while (!isDone) {
            FormattedPrint.inputWaiting();
            String input = scanner.nextLine();
            isDone = Parser.checkCommand(input, taskList);
            FileUtils.saveTasks(taskList);
        }
    }
}
