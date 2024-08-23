import java.util.Scanner;

public class Chappy {

    private static Task[] userInputArray = new Task[100];
    private static int userInputArrayPointer = 0;

    public static void main(String[] args) {
        String logo = """            
            :..               
           #@@@@#+:           
          -@@@@@@@@@*-        
         .@@@@@@@@@@@@@#-     
         %@@@@@@@@@@@@@@@%=   
  -==:. *#@@@@@@@@@@@@@@@@@@. 
 .@@@@@%=--*@@@@@@@@%%%%@@%-  
  .#@@@#-::::=#@@@@%%%@@%-    
    :#@@@#-::...:+#%@@%=      
      .+%@@%*-     :*=        
         :+%@@@*=-=%%=        
            :=#@@@@@@@%.      
                :=*#@@@*      
 .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.
| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
| |     ______   | || |  ____  ____  | || |      __      | || |   ______     | || |   ______     | || |  ____  ____  | |
| |   .' ___  |  | || | |_   ||   _| | || |     /  \\     | || |  |_   __ \\   | || |  |_   __ \\   | || | |_  _||_  _| | |
| |  / .'   \\_|  | || |   | |__| |   | || |    / /\\ \\    | || |    | |__) |  | || |    | |__) |  | || |   \\ \\  / /   | |
| |  | |         | || |   |  __  |   | || |   / ____ \\   | || |    |  ___/   | || |    |  ___/   | || |    \\ \\/ /    | |
| |  \\ `.___.'\\  | || |  _| |  | |_  | || | _/ /    \\ \\_ | || |   _| |_      | || |   _| |_      | || |    _|  |_    | |
| |   `._____.'  | || | |____||____| | || ||____|  |____|| || |  |_____|     | || |  |_____|     | || |   |______|   | |
| |              | || |              | || |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
 """;
        System.out.println("Good day sir! \n" + logo + "\nat your service!");
        System.out.println("I shall await your next request.");
        System.out.println("____________________________________________________________");

        while(true) {
            Scanner scannerObj = new Scanner(System.in); 
            String userInput = scannerObj.nextLine();
            
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("It's been a pleasure serving you! Farewell sir.");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < userInputArray.length; i++) {
                    if (userInputArray[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + "." + userInputArray[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().contains("unmark")) {
                int t = Integer.parseInt(userInput.split(" ")[1]) - 1;
                userInputArray[t].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("Aww.. I've marked this task as not done yet..");
                System.out.println(userInputArray[t].toString());
                System.out.println("____________________________________________________________");

            }  else if (userInput.toLowerCase().contains("mark")) {
                int t = Integer.parseInt(userInput.split(" ")[1]) - 1;
                userInputArray[t].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Fantastic! I've marked this task as done!");
                System.out.println(userInputArray[t].toString());
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().contains("todo")) {
                String[] t = userInput.split("todo");
                if (t.length < 2) {
                    System.out.println("Oh SIR! The description of a todo cannot be empty!");
                    continue;
                }
                Todo task = new Todo(t[1]);
                userInputArray[userInputArrayPointer] = task;
                userInputArrayPointer++;
                System.out.println("____________________________________________________________");
                System.out.println("Alright sir! I've added this task:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().contains("deadline")) {
                String[] t = userInput.split("deadline");
                if (t.length < 2) {
                    System.out.println("Oh SIR! The description of a deadline cannot be empty!");
                    continue;
                }
                String[] t2 = t[1].split("/by");
                if (t2.length < 2) {
                    System.out.println("Oh SIR! The \"by\" description of a deadline cannot be empty!");
                    continue;
                }
                Deadline task = new Deadline(t2[0], t2[1]);
                userInputArray[userInputArrayPointer] = task;
                userInputArrayPointer++;
                System.out.println("____________________________________________________________");
                System.out.println("Alright sir! I've added this task:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().contains("event")) {
                String[] t = userInput.split("event");
                if (t.length < 2) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Oh SIR! The description of an event cannot be empty!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                String[] t2 = t[1].split("/from");
                if (t2.length < 2) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Oh SIR! The \"from\" description of an event cannot be empty!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                
                String[] t3 = t2[1].split("/to");
                if (t3.length < 2) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Oh SIR! The \"by\" description of an event cannot be empty!");
                    System.out.println("____________________________________________________________");
                    continue;
                }

                Event task = new Event(t2[0], t3[0], t3[1]);
                userInputArray[userInputArrayPointer] = task;
                userInputArrayPointer++;
                System.out.println("____________________________________________________________");
                System.out.println("Alright sir! I've added this task:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else if (userInput != "") {
                System.out.println("____________________________________________________________");
                System.out.println("Oh SIR! I can't understand what you are saying!");
                System.out.println("____________________________________________________________");
                
            }
            
           
            
        }
        
       
    }
}
