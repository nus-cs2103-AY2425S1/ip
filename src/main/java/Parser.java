import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

public class Parser {
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
}