package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileWriter; 
import java.io.FileReader; 
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Duke {

    public static int TODO = 0;
    public static int DEADLINE = 1;
    public static int EVENT = 2;




    public static boolean isEmptyString(String str) {
        for(int i=0; i<str.length(); i++) {
            if(str.charAt(i) != ' ')
                return false;
        }

        return true;
    }

    public String exitCommand = "bye";
    public String listCommand = "list";

    public String openingText = "Hello! I'm Jeff\n " +
            "What can I do for you?";

    public String closingText = "Bye. Hope to see you again soon!";

    public Ui ui = new Ui(openingText, closingText);
    //public Storage storage = new Storage("data.txt", ui);
    public Saver saver = new Saver("data.txt", ui);
    public Loader loader = new Loader("data.txt", ui);
    public TaskList taskList = loader.load();

    public Scanner inputReader = new Scanner(System.in);
    public Parser parser = new Parser();

    public String getResponse(String input) {
        parser.readInput(input);
        String response = "";

        String command = parser.getArgument(' ');
        taskList = loader.load();
        if(command.equals(exitCommand)) {

            ui.printClosing();
            response = "bye!";

        } else if(command.equals("list")) {

            response = taskList.listTasks();

        } else if(command.equals("mark")) {

            int rankToMark = Integer.valueOf(parser.getArgument('\n'));

            response = taskList.markTask(rankToMark);
        } else if(command.equals("unmark")) {

            int rankToUnmark = Integer.valueOf(parser.getArgument('\n'));

            response = taskList.unmarkTask(rankToUnmark);
        } else if(command.equals("todo")) {
            assert input.length() > 5;

            if(input.length() == 4) {
                ui.printError("Error: The description of a todo cannot be empty. Terminating program.");
                response = "Error: The description of a todo cannot be empty.";
            }

            String taskName = parser.getArgument('\n');

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a todo cannot be empty. Terminating program.");
                response = "Error: The description of a todo cannot be empty.";
            }

            taskName = taskName.trim();
            response = taskList.addTask(new Task(taskName, TODO));
        } else if(command.equals("deadline")) {
            assert input.length() > 9;


            if(input.length() <= 9) {
                ui.printError("Error: The description of a deadline cannot be empty. Terminating program.");
                response = "Error: The description of a deadline cannot be empty.";
            }

            String taskName = parser.getArgument('/', 4);

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a deadline cannot be empty. Terminating program.");
                response = "Error: The description of a deadline cannot be empty.";
            }

            if(input.length() <= 9 + taskName.length() + 4) {
                ui.printError("Error: No deadline provided. Terminating program.");
                response = "Error: No deadline provided.";
            }

            String deadline = parser.getArgument('\n');
            deadline = deadline.trim();

            if(isEmptyString(deadline)) {
                ui.printError("Error: No deadline provided. Terminating program.");
                response = "Error: No deadline provided.";
            }

            taskName = taskName.trim();

            response = taskList.addTask(new Task(taskName, DEADLINE, deadline));

        } else if(command.equals("event")) {

            assert input.length() > 6;

            if(input.length() <= 5) {
                ui.printError("Error: The description of an event cannot be empty. Terminating program.");
                response = "Error: The description of an event cannot be empty.";
            }

            String taskName = parser.getArgument('/', 6);

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of an event cannot be empty. Terminating program.");
                response = "Error: The description of an event cannot be empty.";
            }

            if(input.length() <= 6 + taskName.length() + 6) {
                ui.printError("Error: No start time provided for event. Terminating program.");
                response = "Error: No start time provided for event.";
            }

            String startTime = parser.getArgument('/', 4);

            if(isEmptyString(startTime)) {
                ui.printError("Error: No start time provided for event. Terminating program.");
                response = "Error: No start time provided for event.";
            }

            if(input.length() <= 6 + taskName.length() + 6 + startTime.length() + 4) {
                ui.printError("Error: No end time provided for event. Terminating program.");
                response = "Error: No end time provided for event.";
            }

            String endTime = parser.getArgument('\n');
            endTime = endTime.trim();

            if(isEmptyString(endTime)) {
                ui.printError("Error: No end time provided for event. Terminating program.");
                response = "Error: No end time provided for event.";
            }

            taskName = taskName.trim();
            startTime = startTime.trim();

            String[] eventTimings = new String[] {startTime, endTime};


            response = taskList.addTask(new Task(taskName, EVENT, eventTimings));
        } else if(command.equals("delete")) {
            assert input.length() > 7;

            if(input.length() <= 7) {
                ui.printError("Error: You need to specify which task to delete. Terminating program.");
                response = "Error: You need to specify which task to delete.";
            }

            int rankToDelete = Integer.valueOf(parser.getArgument('\n'));

            response = taskList.deleteTask(rankToDelete);
        }  else if(command.equals("find")) {
            assert input.length() > 5;

            if(input.length() <= 5) {
                ui.printError("Error: You need to give a search query. Terminating program.");
                response = "Error: You need to give a search query.";
            }
            String query = parser.getArgument('\n');
            response = taskList.fetchQuery(query);
        }  else {

            System.out.println("Error: Invalid input, terminating program.");
            return "Error: Invalid input";
        }

        saver.save(taskList);
        return response;
    }

}