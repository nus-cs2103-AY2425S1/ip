import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Asura {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Asura(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AsuraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.showIntroduction();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AsuraException e) {
                ui.showError(e.getMessage());
            }

        }

        ui.showGoodbye();
    }


    public static void main(String[] args) throws AsuraException {
        new Asura("data/asura.txt").run();
        Scanner scanner = new Scanner(System.in);
        String savePath = "./data/asura.txt";
        List<Task> tasks = initializeData(savePath);

        List<String> input = Arrays.asList(scanner.nextLine().split(" "));
        String prefix = input.get(0);

        while (!prefix.equals("bye")) {
            StringBuilder output = new StringBuilder();
            switch (prefix) {
                case "list":
                    output.append("Here are the tasks in your list:\n");
                    for (int i=0; i<tasks.size(); i++) {
                        output.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
                    }
                    System.out.println(formatResponse(output.toString()));
                    break;
                case "mark":
                    if (1 >= input.size()) {
                        // if user inputted mark by itself
                        throw new AsuraException("Please indicate a task to mark.");
                    } else {
                        int selection = Integer.parseInt(input.get(1)) - 1;
                        tasks.get(selection).markAsDone();
                        saveTasks(tasks, savePath);
                        output.append("Nice! I've marked this task as done:").append("\n").append(tasks.get(selection).toString());
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                case "unmark":
                    if (1 >= input.size()) {
                        // if user inputted mark by itself
                        throw new AsuraException("Please indicate a task to unmark.");
                    } else {
                        int selection = Integer.parseInt(input.get(1)) - 1;
                        tasks.get(selection).markAsNotDone();
                        saveTasks(tasks, savePath);
                        output.append("OK, I've marked this task as not done yet:").append("\n").append(tasks.get(selection).toString());
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                case "todo":
                    if (1 >= input.size()) {
                        throw new AsuraException("The description todo cannot be empty.");
                    }
                    String taskString = String.join(" ", input.subList(1, input.size()));
                    Todo newTodo = new Todo(taskString);
                    tasks.add(newTodo);
                    saveTasks(tasks, savePath);
                    output.append("Got it. I've added this task:\n").append(newTodo.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                    System.out.println(formatResponse(output.toString()));
                    break;
                case "deadline":
                    int byIndex = input.indexOf("/by");
                    try {
                        List<String> descriptionArray = input.subList(1, byIndex);
                        if (descriptionArray.isEmpty()) {
                            throw new AsuraException("The description todo cannot be empty.");
                        }
                        List<String> dateArray = input.subList(byIndex + 1, input.size());
                        if (dateArray.isEmpty()) {
                            throw new AsuraException("The date cannot be empty.");
                        }
                        String dateStr = String.join(" ", dateArray);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                        Deadline newDeadline = new Deadline(String.join(" ", descriptionArray), dateTime);
                        tasks.add(newDeadline);
                        saveTasks(tasks, savePath);
                        output.append("Got it. I've added this task:\n").append(newDeadline.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                        System.out.println(formatResponse(output.toString()));
                    } catch (Exception e) {
                        throw new AsuraException(e.getMessage());
                    }
                    break;
                case "event":
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    try {
                        List<String> descriptionArray = input.subList(1, fromIndex);
                        if (descriptionArray.isEmpty()) {
                            throw new AsuraException("The description todo cannot be empty.");
                        }
                        List<String> fromArray = input.subList(fromIndex + 1, toIndex);
                        if (fromArray.isEmpty()) {
                            throw new AsuraException("The from date cannot be empty.");
                        }
                        List<String> toArray = input.subList(toIndex + 1, input.size());
                        if (toArray.isEmpty()) {
                            throw new AsuraException("The to date cannot be empty.");
                        }
                        Event newEvent = new Event(String.join(" ", descriptionArray), String.join(" ", fromArray), String.join(" ", toArray));
                        tasks.add(newEvent);
                        saveTasks(tasks, savePath);
                        output.append("Got it. I've added this task:\n").append(newEvent.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                        System.out.println(formatResponse(output.toString()));
                    }
                    catch (Exception e) {
                        throw new AsuraException(e.getMessage());
                    }
                    break;
                case "delete":
                    if (1 >= input.size()) {
                        throw new AsuraException("Please indicate a task to delete.");
                    } else {
                        int selection = Integer.parseInt(input.get(1)) - 1;
                        output.append("Noted! I've removed this task :").append("\n").append(tasks.get(selection).toString()).append("\n").append("Now you have ").append(tasks.size() - 1).append(" tasks in your list.\n");
                        tasks.remove(selection);
                        saveTasks(tasks, savePath);
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                default:
                    throw new AsuraException("Invalid input");
            }

            input = Arrays.asList(scanner.nextLine().split(" "));
            prefix = input.get(0);
        }
    }
}
