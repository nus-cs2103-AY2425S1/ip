import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
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
    @Override
    public void introduction() {

        System.out.println("Hello! I'm Derek! Can we be friends?\n" + logo);
        String userInput =
                "Your response (Y/N): ";
        System.out.println(userInput);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        if (response.equals("Y")) {
            System.out.println("Great! I have always wanted a friend!\n"
                    + "What do I call you?");
            Scanner name = new Scanner(System.in);
            this.user = name.nextLine();
            System.out.println("\n" + "Hi! " + this.user + "! So, I guess as a friend I just become your little slave!\n"
                    + "What do you want me to do?\n");
            acceptCommands();
        } else {
            System.out.println(leavingMessage);
        }

    }

    @Override
    public void acceptCommands() {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        Command command = new Command(name);
        if (command.isLeavingCommand()) {
            System.out.println(leavingMessage);
        } else if (command.isListCommand()) {
            returnList();
        } else if (command.isCompletedCommand()) {
            String[] words = name.split("\\s+");
            markCompleted(Integer.valueOf(words[1]));
        } else if (command.isIncompleteCommand()) {
            String[] words = name.split("\\s+");
            markIncomplete(Integer.valueOf(words[1]));
        } else {
            addTask(name);
        }
    }

    public void markCompleted(Integer number) {
        Task task = taskList.get(number - 1);
        task.markCompleted();
        String celebration = generateRandomCelebration();
        System.out.println(celebration + " you slayed that!" + "\n" + task.toString());
        acceptCommands();
    }

    public void markIncomplete(Integer number) {
        Task task = taskList.get(number - 1);
        task.markIncomplete();
        System.out.println("You'll get 'em next time!" + "\n" + task.toString());
        acceptCommands();
    }

    public void addTask(String name) {
        String celebration = generateRandomCelebration();
        System.out.println(celebration + " " +  name + "\n");
        Task task = new Task(name);
        taskList.add(task);
        System.out.println("anything else?");
        acceptCommands();
    }

    public String generateRandomCelebration() {
        String[] celebrationMessages = new String[]{"yay!", "woohoo!", "let's go!!!!"};
        Random random = new Random();
        int min = 0;
        int max = 2;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        String celebration = celebrationMessages[randomNumber];
        return celebration;
    }

    @Override
    public void returnList(){
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += String.format((i+1) + ". " + taskList.get(i).toString() + "\n");
        }
        System.out.println("I think these are your tasks! Please don't leave me!!\n" + list);
        acceptCommands();
    }



}




