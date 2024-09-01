import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Tira {
    private static final String Directory = "./data";
    private static final String FileName = "./data/Tira.txt";
    private static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Tira(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TiraException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws TiraException{
        Ui ui = new Ui();
        ui.welcome();
        boolean isDone = false;
        PrintWriter printer = new PrintWriter(System.out);
        // check the user input
        /*
        ArrayList<Task> taskList = new ArrayList<Task>();
         */
        //Solution for Save below (Level-7)  inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/Hana.java
            while (true) {
                String command = ui.read();
                String[] splitCommand = command.split(" ");
                String firstWord = splitCommand[0];
                if (command.equals("bye")) { //BYE
                    break;
                } else if (firstWord.equals("list")) { //LIST
                    ui.showTaskList(tasks);
                } else {
                    tasks.modifyTask(firstWord, command, splitCommand);
                }
            }
            try {
                storage.save(tasks.getTasks());
                System.out.println("managed to save");
            } catch (IOException e) {
                System.out.println("Error while saving tasks. Please try again");
            }
            ui.bye();
            printer.close();
    }

    public static void main(String[] args) throws TiraException, IOException {
        // variable declarations
        new Tira("data/tasks/txt").run();
    }
}


