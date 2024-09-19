package brainrot;

import brainrot.exceptions.*;
import java.io.IOException;

/**
 * BrainRot class is the main controller for the task management system.
 * It coordinates between the UI, task storage, and task operations.
 * This class handles user commands, manages the task list, and ensures data is saved to storage.
 */
public class BrainRot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a BrainRot.BrainRot object that initializes the user interface, storage, and task list.
     *
     * @param filePath The file path where tasks are stored.
     */
    public BrainRot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (UnknownLoadingError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the main loop to process user commands.
     * This method continually reads user input and performs the appropriate actions.
     */
    public String run(String userInput) {

        String[] parsedInput = Parser.parse(userInput);
        assert parsedInput.length == 2 : "There should only be two parts of the input";
        String action = parsedInput[0];
        String details = parsedInput[1];

        try {

            return switch (action) {
                case "list" -> ui.showTaskList(tasks);
                case "bye" -> ui.showExit();
                case "find" -> findTask(details);
                case "mark" -> markTask(details);
                case "unmark" -> unmarkTask(details);
                case "delete" -> deleteTask(details);
                case "add" -> addTask(details);
                case "tag" -> tagTask(details);
                default -> throw new UnknownCommandException("Unknown command");
            };

        } catch (UnknownCommandException | UnknownActivityException | IOException e) {
            return ui.showCommandError();
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param details The index of the task to be marked.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private String markTask(String details) throws IOException {
        int markIndex = Integer.parseInt(details) - 1;
        tasks.getTask(markIndex).mark();
        storage.save(tasks.getTasks());
        return ui.showMarkMsg(tasks.getTask(markIndex).toString());
    }

    /**
     * Unmarks a task, setting it as not completed.
     *
     * @param details The index of the task to be unmarked.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private String unmarkTask(String details) throws IOException {
        int unmarkIndex = Integer.parseInt(details) - 1;
        tasks.getTask(unmarkIndex).unmark();
        storage.save(tasks.getTasks());
        return ui.showUnMarkMsg(tasks.getTask(unmarkIndex).toString());
    }

    /**
     * Finds and displays tasks that match the given search criteria.
     * If no matching tasks are found, the UI will notify the user.
     *
     * @param details The search term used to match against task descriptions.
     * @throws IOException If an I/O error occurs during the task searching process.
     */
    private String findTask(String details) throws IOException {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task unchecked = tasks.getTask(i);
            if (unchecked.description.contains(details)) {  // Use contains for partial matches
                matchingTasks.addTask(unchecked);
            }
        }
        return ui.showFind(matchingTasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param details The index of the task to be deleted.
     * @throws IOException If an I/O error occurs during task saving.
     */
    private String deleteTask(String details) throws IOException {
        int deleteIndex = Integer.parseInt(details) - 1;
        String taskDetails = tasks.getTask(deleteIndex).toString();
        tasks.removeTask(deleteIndex);
        storage.save(tasks.getTasks());
        return ui.showDeleteMsg(taskDetails);
    }

    /**
     * Adds a new task to the task list based on the details provided.
     *
     * @param details The details of the task to be added.
     * @throws UnknownCommandException  If the command to add the task is unknown.
     * @throws UnknownActivityException If the task description is invalid.
     * @throws IOException              If an I/O error occurs during task saving.
     */
    private String addTask(String details) throws UnknownCommandException, UnknownActivityException, IOException {
        Task newTask;
        if (details.startsWith("todo")) {
            newTask = new ToDo(details.substring(5).trim());
        } else if (details.startsWith("deadline")) {
            String[] parts = details.split("/by");
            newTask = new Deadline(parts[0].substring(9).trim(), parts[1].trim());
        } else if (details.startsWith("event")) {
            String[] parts = details.split("/from");
            String[] timings = parts[1].split("/to");
            newTask = new Event(parts[0].substring(6).trim(), timings[0].trim(), timings[1].trim());
        } else {
            throw new UnknownCommandException("Unknown command");
        }
        tasks.addTask(newTask);
        storage.save(tasks.getTasks());
        return ui.showAddTaskMsg(newTask.toString());
    }

    private String tagTask(String tag) throws IOException {
        int tagIndex = Integer.parseInt(tag) - 1;
        tasks.editTask(tagIndex, tag);
        storage.save(tasks.getTasks());
        return ui.showTagTaskMsg(tag);
    }

}
