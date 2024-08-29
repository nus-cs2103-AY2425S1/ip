package derek;

import derek.command.Command;
import derek.task.Task;
import derek.task.TaskList;

import java.util.Random;
import java.util.Scanner;

public class Ui {
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
    private boolean isRunning;
    private Storage storage;
    private TaskList taskList;
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Introduces Derek and initiates user interaction to become friends.
     */
    public void introduce() {
        try {
            this.isRunning = true;
            System.out.println("Hello! I'm Derek! Can we be friends?\n" + logo);
            String userInput =
                    "Your response (Y/N):";
            System.out.println(userInput);
            Scanner sc = new Scanner(System.in);
            String response = sc.nextLine();
            Command intro = new Command(response);
            intro.isConsent();
            if (response.equalsIgnoreCase("Y")) {
                getUserName();
            } else if (response.equalsIgnoreCase("N")) {
                System.out.println(leavingMessage);
            }
        } catch (IncorrectCommandException e) {
            System.out.println(e.getMessage() + "\n");
            this.introduce();
        }

    }

    public void getUserName() {
        System.out.println("Great! I have always wanted a friend!\n"
                + "What do I call you?");
        Scanner name = new Scanner(System.in);
        this.user = name.nextLine();
        initiateUserInteraction();
    }

    public void initiateUserInteraction() {
        System.out.println("\n" + "Hi! " + this.user + "! So, I guess as a friend I become your little slave!\n"
                + "What do you want me to do?\n"
                + "----------------------------------------------------------------------\n"
                + "Please enter your commands correctly for Derek (he's a little slow):\n"
                + "todo (task)\n"
                + "event (task) /from (DD/MM/YYYY HH:MM) /to (DD/MM/YYYY HH:MM))\n"
                + "deadline (task) /by (DD/MM/YYYY HH:MM)\n"
                + "mark (task number)\n"
                + "unmark (task number)\n"
                + "delete (task number)\n"
                + "bye");
        this.acceptCommands();
    }

    /**
     * Accepts commands from the user and processes them.
     */
    public void acceptCommands() {
        Scanner sc = new Scanner(System.in);

        while (isRunning) {
            String name = sc.nextLine();
            Parser parser = new Parser(name, this.storage, this);
            parser.getCommand();

        }
    }

    public void printLeavingMessage() {
        System.out.println(leavingMessage);
        this.isRunning = false;
    }

    public void returnList() {
        System.out.println("I think these are your tasks! Please don't leave me!!\n" + this.taskList);
    }

    public void removeTask(Task task) {
        System.out.println( "phew! that list was looooonngggg... i was getting tired of remembering it!" + "\n" + task.toString());
    }

    public void completeTask(Task task) {
        String celebration = generateRandomCelebration();
        System.out.println(celebration + " you slayed that!" + "\n" + task.toString());
    }

    public void incompleteTask(Task task) {
        System.out.println("You'll get 'em next time!" + "\n" + task.toString());
    }

    public void addTask(Task task) {
        String celebration = generateRandomCelebration();
        System.out.println(celebration + "\n" + task + "\n");
    }

    public String generateRandomCelebration() {
        String[] celebrationMessages = new String[]{"yay!", "woohoo!", "let's go!!!!", "great job :)", "you're on a roll!"};
        Random random = new Random();
        int min = 0;
        int max = celebrationMessages.length - 1;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        String celebration = celebrationMessages[randomNumber];
        return celebration;
    }



}




