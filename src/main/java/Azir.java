import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Azir {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Azir(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            File newFile = new File("./data/Azir.txt");
            if (!Files.exists(Paths.get("./data"))) {
                Files.createDirectory(Paths.get("./data"));
            }
            newFile.createNewFile();
            tasks = new TaskList();
            System.out.println("Your current list does not have any tasks");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        Scanner obj = new Scanner(System.in);
        while (!isExit && obj.hasNextLine()) {
            try {
                String fullCommand = ui.readCommand(obj);
                ui.showLine();
                String[] result = Parser.parse(fullCommand);
                switch (result[0]) {
                case "list":
                    ui.showCommandEndMessage();
                    for (int i = 0; i < tasks.getSize(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.getTask(i));
                    }
                    break;
                case "mark":
                    if (result.length == 1) {
                        throw new AzirException("Input the task number you would like to mark.");
                    }
                    if (Integer.valueOf(result[1]) < 1 || (Integer.valueOf(result[1]) > tasks.getSize())) {
                        throw new AzirException("Invalid value");
                    }
                    Task chosenTask = tasks.getTask(Integer.valueOf(result[1]) - 1);
                    chosenTask.setDone();
                    ui.showCommandEndMessage(result[0], chosenTask.toString());
                    break;

                case "unmark":
                    if (result.length == 1) {
                        throw new AzirException("Input the task number you would like to unmark.");
                    }
                    if (Integer.valueOf(result[1]) < 1 || (Integer.valueOf(result[1]) > tasks.getSize())) {
                        throw new AzirException("Invalid value");
                    }
                    Task task = tasks.getTask(Integer.valueOf(result[1]) - 1);
                    task.setNotDone();
                    ui.showCommandEndMessage(result[0], task.toString());
                    break;

                case "delete":
                    if (result.length == 1) {
                        throw new AzirException("Input the task number you would like to delete.");
                    }
                    if (Integer.valueOf(result[1]) < 1 || (Integer.valueOf(result[1]) > tasks.getSize())) {
                        throw new AzirException("Invalid value");
                    }
                    Task deletedTask = tasks.getTask(Integer.valueOf(result[1]) - 1);
                    tasks.DeleteTask(Integer.valueOf(result[1]) - 1);
                    ui.showCommandEndMessage(result[0], deletedTask.toString());
                    ui.showTaskNumber(tasks.getSize());
                    break;

                case "todo":
                    if (fullCommand.length() == 4) {
                        throw new AzirException("todo cannot have an empty description. " +
                                "Format: todo [description]");
                    }
                    Task todoTask = new Todo(result[1]);
                    tasks.addTask(todoTask);
                    ui.showCommandEndMessage(result[0], todoTask.toString());
                    ui.showTaskNumber(tasks.getSize());
                    break;

                case "deadline":
                    int byIndex = fullCommand.indexOf("/by");
                    if (byIndex == -1) {
                        throw new AzirException("deadline needs a /by date. " +
                                "Format: deadline [description] /by [date]");
                    }
                    if (fullCommand.substring(8, byIndex).trim().isEmpty()) {
                        throw new AzirException("deadline needs a description. " +
                                "Format: deadline [description] /by [date]");
                    }
                    if (fullCommand.trim().endsWith("/by")) {
                        throw new AzirException("You need a deadline day. " +
                                "Format: deadline [description] /by [date]");
                    }
                    String dateFormat = "yyyy-MM-dd";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                    try {
                        Task deadlineTask = new Deadline(result[1], LocalDate.parse(result[2], formatter));
                        tasks.addTask(deadlineTask);
                        ui.showCommandEndMessage(result[0], deadlineTask.toString());
                        ui.showTaskNumber(tasks.getSize());
                    } catch (DateTimeParseException e) {
                        throw new AzirException("deadline needs to be in the following format: yyyy-mm-dd");
                    }
                    break;

                case "event":
                    int fromIndex = fullCommand.indexOf("/from");
                    int toIndex = fullCommand.indexOf("/to");
                    if (fromIndex == -1) {
                        throw new AzirException("event needs a /from. " +
                                "Format: event [description] /from [date] /to [date]");
                    }
                    if (toIndex == -1) {
                        throw new AzirException("event needs a /to. " +
                                "Format: event [description] /from [date] /to [date]");
                    }
                    if (fullCommand.substring(fromIndex + 5, toIndex).trim().isEmpty()) {
                        throw new AzirException("event needs a from date. " +
                                "Format: event [description] /from [date] /to [date]");
                    }
                    if (fullCommand.substring(5, fromIndex).trim().isEmpty()) {
                        throw new AzirException("event needs a description. " +
                                "Format: event [description] /from [date] /to [date]");
                    }
                    if (fullCommand.trim().endsWith("/to")) {
                        throw new AzirException("You need an ending date. " +
                                "Format: event [description] /from [date] /to [date]");
                    }
                    Task eventTask = new Event(result[1], result[2], result[3]);
                    tasks.addTask(eventTask);
                    ui.showCommandEndMessage(result[0], eventTask.toString());
                    ui.showTaskNumber(tasks.getSize());
                    break;

                case "bye":
                    isExit = true;
                    ui.showExit();
                }
            } catch (AzirException e) {
                ui.showError(e.getMessage());
            } finally {
                ArrayList<String> lines = new ArrayList<>();
                // Write tasks to Azir.txt
                for (int i = 0; i < tasks.getSize(); i++) {
                    lines.add(tasks.getTask(i).formatText());
                }
                storage.write("./data/Azir.txt", lines);
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Azir("./data/Azir.txt").run();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }

    }
}
