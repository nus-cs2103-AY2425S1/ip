import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import executable.Executable;
import executable.Greet;
import executable.Executable;
import executable.Executable.exitCode;
import executable.TaskModifier;

import task.Task;

import exception.BotException;
import exception.IncorrectTaskFormatException;

/**
 * Your friendly todolist bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // The file path to read tasks data from
    private static final String FILEPATH = "data/task.txt";

    // List of tasks
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        try {
            initializeTasks();
        } catch (IncorrectTaskFormatException e) {
            normalPrint("An error occured. " + e.getMessage());
            return;
        }
        greetings();

        Scanner userInput = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = userInput.nextLine();

                // Returns an Executable based on what was parsed into the parser.
                Executable exec = parser.parse(input);

                if (exec instanceof TaskModifier) {
                    TaskModifier taskModifier = (TaskModifier) exec;
                    taskModifier.setTasks(tasks);
                    exec = taskModifier;
                }

                exitCode code = exec.execute();

                prettyPrint(exec.getOutput());

                if (code != exitCode.NORMAL){
                    isExit = true;
                }
            } catch (IllegalStateException | NoSuchElementException | NullPointerException e) {
                normalPrint("An error occured. " + e.getMessage());
                isExit = true;
            } catch (BotException e) {
                prettyPrint(e.getMessage());
            }
        }
        userInput.close();
        try {
            writeTasksToFile();
        } catch (IOException e) {
            normalPrint("An error occured while writing to file.\n" + e.getMessage());
        }
    }

    private static void initializeTasks() throws IncorrectTaskFormatException {
        normalPrint("Initializing tasks...");
        File data = new File(FILEPATH);
        try {
            TaskReader taskReader = new TaskReader(data);
            tasks = taskReader.read();
        } catch (FileNotFoundException e) {
            normalPrint("No file with path " + FILEPATH + " found.\n"
                    + "If this file exists, make sure to run the program where "
                    + FILEPATH + " is accessible.\n"
                    + "Initializing an empty array of tasks...");
            tasks = new ArrayList<>();
        }
        normalPrint("Tasks initialized");
    }

    private static void writeTasksToFile() throws IOException {
        normalPrint("Writing tasks to " + FILEPATH + "...");
        TaskWriter taskWriter = new TaskWriter(FILEPATH, tasks);
        taskWriter.write();
        taskWriter.close();
        normalPrint("File written succesfully.");
    }

    private static void greetings() {
        Greet greet = new Greet(NAME);
        greet.execute();
        prettyPrint(greet.getOutput());
    }

    private static void prettyPrint(String s) {
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static void normalPrint(String s) {
        System.out.println(s);
    }

    private static String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
    }
}

