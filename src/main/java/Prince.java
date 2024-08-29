import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList; // because i need to create a list of String items
import static java.lang.Integer.parseInt;
import java.io.File;

public class Prince {

    private static final String dirPath = "./data";
    private static final String filePath = dirPath + File.separator + "prince.txt";
    //prince.txt is a file that will automatically be created if it doesnt exist
    //stores the tasks

    public enum TaskType {
        todo,
        deadline,
        event
    }

    static ArrayList<Task> list = new ArrayList<>(); //static variable
    public static String conversation(String command) throws UnknownWordException, IncompleteDescException{
        /*if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";*/
        if(command.equals("list")) {
            return listDisplay(list);
        } else if(command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            // used the library function .startsWith() to match the prefix to mark/unmark
            // use.split("") to split up the words
            // use.parseInt(num) to extract integer from the string

            // if mark, then extract integer and mark that task of the list as done
            // if unmark then extract integer and unmark that task of the list as not done

            String[] stringList = command.split(" ");
            int taskNum = Integer.parseInt(stringList[1]); //second word is the number
            Task t = list.get(taskNum - 1);

            if (taskNum < 1 || taskNum > list.size()) {
                return "Task number is out of range. Please retry.";
            }

            if(stringList[0].equals("mark")) {
                return t.markDone();
            } else if(stringList[0].equals("unmark")){
                return t.markIncomplete();
            } else {
                return taskDelDescription(taskNum, t);
            }

        } else if(checkCommandLength(command)) {
            if(command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new IncompleteDescException("OH NO! Description of the task cannot be empty!\n " +
                        "Please retry with a command like this format <task type> <task>");
            } else {
                throw new UnknownWordException("Sorry, I do not know what that means :(\n " +
                        "Please try again with a proper command.");
            }
        } else {
            // according to the first word, create a new specific task
            // split into two, first word is type, and the second phrase is task

            String[] split = command.split(" ", 2);

            String type = split[0];
            String stringTask = split[1];

            if(type.equals(TaskType.todo.toString())) {
                ToDoTask tsk = new ToDoTask(stringTask);
                addTask(tsk);
                return taskAddDescription(tsk);

            } else if (type.equals(TaskType.deadline.toString())) {
                // before splitting further in the deadline, need to check if correct format

                try {
                    // split again after by
                    String[] splitAgain = stringTask.split(" /by ", 2);
                    String taskDes = splitAgain[0];
                    String deadline = splitAgain[1];

                    DeadlinesTask tsk = new DeadlinesTask(taskDes, deadline);
                    addTask(tsk);
                    return taskAddDescription(tsk);
                } catch(InvalidDeadlineException e) {
                    return e.getMessage();
                }

            } else {
                // split again after from
                // split again after to
                String[] firstSplit = stringTask.split(" /from ", 2);
                String taskDes = firstSplit[0];
                String second = firstSplit[1];

                String[] secondSplit = second.split(" /to ", 2);
                String from = secondSplit[0];
                String to = secondSplit[1];

                EventTask tsk = new EventTask(taskDes, from, to);
                addTask(tsk);
                return taskAddDescription(tsk);
            }
        }
    }

    public static boolean checkCommandLength(String command) {
        String[] split = command.split(" ");
        return split.length == 1;
    }

    public static String listDisplay(ArrayList<Task> list) {
        int length = list.size();
        // use String Builder to ensure that the string can be created on another line
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(i + 1 + ". " + list.get(i).printTask()).append("\n");
        }

