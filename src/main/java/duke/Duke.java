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

    public String exitCommand = "bye!";
    public String listCommand = "list";

    public String openingText = "Hello! I'm Jeff\n " +
            "What can I help you with today?";

    public String closingText = "Goodbye! Hope to chat with you again soon!";

    public Ui ui = new Ui(openingText, closingText);

    public Saver saver = new Saver("data.txt", ui);
    public Loader loader = new Loader("data.txt", ui);
    public TaskList taskList = loader.load();

    public Scanner inputReader = new Scanner(System.in);
    public Parser parser = new Parser();

    boolean hasOpened = false;

    public String getResponse(String input) {
        parser.readInput(input);
        String response = "";

        String command = parser.getArgument(' ');
        taskList = loader.load();
        if(command.equals(exitCommand)) {

            ui.printClosing();
            response = "bye!";
            System.exit(0);

        } else if(command.equals("list")) {

            response = taskList.listTasks();

        } else if(command.equals("mark")) {

            int rankToMark = Integer.valueOf(parser.getArgument('\n'));

            try {
                response = taskList.markTask(rankToMark);
            } catch (TaskOutOfBounds e) {
                return e.getMsg();
            }

        } else if(command.equals("unmark")) {

            int rankToUnmark = Integer.valueOf(parser.getArgument('\n'));

            try {
                response = taskList.unmarkTask(rankToUnmark);
            } catch (TaskOutOfBounds e) {
                return e.getMsg();
            }

        } else if(command.equals("todo")) {
            assert input.length() > 5;

            if(input.length() == 4) {
                ui.printError("Error: The description of a todo cannot be empty.");
                response = "Error: The description of a todo cannot be empty.";
                return response;
            }

            String taskName = parser.getArgument('\n');

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a todo cannot be empty.");
                response = "Error: The description of a todo cannot be empty.";
                return response;
            }

            taskName = taskName.trim();

            if(taskName.length() == 0) {
                ui.printError("Error: The description of a todo cannot be empty.");
                response = "Error: The description of a todo cannot be empty.";
                return response;
            }

            response = taskList.addTask(new Todo(taskName));
        } else if(command.equals("deadline")) {
            assert input.length() > 9;


            if(input.length() <= 9) {
                ui.printError("Error: The description of a deadline cannot be empty.");
                response = "Error: The description of a deadline cannot be empty.";
                return response;
            }

            String taskName = "";

            try {
                taskName = parser.getArgumentStr("/by ", 4);
            } catch (ArgumentNotFoundException e) {
                //System.out.println(e.getMessage());
                return e.getMsg();
            }

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a deadline cannot be empty.");
                response = "Error: The description of a deadline cannot be empty.";
                return response;
            }

            if(input.length() <= 9 + taskName.length() + 4) {
                ui.printError("Error: No deadline provided.");
                response = "Error: No deadline provided.";
                return response;
            }

            String deadline = parser.getArgument('\n');
            deadline = deadline.trim();

            if(isEmptyString(deadline)) {
                ui.printError("Error: No deadline provided.");
                response = "Error: No deadline provided.";
                return response;
            }

            taskName = taskName.trim();

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of a deadline cannot be empty.");
                response = "Error: The description of a deadline cannot be empty.";
                return response;
            }

            response = taskList.addTask(new Deadline(taskName, deadline));

        } else if(command.equals("event")) {

            assert input.length() > 6;

            if(input.length() <= 5) {
                ui.printError("Error: The description of an event cannot be empty.");
                response = "Error: The description of an event cannot be empty.";
                return response;
            }

            String taskName = "";

            try {
                taskName = parser.getArgumentStr("/from ", 6);
            } catch (ArgumentNotFoundException e) {
                return e.getMsg();
            }

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of an event cannot be empty.");
                response = "Error: The description of an event cannot be empty.";
                return response;
            }

            if(input.length() <= 6 + taskName.length() + 6) {
                ui.printError("Error: No start time provided for event.");
                response = "Error: No start time provided for event.";
                return response;
            }

            String startTime;

            try {
                startTime = parser.getArgumentStr("/to ", 4);
            } catch(ArgumentNotFoundException e) {
                return e.getMsg();
            }

            if(isEmptyString(startTime)) {
                ui.printError("Error: No start time provided for event.");
                response = "Error: No start time provided for event.";
                return response;
            }

            if(input.length() <= 6 + taskName.length() + 6 + startTime.length() + 4) {
                ui.printError("Error: No end time provided for event.");
                response = "Error: No end time provided for event.";
                return response;
            }

            String endTime = parser.getArgument('\n');
            endTime = endTime.trim();

            if(isEmptyString(endTime)) {
                ui.printError("Error: No end time provided for event.");
                response = "Error: No end time provided for event.";
                return response;
            }

            taskName = taskName.trim();
            startTime = startTime.trim();

            if(isEmptyString(taskName)) {
                ui.printError("Error: The description of an event cannot be empty.");
                response = "Error: The description of an event cannot be empty.";
                return response;
            }

            String[] eventTimings = new String[] {startTime, endTime};


            response = taskList.addTask(new Event(taskName, eventTimings));
        } else if(command.equals("delete")) {
            assert input.length() > 7;

            if(input.length() <= 7) {
                ui.printError("Error: You need to specify which task to delete.");
                response = "Error: You need to specify which task to delete.";
                return response;
            }

            int rankToDelete = Integer.valueOf(parser.getArgument('\n'));

            try {
                response = taskList.deleteTask(rankToDelete);
            } catch (TaskOutOfBounds e) {
                return e.getMsg();
            }

        }  else if(command.equals("find")) {
            assert input.length() > 5;

            if(input.length() <= 5) {
                ui.printError("Error: You need to give a search query.");
                response = "Error: You need to give a search query.";
                return response;
            }
            String query = parser.getArgument('\n');
            response = taskList.fetchQuery(query);
        }  else {

            System.out.println("Error: Invalid input.");
            return "Error: Invalid input";
        }

        saver.save(taskList);
        return response;
    }

}