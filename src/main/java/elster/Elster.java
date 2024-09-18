package elster;

import java.nio.file.Path;
import java.util.List;

import elster.tasks.DeadlineTask;
import elster.tasks.EventTask;
import elster.tasks.Task;
import elster.tasks.ToDoTask;

/**
 * Elster class that handles the logic for processing user inputs.
 */
public class Elster {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for Elster class. Initialises the Ui, Storage and Tasklist components.
     *
     * @param filePath file path of the save file.
     */
    public Elster(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            taskList = new TaskList();
            storage.loadFromFile(this.taskList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Run function of the Elster instance, handles the loading of the save file from storage as
     * well as general logic of the chatbot.
     */
    public String parse(String input) {
        try {
            input = input.strip().toLowerCase();
            if (input.equals("bye")) {
                return ui.goodbyeMessage();

            } else if (input.equals("list")) {
                return ui.printList(taskList);

            } else if (input.startsWith("deadline")) {
                DeadlineTask task = DeadlineTask.of(input);
                taskList.addToList(task);
                storage.writeToFile(taskList);
                return ui.addTaskMessage(taskList, task);

            } else if (input.startsWith("event")) {
                EventTask task = EventTask.of(input);
                taskList.addToList(task);
                storage.writeToFile(taskList);
                return ui.addTaskMessage(taskList, task);

            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7).strip());

                Task task = taskList.deleteTask(index);
                assert task != null : "Bug when deleting task";
                return ui.deleteTaskMessage(taskList, task);


            } else if (input.startsWith("todo")) {
                ToDoTask task = ToDoTask.of(input);
                taskList.addToList(task);
                storage.writeToFile(taskList);
                return ui.addTaskMessage(taskList, task);

            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5).strip());

                if (taskList.markTaskAsDone(index)) {
                    storage.writeToFile(taskList);
                    return ui.taskDoneMessage(taskList.getTask(index));
                }

            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7).strip());

                if (taskList.unmarkTaskAsUndone(index)) {
                    storage.writeToFile(taskList);
                    return ui.taskUndoneMessage(taskList.getTask(index));
                }

            } else if (input.startsWith("find")) {
                List<Task> foundTasks = taskList.findByDescription(input.substring(5).strip());
                return ui.findByDescriptionMessage(foundTasks);

            }

        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.nonsenseErrorMessage();
    }
}
