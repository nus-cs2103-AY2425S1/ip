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

    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String logo = "   _____                .__                          __                \n"
                    + "  /  _  \\ ______ ______ |  |   ____ _____    _______/  |_  ___________ \n"
                    + " /  /_\\  \\\\____ \\\\____ \\|  | _/ __ \\\\__  \\  /  ___/\\   __\\/ __ \\_  __ \\\n"
                    + "/    |    \\  |_> >  |_> >  |_\\  ___/ / __ \\_\\___ \\  |  | \\  ___/|  | \\/\n"
                    + "\\____|__  /   __/|   __/|____/\\___  >____  /____  > |__|  \\___  >__|   \n"
                    + "        \\/|__|   |__|             \\/     \\/     \\/            \\/       \n";
        Random random = new Random();
        
        System.out.println("------------------------------------");
        System.out.println(logo);
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
        if (input.equalsIgnoreCase("list")) {
            listTasks();
        } else {
            addTask(input);
        }
    }

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("added: " + task);
        } else {
            System.out.println("Task list is full. Cannot add more tasks.");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("No tasks in the list.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }
}