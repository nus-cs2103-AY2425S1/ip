package Azir;

import java.util.ArrayList;
import java.util.Scanner;
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

    public Azir(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            if (tasks.getSize() == 0) {
                loadMessage = "Your current list does not have any tasks";
            } else {
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws IOException {
        String response = "";
        try {
            String[] result = Parser.parse(input, tasks.getSize());
            switch (result[0]) {
            case "list":
                response += ui.showCommandEndMessage(input);
                for (int i = 0; i < tasks.getSize(); i++) {
                    response += String.format("%d. %s\n", i + 1, tasks.getTask(i));
                }
                break;
            case "mark":
                Task chosenTask = tasks.getTask(Integer.valueOf(result[1]) - 1);
                chosenTask.setDone();
                response += ui.showCommandEndMessage(result[0], chosenTask.toString());
                break;
            case "unmark":
                Task task = tasks.getTask(Integer.valueOf(result[1]) - 1);
                task.setNotDone();
                response += ui.showCommandEndMessage(result[0], task.toString());
                break;

            case "delete":
                Task deletedTask = tasks.getTask(Integer.valueOf(result[1]) - 1);
                tasks.DeleteTask(Integer.valueOf(result[1]) - 1);
                response += ui.showCommandEndMessage(result[0], deletedTask.toString());
                response += ui.showTaskNumber(tasks.getSize());
                break;

            case "todo":
                if (input.length() == 4) {
                    throw new AzirException("todo cannot have an empty description. " +
                            "Format: todo [description]");
                }
                Task todoTask = new Todo(result[1]);
                tasks.addTask(todoTask);
                response += ui.showCommandEndMessage(result[0], todoTask.toString());
                response += ui.showTaskNumber(tasks.getSize());
                break;

            case "deadline":
                String dateFormat = "yyyy-MM-dd";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                try {
                    Task deadlineTask = new Deadline(result[1], LocalDate.parse(result[2], formatter));
                    tasks.addTask(deadlineTask);
                    response += ui.showCommandEndMessage(result[0], deadlineTask.toString());
                    response += ui.showTaskNumber(tasks.getSize());
                    break;
                } catch (DateTimeParseException e) {
                    throw new AzirException("deadline needs to be in the following format: yyyy-mm-dd");
                }

            case "event":
                Task eventTask = new Event(result[1], result[2], result[3]);
                tasks.addTask(eventTask);
                response += ui.showCommandEndMessage(result[0], eventTask.toString());
                response += ui.showTaskNumber(tasks.getSize());
                break;

            case "find":
                response += ui.showCommandEndMessage(result[0]);
                for (int i = 0; i < tasks.getSize(); i++) {
                    String description = tasks.getTask(i).getDescription();
                    String[] arr = description.split(" ");
                    if (Arrays.stream(arr).anyMatch(str -> str.equals(result[1]))) {
                        response += String.format("%d. %s\n", i + 1, tasks.getTask(i));
                    }
                }
                break;

            default:
                assert false: result[0];
            }
        } catch (AzirException e) {
            return ui.showError(e.getMessage());
        } finally {
            ArrayList<String> lines = new ArrayList<>();
            // Write tasks to Azir.txt
            for (int i = 0; i < tasks.getSize(); i++) {
                lines.add(tasks.getTask(i).formatText());
            }
            storage.write("./data/Azir.txt", lines);
        }
        return response;
    }
}
