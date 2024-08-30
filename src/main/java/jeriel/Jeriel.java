package jeriel;
import jeriel.util.*;
import jeriel.command.*;
import jeriel.task.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Jeriel {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jeriel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application.
     * 
     * Shows the welcome message, and then enters a loop where it reads a command from the user,
     * parses it, executes it, and then shows the result.
     * 
     * The loop continues until the user enters a command that causes the application to exit.
     * 
     * If an error occurs while parsing or executing the command, the error message is shown to the user.
     * 
     * The loop also shows a divider line after each command.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JerielException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

        /**
         * The entry point of the application.
         * 
         * Creates a new instance of Jeriel with the default file path, and calls its
         * run method to start the application.
         * 
         * @param args the command line arguments
         */
    public static void main(String[] args) {
        new Jeriel("data/tasks.txt").run();
    }
}


// public class Jeriel {
//     public static void main(String[] args) {  
//         String logo = "     _  ____  ___   _  ____  _      \n"
//                     + "    | || ___|| _ \\ | || ___|| |     \n"
//                     + "    | |||___ ||_> || |||___ | |     \n"
//                     + " _  | || ___||  _/ | || ___|| |     \n"
//                     + "| |_| |||___ ||\\\\  | |||___ | |____ \n"
//                     + " \\___/ |____||| \\\\ |_||____||______|\n";
//         System.out.println("Hello from\n" + logo);

//         // System.out.println("Hello from\n" + logo);
//         System.out.println("____________________________________________________________");
//         System.out.println(" Hello! I'm Jeriel");
//         System.out.println(" What can I do for you?");
//         System.out.println("____________________________________________________________");

//         Scanner scanner = new Scanner(System.in);
//         String input;

//         // Task[] tasks = new Task[100];
//         // int taskCount = 0;
//         ArrayList<Task> tasks = new ArrayList<>();

//         Storage storage = new Storage("../../data/duke.txt");  // Initialize Storage with file path

//         try {
//             tasks = storage.load();
//         } catch (Exception e) {
//             System.out.println("Error loading tasks: " + e.getMessage());
//         }


//         while (true) {
//             input = scanner.nextLine(); 
//             // System.out.println("____________________________________________________________");
//             // System.out.println(" " + input);
//             // System.out.println("____________________________________________________________");

//             try {
//                 if (input.equals("bye")) {
//                     System.out.println("____________________________________________________________");
//                     System.out.println(" Bye. Hope to see you again soon!");
//                     System.out.println("____________________________________________________________");

//                     break;

//                 } else if (input.equals("list")) {
//                     System.out.println("____________________________________________________________");
//                     System.out.println(" Here are the tasks in your list:");
//                     for (int i = 0; i < tasks.size(); i++) {
//                         System.out.println(" " + (i + 1) + ". " + tasks.get(i));
//                     }
//                     System.out.println("____________________________________________________________");


//                 } else if (input.startsWith("mark")) {
//                     int taskNumber = Integer.parseInt(input.substring(5)) - 1;
//                     if (taskNumber < 0 || taskNumber >= tasks.size()) {
//                         throw new JerielException("Invalid task number. Please enter a valid task number.");
//                     }                    System.out.println("____________________________________________________________");
//                     System.out.println(" Nice! I've marked this task as done:");
//                     System.out.println("   " + tasks.get(taskNumber));
//                     System.out.println("____________________________________________________________");
//                     storage.save(tasks);

//                 } else if (input.startsWith("unmark")) {
//                     int taskNumber = Integer.parseInt(input.substring(7)) - 1;
//                     if (taskNumber < 0 || taskNumber >= tasks.size()) {
//                         throw new JerielException("Invalid task number. Please enter a valid task number.");
//                     }
//                     System.out.println("____________________________________________________________");
//                     System.out.println(" OK, I've marked this task as not done yet:");
//                     System.out.println("   " + tasks.get(taskNumber));
//                     System.out.println("____________________________________________________________");
//                     storage.save(tasks);

//                 } else if (input.startsWith("todo")) {
//                     String description = input.substring(5).trim();
//                     if (description.isEmpty()) {
//                         throw new JerielException("The description of a todo cannot be empty.");
//                     }
//                     tasks.add(new Todo(description));
//                     System.out.println("____________________________________________________________");
//                     System.out.println(" Got it. I've added this task:");
//                     System.out.println("   " + tasks.get(tasks.size() - 1));
//                     System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
//                     System.out.println("____________________________________________________________");
//                     storage.save(tasks);


//                 } else if (input.startsWith("deadline")) {
//                     String[] parts = input.substring(9).split(" /by ");
//                     if (parts.length < 3 || parts[0].trim().isEmpty()) {
//                         throw new JerielException("The description and due date of a deadline cannot be empty.");
//                     }
//                     String description = parts[0].trim();
//                     String by = parts[1].trim();
//                     System.out.println("____________________________________________________________");
//                     System.out.println(" Got it. I've added this task:");
//                     System.out.println("   " + tasks.get(tasks.size() - 1));
//                     System.out.println(" Now you have " + tasks.get(tasks.size() - 1) + " tasks in the list.");
//                     System.out.println("____________________________________________________________");
//                     storage.save(tasks);


                
//                 } else if (input.startsWith("event")) {
//                     String[] parts = input.substring(6).split(" /from | /to ");
//                     if (parts.length < 3 || parts[0].trim().isEmpty()) {
//                         throw new JerielException("The description, start time, and end time of an event cannot be empty.");
//                     }
//                     String description = parts[0].trim();
//                     String from = parts[1].trim();
//                     String to = parts[2].trim();
//                     tasks.add(new Event(description, from, to));

//                     System.out.println("____________________________________________________________");
//                     System.out.println(" Got it. I've added this task:");
//                     System.out.println("   " + tasks.get(tasks.size() - 1));
//                     System.out.println(" Now you have " + tasks.get(tasks.size()) + " tasks in the list.");
//                     System.out.println("____________________________________________________________");
//                     storage.save(tasks);



            
//                 } else if (input.startsWith("delete ")) {
//                     int taskNumber = Integer.parseInt(input.substring(7)) - 1;
//                     if (taskNumber < 0 || taskNumber >= tasks.size()) {
//                         throw new JerielException("Invalid task number. Please enter a valid task number.");
//                     }
//                     Task removedTask = tasks.remove(taskNumber);
//                     System.out.println("____________________________________________________________");
//                     System.out.println(" Noted. I've removed this task:");
//                     System.out.println("   " + removedTask);
//                     System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
//                     System.out.println("____________________________________________________________");
//                     storage.save(tasks);



                
//                 } else {
//                     throw new JerielException("I'm sorry, but I don't know what that means :-(");
//                 }
//             } catch (JerielException e) {
//                 System.out.println("____________________________________________________________");
//                 System.out.println(" OH NO DIOS MIOS!!! " + e.getMessage());
//                 System.out.println("____________________________________________________________");


//             } catch (Exception e) {
//                 System.out.println("____________________________________________________________");
//                 System.out.println(" Something went wrong: " + e.getMessage());
//                 System.out.println("____________________________________________________________");
//             }
//         }

//         scanner.close();
//     }

// }
