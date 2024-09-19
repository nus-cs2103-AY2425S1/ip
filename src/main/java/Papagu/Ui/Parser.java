package Papagu.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Deals with making sense of user command
 */
public class Parser {
    /**
     * Function to help convert month to int format
     * @param month
     * @return int representation of month
     */
    private static int monthConverter(String month) {
        switch (month) {
        case "Jan":
            return 1;
        case "Feb":
            return 2;
        case "Mar":
            return 3;
        case "Apr":
            return 4;
        case "May":
            return 5;
        case "Jun":
            return 6;
        case "Jul":
            return 7;
        case "Aug":
            return 8;
        case "Sep":
            return 9;
        case "Oct":
            return 10;
        case "Nov":
            return 11;
        case "Dec":
            return 12;
        default:
            return 0;
        }
    }

    /**
     * Transferrs task already in the Tasks.txt file to the taskList
     * No need to handle writing to file here
     * @param file
     * @param taskList
     */
	public static void parseFile(File file, TaskList taskList) {
        try {
            assert file.exists() : "File does not exist";
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();

                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                String isDone = parts[1];

                if (taskType.equals("T")) {
                    assert parts.length == 3 : "Todo task has wrong input format";
                    parseFileTodo(parts, isDone.equals("1"), taskList);
                } else if (taskType.equals("D")) {
                    assert parts.length == 4 : "Deadline task has wrong input format";
                    parseFileDeadline(parts, isDone.equals("1"), taskList);
                } else if (taskType.equals("E")) {  
                    assert parts.length == 4 : "Event task has wrong input format";
                    parseFileEvent(parts, isDone.equals("1"), taskList);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Parses the file for ToDos
     * @param input
     * @param isDone
     * @param taskList
     */
    public static void parseFileTodo(String[] input, boolean isDone, TaskList taskList) {
        if (isDone) {
            ToDos newToDo = new ToDos(input[2]);
            newToDo.markAsDone();
            taskList.addTask(newToDo);
        } else {
            ToDos newToDo = new ToDos(input[2]);
            taskList.addTask(newToDo);
        }
    }

    /**
     * Parses the file for Deadlines
     * @param input
     * @param isDone
     * @param taskList
     */
    public static void parseFileDeadline(String[] input, boolean isDone, TaskList taskList) {
        if (isDone) {
            String[] dateTime = input[3].split(" ");

            String[] dates = dateTime[0].split("-");
            String month = String.format("%02d", monthConverter(dates[0]));
            String day = String.format("%02d", Integer.parseInt(dates[1]));
            String year = dates[2];

            LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

            LocalTime time = LocalTime.parse(dateTime[1]);

            Deadlines newDeadline = new Deadlines(input[2], date, time);
            newDeadline.markAsDone();
            taskList.addTask(newDeadline);
        } else {
            String[] dateTime = input[3].split(" ");

            String[] dates = dateTime[0].split("-");
            String month = String.format("%02d", monthConverter(dates[0]));
            String day = String.format("%02d", Integer.parseInt(dates[1]));
            String year = dates[2];

            LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

            LocalTime time = LocalTime.parse(dateTime[1]);

            Deadlines newDeadline = new Deadlines(input[2], date, time);
            taskList.addTask(newDeadline);
        }
    }

    /**
     * Parses the file for Events
     * @param input
     * @param isDone
     * @param taskList
     */
    public static void parseFileEvent(String[] input, boolean isDone, TaskList taskList) {
        String description = input[2];

        String[] dateTime = input[3].split(" ");
                   
        String[] dates = dateTime[0].split("-");
        String month = String.format("%02d", monthConverter(dates[0]));
        String day = String.format("%02d", Integer.parseInt(dates[1]));
        String year = dates[2];
                    
        LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

        String[] times = dateTime[1].split("-");


        LocalTime start = LocalTime.parse(times[0]);
        LocalTime end = LocalTime.parse(times[1]);

        if (isDone) {
            Events newEvent = new Events(input[2], date, start, end);
            newEvent.markAsDone();
            taskList.addTask(newEvent);
        } else {
            Events newEvent = new Events(input[2], date, start, end);
            taskList.addTask(newEvent);
        }
    }

    /**
     * Parses the user input commands and executes the relevant methods from the other classes
     * @param taskList
     * @param userInput
     * @param storage
     */
    public static void parseInput(TaskList taskList, String userInput, Storage storage) {
        try {
            String[] typeDetails = userInput.split(" ", 2);
            String taskType = typeDetails[0];
            if (taskType.equals("bye")) {
                Ui.printBye();
            } else if(taskType.equals("help")) {
                Ui.printHelp();
            } else if (taskType.equals("list")) {
                Ui.printList(taskList);
            } else if (taskType.equals("mark")) {
                parseMark(typeDetails, taskList, storage);
            } else if (taskType.equals("unmark")) {
                parseUnmark(typeDetails, taskList, storage);
            } else if (taskType.equals("find")) {
                parseFind(typeDetails, taskList);
            } else if(taskType.equals("delete")) {
                parseDelete(typeDetails, taskList, storage);
            } else if (taskType.equals("todo")) {
                parseInputTodo(typeDetails, taskList, storage);
            } else if (taskType.equals("deadline")) {
                parseInputDeadline(typeDetails, taskList, storage);
            } else if (taskType.equals("event")) {
                parseInputEvent(typeDetails, taskList, storage);
            } else {
                Ui.printUnkownCommand();
            }
        } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
        }
    }

    /**
     * Parses the input for ToDos
     * @param input
     * @param taskList
     * @param storage
     */
    public static void parseInputTodo(String[] input, TaskList taskList, Storage storage) throws IllegalTodoException{
        if (input.length != 2) {
            throw new IllegalTodoException("Wrong input format for todo");
        }
        ToDos newToDo = new ToDos(input[1]);
        taskList.addTask(newToDo);
        storage.save();
        Ui.printAdded(newToDo);
    }

    /**
     * Parses the input for Deadlines
     * @param input
     * @param tasklist
     * @param storage
     */
    public static void parseInputDeadline(String[] input, TaskList tasklist, Storage storage) throws IllegalDeadlineException{
        String[] parts = input[1].split(" /by ");

        if (parts.length != 2) {
            throw new IllegalDeadlineException("Wrong input format for deadline");
        }

        String description = parts[0];
        String[] dateTime = parts[1].split(" ");

        String[] dates = dateTime[0].split("/");
        String day = String.format("%02d", Integer.parseInt(dates[0]));
        String month = String.format("%02d", Integer.parseInt(dates[1]));

        String year = dates[2];

        LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

        LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));

        Deadlines newDeadline = new Deadlines(description, date, time);
        tasklist.addTask(newDeadline);
        storage.save();
        Ui.printAdded(newDeadline);
    }

