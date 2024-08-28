import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Stobberi {
    private TaskList taskList;
    private Ui ui;

    public Stobberi() {
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    private void createList() {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();

        while (!temp.equals("bye")) {
            if (temp.equals("list")) {
                taskList.displayList();
            } else {
                String[] parts = temp.split(" ");
                String firstWord = parts[0];
                String restOfTask = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
                if (parts.length == 2 && parts[1].matches("\\d+")) {
                    int number = Integer.parseInt(parts[1]);
                    if (firstWord.equals("mark")) {
                        taskList.markTask(number);
                        temp = scanner.nextLine();
                        continue;
                    } else if (firstWord.equals("unmark")) {
                        taskList.unmarkTask(number);
                        temp = scanner.nextLine();
                        continue;
                    } else if (firstWord.equals("delete")) {
                        taskList.delete(number);
                        temp = scanner.nextLine();
                        continue;
                    }
                }
                if (parts.length == 2 && firstWord.equals("date")) {
                    try {
                        taskList.filterListByDate(parts[1]);
                    } catch (DateTimeParseException e) {
                        Ui.displayForm("Date needs to be in the format dd-MM-yyyy\n Example: 27-12-2004\n" + e.getMessage());
                    }
                    temp = scanner.nextLine();
                    continue;
                }
                try {
                    taskList.addTask(firstWord, restOfTask);
                } catch (StobberiException e) {
                    Ui.displayForm(e.getMessage());
                } catch (DateTimeParseException e) {
                    Ui.displayForm("Date and Time needs to be in the format dd-MM-yyyy HHmm'hrs'\n Example: 27-12-2004 1700hrs\n" + e.getMessage());
                }
            }
            temp = scanner.nextLine();
        }
    }

    public void run() {
        Storage storage = new Storage("data/list.txt");
        taskList.updateTaskList(storage.getList());
        ui.greet();
        createList();
        ui.goodbye();
        storage.saveList(taskList.getListOfTasks());
    }

    public static void main(String[] args) {
        new Stobberi().run();
    }
}
