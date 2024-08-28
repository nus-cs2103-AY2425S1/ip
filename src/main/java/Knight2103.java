import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Knight2103 {

    public static Task formatToTask(String lineInFile) {
        String[] inputArray = lineInFile.split(" \\| ");
        try {
            Task taskToAdd;
            switch (inputArray[0]) {
                case "T":
                    if (inputArray.length != 3) {
                        throw new InstructionInvalid();
                    }
                    taskToAdd = new Todo(inputArray[2]);
                    if (inputArray[1].equals("0")) {
                        return taskToAdd;
                    } else if (inputArray[1].equals("1")) {
                        taskToAdd.markDone();
                        return taskToAdd;
                    }
                    break;

                case "D":
                    if (inputArray.length != 4) {
                        throw new InstructionInvalid();
                    }
                    taskToAdd = new Deadline(inputArray[2], inputArray[3]);
                    if (inputArray[1].equals("0")) {
                        return taskToAdd;
                    } else if (inputArray[1].equals("1")) {
                        taskToAdd.markDone();
                        return taskToAdd;
                    }
                    break;

                case "E":
                    if (inputArray.length != 5) {
                        throw new InstructionInvalid();
                    }
                    taskToAdd = new Event(inputArray[2], inputArray[3], inputArray[4]);
                    if (inputArray[1].equals("0")) {
                        return taskToAdd;
                    } else if (inputArray[1].equals("1")) {
                        taskToAdd.markDone();
                        return taskToAdd;
                    }
                    break;
                default:
                    throw new InstructionInvalid();
            }
        } catch (InstructionInvalid e) { // need to change type of Exception
            System.out.println("help me");
        }
        return new Task(""); // code will be problematic
    }
    public static ArrayList<Task> loadTaskList(File fileInput) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(fileInput);
            while (scanner.hasNextLine()) {
                taskList.add(formatToTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return taskList;
    }

    public static String printList(ArrayList<Task> list, int length) {
        String stringToPrint = "";
        for (int i = 0; i < length; i++) {
            int bulletPoint = i + 1;
            stringToPrint += bulletPoint + ". " + list.get(i) + "\n";
        }
        return stringToPrint;
    }

    public static void main(String[] args) {
        // Starting of bot programme
        String botName = "Knight2103";
        String horiLine = "_____________";
        String intro = horiLine + "\n"
                + "Hello! I'm " + botName + "\n"
                + "What can I do for you?\n"
                + horiLine + "\n";
        System.out.println(intro);

        File savedTaskList = new File("../savedTaskList.txt");

        // Initialise Task list
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            if (!savedTaskList.createNewFile()) {
                taskList = loadTaskList(savedTaskList); // is it?
            }
        } catch (IOException e) {
                System.out.println("something went wrong");
            }

        // Enable Input
        Scanner scanObject = new Scanner(System.in);
        String input = scanObject.nextLine();

        while (!input.equals("bye")) {
            String[] inputArray = input.split(" ", 2);
            Task taskToAdd;
            try {
                switch (inputArray[0]) {
                    case "list":
                        String listContents = printList(taskList, taskList.size());
                        System.out.println(horiLine + "\n" + listContents + horiLine);
                        break;
                    case "todo":
                        taskToAdd = new Todo(inputArray[1]);
                        taskList.add(taskToAdd);
                        System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                        break;
                    case "deadline":
                        String[] deadlineArray = inputArray[1].split(" /by ");
                        taskToAdd = new Deadline(deadlineArray[0], deadlineArray[1]);
                        taskList.add(taskToAdd);
                        System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                        break;
                    case "event":
                        String[] eventArray = inputArray[1].split(" /from | /to ");
                        taskToAdd = new Event(eventArray[0], eventArray[1], eventArray[2]);
                        taskList.add(taskToAdd);
                        System.out.println(horiLine + "\nGot it. I've added this task:\n" + taskToAdd + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                        break;
                    case "mark":
                        int taskMarkIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                        taskList.get(taskMarkIndex).markDone(); // need to check if it works
                        System.out.println(horiLine + "\nNice! I've marked this task as done:\n" + taskList.get(taskMarkIndex) + "\n" + horiLine);
                        break;
                    case "unmark":
                        int taskUnmarkIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                        taskList.get(taskUnmarkIndex).unmarkDone();
                        System.out.println(horiLine + "\nOK, I've marked this task as not done yet:\n" + taskList.get(taskUnmarkIndex) + "\n" + horiLine);
                        break;
                    case "delete":
                        int taskDeleteIndex = Integer.parseInt(inputArray[1]) - 1; // can try
                        Task taskToDelete = taskList.get(taskDeleteIndex);
                        taskList.remove(taskDeleteIndex);
                        System.out.println(horiLine + "\nNoted. I've removed this task:\n" + taskToDelete + "\n Now you have " + taskList.size() + " tasks in the list.\n" + horiLine);
                        break;

                    default:
                        throw new InstructionInvalid();
                }
            } catch (InstructionInvalid e) {
                System.out.println("Invalid Instruction. Only valid Instructions are list, todo, deadline, event, mark, unmark, delete");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There's an issue in the instruction format");
            } catch (NullPointerException e) { // only happen in mark and unmark I think due to TaskList dynamic allocation specified
                System.out.println("There aren't so many tasks. Please if the task number is keyed in correctly. To see all tasks, type list");
            } catch (NumberFormatException e) {
                System.out.println("Please state the task number in INTEGER. Definitely not the task name");
            }
            input = scanObject.nextLine();
        }
        System.out.println(horiLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + horiLine);
        scanObject.close();
    }
}
