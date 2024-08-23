import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sinatra {
    

    private ArrayList<Task> Tasks;

    public Sinatra() {
        this.Tasks = new ArrayList<Task>();
        this.printIntro();
     
        this.sinatraScanner();
    }
    private void printIntro() {
        String initial = "Hello! I'm Sinatra \nWhat can I do for you? ";
       
        //print intro
        System.out.println(initial);
    }

    public static void main(String[] args) {
        new Sinatra();

      

    }

    private void sinatraScanner() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            handleInputs(input);
        }
      
    }

    private void handleInputs(String message) {
        try{
        if (message.equals("list")) {
       System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Tasks.size(); i++) {
                int count = i + 1;
             
    
                System.out.println(count + "."+ Tasks.get(i).toString());

            }
        } else if (message.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (message.length()>4 && message.substring(0, 4).equals("mark")) {
            Task currTask = Tasks.get(Integer.parseInt(message.substring(5)) - 1);
             currTask.setStatus(true);
            System.out.println("Nice! I've marked this task as done: ");
              System.out.println("  "+currTask);

        
          } else if (message.length() > 6 && message.substring(0, 6).equals("unmark")) {
              Task currTask = Tasks.get(Integer.parseInt(message.substring(7)) - 1);
              currTask.setStatus(false);
              System.out.println("OK, I've marked this task as not done yet: ");
              System.out.println("  "+currTask);

          } else if (message.length() >= 4 && message.substring(0, 4).equals("todo")) {
           
                    if (message.length() <= 4) {
                        throw new SinatraException("OOPS!!! The description of a event cannot be empty.");
                    }
              Tasks.add(new ToDo(message.substring(5, message.length()), false));
              System.out.println("Got it. I've added this task:");
                System.out.println("  "+Tasks.getLast());
                System.out.println("Now you have " + Tasks.size() + " tasks in the list.");

            } else if (message.length() >= 8 && message.substring(0, 8).equals("deadline")) {

                    if (message.length() <= 8) {
                        throw new SinatraException("OOPS!!! The description of a deadline cannot be empty.");
                    }


                   String[] parts = message.substring(9).split(" /by ");
                   String content = parts[0];
                   String by = parts[1];
                   Tasks.add(new Deadline(content, false, by));
                   System.out.println("Got it. I've added this task:");
                   System.out.println("  "+Tasks.getLast());
                   System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
                   
               

            } else if (message.length() >= 5 && message.substring(0, 5).equals("event")) {
               if (message.length() <= 5) {
                        throw new SinatraException("OOPS!!! The description of a event cannot be empty.");
                    }
            String[] parts = message.substring(6).split(" /from ");
            String content = parts[0];
            String[] timeParts = parts[1].split(" /to ");
            String from = timeParts[0];
            String to = timeParts[1];
            Tasks.add(new Event(content, false, from, to));
                 System.out.println("Got it. I've added this task:");
                System.out.println("  "+Tasks.getLast());
                System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
            

            } else if (message.length() >= 6 && message.substring(0, 6).equals("delete")) {
                Task currTask = Tasks.get(Integer.parseInt(message.substring(7)) - 1);
                Tasks.remove(currTask);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  "+currTask);
                System.out.println("Now you have " + Tasks.size() + " tasks in the list.");
            }
        else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
        catch (SinatraException e) {
                   System.out.println(e.getMessage());

               
               } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());

            } finally {
                     
               }
        
    }
}
