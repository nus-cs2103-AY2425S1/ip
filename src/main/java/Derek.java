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
                    + "What do you want me to do?\n"
                    +"----------------------------------------------------------------------\n"
                    + "Please enter your commands correctly for Derek (he's a little slow):\n"
                    + "todo (task)\n"
                    + "event (task) /from (start time) /to (end time) \n"
                    + "deadline (task) /by (date) \n");
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
        } else if (command.isDeleteCommand()) {
            String[] words = name.split("\\s+");
            deleteTask(Integer.valueOf(words[1]));
        } else {
            addTask(command);
        }
    }

    public void deleteTask(Integer number) {
        Task task = taskList.get(number);
        taskList.remove(number);
        System.out.println( "phew! that list was looooonngggg... i was getting tired of remembering it!" + "\n" + task.toString());
        acceptCommands();
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

    public void addTask(Command command) {
        try {
            String name = command.getTask();
            Task task;
            if (command.isDeadlineTask()) {
                String[] information = name.split("/");
                task = Task.deadlineTask(information[0], information[1]);
            } else if (command.isEventTask()) {
                String[] information = name.split("/");
                task = Task.eventTask(information[0], information[1], information[2]);
            } else if (command.isToDoTask()) {
                task = Task.toDoTask(String.join(" ", name));
            } else {
                throw new IncorrectCommandException(String.format("Is it a todo, event, or deadline?\n"
                                                                    + "Please enter your commands correctly for Derek (e.g. todo (task)), he keeps throwing tantrums"));
            }
            taskList.add(task);
            String celebration = generateRandomCelebration();
            System.out.println(celebration + "\n" + task + "\n");

            System.out.println("anything else?");
            acceptCommands();
        } catch (IncorrectCommandException e) {
            System.out.println("C'mon, I can't understand what you're saying! Help me out here!\n"
                    + sadLogo
                    + "\n"
                    + e.getMessage());

            acceptCommands();
        }

    }

    public String generateRandomCelebration() {
        String[] celebrationMessages = new String[]{"yay!", "woohoo!", "let's go!!!!", "great job :)"};
        Random random = new Random();
        int min = 0;
        int max = celebrationMessages.length;
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




