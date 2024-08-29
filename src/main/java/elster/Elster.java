package elster;

import elster.tasks.DeadlineTask;
import elster.tasks.EventTask;
import elster.tasks.Task;
import elster.tasks.ToDoTask;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
        boolean byeSentinel = true;
        String input;
        Scanner myScanner = new Scanner(System.in);
        try {
            storage.loadFromFile(this.taskList);
        } catch (Elseption e) {
            ui.loadFileErrorMessage();
        }

        ui.welcomeMessage();

        while (byeSentinel) {
            input = myScanner.nextLine().strip();
            if (input.equals("bye")) {
                byeSentinel = false;

            } else if (input.equals("list")) {
                ui.printList(taskList);

            } else if (input.startsWith("deadline")) {
                DeadlineTask task = DeadlineTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                    ui.addTaskMessage(taskList, task);
                }

                try {
                    storage.writeToFile(taskList);
                } catch (Elseption e) {
                    ui.saveFileErrorMessage();
                }

            } else if (input.startsWith("event")) {
                EventTask task = EventTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                    ui.addTaskMessage(taskList, task);
                }

                try {
                    storage.writeToFile(taskList);
                } catch (Elseption e) {
                    ui.saveFileErrorMessage();
                }

            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7).strip());

                try {
                    Task task = taskList.deleteTask(index);
                    ui.deleteTaskMessage(taskList, task);

                } catch (Elseption e) {
                    ui.indexOutOfBoundsErrorMessage();
                }


            } else if (input.startsWith("todo")) {
                ToDoTask task = ToDoTask.of(input);
                if (!(task == null)) {
                    taskList.addToList(task);
                    ui.addTaskMessage(taskList, task);
                }

                try {
                    storage.writeToFile(taskList);
                } catch (Elseption e) {
                    ui.saveFileErrorMessage();
                }

            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5).strip());

                try {
                    if (taskList.markTaskAsDone(index)) {
                        ui.taskDoneMessage(taskList.getTask(index));
                    } else {
                        ui.alreadyDoneErrorMessage();
                    }

                } catch (Elseption e) {
                    ui.indexOutOfBoundsErrorMessage();
                }

                try {
                    storage.writeToFile(taskList);
                } catch (Elseption e) {
                    ui.saveFileErrorMessage();
                }

            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7).strip());

                try {
                    if (taskList.unmarkTaskAsUndone(index)) {
                        ui.taskUndoneMessage(taskList.getTask(index));
                    } else {
                        ui.alreadyUndoneErrorMessage();
                    }

                } catch (Elseption e) {
                    ui.indexOutOfBoundsErrorMessage();
                }

                try {
                    storage.writeToFile(taskList);
                } catch (Elseption e) {
                    ui.saveFileErrorMessage();
                }

            } else {
                ui.nonsenseErrorMessage();
            }
        }

        ui.goodbyeMessage();
    }

    public static void main(String[] args) {
        Path dataDir = Paths.get("data");
        new Elster(dataDir).run();
    }


}
