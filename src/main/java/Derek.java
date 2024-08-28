import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;

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


    private static String dataFilePathName = "./data/derek.txt";
    private String user;

    private static String leavingMessage = String.format("Ok...\n" + sadLogo);

    private ArrayList<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {
        try {
            Derek instance = new Derek();
            File tasks = new File(dataFilePathName);
            if (!tasks.exists()) {
                throw new FileNotFoundException("Data file not found");
            } else if (tasks.isDirectory()) {
                throw new FileNotFoundException("Path is a directory");
            }
            Scanner getList = new Scanner(tasks);
            while (getList.hasNext()) {
                String task = getList.nextLine();
                instance.populateTaskList(task);
            }
            instance.overrideFile();
            instance.introduction();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void populateTaskList(String task) {
        String[] components = task.split("\\|");
        if (components[0].equals("D")) {
            Task newTask = Task.deadlineTask(components[2], components[3], components[1]);
            taskList.add(newTask);
        } else if (components[0].equals("E")) {
            Task newTask = Task.eventTask(components[2], components[3], components[4], components[1]);
            taskList.add(newTask);
        } else {
            Task newTask = Task.toDoTask(components[2], components[1]);
            taskList.add(newTask);
        }
    }


    /**
     * Introduces Derek and initiates user interaction to become friends.
     */
    @Override
    public void introduction() {
        try {
            System.out.println("Hello! I'm Derek! Can we be friends?\n" + logo);
            String userInput =
                    "Your response (Y/N):";
            System.out.println(userInput);
            Scanner sc = new Scanner(System.in);
            String response = sc.nextLine();
            Command intro = new Command(response);
            intro.isConsent();
            if (response.equalsIgnoreCase("Y")) {
                System.out.println("Great! I have always wanted a friend!\n"
                        + "What do I call you?");
                Scanner name = new Scanner(System.in);
                this.user = name.nextLine();
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
                acceptCommands();
            } else if (response.equalsIgnoreCase("N")) {
                System.out.println(leavingMessage);
            }
        } catch (IncorrectCommandException e) {
            System.out.println(e.getMessage() + "\n");
            this.introduction();
        }

    }

    /**
     * Accepts commands from the user and processes them.
     */
    @Override
    public void acceptCommands() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String name = sc.nextLine();
                Command command = new Command(name);

                if (command.isLeavingCommand()) {
                    System.out.println(leavingMessage);
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        String isCompleted = task.isCompleted();
                        if (task instanceof DeadlineTask) {
                            DeadlineTask deadlineTask = (DeadlineTask) task;
                            String deadline = deadlineTask.getDeadline();
                            String taskName = deadlineTask.getName();
                            writeToFile("D|" + isCompleted  + "|" + taskName + "|" + deadline + "\n");
                        } else if (task instanceof EventTask) {
                            EventTask eventTask = (EventTask) task;
                            String startTime = eventTask.getStartTime();
                            String endTime = eventTask.getEndTime();
                            String taskName = eventTask.getName();
                            writeToFile("E|" + isCompleted + "|" + taskName + "|" + startTime + "|" + endTime + "\n");
                        } else {
                            String taskName = task.getName();
                            writeToFile("T|" + isCompleted + "|" + taskName + "\n");
                        }
                    }
                    break;  // Exit the loop and end the program
                } else if (command.isListCommand()) {
                    this.returnList();
                } else if (command.isCompletedCommand(this.taskList.size())) {
                    String[] words = name.split("\\s+");
                    this.markCompleted(Integer.valueOf(words[1]));
                } else if (command.isIncompleteCommand(this.taskList.size())) {
                    String[] words = name.split("\\s+");
                    this.markIncomplete(Integer.valueOf(words[1]));
                } else if (command.isDeleteCommand(this.taskList.size())) {
                    String[] words = name.split("\\s+");
                    this.deleteTask(Integer.valueOf(words[1]));
                } else {
                    this.addTask(command);
                }

                System.out.println("anything else?");

            } catch (IncorrectCommandException e) {
                System.out.println(e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Deletes a task from the task list.
     * @param number the index of the task to delete
     */
    public void deleteTask(Integer number) {
        Task task = taskList.get(number - 1);
        taskList.remove(number - 1);
        System.out.println( "phew! that list was looooonngggg... i was getting tired of remembering it!" + "\n" + task.toString());
    }

    /**
     * Marks a task as completed.
     * @param number the index of the task to mark as completed
     */
    public void markCompleted(Integer number) {
        Task task = taskList.get(number - 1);
        task.markCompleted();
        String celebration = generateRandomCelebration();
        System.out.println(celebration + " you slayed that!" + "\n" + task.toString());
    }


    /**
     * Marks a task as incomplete.
     * @param number the index of the task to mark as incomplete
     */
    public void markIncomplete(Integer number) {
        Task task = taskList.get(number - 1);
        task.markIncomplete();
        System.out.println("You'll get 'em next time!" + "\n" + task.toString());
    }


    /**
     * Adds a task to the task list based on the user's command.
     * @param command the command containing task details
     */
    public void addTask(Command command) {
        try {
            String name = command.getTask();
            Task task;
            if (command.isDeadlineTask()) {
                String[] information = name.split("/by");
                task = Task.deadlineTask(information[0], information[1]);
            } else if (command.isEventTask()) {
                String[] information = name.split("/from");
                String[] time = information[1].split("/to");
                task = Task.eventTask(information[0], time[0], time[1]);
            } else if (command.isToDoTask()) {
                task = Task.toDoTask(name);
            } else {
                throw new IncorrectCommandException("Is it a todo, event, or deadline?\nPlease enter your commands correctly for Derek (e.g. todo (task)), he keeps throwing tantrums");
            }
            taskList.add(task);
            String celebration = generateRandomCelebration();
            System.out.println(celebration + "\n" + task + "\n");

        } catch (IncorrectCommandException e) {
            System.out.println("C'mon, I can't understand what you're saying! Help me out here!\n" + sadLogo + "\n" + e.getMessage());
        }
    }

    @Override
    public void writeToFile(String textToAdd) throws IOException{
        FileWriter writer = new FileWriter(dataFilePathName, true);
        writer.write(textToAdd);
        writer.close();

    }

    public void overrideFile() throws IOException{
        FileWriter writer = new FileWriter(dataFilePathName);
        writer.write("");
        writer.close();

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






