import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JBot {

    private static boolean isRunning = true;
    private static Map<String, JBotCommand> commandMap;
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static void hLine() {
        System.out.println("________________________________________");
    }

    public static void initCommandMap() {
        commandMap = new HashMap<>();

        commandMap.put("list", ListCommand.getInstance());
        commandMap.put("bye", ByeCommand.getInstance());
    }
    private static void greetUser() {
        hLine();
        System.out.println("Hello! I'm JBot");
        System.out.println("What can I do for you?");
        hLine();
    }
    private static void endSession() {

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initCommandMap();

        greetUser();

        while (JBot.isRunning) {
            String userInput = sc.nextLine();
            JBotCommand command = commandMap.getOrDefault(userInput, AddCommand.getInstance());

            hLine();
            command.run(userInput, taskList);
            hLine();

            if (command.equals(ByeCommand.getInstance())) {
                sc.close();
                JBot.isRunning = false;
            }
        }
    }
}
