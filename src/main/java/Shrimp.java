import java.util.Scanner;

public class Shrimp {

    private static final String PARTITION = "____________________________________________________________";

    public static void main(String[] args) {
        //program initialise
        programStart();
    }

    static void programStart() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3\n";
        String logo = """
                
                       .__          .__               \s
                  _____|  |_________|__| _____ ______ \s
                 /  ___/  |  \\_  __ \\  |/     \\\\____ \\\s
                 \\___ \\|   Y  \\  | \\/  |  Y Y  \\  |_> >
                /____  >___|  /__|  |__|__|_|  /   __/\s
                     \\/     \\/               \\/|__|   \s
                                                      \s
                """;
        String output = greetings + logo;
        System.out.println(PARTITION);
        System.out.println(output);
        System.out.println(PARTITION);
        chatBotRun();
    }

    static void chatBotRun() {
        //scans for user's command
        Scanner sc = new Scanner(System.in);
        String userInput, output;
        TaskList taskList = new TaskList();

        while (true) {
            userInput = sc.nextLine();  //read the next line of user input

            CommandParser.CommandType commandType = CommandParser.parseCommand(userInput);

            switch (commandType) {
                case BYE: //exits the program
                    programExit();
                    return;

                case LIST:
                    System.out.println(PARTITION);
                    System.out.println("Gotchaaa~ Here's the list so far:");
                    for (int i = 0; i < taskList.getCount(); i++) {
                        Task task = taskList.getTask(i);
                        output =  String.format("    %s.%s", i + 1, task);
                        System.out.println(output);
                    }
                    System.out.printf("Lemme count~ You now have %s item(s) in your list!%n", taskList.getCount());
                    System.out.println(PARTITION);
                    break;

                case MARK:
                    int indexMark = getTaskNumber(userInput);
                    Task oldTaskMark = taskList.getTask(indexMark);
                    Task updatedTaskMark = oldTaskMark.markAsDone();
                    taskList.replaceTask(indexMark, updatedTaskMark);
                    output = "heya~ I've checked this task as complete! Feels good, right?";
                    System.out.println(PARTITION);
                    System.out.println(output);
                    System.out.println("    " + updatedTaskMark);
                    System.out.println(PARTITION);
                    break;

                case UNMARK:
                    int indexUnmark = getTaskNumber(userInput);
                    Task oldTaskUnmark = taskList.getTask(indexUnmark);
                    Task updatedTaskUnmark = oldTaskUnmark.markAsNotDone();
                    taskList.replaceTask(indexUnmark, updatedTaskUnmark);
                    output = "Whoops~ I've unchecked the task as incomplete! Be careful next time~";
                    System.out.println(PARTITION);
                    System.out.println(output);
                    System.out.println("    " + updatedTaskUnmark);
                    System.out.println(PARTITION);
                    break;

                case ADD:
                    Todo newTodo = new Todo(userInput); //creates a new Task object
                    taskList.addTask(newTodo);
                    output = "rawr! '" + userInput + "' has been added to the list~";
                    System.out.println(PARTITION);
                    System.out.println(output);
                    System.out.println(PARTITION);
                    break;

                case DEADLINE:
                    String[] deadlineDetails = userInput.split("/by ");
                    String deadlineDescription = deadlineDetails[0].substring(9); // Extracting the task description
                    String by = deadlineDetails[1];
                    Task newDeadline = new Deadline(deadlineDescription, by);
                    taskList.addTask(newDeadline);
                    System.out.println(PARTITION);
                    System.out.println("Gotchaa~ I've added this task:");
                    System.out.println("    " + newDeadline);
                    System.out.println("You now have " + taskList.getCount() + " task(s) in the list~");
                    System.out.println(PARTITION);
                    break;

                case EVENT:
                    String[] eventDetails = userInput.split("/from | /to ");
                    String eventDescription = eventDetails[0].substring(6); // Extracting the task description
                    String from = eventDetails[1];
                    String to = eventDetails[2];
                    Task newEvent = new Event(eventDescription, from, to);
                    taskList.addTask(newEvent);
                    System.out.println(PARTITION);
                    System.out.println("Gotchaa~ I've added this task:");
                    System.out.println("    " + newEvent);
                    System.out.println("You now have " + taskList.getCount() + " task(s) in the list~");
                    System.out.println(PARTITION);
                    break;

                default:
                    System.out.println(PARTITION);
                    System.out.println("Oh nyoo~ I don't recognise that, can you try again?");
                    System.out.println(PARTITION);
            }
        }
    }

    static void programExit() {
        String output = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~";
        System.out.println(output);
        System.out.println(PARTITION);
    }

    // Helper method to extract task number for MARK
    private static int getTaskNumber(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }
}
