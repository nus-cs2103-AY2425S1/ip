import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Papagu {

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
                        Deadlines newDeadline = new Deadlines(parts[2], parts[3]);
                        newDeadline.markAsDone();
                        taskList.addTask(newDeadline);
                    } else {
                        Deadlines newDeadline = new Deadlines(parts[2], parts[3]);
                        taskList.addTask(newDeadline);
                    }
                } else if (taskType.equals("E")) {  
                    String[] times = parts[3].split("-");      //Event task                    
                    if (isDone.equals("1")) {
                        Events newEvent = new Events(parts[2], times[0], times[1]);
                        newEvent.markAsDone();
                        taskList.addTask(newEvent);
                    } else {
                        Events newEvent = new Events(parts[2], times[0], times[1]);
                        taskList.addTask(newEvent);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

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
                    Deadlines newDeadline = new Deadlines(description, time);

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
                    String time = parts[1];
                    String[] duration = time.split(" /to ");
                    String start = duration[0];
                    String end = duration[1];
                    Events newEvent = new Events(description, start, end);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    taskList.addTask(newEvent);
                    System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
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
