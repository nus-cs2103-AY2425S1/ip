
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Chappy {

    private enum Command {
        BYE("bye"), LIST("list"), UNMARK("unmark"), MARK("mark"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), DELETE("delete");
        private final String keyword;

        Command(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return this.keyword;
        }

    }

    protected static ArrayList<Task> userInputArray = new ArrayList<Task>();

    public static void main(String[] args) throws CreateTaskException {
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
        Scanner scannerObj = new Scanner(System.in);
        String userInput;
        while (scannerObj.hasNext()) {
            userInput = scannerObj.nextLine().trim();
            if (userInput.equals("")) {
                continue;
            }
            System.out.println("____________________________________________________________");
            Command userInputCommand = null;
            for (Command command : Command.values()) {
                if (userInput.toLowerCase().contains(command.getKeyword())) {
                    userInputCommand = command;
                    break;
                }
            }
            try {
                if (!Objects.isNull(userInputCommand)) {

                    switch (userInputCommand) {
                        case BYE:
                            System.out.println("It's been a pleasure serving you! Farewell sir.");
                            return;

                        case LIST:
                            System.out.println("As requested, here are your outstanding tasks sir:");
                            for (int i = 0; i < Chappy.userInputArray.size(); i++) {
                                System.out.println(i + 1 + "." + userInputArray.get(i).toString());
                            }
                            break;

                        case UNMARK:
                            String[] unmarkInput = userInput.trim().split("(?i)"+ Command.UNMARK.getKeyword());
                            if (unmarkInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The index input of a Unmark command cannot be empty!");
                            }
                            int unmarkIndex = Integer.parseInt(unmarkInput[1].trim()) - 1;
                            if (unmarkIndex < 0 || unmarkIndex > userInputArray.size() - 1) {
                                throw new CreateTaskException("Oh SIR! That task index does not exist!");
                            }
                            userInputArray.get(unmarkIndex).markAsNotDone();
                            break;

                        case MARK:
                            String[] markInput = userInput.trim().split("(?i)"+ Command.MARK.getKeyword());
                            if (markInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The index input of a Mark command cannot be empty!");
                            }
                            int markIndex = Integer.parseInt(markInput[1].trim()) - 1;
                            if (markIndex < 0 || markIndex > userInputArray.size() - 1) {
                                throw new CreateTaskException("Oh SIR! That task index does not exist!");
                            }
                            userInputArray.get(markIndex).markAsDone();
                            break;

                        case TODO:
                            String[] todoInput = userInput.trim().split("(?i)" + Command.TODO.getKeyword());
                            if (todoInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The description of a Todo cannot be empty!");
                            }
                            Todo todo = new Todo(todoInput[1].trim());
                            Task.addTask(todo);
                            break;

                        case DEADLINE:
                            String[] deadlineInput = userInput.trim().split("(?i)" + Command.DEADLINE.getKeyword());
                            if (deadlineInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The description of a Deadline cannot be empty!");
                            }
                            Deadline.validateOptions(userInput.trim());
                            String[] deadlineInput2 = deadlineInput[1].split(Deadline.Option.values()[0].getKeyword());
                            Deadline deadline = new Deadline(deadlineInput2[0].trim(), deadlineInput2[1].trim());
                            Task.addTask(deadline);
                            break;

                        case EVENT:
                            String[] eventInput = userInput.trim().split("(?i)" + Command.EVENT.getKeyword());
                            if (eventInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The description of an Event cannot be empty!");
                            }
                            Event.validateOptions(userInput.trim());
                            String[] eventInput2 = eventInput[1].split(Event.Option.values()[0].getKeyword());
                            String[] eventInput3 = eventInput2[1].split(Event.Option.values()[1].getKeyword());
                            Event task = new Event(eventInput2[0].trim(), eventInput3[0].trim(), eventInput3[1].trim());
                            Task.addTask(task);

                            break;

                        case DELETE:
                            String[] deleteInput = userInput.trim().split("(?i)" + Command.DELETE.getKeyword());

                            if (deleteInput.length < 2) {
                                throw new CreateTaskException("Oh SIR! The index input of a Delete command cannot be empty!");
                            }
                            int deleteIndex = Integer.parseInt(deleteInput[1].trim()) - 1;
                            if (deleteIndex < 0 || deleteIndex > userInputArray.size() - 1) {
                                throw new CreateTaskException("Oh SIR! That task index does not exist!");
                            }
                            Task.removeTask(deleteIndex);

                            break;
                    }
                } else {
                    throw new CreateTaskException("Oh SIR! I can't understand what you are saying!");
                }

            } catch (CreateTaskException e) {
                System.out.println(e.getMessage());

            }

        }
    }
}
