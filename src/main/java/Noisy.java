import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Noisy {


    public static void main(String[] args) {
        Parser parser = new Parser();
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(storage.loadTasks());
        Task task = null;
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                ui.printGoodbye();
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                ui.printList(taskList);
                continue;
            }
            if (input.startsWith("mark ")) {
                String[] string = input.split(" ");
                Integer index = Integer.parseInt(string[1]);
                taskList.markDoneFromList(index - 1);
                ui.printMark(index, taskList);
                continue;
            }

            task = null;
            try {
                switch (input.split(" ")[0]) {
                    case "todo":
                        if (input.split(" ", 2).length < 2) {
                            throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new Todo(input.split(" ", 2)[1], false);
                        break;
                    case "Deadline":
                        String[] string = input.split(" ", 3);
                        task = new Deadline(string[1], false, parser.parseDate(string[2]));
                        break;
                    case "Event":
                        String[] eventString = input.split(" ", 4);
                        task = new Event(eventString[1], false, parser.parseDate(eventString[2]), parser.parseDate(eventString[3]));
                        break;
                    case "delete":
                        String[] deleteString = input.split(" ");
                        Integer index = Integer.parseInt(deleteString[1]);
                        Task deletedTask = taskList.getTask(index - 1);
                        taskList.deleteFromList(index - 1);
                        ui.printDelete(index - 1, taskList);
                        continue;
                    default:
                        throw new NoisyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NoisyException e) {
                System.out.println(e);
                continue;
            }


            taskList.addToList(task);
            storage.saveTasks(taskList.getTasks());
            ui.printAdd(task, taskList);
        }
    }
}

