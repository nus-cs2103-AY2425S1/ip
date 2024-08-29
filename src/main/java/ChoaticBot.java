import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

import actions.ActionManager;
import actions.Action;
import inputs.InputProcessor;
import inputs.ProcessedInput;
import tasks.TaskList;
import tasks.Task;
import tasks.ToDos;
import tasks.Deadlines;
import tasks.Events;

import static ui.Ui.printText;

public class ChoaticBot {
    public TaskList tasklist;
    private static final String DATA_FILE_PATH = "./data/choaticbot.txt";

    public ChoaticBot() {
        this.tasklist = new TaskList();
        loadTasks();
    }

    public static void welcome() {
        String welcomeMsg = "Hello! I'm ChoaticBot\n" + "What can I do for you?\n";
        printText(welcomeMsg);
    }

    public static void bye() {
        String byeMsg = "Bye. Hope to see you again soon!\n";
        printText(byeMsg);
    }

    private void loadTasks() {
        try {
            Files.createDirectories(Paths.get("./data"));
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 2) {
                        String type = parts[0];
                        String description = parts[1];
                        boolean isDone = parts.length > 2 && parts[2].equals("true");
                        Task task;
                        switch (type) {
                            case "T":
                                task = new ToDos(description);
                                break;
                            case "D":
                                String deadline = parts.length > 3 ? parts[3] : "";
                                task = new Deadlines(description, deadline);
                                break;
                            case "E":
                                String from = parts.length > 3 ? parts[3] : "";
                                String to = parts.length > 4 ? parts[4] : "";
                                task = new Events(description, from, to);
                                break;
                            default:
                                continue;
                        }
                        task.setDone(isDone);
                        tasklist.addTask(task);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            printText("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    public void saveTasks() {
        try {
            Files.createDirectories(Paths.get("./data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH));
            for (Task task : tasklist.getTasks()) {
                String line = "";
                if (task instanceof ToDos) {
                    line = "T|" + task.getName() + "|" + task.isComplete();
                } else if (task instanceof Deadlines) {
                    Deadlines deadline = (Deadlines) task;
                    line = "D|" + task.getName() + "|" + task.isComplete() + "|" + deadline.getDeadline();
                } else if (task instanceof Events) {
                    Events event = (Events) task;
                    line = "E|" + task.getName() + "|" + task.isComplete() + "|" + event.getFrom() + "|" + event.getTo();
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            printText("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ChoaticBot chatBot = new ChoaticBot();
        InputProcessor processor = new InputProcessor();
        Scanner scanner = new Scanner(System.in);
        ActionManager actionManager = new ActionManager();
        boolean isEnd = false;

        ChoaticBot.welcome();
        while (!isEnd) {
            String userInput = scanner.nextLine();
            ProcessedInput processedInput = processor.processInput(userInput);
            Action action = actionManager.createAction(processedInput, chatBot.tasklist);
            action.execute();
            chatBot.saveTasks(); // Save tasks after each action
            isEnd = action.isEnd();
        }
        ChoaticBot.bye();
    }
}