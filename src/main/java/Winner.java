import java.util.Scanner;

public class Winner {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage.checkAndCreateFile();
        Storage.loadTasks(taskList.getTasks());
        WinnerTaskBot(taskList);
        Storage.saveTasks(taskList.getTasks());
    }

    private static void WinnerTaskBot(TaskList taskList) {
        Scanner scanner = new Scanner(System.in);
        Ui.winnerSaysHi();

        while (true) {
            String input = scanner.nextLine();
            try {
                Parser.parseInput(input, taskList);
            } catch (WinnerException e) {
                Ui.applyTemplate(e.getMessage());
            }
            if (input.matches("(?i).*bye.*")) {
                break;
            }
        }
        scanner.close();
    }
}