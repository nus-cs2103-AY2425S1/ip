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
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < userInputArray.length; i++) {
                    if (userInputArray[i] == null) {
                        break;
                    }
                    System.out.println(i+1 + ".[" + userInputArray[i].getStatusIcon() + "] " + userInputArray[i].toString());
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
                String t = userInput.split("todo")[1];
                Todo task = new Todo(t);
                userInputArray[userInputArrayPointer] = task;
                userInputArrayPointer++;
                System.out.println("Alright sir! I've added this task:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().contains("deadline")) {
                String t = userInput.split("deadline")[1];
                String[] t2 = t.split("/by");
                Deadline task = new Deadline(t2[0], t2[1]);
                userInputArray[userInputArrayPointer] = task;
                userInputArrayPointer++;
                System.out.println("Alright sir! I've added this task:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().contains("event")) {
                String t = userInput.split("event")[1];
                String[] t2 = t.split("/from");
                String[] t3 = t2[1].split("/to");

                Event task = new Event(t2[0], t3[0], t3[1]);
                userInputArray[userInputArrayPointer] = task;
                userInputArrayPointer++;
                System.out.println("Alright sir! I've added this task:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            }
            
           
            
        }
        System.out.println("____________________________________________________________");
        System.out.println("It's been a pleasure serving you! Farewell sir.");
        System.out.println("____________________________________________________________");
    }
}
