import java.util.Arrays;
import java.util.Scanner;
public class Alpha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String initialResponse = "____________________________________________________________\n"
                + "Hello! I'm Alpha\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        
        System.out.println(initialResponse);
    
        while (true) {
            String s1 = scanner.nextLine();
        
            // Check if the user input is "bye"
            if (s1.equals("bye")) {
                String echoResponse = "____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!" +"\n"
                        + "____________________________________________________________\n";
                System.out.println(echoResponse);
                break;  // Exit the loop
            }
    
            else if (s1.equals("list")) {
                String echoResponse = "____________________________________________________________\n"
                        +"Here are the tasks in your list:\n"
                        + storage.listWord() +"\n"
                        + "____________________________________________________________\n";
                System.out.println(echoResponse);
            }
            
            else if (s1.split(" ")[0].equals("mark")) {
                Integer indexInvolved = Integer.valueOf(s1.split(" ")[1]);
                String modifiedRecord = storage.modifyOperation(indexInvolved, true);
                String echoResponse = "____________________________________________________________\n"
                        +"Nice! I've marked this task as done:\n"
                        + modifiedRecord +"\n"
                        + "____________________________________________________________\n";
                System.out.println(echoResponse);
            }
    
            else if (s1.split(" ")[0].equals("unmark")) {
                Integer indexInvolved = Integer.valueOf(s1.split(" ")[1]);
                String modifiedRecord = storage.modifyOperation(indexInvolved, false);
                String echoResponse = "____________________________________________________________\n"
                        +"OK, I've marked this task as not done yet:\n "
                        + modifiedRecord +"\n"
                        + "____________________________________________________________\n";
                System.out.println(echoResponse);
            }
            
            else if (s1.split(" ")[0].equals("todo")) {
                ToDo NewToDo = new ToDo(s1.split(" ")[1]);
                storage.storeTask(NewToDo);
                String echoResponse = "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + storage.lastTask().toString() + "\n"
                        + storage.getLength() + "\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            }

            else if (s1.split(" ")[0].equals("deadline")) {
                String[] splitArray = s1.split(" ");
                String processedInput = String.join(" ", Arrays.copyOfRange(splitArray, 1, splitArray.length));
    
                // Further processing to get the description before the "/"
                String description = processedInput.split("/")[0].trim();
                
                //Further processing to get by date after the "/"
                String by = processedInput.split("/")[1].trim();
                by = by.replace("by ", "");
                
                Deadline NewDeadline = new Deadline(description, by);
                storage.storeTask(NewDeadline);
                String echoResponse = "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + storage.lastTask().toString() + "\n"
                        + storage.getLength() + "\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            }

            else if (s1.split(" ")[0].equals("event")) {
                String[] splitArray = s1.split(" ");
                String processedInput = String.join(" ", Arrays.copyOfRange(splitArray, 1, splitArray.length));
    
                // Further processing to get the description before the "/"
                String description = processedInput.split("/")[0].trim();
    
                //Further processing to get start date after the "/"
                String start = processedInput.split("/")[1].trim();
                start = start.replace("from ", "");
    
                //Further processing to get start date after the "/"
                String end = processedInput.split("/")[2].trim();
                end = start.replace("to" , "");
                
                Event NewEvent = new Event(description, start, end);
                storage.storeTask(NewEvent);
                String echoResponse = "____________________________________________________________ \n"
                        + "Got it. I've added this task: \n"
                        + storage.lastTask().toString() + "\n"
                        + storage.getLength() + "\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            } else {
                System.out.println("Sorry User, Alpha only accepts, todo, deadline, event, list, bye, mark and unmark commands");
            }
            
        }
    
        // Close the scanner after the loop ends
        scanner.close();
    }
}