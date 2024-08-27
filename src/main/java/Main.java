import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();

        // storage checks if the current directory has a janet.txt file
        Storage storage = new Storage("./janet.txt");

        TaskList taskList;
        try {
            taskList = new TaskList(storage.textFileToArrayList());
        } catch (JanetException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

        ui.showWelcome();

        Scanner input = new Scanner(System.in);

        boolean hasTypedBye = false;

        while (input.hasNext()) {
            String command = input.nextLine();
            String[] commandDetails = command.split(" ");   // an array containing each word of the command
            // handle exceptions
            try {
                // validateCommand and checkInaccurateCommand will throw out a JanetException
                validateCommand(commandDetails, taskList.getNumberOfTasks());
                checkInaccurateCommand(commandDetails);

                CommandType commandType = CommandType.valueOf(commandDetails[0].toUpperCase());  // pass user input to be CommandType enum
                switch (commandType) {
                case BYE -> {
                    hasTypedBye = true;
                    ui.exitMessage();
                }
                case LIST -> {
                    ui.showTasks(taskList);
                }
                case MARK -> {
                    // mark the task as done
                    String result = taskList.markAsDone(Integer.parseInt(commandDetails[1]));
                    ui.showMarkedMessage(result, taskList.getTask(Integer.parseInt(commandDetails[1]) - 1));
                }
                case UNMARK -> {
                    // unmark the task
                    String result = taskList.unmark(Integer.parseInt(commandDetails[1]));
                    ui.showUnmarkedMessage(result, taskList.getTask(Integer.parseInt(commandDetails[1]) - 1));
                }
                case DELETE -> {
                    // delete specified task
                    ui.showDeleteTaskMessage(taskList.getTask(Integer.parseInt(commandDetails[1]) - 1), taskList.getNumberOfTasks() - 1);
                    taskList.deleteTask(Integer.parseInt(commandDetails[1]));
                }
                case TODO -> {
                    // create a ToDo object directly from what the user typed into command line
                    Task todo = new ToDo(command);
                    taskList.addTaskToList(todo);
                    ui.showSuccessfulTaskAddition(todo, taskList.getNumberOfTasks());
                }
                case DEADLINE -> {
                    // create a Deadline object directly from what the user typed into command line
                    Task deadline = new Deadline(command);
                    taskList.addTaskToList(deadline);
                    ui.showSuccessfulTaskAddition(deadline, taskList.getNumberOfTasks());
                }
                case EVENT -> {
                    // create an Event object directly from what the user typed into command line
                    Task event = new Event(command);
                    taskList.addTaskToList(event);
                    ui.showSuccessfulTaskAddition(event, taskList.getNumberOfTasks());
                }
                }
                if (hasTypedBye) {
                    break;
                }
            } catch (JanetException e) {
                // print the error message and allow the program to continue (don't exit the program)
                System.out.println(e.getMessage());
                continue;
            }
        }
        // once the program exits (user types in 'bye'), save the elements in listOfTasks to the text file
        storage.saveToJanetTextFile(taskList.getListOfTasks());
    }
}
