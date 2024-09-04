package elster;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import elster.tasks.DeadlineTask;
import elster.tasks.EventTask;
import elster.tasks.Task;
import elster.tasks.ToDoTask;

public class Elster {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Elster(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);

        try {
            taskList = new TaskList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        boolean isStillRunning = true;
        String input;
        Scanner myScanner = new Scanner(System.in);
        try {
            storage.loadFromFile(this.taskList);
        } catch (Elseption e) {
            ui.printErrorMessage(e.getMessage());
        }

        ui.welcomeMessage();

        while (isStillRunning) {
            try {
                input = myScanner.nextLine().strip();
                if (input.equals("bye")) {
                    isStillRunning = false;

                } else if (input.equals("list")) {
                    ui.printList(taskList);

                } else if (input.startsWith("deadline")) {
                    DeadlineTask task = DeadlineTask.of(input);
                    if (!(task == null)) {
                        taskList.addToList(task);
                        ui.addTaskMessage(taskList, task);
                    }

                    storage.writeToFile(taskList);

                } else if (input.startsWith("event")) {
                    EventTask task = EventTask.of(input);
                    if (!(task == null)) {
                        taskList.addToList(task);
                        ui.addTaskMessage(taskList, task);
                    }

                    storage.writeToFile(taskList);

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.substring(7).strip());

                    Task task = taskList.deleteTask(index);
                    ui.deleteTaskMessage(taskList, task);


                } else if (input.startsWith("todo")) {
                    ToDoTask task = ToDoTask.of(input);
                    if (!(task == null)) {
                        taskList.addToList(task);
                        ui.addTaskMessage(taskList, task);
                    }

                    storage.writeToFile(taskList);

                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.substring(5).strip());

                    if (taskList.markTaskAsDone(index)) {
                        ui.taskDoneMessage(taskList.getTask(index));
                    }

                    storage.writeToFile(taskList);

                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.substring(7).strip());

                    if (taskList.unmarkTaskAsUndone(index)) {
                        ui.taskUndoneMessage(taskList.getTask(index));
                    }

                    storage.writeToFile(taskList);

                } else if (input.startsWith("find")) {
                    List<Task> foundTasks = taskList.findByDescription(input.substring(5).strip());
                    ui.findByDescriptionMessage(foundTasks);

                } else {
                    ui.nonsenseErrorMessage();
                }

            } catch (Elseption e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
        ui.goodbyeMessage();
    }

    public static void main(String[] args) {
        Path dataDir = Paths.get("data");
        new Elster(dataDir).run();
    }


}
