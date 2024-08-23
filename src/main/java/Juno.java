import java.util.Scanner;

public class Juno {
    private String logo;
    private String greeting;
    private String farewell;
    private TaskManager taskManager;

    public Juno() {
        this.logo = """
                
            .----------------.  .----------------.  .-----------------. .----------------.\s
            | .--------------. || .--------------. || .--------------. || .--------------. |
            | |     _____    | || | _____  _____ | || | ____  _____  | || |     ____     | |
            | |    |_   _|   | || ||_   _||_   _|| || ||_   \\|_   _| | || |   .'    `.   | |
            | |      | |     | || |  | |    | |  | || |  |   \\ | |   | || |  /  .--.  \\  | |
            | |   _  | |     | || |  | '    ' |  | || |  | |\\ \\| |   | || |  | |    | |  | |
            | |  | |_' |     | || |   \\ `--' /   | || | _| |_\\   |_  | || |  \\  `--'  /  | |
            | |  `.___.'     | || |    `.__.'    | || ||_____|\\____| | || |   `.____.'   | |
            | |              | || |              | || |              | || |              | |
            | '--------------' || '--------------' || '--------------' || '--------------' |
             '----------------'  '----------------'  '----------------'  '----------------'\s
            """;
        this.greeting = "🌟 Welcome to the Future! I'm Juno, your digital assistant.\n" +
                "How can I assist you on your journey today?";
        this.farewell = "👋 Farewell for now! Looking forward to our next interaction.";
    }

    public void startBot() {
        // start the chat bot
        System.out.println("___________________________________________________________________");
        System.out.println(this.logo);
        System.out.println("___________________________________________________________________");
        System.out.println(this.greeting);
        System.out.println("___________________________________________________________________");

        // task manager to handle all the task related method calls
        this.taskManager = new TaskManager();
        // detect what user inputs with a scanner
        this.detectUserInput();
    }

    public void detectUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            String markTaskString = "mark";
            String unmarkTaskString = "unmark";
            String deleteTaskString = "delete";
            String addTodoTaskString = "add todo";
            String addDeadlineTaskString = "add deadline";
            String addEventTaskString = "add event";
            String listTaskString = "list";
            String exitString = "bye";
            try {
                if (userInput.equalsIgnoreCase(exitString)) {
                    this.farewellMessage();
                    break;
                } else if (userInput.equalsIgnoreCase(listTaskString)) {
                    this.taskManager.listTasks();
                } else if (userInput.isEmpty()) {
                    this.invalidUserInput();
                } else if (userInput.startsWith(markTaskString)) {
                    this.taskManager.toggleTaskStatus(userInput, true, false);
                } else if (userInput.startsWith(unmarkTaskString)) {
                    this.taskManager.toggleTaskStatus(userInput, false, false);
                } else if (userInput.startsWith(deleteTaskString)) {
                    this.taskManager.toggleTaskStatus(userInput, false, true);
                } else if (userInput.startsWith(addTodoTaskString)) {
                    this.taskManager.addTask(userInput, "todo");
                } else if (userInput.startsWith(addDeadlineTaskString)) {
                    this.taskManager.addTask(userInput, "deadline");
                } else if (userInput.startsWith(addEventTaskString)) {
                    this.taskManager.addTask(userInput, "event");
                } else {
                    this.invalidFunctionInput();
                }
            } catch (TaskManagerException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private void farewellMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(this.farewell);
        System.out.println("____________________________________________________________");
    }

    private void invalidUserInput() {
        System.out.println("Well, seems like you did not input anything! Please try again.");
    }

    // Could implement a help function to see all possible commands in the future
    private void invalidFunctionInput() {
        System.out.println("The input you provided is invalid! " +
                "(\uD83D\uDCA1 Tip: You can use the following commands \"add\", \"list\",\"mark\", \"unmark\".)");
    }

    public static void main(String[] args) {
        Juno junoChatBot = new Juno();
        junoChatBot.startBot();
    }
}
