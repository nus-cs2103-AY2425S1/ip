package wenjigglybot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class WenJigglyBot {
    private TaskList tasks;
    private final Ui ui;

    public WenJigglyBot() {

        tasks = new TaskList();
        ui = new Ui();
        try {
            Storage storage = new Storage("./data/data.txt");
            storage.load(tasks);
        } catch (WenJigglyBotException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new WenJigglyBot().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.intro();
        String task;
        boolean flag = true;
        while (flag) {
            task = scanner.nextLine();
            task = task.trim();
            Command command = null;
            try {
                command = Parser.parseCommand(task);
            } catch (InvalidCommandException e) {
                System.out.println(e);
                continue;
            }

            String[] strings;
            String action;
            int idx;
            String taskName;

            switch (Objects.requireNonNull(command)) {
            case LIST:
                ui.displayTasks(tasks);
                break;
            case MARK:
                strings = task.split(" ");
                action = "mark";
                idx = Integer.parseInt(strings[1].trim()) - 1;
                toggleTask(action, idx);
                break;
            case UNMARK:
                action = "unmark";
                strings = task.split(" ");
                idx = Integer.parseInt(strings[1].trim()) - 1;
                toggleTask(action, idx);
                break;
            case TODO:
                taskName = task.replaceFirst("todo", "").trim();
                addTask(new ToDoTask(taskName));
                break;
            case DEADLINE:
                try {
                    String[] parts = Parser.processDeadlineTask(task);
                    taskName = parts[0].trim();
                    String deadline = parts[1].trim();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(deadline);
                        addTask(new DeadlineTask(taskName, date));
                    } catch (DateTimeParseException e) {
                        System.out.println("Incorrect date format, please input in yyyy-mm-dd format!");
                    }
                } catch (DeadlineException deadlineException) {
                    System.out.println(deadlineException);
                }
                break;
            case EVENT:
                // Split the string by "/from" and "/to"
                try {
                    String[] processedEvent = Parser.processEventTask(task);
                    addTask(new EventTask(processedEvent[0], processedEvent[1], processedEvent[2]));
                } catch (EventException eventException) {
                    System.out.println(eventException);
                }
                break;
            case DELETE:
                strings = task.split(" ");
                idx = Integer.parseInt(strings[1].trim()) - 1;
                deleteTask(idx);
                break;
            case BYE:
                flag = false;
                break;

            }
        }
        System.out.println("Goodbye!");
    }


    private void deleteTask(int idx) {
        if (idx < 0 || idx > tasks.size() - 1) {
            System.out.println("You entered an invalid index you fool!");
            return;
        }
        ui.showLine();
        ui.showDeleteTask(tasks, idx);
        tasks.remove(idx);
        ui.showTaskCount(tasks);
        ui.showLine();
    }

    private void toggleTask(String action, int idx) {
        // handle invalid index
        if (idx < 0 || idx > tasks.size() - 1) {
            System.out.println("You entered an invalid index you fool!");
            return;
        }
        Task task = tasks.get(idx);
        if (action.equals("mark")) {
            ui.showLine();
            task.markTask();
            ui.showCompletedTask(task);
            ui.showLine();
        } else {
            ui.showLine();
            tasks.get(idx).unmarkTask();
            ui.showUncompletedTask(task);
            ui.showLine();
        }
    }

    private void addTask(Task task) {
        tasks.addTask(task);
        Storage.saveTasksToFile(tasks);
        ui.showLine();
        ui.showAddedTask(task);
        ui.showTaskCount(tasks);
        ui.showLine();
    }


}