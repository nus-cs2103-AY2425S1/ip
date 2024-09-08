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

@SuppressWarnings("CheckStyle")
public class Parser {
    /**
     * Function to help convert month to int format
     * @param month
     * @return
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
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                
                //Take out first letter
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                String isDone = parts[1];
                
                //Todo task
                if (taskType.equals("T")) {
                    if (isDone.equals("1")) {
                        ToDos newToDo = new ToDos(parts[2]);
                        newToDo.markAsDone();
                        taskList.addTask(newToDo);
                    } else {
                        ToDos newToDo = new ToDos(parts[2]);
                        taskList.addTask(newToDo);
                    }    
                } else if (taskType.equals("D")) {          //Deadline task                    
                    if (isDone.equals("1")) {
                        String[] dateTime = parts[3].split(" "); 

                        String[] dates = dateTime[0].split("-");
                        String month = String.format("%02d", monthConverter(dates[0]));
                        String day = String.format("%02d", Integer.parseInt(dates[1]));
                        String year = dates[2];

                        LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

                        LocalTime time = LocalTime.parse(dateTime[1]);

                        Deadlines newDeadline = new Deadlines(parts[2], date, time);
                        newDeadline.markAsDone();
                        taskList.addTask(newDeadline);
                    } else {
                        String[] dateTime = parts[3].split(" "); 

                        String[] dates = dateTime[0].split("-");
                        String month = String.format("%02d", monthConverter(dates[0]));
                        String day = String.format("%02d", Integer.parseInt(dates[1]));
                        String year = dates[2];

                        LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

                        LocalTime time = LocalTime.parse(dateTime[1]);

                        Deadlines newDeadline = new Deadlines(parts[2], date, time);
                        taskList.addTask(newDeadline);
                    }
                } else if (taskType.equals("E")) {  
                    String description = parts[2];

                    String[] dateTime = parts[3].split(" ");
                   
                    String[] dates = dateTime[0].split("-");
                    String month = String.format("%02d", monthConverter(dates[0]));
                    String day = String.format("%02d", Integer.parseInt(dates[1]));
                    String year = dates[2];
                    
                    LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

                    String[] times = dateTime[1].split("-");      

   
                    LocalTime start = LocalTime.parse(times[0]);
                    LocalTime end = LocalTime.parse(times[1]);

                    if (isDone.equals("1")) {
                        Events newEvent = new Events(parts[2], date, start, end);
                        newEvent.markAsDone();
                        taskList.addTask(newEvent);
                    } else {
                        Events newEvent = new Events(parts[2], date, start, end);
                        taskList.addTask(newEvent);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Parses the user input commands and executes the relevant methods from the other classes
     * @param taskList
     * @param userInput
     * @param storage
     */
    public static void parseInput(TaskList taskList, String userInput, Storage storage) {
        
        String[] typeDetails = userInput.split(" ", 2);
        String taskType = typeDetails[0];
        if (taskType.equals("bye")) {
            Ui.printBye();
        } else if (taskType.equals("list")) {
            Ui.printList(taskList);
        } else if (taskType.equals("mark")) {
            String taskNumber = typeDetails[1];
            int num = Integer.parseInt(taskNumber) - 1;
            taskList.markTaskAsDone(num);
            storage.save();
            Ui.printMarkAsDone(taskList, num);
        } else if (taskType.equals("unmark")) {
            String taskNumber = typeDetails[1];
            int num = Integer.parseInt(taskNumber) - 1;
            taskList.markTaskAsNotDone(num);
            storage.save();
            Ui.printMarkAsDone(taskList, num);
        } else if (taskType.equals("find")) {
            String keyword = typeDetails[1];
            TaskList found = taskList.findTasks(keyword);
            Ui.printFound(found);
        } else if(taskType.equals("delete")) {
            String taskNumber = typeDetails[1];
            int num = Integer.parseInt(taskNumber) - 1;
            Task temp = taskList.getTask(num);
            taskList.deleteTask(num);
            storage.save();
            Ui.printDelete(temp);
        } else if (taskType.equals("todo")) {
            ToDos newToDo = new ToDos(typeDetails[1]);
            taskList.addTask(newToDo);
            storage.save();
            Ui.printAdded(newToDo);
        } else if (taskType.equals("deadline")) {
            String[] parts = typeDetails[1].split(" /by ");
            String description = parts[0];
            String[] dateTime = parts[1].split(" "); 

            String[] dates = dateTime[0].split("/");
            String day = String.format("%02d", Integer.parseInt(dates[0]));
            String month = String.format("%02d", Integer.parseInt(dates[1]));
            
            String year = dates[2];

            LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);

            LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));

            Deadlines newDeadline = new Deadlines(description, date, time);
            taskList.addTask(newDeadline);
            storage.save();
            Ui.printAdded(newDeadline);
        } else if (taskType.equals("event")) {
            String[] parts = typeDetails[1].split(" /from ");

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

            taskList.addTask(newEvent);
            storage.save();
            Ui.printAdded(newEvent);
        }
    }   
}
