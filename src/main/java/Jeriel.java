import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Jeriel {

    public static void main(String[] args) {  
        String logo = "     _  ____  ___   _  ____  _      \n"
                    + "    | || ___|| _ \\ | || ___|| |     \n"
                    + "    | |||___ ||_> || |||___ | |     \n"
                    + " _  | || ___||  _/ | || ___|| |     \n"
                    + "| |_| |||___ ||\\\\  | |||___ | |____ \n"
                    + " \\___/ |____||| \\\\ |_||____||______|\n";
        System.out.println("Hello from\n" + logo);

        // System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jeriel");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        // Task[] tasks = new Task[100];
        // int taskCount = 0;
        ArrayList<Task> tasks = new ArrayList<>();

        Storage storage = new Storage("../../data/duke.txt");  // Initialize Storage with file path

        try {
            tasks = storage.load();
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }


        while (true) {
            input = scanner.nextLine(); 
            // System.out.println("____________________________________________________________");
            // System.out.println(" " + input);
            // System.out.println("____________________________________________________________");

            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");

                    break;

                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");


                } else if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new JerielException("Invalid task number. Please enter a valid task number.");
                    }                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNumber));
                    System.out.println("____________________________________________________________");
                    storage.save(tasks);

                } else if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new JerielException("Invalid task number. Please enter a valid task number.");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskNumber));
                    System.out.println("____________________________________________________________");
                    storage.save(tasks);

                } else if (input.startsWith("todo")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new JerielException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    storage.save(tasks);




                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new JerielException("The description and due date of a deadline cannot be empty.");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.get(tasks.size() - 1) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    storage.save(tasks);


                
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split(" /from | /to ");
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new JerielException("The description, start time, and end time of an event cannot be empty.");
                    }
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(description, from, to));

                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.get(tasks.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    storage.save(tasks);



            
                } else if (input.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new JerielException("Invalid task number. Please enter a valid task number.");
                    }
                    Task removedTask = tasks.remove(taskNumber);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    storage.save(tasks);



                
                } else {
                    throw new JerielException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (JerielException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OH NO DIOS MIOS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");


            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Something went wrong: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

}
