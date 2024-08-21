import java.util.Scanner;

public class Snah {

    private final static String EXIT_INPUT = "bye";
    private final static String LIST_INPUT = "list";
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

        String[] memory = new String[100];
        int memoryIndex = 0;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals(EXIT_INPUT)) {
                chatbotPrint(START_DIVIDER);
                chatbotPrint("Goodbye! See you sooooonnn!");
                chatbotPrint(END_DIVIDER);
                break;
            } else if (userInput.equals(LIST_INPUT)) {
                chatbotPrint(START_DIVIDER);
                for (int i = 0; i < memoryIndex; i++) {
                    chatbotPrint(String.format("%d. %s", i + 1, memory[i]));
                }
                chatbotPrint(END_DIVIDER);
            } else {
                chatbotPrint(START_DIVIDER);
                chatbotPrint(String.format("Add to list: \"%s\"", userInput));
                chatbotPrint(END_DIVIDER);

                memory[memoryIndex] = userInput;
                memoryIndex++;
            }

        }
        scanner.close();
    }
}