    /**
     * Parses the input for Events
     * @param input
     * @param tasklist
     * @param storage
     */
    public static void parseInputEvent(String[] input, TaskList tasklist, Storage storage) throws IllegalEventException{
        String[] parts = input[1].split(" /from ");

        if (parts.length != 2) {
            throw new IllegalEventException("Wrong input format for event");
        }

        String description = parts[0];
        String times = parts[1];
        String[] duration = times.split(" /to ");

        String[] startDateTime = duration[0].split(" ");
        String date = startDateTime[0];
        String startTime = startDateTime[1];
        String[] splitDate = date.split("/");
        String day = String.format("%02d", Integer.parseInt(splitDate[0]));
        String month = String.format("%02d", Integer.parseInt(splitDate[1]));
        String year = splitDate[2];
        LocalDate startDate = LocalDate.parse(year + "-" + month + "-" + day);
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));

        String endTime = duration[1];
        LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));

        Events newEvent = new Events(description, startDate, start, end);

        tasklist.addTask(newEvent);
        storage.save();
        Ui.printAdded(newEvent);
    }

    /**
     * Parses the input for marking tasks as done
     * @param input
     * @param taskList
     * @param storage
     */
    public static void parseMark(String[] input, TaskList taskList, Storage storage) {
        String taskNumber = input[1];
        int num = Integer.parseInt(taskNumber) - 1;
        taskList.markTaskAsDone(num);
        storage.save();
        Ui.printMarkAsDone(taskList, num);
    }

    /**
     * Parses the input for marking tasks as not done
     * @param input
     * @param taskList
     * @param storage
     */
    public static void parseUnmark(String[] input, TaskList taskList, Storage storage) {
        String taskNumber = input[1];
        int num = Integer.parseInt(taskNumber) - 1;
        taskList.markTaskAsNotDone(num);
        storage.save();
        Ui.printMarkAsDone(taskList, num);
    }

    /**
     * Parses the input for finding tasks
     * @param input
     * @param taskList
     */
    public static void parseFind(String[] input, TaskList taskList) {
        String keyword = input[1];
        TaskList found = taskList.findTasks(keyword);
        Ui.printFound(found);
    }

    /**
     * Parses the input for deleting tasks
     * @param input
     * @param taskList
     * @param storage
     */
    public static void parseDelete(String[] input, TaskList taskList, Storage storage) {
        String taskNumber = input[1];
        int num = Integer.parseInt(taskNumber) - 1;
        Task temp = taskList.getTask(num);
        taskList.deleteTask(num);
        storage.save();
        Ui.printDelete(temp);
    }
}
