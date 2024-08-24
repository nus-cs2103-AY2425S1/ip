import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


/**
 * Derek is a bot that interacts with the user, manages tasks, and provides functionalities like
 * adding tasks, marking them as complete or incomplete, deleting tasks, and more.
 */
public class Derek implements Bot {
    private static String logo = " ---    ---\n"
            +"| # |  | # |\n"
            +" ---    ---\n"
            +"  \\      /\n"
            +"    ----\n";

    private static String sadLogo = " ---    ---\n"
            + "| # |  | # |\n"
            + " ---    ---\n"
            + "    ----\n"
            + "  /      \\\n";


    private String user;

    private static String leavingMessage = String.format("Ok...\n" + sadLogo);

    private ArrayList<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {
        Derek instance = new Derek();
        instance.introduction();
    }

    /**
     * Introduces Derek and initiates user interaction to become friends.
     */
    @Override
    public void introduction() {
        System.out.println("Hello! I'm Derek! Can we be friends?\n" + logo);
        String userInput =
                "Your response (Y/N):";
        System.out.println(userInput);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        if (response.equalsIgnoreCase("Y")) {
            System.out.println("Great! I have always wanted a friend!\n"
                    + "What do I call you?");
            Scanner name = new Scanner(System.in);
            this.user = name.nextLine();
            System.out.println("\n" + "Hi! " + this.user + "! So, I guess as a friend I become your little slave!\n"
                    + "What do you want me to do?\n"
                    + "----------------------------------------------------------------------\n");
            acceptCommands();
        } else if (response.equalsIgnoreCase("N")) {
            System.out.println(leavingMessage);
        }


    }

    /**
     * Accepts commands from the user and processes them.
     */
    @Override
    public void acceptCommands() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String name = sc.nextLine();
            Command command = new Command(name);

            if (command.isLeavingCommand()) {
                System.out.println(leavingMessage);
                break;  // Exit the loop and end the program
            } else if (command.isListCommand()) {
                this.returnList();
            } else {
                this.addTask(name);
            }

        }
    }


    /**
     * Adds a task to the task list based on the user's command.
     * @param name the command containing task details
     */
    public void addTask(String name) {
        Task task = new Task(name);
        taskList.add(task);
        String celebration = generateRandomCelebration();
        System.out.println(celebration + "\n" + task + "\n");
    }

    /**
     * Generates a random celebration message.
     * @return a random celebration message
     */
    public String generateRandomCelebration() {
        String[] celebrationMessages = new String[]{"yay!", "woohoo!", "let's go!!!!", "great job :)", "you're on a roll!"};
        Random random = new Random();
        int min = 0;
        int max = celebrationMessages.length - 1;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        String celebration = celebrationMessages[randomNumber];
        return celebration;
    }

    /**
     * Returns the task list.
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the list of tasks in string format.
     */
    @Override
    public void returnList(){
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += String.format((i+1) + ". " + taskList.get(i).toString() + "\n");
        }
        System.out.println("I think these are your tasks! Please don't leave me!!\n" + list);
    }



}






