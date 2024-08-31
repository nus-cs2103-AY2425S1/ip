import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;


public class Papagu {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        File file = new File("./Data/Tasks.txt");
        file.getParentFile().mkdirs();

        System.out.println("____________________________________________________________");
        System.out.println("Hello from Papagu!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        System.out.println("These are the current tasks in your list");
        System.out.println("____________________________________________________________");
        
        //Hanlde file creating if it dont exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }
        
        //Handle file reading
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

        System.out.println("____________________________________________________________");
        
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(taskList);
                    System.out.println("____________________________________________________________");
                } else if (userInput.contains("mark")){
                    String[] parts = userInput.split(" ");
                    String word = parts[0];
                    int num = Integer.parseInt(parts[1]) - 1;
                    file.delete();
                    File newFile = new File("./Data/Tasks.txt");
                    if (word.equals("mark")) {
                        System.out.println("____________________________________________________________");
                        taskList.markTaskAsDone(num);
                        System.out.println("____________________________________________________________");
                        for (int i = 0; i < taskList.getTaskCount(); i++) {
                            try {
                                FileWriter writer = new FileWriter(newFile, true);
                                writer.write(taskList.getTask(i).toFile() + "\n");
                                writer.close();
                            } catch (IOException e) {
                                System.out.println("Error writing to file");
                            }
                        }
                    } else if (word.equals("unmark")) {
                        System.out.println("____________________________________________________________");
                        taskList.markTaskAsNotDone(num);
                        System.out.println("____________________________________________________________");
                        for (int i = 0; i < taskList.getTaskCount(); i++) {
                            try {
                                FileWriter writer = new FileWriter(newFile, true);
                                writer.write(taskList.getTask(i).toFile() + "\n");
                                writer.close();
                            } catch (IOException e) {
                                System.out.println("Error writing to file");
                            }
                        }
                    }    
                } else if (userInput.contains("todo")) {
                    if (userInput.equals("todo")) {
                        throw new IllegalTodoException("Good sir your the description of a todo cannot be empty.");
                    }
                    String[] input = userInput.split(" ", 2);
                    String description = input[1];
                    ToDos newToDo = new ToDos(description);

                    file.delete();
                    File newFile = new File("./Data/Tasks.txt");

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    taskList.addTask(newToDo);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                    for (int i = 0; i < taskList.getTaskCount(); i++) {
                        try {
                            FileWriter writer = new FileWriter(newFile, true);
                            writer.write(taskList.getTask(i).toFile() + "\n");
                            writer.close();
                        } catch (IOException e) {
                            System.out.println("Error writing to file");
                        }
                    }
                } else if (userInput.contains("deadline")) {
                    if (userInput.equals("deadline")) {
                        throw new IllegalDeadlineException("Good sir the description of a deadline cannot be empty.");
                    }

                    String[] input = userInput.split(" ", 2);
                    String[] parts = input[1].split(" /by ");
                    String description = parts[0];
                    String time = parts[1];

                    String[] dateTime = time.split(" ");
                    
                    String[] date = dateTime[0].split("/");
                    String day = String.format("%02d", Integer.parseInt(date[0]));
                    String month = String.format("%02d", Integer.parseInt(date[1]));
                    String year = date[2];
                    
                    LocalDate currDate = LocalDate.parse(year + "-" + month + "-" + day);
                    LocalTime currTime = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm"));
                    
                    Deadlines newDeadline = new Deadlines(description, currDate, currTime);

                    file.delete();
                    File newFile = new File("./Data/Tasks.txt");

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    taskList.addTask(newDeadline);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");



                    for (int i = 0; i < taskList.getTaskCount(); i++) {
                        try {
                            FileWriter writer = new FileWriter(newFile, true);
                            writer.write(taskList.getTask(i).toFile() + "\n");
                            writer.close();
                        } catch (IOException e) {
                            System.out.println("Error writing to file");
                        }
                    }
                } else if (userInput.contains("event")) {
                    if (userInput.equals("event")) {
                        throw new IllegalEventException("Good sir the description of an event cannot be empty.");
                    }

                    String[] input = userInput.split(" ", 2);
                    String[] parts = input[1].split(" /from ");

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

                    file.delete();
                    File newFile = new File("./Data/Tasks.txt");

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    taskList.addTask(newEvent);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                    for (int i = 0; i < taskList.getTaskCount(); i++) {
                        try {
                            FileWriter writer = new FileWriter(newFile, true);
                            writer.write(taskList.getTask(i).toFile() + "\n");
                            writer.close();
                        } catch (IOException e) {
                            System.out.println("Error writing to file");
                        }
                    }
                } else if (userInput.contains("delete")) {
                    String[] parts = userInput.split(" ");
                    int num = Integer.parseInt(parts[1]) - 1;
                    System.out.println("____________________________________________________________");
                    taskList.deleteTask(num);
                    System.out.println("____________________________________________________________");
                } else {
                    throw new IllegalInputException("I'm sorry, but I don't know what that means :-(" + "\n" 
                    + "Please enter a valid command."
                    + "\n" + "Valid commands are: list, todo, deadline, event, mark, unmark, bye");
                }
                userInput = scanner.nextLine();
            }   catch (IllegalInputException | IllegalTodoException | IllegalDeadlineException | IllegalEventException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
                userInput = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close(); 
    }
}
