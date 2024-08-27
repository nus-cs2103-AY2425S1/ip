
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LeBron {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public LeBron(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        int taskCount = taskList.size();
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (scanner.hasNextLine()) {
            try {
                input = ui.getUserCommand().trim();

                String[] parts = input.split(" ", 2);

                if (input.equals("bye")) {
                    ui.showGoodbyeMessage();
                    System.out.println("------------------------");
                    try {
                        storage.saveTasks(taskList);
                    } catch (IOException e) {
                    }
                    scanner.close();
                    break;
                } else if (input.equals("list")) {
                    for (int i = 1; i <= taskCount; i++) {
                        Task task = taskList.getTask(i - 1);
                        System.out.println(String.format("%d. %s", i, task.toString()));
                    }
                    System.out.println("------------------------");
                } else if (parts[0].equals("mark")) {
                    if (parts.length > 1 && isNumeric(parts[1])) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskCount >= taskNumber) {
                            Task task = taskList.getTask(taskNumber - 1);
                            task.markAsDone();
                            System.out.println("Alright bro, great work");
                            System.out.println(task.toString());
                            System.out.println("------------------------");
                        } else {
                            throw new LeBronException("I can't mark that bro");
                        }
                    } else {
                        throw new LeBronException("Mark command needs a number bro");
                    }
                } else if (parts[0].equals("delete")) {
                    if (parts.length > 1 && isNumeric(parts[1])) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskCount >= taskNumber) {
                            Task task = taskList.getTask(taskNumber - 1);
                            taskList.deleteTask(taskNumber - 1);
                            taskCount--;
                            System.out.println("Alright bro, I've deleted that task.");
                            System.out.println(task.toString());
                            System.out.println("------------------------");
                        } else {
                            throw new LeBronException("I can't delete that bro");
                        }
                    } else {
                        throw new LeBronException("Mark command needs a number bro");
                    }
                } else if (parts[0].equals("unmark")) {
                    if (parts.length > 1 && isNumeric(parts[1])) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        if (taskCount >= taskNumber) {
                            Task task = taskList.getTask(taskNumber - 1);
                            task.markAsUndone();
                            System.out.println("Let's get it done soon!");
                            System.out.println(task.toString());
                            System.out.println("------------------------");
                        } else {
                            throw new LeBronException("I can't unmark that bro");
                        }
                    } else {
                        throw new LeBronException("Unmark command needs a number bro");
                    }
                } else if (parts[0].equals("todo")) {
                    if (parts.length > 1) {
                        ToDos todo = new ToDos(parts[1].trim());
                        taskList.addTask(todo);
                        taskCount++;
                        System.out.println("Gotchu, I added the task: ");
                        System.out.println(todo.toString());
                        System.out.println(String.format("Now you have %d tasks in the list", taskCount));
                        System.out.println("------------------------");
                    } else {
                        throw new LeBronException("Todo task can't be empty bro!!");
                    }
                } else if (parts[0].equals("deadline")) {
                    if (parts.length > 1) {
                        String[] splStr = parts[1].split("/by ", 2);
                        LocalDate date = LocalDate.parse(splStr[1]);
                        Deadlines deadline = new Deadlines(splStr[0].trim(), date);
                        taskList.addTask(deadline);
                        taskCount++;
                        System.out.println("Gotchu, I added the task: ");
                        System.out.println(deadline.toString());
                        System.out.println(String.format("Now you have %d tasks in the list", taskCount));
                        System.out.println("------------------------");
                    } else {
                        throw new LeBronException("Deadline task can't be empty bro!!");
                    }
                } else if (parts[0].equals("event")) {
                    if (parts.length > 1) {
                        if (parts[1].contains("/from")) {
                            String[] splStrings = parts[1].split("/from", 2);
                            String taskDescription = splStrings[0].trim();
                            LocalDate start = LocalDate.now();
                            LocalDate end = LocalDate.now();
                            if (splStrings[1].contains("/to")) {
                                String[] timeParts = splStrings[1].split("/to", 2);
                                start = LocalDate.parse(timeParts[0].trim());
                                end = LocalDate.parse(timeParts[1].trim());
                            } else {
                                throw new LeBronException("Your event has to end bro!");
                            }
                            Event event = new Event(taskDescription, start, end);
                            taskList.addTask(event);
                            taskCount++;
                            System.out.println("Gotchu, I added the task: ");
                            System.out.println(event.toString());
                            System.out.println(String.format("Now you have %d tasks in the list", taskCount));
                            System.out.println("------------------------");
                        } else {
                            throw new LeBronException("Your event has to start bro!");
                        }
                    } else {
                        throw new LeBronException("Event task can't be empty bro!");
                    }
                } else {
                    throw new LeBronException("What does that mean bro?");
                }
            } catch (LeBronException e) {
                System.out.println("------------------------");
                System.out.println(e.getMessage());
                System.out.println("------------------------");
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new LeBron("./data/lebron.txt").run();
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
