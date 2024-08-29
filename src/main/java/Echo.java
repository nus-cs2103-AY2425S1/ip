import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Echo {
    private String word;
    private TaskList list;
    private final Storage storage;
    private Ui ui;

    public Echo() {
        this.storage = new Storage("data/chatHistory.txt");
        try {
            this.list = storage.load();
        } catch (IOException e) {
            this.list = new TaskList();
        }
        this.ui = new Ui();
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void echoOut() {

            String description = word;
            String[] parts = description.split(" ", 2);
            String command = parts[0];

        switch (command) {
            case "list":
                list.printList();
                System.out.println(ui.line());
                break;
            case "mark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to mark \n" + ui.line());
                } else {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        list.mark(index);
                    } catch(NumberFormatException e) {
                        System.out.println("Please enter a valid task number\n");
                    }
                }
                System.out.print(ui.line());
                break;

            case "unmark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to unmark \n" + ui.line());
                } else {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        list.unmark(index);
                    } catch(NumberFormatException e) {
                        System.out.println("Please enter a valid task number\n");
                    }
                }
                System.out.print(ui.line());
                break;

            case "todo":
                try {
                    Task toDoTask = new ToDoTask(parts[1], false);
                    list.addTask(toDoTask);
                    System.out.print(ui.affirm() + toDoTask.getDescription() + "\n" + ui.line() +
                            String.format("Now you have %d tasks in the list\n", list.size()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("Oh no! Please input a ToDo description!");
                }
                break;

            case "deadline":
                try {
                    int byIndex = parts[1].indexOf("/by");
                    String desc = parts[1].substring(0, byIndex);
                    String deadline = parts[1].substring(byIndex + 4);
                    Task deadlineTask = new DeadlineTask(desc, false, deadline);
                    list.addTask(deadlineTask);
                    System.out.print(ui.affirm() + deadlineTask.getDescription() +
                                    "\n" + ui.line() + String.format("Now you have %d tasks in the list\n", list.size()));
                } catch  (StringIndexOutOfBoundsException e) {
                    System.out.println("Incorrect format of adding deadline tasks. " +
                            "Use '/by to specify the deadline after the task description");
                } catch (DateTimeParseException e) {
                    System.out.println("Please input the correct deadline format /by yyyy-MM-dd XXXX <- Time");
                }

                break;
            case "event":
                try {
                    int fromIndex = parts[1].indexOf("/from");
                    int toIndex = parts[1].indexOf("/to");

                    String details = parts[1].substring(0, fromIndex);
                    String start = parts[1].substring(fromIndex + 6, toIndex - 1);
                    String end = parts[1].substring(toIndex + 4);

                    Task eventTask = new EventTask(details, false, start, end);
                    list.addTask(eventTask);

                    System.out.print(ui.affirm() + eventTask.getDescription() +
                            "\n" + ui.line() + String.format("Now you have %d tasks in the list\n", list.size()));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Incorrect format of adding event tasks. " +
                            "Use '/from to specify the start and /to to specify the end " +
                            "after the task description");
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Incorrect format of adding event tasks timings. /from yyyy-MM-dd XXXX <- Time" +
                            "and /to yyyy-MM-dd XXXX <- Time\n");
                }

                break;
            case "delete":
                int index = Integer.parseInt(parts[1]) - 1;
                list.removeTask(index);
                break;

            case "bye":
                ui.goodbye();
                try {
                    storage.saveTasksToFile(this.list);
                } catch (IOException e) {
                    System.out.print("Unable to save chat data to local disk!");
                }
                break;
            default:
                ui.invalidCommand();
                break;
        }
    }
}
