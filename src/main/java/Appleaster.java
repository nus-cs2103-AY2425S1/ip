import java.util.Scanner;
import java.util.Random;

public class Appleaster {
    private static final String[] GREETINGS = {
        "Hello there! I'm feeling particularly crisp today!",
        "Greetings, human! Ready for some apple-solutely amazing conversation?",
        "Hi! I'm so excited to chat, I'm practically bobbing!"
    };
    
    private static final String[] GOODBYES = {
        "See you later! Stay fresh and don't let anyone apple-sauce you around!",
        "Goodbye! Remember, an apple a day keeps the doctor away, but I hope to see you sooner!",
        "Farewell! I'll be here, hanging on my digital tree, waiting for your return!"
    };

    private static final String LOGO = "   _____                .__                          __                \n"
                                     + "  /  _  \\ ______ ______ |  |   ____ _____    _______/  |_  ___________ \n"
                                     + " /  /_\\  \\\\____ \\\\____ \\|  | _/ __ \\\\__  \\  /  ___/\\   __\\/ __ \\_  __ \\\n"
                                     + "/    |    \\  |_> >  |_> >  |_\\  ___/ / __ \\_\\___ \\  |  | \\  ___/|  | \\/\n"
                                     + "\\____|__  /   __/|   __/|____/\\___  >____  /____  > |__|  \\___  >__|   \n"
                                     + "        \\/|__|   |__|             \\/     \\/     \\/            \\/       \n";

    private static TaskList taskList = new TaskList();
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("------------------------------------");
        System.out.println(LOGO);
        System.out.println(GREETINGS[random.nextInt(GREETINGS.length)]);
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------");
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            
            System.out.println("------------------------------------");
            processInput(input);
            System.out.println("------------------------------------");
        }
        System.out.println("------------------------------------");
        System.out.println(GOODBYES[random.nextInt(GOODBYES.length)]);
        System.out.println("------------------------------------");
        
        scanner.close();
    }

    private static void processInput(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "list":
                taskList.listTasks();
                break;
            case "mark":
                if (parts.length > 1) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        taskList.markTask(index, true);
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid task number.");
                    }
                } else {
                    System.out.println("Please specify a task number to mark.");
                }
                break;
            case "unmark":
                if (parts.length > 1) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        taskList.markTask(index, false);
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid task number.");
                    }
                } else {
                    System.out.println("Please specify a task number to unmark.");
                }
                break;
            default:
                taskList.addTask(input);
                break;
        }
    }
}
