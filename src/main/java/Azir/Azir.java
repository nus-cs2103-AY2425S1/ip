package Azir;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Azir {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String loadMessage = "";

    /**
     * Constructs a new Azir and checks for tasks at the specified file path.
     *
     * @param filePath file path of stored tasks.
     * @throws IOException If some error occurs.
     */
    public Azir(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            if (tasks.getSize() == 0) {
                loadMessage = "Your current list does not have any tasks";
            } else {
                loadMessage = "These are your saved tasks:\n";
                for (int i = 0; i < tasks.getSize(); i++) {
                    loadMessage += tasks.getTask(i) + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            File newFile = new File("./data/Azir.txt");
            if (!Files.exists(Paths.get("./data"))) {
                Files.createDirectory(Paths.get("./data"));
            }
            newFile.createNewFile();
            tasks = new TaskList();
            loadMessage = "Your current list does not have any tasks";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Azir() throws IOException {
        this("./data/Azir.txt");
    }

    public String getLoadMessage() {
        return loadMessage;
    }

    private String addIfNoDuplicate(Task task, String response, String taskType) throws AzirException {
        boolean isInTaskList = this.tasks.isInTaskList(task);
        if (!isInTaskList) {
            this.tasks.addTask(task);
            response += ui.showCommandEndMessage(taskType, task.toString());
            response += ui.showTaskNumber(this.tasks.getSize());
            return response;
        } else {
            throw new AzirException("This task is already in your task list.");
        }
    }

    private String handleListInput(String response, String input) {
        response += ui.showCommandEndMessage(input);
        for (int i = 0; i < tasks.getSize(); i++) {
            response += String.format("%d. %s\n", i + 1, tasks.getTask(i));
        }
        return response;
    }

    private String handleMarkInput(String response, String taskIndex, String command) {
        Task chosenTask = tasks.getTask(Integer.valueOf(taskIndex) - 1);
        chosenTask.setDone();
        response += ui.showCommandEndMessage(command, chosenTask.toString());
        return response;
    }

    private String handleUnmarkInput(String response, String taskIndex, String command) {
        Task task = tasks.getTask(Integer.valueOf(taskIndex) - 1);
        task.setNotDone();
        response += ui.showCommandEndMessage(command, task.toString());
        return response;
    }

    private String handleDeleteInput(String response, String taskIndex, String command) {
        Task deletedTask = tasks.getTask(Integer.valueOf(taskIndex) - 1);
        tasks.DeleteTask(Integer.valueOf(taskIndex) - 1);
        response += ui.showCommandEndMessage(command, deletedTask.toString());
        response += ui.showTaskNumber(tasks.getSize());
        return response;
    }

    private String handleTodoInput(String response, String input, String taskType, String description) throws AzirException {
        if (input.length() == 4) {
            throw new AzirException("todo cannot have an empty description. " +
                    "Format: todo [description]");
        }
        Task todoTask = new Todo(description);
        return this.addIfNoDuplicate(todoTask, response, taskType);
    }

    private String handleDeadlineInput(String response, String taskType, String description, String date) throws AzirException{
        String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            Task deadlineTask = new Deadline(description, LocalDate.parse(date, formatter));
            response = this.addIfNoDuplicate(deadlineTask, response, taskType);
            return response;
        } catch (DateTimeParseException e) {
            throw new AzirException("deadline needs to be in the following format: yyyy-mm-dd");
        }
    }

    private String handleEventInput(String response, String taskType, String description,
                                    String startDate, String endDate) throws AzirException {
        Task eventTask = new Event(description, startDate, endDate);
        response = this.addIfNoDuplicate(eventTask, response, taskType);
        return response;
    }

    private String handleFindInput(String response, String command, String keyword) {
        response += ui.showCommandEndMessage(command);
        for (int i = 0; i < tasks.getSize(); i++) {
            String description = tasks.getTask(i).getDescription();
            String[] arr = description.split(" ");
            if (Arrays.stream(arr).anyMatch(str -> str.equals(keyword))) {
                response += String.format("%d. %s\n", i + 1, tasks.getTask(i));
            }
        }
        return response;
    }

    private String handleByeInput() {
        return ui.showExit();
    }

    private void handleWriteTask() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        // Write tasks to Azir.txt
        for (int i = 0; i < tasks.getSize(); i++) {
            lines.add(tasks.getTask(i).formatText());
        }
        storage.write("./data/Azir.txt", lines);
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input User's chat input.
     */
    public String getResponse(String input) throws IOException {
        String response = "";
        try {
            String[] result = Parser.parse(input, tasks.getSize());
            switch (result[0]) {
            case "list":
                response = handleListInput(response, input);
                break;
            case "mark":
                response = handleMarkInput(response, result[1], result[0]);
                break;
            case "unmark":
                response = handleUnmarkInput(response, result[1], result[0]);
                break;
            case "delete":
                response = handleDeleteInput(response, result[1], result[0]);
                break;
            case "todo":
                response = handleTodoInput(response, input, result[0], result[1]);
                break;
            case "deadline":
                response = handleDeadlineInput(response, result[0], result[1], result[2]);
                break;
            case "event":
                response = handleEventInput(response, result[0], result[1], result[2], result[3]);
                break;
            case "find":
                response = handleFindInput(response, result[0], result[1]);
                break;
            case "bye":
                response = handleByeInput();
                break;
            default:
                assert false: result[0];
            }
        } catch (AzirException e) {
            return ui.showError(e.getMessage());
        } finally {
            handleWriteTask();
        }
        return response;
    }
}
