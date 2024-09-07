package noisy;

import java.util.Scanner;

/**
 * The main entry point for the Noisy task management application.
 * This class handles the user input loop, processes commands, and interacts with the
 * {@code Parser}, {@code Storage}, {@code TaskList}, and {@code Ui} classes to manage tasks.
 */
public class Noisy {


    /**
     * The main method of the Noisy application.
     * It initializes the necessary components and processes user input to manage tasks.
     * Users can add tasks, mark them as done, delete tasks, or view the task list.
     *
     * @param args Command-line arguments (not used in this application).
     */
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

            if (input.startsWith("find ")) {
                String keyword = input.split(" ", 2)[1];
                ui.printFind(taskList, keyword);
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
                        input = input.trim();
                        String[] string = input.split(" ", 4);
                        task = new Deadline(string[1], Boolean.parseBoolean(string[2]), parser.parseDate(string[3]));
                        break;
                    case "Event":
                        String[] eventString = input.split(" ", 5);
                        task = new Event(eventString[1], false, parser.parseDate(eventString[3]), parser.parseDate(eventString[4]));
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