        return "Here are the tasks in your list:\n" + sb.toString();
    }

    public static void addTask(Task task){
        // add task to the List
        // return a string
        list.add(task);
        //return "added: " + task.getDescription();
    }

    public static String taskAddDescription(Task task) {
        return "Got it. I've added this task:\n" + "  " + task.printTask() + "\n" +
                "Now you have " + list.size() + " tasks in the list";
    }

    public static String taskDelDescription(int num, Task task){
        list.remove(num - 1);
        return "Noted. I've removed this task:\n" + "  " + task.printTask() + "\n" +
                "Now you have " + list.size() + " tasks in the list";
    }

    public static void createListFile() {
        try {
            //create a directory called data and if it doesnt exist then create it using the function .mkdir()
            File dir = new File(dirPath);
            if(!dir.exists()) {
                dir.mkdir();
            }

            //create the file and if the file doesnt exist then create using .createNewFile()
            File file = new File(filePath);
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) { //unchecked exception
            System.out.println("Error creating file. Please try again later.");
            // to have a clearer idea of what the exception is and where the error is, use the printStackTrace()
            e.printStackTrace();
        }
    }

    public static void pushTasksToFile(String filePath, ArrayList<Task> list) {
        //use inbuilt functions like FileWriter - that opens file at filepath, if doesnt exist, creates one
        //PrintWriter - wraps filewriter with printwriter so that can use inbuilt functions for formatting

        try(PrintWriter printwriter = new PrintWriter(new FileWriter(filePath))) {
            int length = list.size();
            for(int i = 0; i < length; i++) {
                printwriter.println(list.get(i).printFileFormat());
            }

        } catch (IOException e) {
            System.out.println("There was an error saving tasks, please try again later.");
            e.printStackTrace();
        }
    }

    public static void loadTasksFromFile(String filePath) {
        try {
            File file = new File(filePath);
            if(!file.exists()) {
                System.out.println("No file found, list is empty so far.");
            } else {
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String taskString = sc.nextLine();
                    Task task = getTaskFromFile(taskString);
                    if(task != null) {
                        addTask(task);
                    }
                }

                sc.close();
            }
        } catch (IOException e) {
            System.out.println("There was an error loading tasks, please try again later.");
            e.printStackTrace();
        }
    }

    public static Task getTaskFromFile(String taskString) {
        // need to split and add accordingly
        // first is the tasktype, then the progress, then the description, then the deadlines
        // but splitting according to " | " does not work as it has a special meaning
        // to take it as literal character, u need to put \\ in front
        String[] splits = taskString.split(" \\| ");
        String taskType = splits[0];
        String progress = splits[1];
        String taskdes = splits[2];
        boolean isDone = splits[1].equals("1");

        if(taskType.equals("T")) {
            ToDoTask task = new ToDoTask(taskdes);
            if(isDone) {
                task.markDone();
            }
            return task;

        } else if(taskType.equals("D")) {
            try {
                String deadline = splits[3];
                DeadlinesTask task = new DeadlinesTask(taskdes, deadline);
                if (isDone) {
                    task.markDone();
                }
                return task;
            } catch(InvalidDeadlineException e) {
                System.out.println(e.getMessage());
                return null;
            }

        } else if(taskType.equals("E")){
            String from = splits[3];
            String to = splits[4];
            EventTask task = new EventTask(taskdes, from, to);
            if(isDone) {
                task.markDone();
            }
            return task;
        } else {
            System.out.println("Unknown task description included. Use only existing task types - T, D, E");
            return null;
        }
    }

    public static void main(String[] args) {
        createListFile();
        loadTasksFromFile(filePath);

        // if any words, repeat scanning, but the moment the word is bye,
        // then exit and print bye
        String line = "";
        System.out.println("Hello! I'm Prince!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like me to add to your TODO list today?");

        line = scanner.nextLine(); // what the user replied

        while(!line.equals("bye")) {
            try {
                System.out.println(conversation(line));
            } catch (IncompleteDescException e) {
                System.out.println(e.getMessage());
            } catch (UnknownWordException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("How else would you like me to edit your TODO list today?");
            line = scanner.nextLine();
        }

        //when bye is said
        pushTasksToFile(filePath, list);
        System.out.println("Bye! Hope to see you again soon!");

        /*try {
            System.out.println(conversation(line));
        } catch (IncompleteDescException e) {
            System.out.println(e.getMessage());
        } catch (UnknownWordException e) {
            System.out.println(e.getMessage());
        }*/
    }
}

