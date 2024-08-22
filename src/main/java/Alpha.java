import java.util.Scanner;
public class Alpha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String initialResponse = "____________________________________________________________\n"
                + "Hello! I'm Alpha \n"
                + "What can I do for you? \n"
                + "____________________________________________________________ \n";
        
        System.out.println("Hello from\n" + initialResponse);
    
        while (true) {
            String s1 = scanner.nextLine();
        
            // Check if the user input is "bye"
            if (s1.equals("bye")) {
                String echoResponse = "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon!" +"\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
                break;  // Exit the loop
            }
    
            else if (s1.equals("list")) {
                String echoResponse = "____________________________________________________________ \nHere are the tasks in your list: \n"
                        + storage.listWord() +"\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            }
            
            else if (s1.split(" ")[0].equals("mark")) {
                Integer indexInvolved = Integer.valueOf(s1.split(" ")[1]);
                String modifiedRecord = storage.modifyOperation(indexInvolved, true);
                String echoResponse = "____________________________________________________________ \nNice! I've marked this task as done: \n"
                        + modifiedRecord +"\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            }
    
            else if (s1.split(" ")[0].equals("unmark")) {
                Integer indexInvolved = Integer.valueOf(s1.split(" ")[1]);
                String modifiedRecord = storage.modifyOperation(indexInvolved, false);
                String echoResponse = "____________________________________________________________ \nOK, I've marked this task as not done yet:\n "
                        + modifiedRecord +"\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            }
            
            else {
                Task TaskOne = new Task(s1);
                storage.storeTask(TaskOne);
                String userInput = storage.lastTask();
                String echoResponse = "____________________________________________________________ \n"
                        + "added: " + userInput + "\n"
                        + "____________________________________________________________ \n";
                System.out.println(echoResponse);
            }
        }
    
        // Close the scanner after the loop ends
        scanner.close();
    }
}