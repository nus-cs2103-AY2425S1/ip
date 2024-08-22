import java.util.Scanner;
import java.util.ArrayList;

public class Juno {
    private String logo;
    private String greeting;
    private String farewell;

    private final String exitString = "bye";

    private final String listTaskString = "list";
    private ArrayList<Task> tasks;

    private final String markTaskString = "mark";
    private final String unmarkTaskString = "unmark";
    private final String addTaskString = "add";

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
        this.tasks = new ArrayList<>();
    }

    public void startBot() {
        // start the chat bot
        System.out.println("___________________________________________________________________");
        System.out.println(this.logo);
        System.out.println("___________________________________________________________________");
        System.out.println(this.greeting);
        System.out.println("___________________________________________________________________");

        // detect what user inputs with a scanner
        this.detectUserInput();
    }

    public void detectUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase(this.exitString)) {
                this.farewellMessage();
                break;
            } else if (userInput.equalsIgnoreCase(this.listTaskString)) {
                this.listTasks();
            } else if (userInput.isEmpty()) {
                this.invalidUserInput();
            } else if (userInput.startsWith(this.markTaskString)) {
                this.toggleTaskStatus(userInput, true);
            } else if (userInput.startsWith(this.unmarkTaskString)) {
                this.toggleTaskStatus(userInput, false);
            } else if (userInput.startsWith(this.addTaskString)) {
                this.addTask(userInput);
            } else {
                this.invalidFunctionInput();
            }
        }

        scanner.close();
    }

    private void farewellMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(this.farewell);
        System.out.println("____________________________________________________________");
    }

    private void listTasks() {
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks added yet!");
        } else {
            System.out.println("Here's a rundown of all your tasks! \uD83D\uDE0A");
            for (int i = 0; i < this.tasks.size(); i++) {
                String formmattedString = String.format(
                        "%d. %s",
                        (i + 1),
                        this.tasks.get(i).toString()
                );
                System.out.println(formmattedString);
            }
        }
    }

    private void addTask(String userInput) {
        // Check if the task already exists
        for (Task task : this.tasks) {
            if (task.getDescription().equalsIgnoreCase(userInput)) {
                System.out.println("This task is already in your list! Maybe you can try renaming it and input again?");
                return;
            }
        }
        String taskDescription;
        try {
            taskDescription = userInput.split("\\s+", 2)[1];
        } catch(Exception e) {
            System.out.println("Hmm, something went wrong. Did you add task correctly? " +
                    "(\uD83D\uDCA1 Tip: Use \"add {Input task description here}\" to add a task)");
            return;
        }
        Task t = new Task(taskDescription);
        tasks.add(t);
        System.out.println("Got it! I've added : \"" + taskDescription + "\" in my memory!");
    }

    private void invalidUserInput() {
        System.out.println("Well, seems like you did not input anything! Please try again.");
    }

    // Could implement a help function to see all possible commands in the future
    private void invalidFunctionInput() {
        System.out.println("The input you provided is invalid! " +
                "(\uD83D\uDCA1 Tip: You can use the following commands \"add\", \"list\",\"mark\", \"unmark\".)");
    }

    private void toggleTaskStatus(String userInput, boolean markAsDone) {
        try {
            int taskNumber = Integer.parseInt(userInput.split("\\s+", 2)[1]) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                Task taskToMark = tasks.get(taskNumber);
                if (markAsDone) {
                    if (taskToMark.getIsDone()) {
                        System.out.println("You have completed the task \"" +
                                taskToMark.getDescription() +
                                "\" already!");
                    } else {
                        taskToMark.markAsDone();
                        System.out.println("Great work! Knew you would have completed it.");
                    }
                } else {
                    if (!taskToMark.getIsDone()) {
                        System.out.println("Task \"" +
                                taskToMark.getDescription() +
                                "\" is still not done! You can't unmark an undone task!");
                    } else {
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println("Hey, I have unmarked this task for you. " +
                                "Maybe you should start working on it soon?");
                    }
                }
                System.out.println("  " + tasks.get(taskNumber).toString());
            } else {
                System.out.println("Oops! That task number is out of range. " +
                        "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)");
            }
        } catch (Exception e) {
            System.out.println("Hmm, something went wrong. Please enter a task number after mark/unmark. " +
                    "(\uD83D\uDCA1 Tip: You can type \"list\" to see task numbers)");
        }
    }

    public static void main(String[] args) {
        Juno junoChatBot = new Juno();
        junoChatBot.startBot();

    }
}
