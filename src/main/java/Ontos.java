import java.util.Scanner;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Ontos {
    public static String hello = " Hello! I'm Ontos \n"
            + " What can I do for you?\n";
    public static String line = "____________________________________________________________\n";
    public static String bye = " Bye. Hope to see you again soon!\n";
    public static String listPrompt = " Here are the tasks in your list:";
    public static String completeTaskPrompt = " Nice! I've marked this task as done:\n   ";
    public static String uncompleteTaskPrompt = " OK, I've marked this task as not done yet:\n   ";
    public static String taskAdded = " Got it. I've added this task:\n";

    public static void main(String[] args) {
        Path projectRoot = Paths.get("").toAbsolutePath().getParent().getParent().getParent();
        SaveManager saveManager = new SaveManager(projectRoot, "Ontos");

        saveManager.createSave();

        TaskList tasks = saveManager.loadSave();
        Scanner sc = new Scanner(System.in);
        
        System.out.println(line + hello + line);

        while (true){
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                try {
                    saveManager.writeToSave(tasks);
                } catch (IOException e) {
                    //Shouldn't reach here as the file is created when the script starts
                    System.out.println("Failed to save: " + e.getMessage());
                }
                System.out.println(line + bye + line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line + listPrompt);
                System.out.println(tasks.toString());
                System.out.println(line);
            } else if (input.startsWith("mark")) {
                int index = 0;
                try {
                    index = Integer.parseInt(input.split(" ")[1]);
                } catch (Exception e) {
                    System.out.println(line 
                        + "The correct usage of 'mark' is: mark n, where n is a natural number (ℕ).\n" 
                        + line);
                    continue;
                }

                try {
                    tasks.completeTaskAt(index);
                } catch (Exception e) {
                    System.out.println(line + "I'm sorry, but this task doesn't exist.\n" + line);
                    continue;
                }
                System.out.println(line + completeTaskPrompt + tasks.getTaskString(index) + "\n" + line); 
            } else if (input.startsWith("unmark")) {
                int index = 0;
                try {
                    index = Integer.parseInt(input.split(" ")[1]);
                } catch (Exception e) {
                    System.out.println(line 
                        + "The correct usage of 'unmark' is: unmark n, where n is a natural number (ℕ).\\n"
                        + line);
                    continue;
                }

                try {
                    tasks.uncompleteTaskAt(index);;
                } catch (Exception e) {
                    System.out.println(line + "I'm sorry, but this task doesn't exist.\n" + line);
                    continue;
                }
                System.out.println(line + uncompleteTaskPrompt + tasks.getTaskString(index) + "\n" + line); 
            } else if (input.startsWith("todo")) {
                Task newToDo = null;
                try {
                    newToDo = Task.toDo(input.split(" ", 2)[1]);
                } catch (Exception e) {
                    System.out.println(line + " OOPS!!! The description of a todo cannot be empty.\n" + line);
                    continue;
                }

                tasks.addTask(newToDo);

                System.out.println(line 
                        + taskAdded 
                        + " " + newToDo.toString() + "\n" 
                        + " Now you have " + tasks.getSize() + " tasks in the list.\n" 
                        + line);
            } else if (input.startsWith("deadline")) {
                Task newDeadline = null;
                try {
                    int startOfDesc = input.indexOf(" ");
                    int endOfDesc = input.indexOf(" /by");

                    String description = input.substring(startOfDesc, endOfDesc).trim();
                    String dueBy = input.substring(endOfDesc + 4).trim();

                    newDeadline = Task.deadline(description, dueBy);
                } catch (Exception e) {
                    System.out.println(line + " OOPS!!! The description of a deadline cannot be empty.\n" + line);
                    continue;
                }

                tasks.addTask(newDeadline);

                System.out.println(line 
                        + taskAdded 
                        + " " + newDeadline.toString() + "\n" 
                        + " Now you have " + tasks.getSize() + " tasks in the list.\n" 
                        + line);
            } else if (input.startsWith("event")) {
                Task newEvent = null;
                try {
                    int startOfDesc = input.indexOf(" ");
                    int endOfDesc = input.indexOf(" /from");
                    int endOfFrom = input.indexOf(" /to");

                    String description = input.substring(startOfDesc, endOfDesc).trim();
                    String start = input.substring(endOfDesc + 6, endOfFrom).trim();
                    String end = input.substring(endOfFrom + 4).trim();

                    newEvent = Task.event(description, start, end);
                } catch (Exception e) {
                System.out.println(line + " OOPS!!! The description of a event cannot be empty.\n" + line);
                continue;
                }
                
                tasks.addTask(newEvent);

                System.out.println(line 
                        + taskAdded 
                        + " " + newEvent + "\n" 
                        + " Now you have " + tasks.getSize() + " tasks in the list.\n" 
                        + line);
            } else if (input.startsWith("delete")) {
                int index = 0;
                try {
                    index = Integer.parseInt(input.split(" ")[1]);
                } catch (Exception e) {
                    System.out.println(line 
                            + "The correct usage of 'delete' is: delete n, where n is a natural number (ℕ).\\n" 
                            + line);
                    continue;
                }
                
                Task task = null;
                try {
                    task = tasks.removeTaskAt(index);
                } catch (Exception e) {
                    System.out.println(line + "I'm sorry, but this task doesn't exist.\n" + line);
                    continue;
                }
                System.out.println(line 
                        + " Noted. I've removed this task:\n" 
                        + " " + task.toString() + "\n"
                        + line);
            } else {
                System.out.println(line + " OOPS!!! I'm sorry, but I don't know what that means :-( \n" + line);
            }
        }
        sc.close();
    }
}
