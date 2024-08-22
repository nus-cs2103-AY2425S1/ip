import java.util.Scanner;

public class Shrimp {

    private static final String PARTITION = "____________________________________________________________";

    public static void main(String[] args) {
        //program initialize
        programStart();

        //echo user's command
        Scanner sc = new Scanner(System.in);
        String userInput;
        TaskList taskList = new TaskList();

        while (true) {
            userInput = sc.nextLine();  // Read the next line of user input
            Task newTask = new Task(userInput);

            if (userInput.equalsIgnoreCase("bye")) {
                //todo Add this as a special case (ExitTask)
                break;  // Exit the loop if "bye" is entered
            }
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(PARTITION);
                System.out.println("Gotchaaa~ Here's the list so far:");
                for (int i = 0; i < taskList.getCount(); i++) {
                    System.out.println(i+1 + ". " + taskList.getTask(i));
                }
                System.out.println(PARTITION);
            } else {
                taskList.addTask(newTask);
                String output = "rawr! '" + userInput + "' has been added to the list~";
                System.out.println(PARTITION);
                System.out.println(output);
                System.out.println(PARTITION);
            }
        }
        programExit();
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
    }

    static void programExit() {
        String output = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~";
        System.out.println(output);
        System.out.println(PARTITION);
    }
}
