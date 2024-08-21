import java.util.Scanner;

public class Snah {

    private final static String EXIT_INPUT = "bye";
    private final static String LIST_INPUT = "list";
    private final static String MARK_DONE_STRING = "mark";
    private final static String UNMARK_DONE_STRING = "unmark";
    private final static String DEADLINE_INPUT = "deadline";
    private final static String EVENT_INPUT = "event";
    private final static String TODO_INPUT = "todo";
    private final static String CHAT_NAME = "Snah";
    private final static String START_DIVIDER = "___________________________________________";
    private final static String END_DIVIDER = "___________________________________________\n";

    public static void main(String[] args) {
        greet();
        chatLoop();
    }

    public static void chatbotPrint(String message) {
        System.out.printf("\t%s\n", message);
    }

    public static void greet() {
        chatbotPrint(START_DIVIDER);
        chatbotPrint(String.format("Hello! I'm %s", CHAT_NAME));
        chatbotPrint("What can I do for you?");
        chatbotPrint(END_DIVIDER);
    }

    public static void chatLoop() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        Task[] memory = new Task[100];
        int memoryIndex = 0;

        while (true) {
            userInput = scanner.nextLine();

            chatbotPrint(START_DIVIDER);
            if (userInput.equals(EXIT_INPUT)) {
                chatbotPrint("Goodbye! See you sooooonnn!");
                break;
            } else if (userInput.equals(LIST_INPUT)) {
                chatbotPrint("Here are the tasks in your list:");
                for (int i = 0; i < memoryIndex; i++) {
                    chatbotPrint(String.format("%d. %s", i + 1, memory[i]));
                }
            } else if (userInput.startsWith(MARK_DONE_STRING)) {
                String[] splitInput = userInput.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                memory[taskIndex].markAsDone();

                chatbotPrint(String.format("Alright, I will mark the task as done"));
                chatbotPrint(String.format("  %s", memory[taskIndex]));

            } else if (userInput.startsWith(UNMARK_DONE_STRING)) {
                String[] splitInput = userInput.split(" ");
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                memory[taskIndex].unmarkAsDone();

                chatbotPrint(String.format("Walao, why you press wrong; Will mark the task as NOT done"));
                chatbotPrint(String.format("  %s", memory[taskIndex]));

            } else if (userInput.startsWith(TODO_INPUT)) {
                String taskDescription = userInput.substring(TODO_INPUT.length() + 1);
                memory[memoryIndex] = new ToDo(taskDescription);
                chatbotPrint("Added todo to list");
                chatbotPrint(String.format("  %s", memory[memoryIndex]));
                memoryIndex++;
            } else if (userInput.startsWith(DEADLINE_INPUT)) {
                String taskDescription = userInput.substring(DEADLINE_INPUT.length() + 1);
                String[] splitInput = taskDescription.split(" /by ");
                memory[memoryIndex] = new Deadline(splitInput[0], splitInput[1]);
                chatbotPrint("Added deadline to list");
                chatbotPrint(String.format("  %s", memory[memoryIndex]));
                memoryIndex++;
            } else if (userInput.startsWith(EVENT_INPUT)) {
                String taskDescription = userInput.substring(EVENT_INPUT.length() + 1);
                String[] splitInput = taskDescription.split(" /from ");
                String[] finalSplit = splitInput[1].split(" /to ");
                memory[memoryIndex] = new Event(splitInput[0], finalSplit[0], finalSplit[1]);
                chatbotPrint("Added event to list");
                chatbotPrint(String.format("  %s", memory[memoryIndex]));
                memoryIndex++;
            } else {
                chatbotPrint(String.format("Add task \"%s\" to list", userInput));

                memory[memoryIndex] = new Task(userInput);
                memoryIndex++;
            }
            chatbotPrint(END_DIVIDER);

        }
        scanner.close();
    }
}
