import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;

public class Lemon {
    /*** Messages ***/
    String logoMsg = "____________________________________________________________\n"
            + " Hello! I'm Lemon\n"
            + " What can I do for you?\n";
    String endMsg = " Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";
    String barMsg = "____________________________________________________________";
    String emptyListMsg = " Sowwy, theres currently no tasks in your list.\n Ill be happy to add some for you OwO!";
    String listMsg = " Here are the tasks in your list:";
    String markMsg = " Nice! I've marked this task as done:";
    String unmarkMsg = " OK, I've marked this task as not done yet:";
    String addTaskMsg = " Got it. I've added this task:";
    String deleteTaskMsg = " Noted. I've removed this task:";

    /*** Initialising ***/
    ArrayList<Task> tasks = new ArrayList<>();
    int numTasks = 0;
    boolean isInitialised = false;

    Scanner systemScanner, fileScanner;
    String filePath = "data/lemonSaves.txt";
    File f;

    enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        EVENT,
        DEADLINE,
        DELETE
    }

    public Lemon(){
        try {
            f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            fileScanner = new Scanner(f);
            systemScanner = new Scanner(System.in);

            numTasks = 0;
            tasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                System.out.println(numTasks);
                String[] temp = fileScanner.nextLine().split("\\|");
                switch (temp[0]) {
                    case "T":
                        addNewTask(new Todo(temp[2], Boolean.parseBoolean(temp[1])));
                        break;
                    case "D":
                        addNewTask(new Deadline(temp[2], temp[3], Boolean.parseBoolean(temp[1])));
                        break;
                    case "E":
                        addNewTask(new Event(temp[2], temp[3], Boolean.parseBoolean(temp[1])));
                        break;
                }
            }

            isInitialised = true;
        } catch (IOException e) {
            isInitialised = false;
            System.out.print(" Im sowwy... Something went wrong, QwQ. Unable to create file.\n" +
                    " I dont think i can do this anymore");
        } catch (DescriptionException e) {
            isInitialised = false;
            System.out.println(e.getMessage());
            // theres an invalid line
        }
    }

    public Lemon(String newFileLocation){
        try {
            filePath = newFileLocation;
            f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            fileScanner = new Scanner(f);
            systemScanner = new Scanner(System.in);

            numTasks = 0;
            tasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                numTasks++;
                fileScanner.nextLine();
            }

            isInitialised = true;
        } catch (IOException e) {
            isInitialised = false;
            System.out.print(" Im sowwy... Something went wrong, QwQ. Unable to create/find file.\n" +
                    " I dont think i can do this anymore");
        }
    }

    public void runLemon() {
        if (!isInitialised) {
            System.out.print(" Ouhiiee, my head hurrrtt, i dont think im initialized properly ;-;\n" +
                    " Please try again...");
            return;
        }

        boolean isRunning = true;

        System.out.print(logoMsg);

        while (isRunning) {
            System.out.println(barMsg);

            String input = systemScanner.next().toUpperCase();
            System.out.println(barMsg);
            try {
                Commands command = Commands.valueOf(input);
                switch (command) {
                case BYE: {
                    boolean isAbleToSave = saveTasks();
                    if (isAbleToSave) {
                        isRunning = false;
                        System.out.println(endMsg);
                    }
                    break;
                }
                case LIST:
                    if (numTasks == 0) {
                        System.out.println(emptyListMsg);
                    }
                    else {
                        System.out.println(listMsg);
                        printList();
                    }
                    break;
                case MARK: {
                    int next = systemScanner.nextInt();
                    if (next > numTasks || next <= 0) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    System.out.println(markMsg);
                    tasks.get(next - 1).markDone();

                    System.out.println("   " + tasks.get(next - 1).toString());
                    break;
                }
                case UNMARK: {
                    int next = systemScanner.nextInt();
                    if (next > numTasks || next <= 0) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    System.out.println(unmarkMsg);
                    tasks.get(next - 1).unmarkDone();

                    System.out.println("   " + tasks.get(next - 1).toString());
                    break;
                }
                case TODO: {
                    String next = systemScanner.nextLine();

                    addNewTask(new Todo(next, false));
                    break;
                }
                case DEADLINE: {
                    String[] next = systemScanner.nextLine().split("/by ");
                    if (next.length < 2) {
                        throw new InvalidCommandException(" Missing/incorrect date format \n" +
                                " Use /by and format date as dd-mm-yyyy");
                    }

                    //LocalDate by = LocalDate.parse(next[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    addNewTask(new Deadline(next[0], next[1], false));
                    break;
                }
                case EVENT: {
                    String[] next = systemScanner.nextLine().split("/from | /to ");

                    if (next.length < 3) {
                        throw new InvalidCommandException(" Missing/incorrect date format \n" +
                                " Use /by and format date as dd-mm-yyyy");
                    }

                    //LocalDate from = LocalDate.parse(next[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    //LocalDate to = LocalDate.parse(next[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    addNewTask(new Event(next[0], next[1], next[2], false));
                    break;
                }
                case DELETE: {
                    int next = systemScanner.nextInt();
                    if (next > numTasks || next <= 0) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    deleteTask(next);
                    break;
                }
                }
            } catch (InvalidCommandException | DescriptionException e) {
                System.out.println(e.getMessage());
                systemScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(" OOPS!!! I cant understand your mark/unmark, please select using numbers xd");
                systemScanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(" OOPS!!! I'm sowwy, but I don't know what that means :-(\n\n" +
                        " I can help you add tasks with \"todo\", \"deadline\", \"event\"\n" +
                        " I can also keep track of all your tasks with \"list\"\n" +
                        " If you wanna update certain tasks, use \"mark\" or \"unmark\" and then its number");
                systemScanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Lemon lemon = new Lemon();
        lemon.runLemon();
    }

    /*** Functions ***/
    private void printList() {
        for (int i = 0; i < numTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    private void addNewTask(Task t) throws DescriptionException {
        if (t.description.isEmpty() || t.description.equals(" "))
            throw new DescriptionException(" OOPS!!! The description of a " + t.getType() + " cannot be empty");
        System.out.println(addTaskMsg);

        tasks.add(t);
        numTasks++;

        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
    }

    private void deleteTask(int index) {
        Task t = tasks.remove(index - 1);
        numTasks--;

        System.out.println(deleteTaskMsg);
        System.out.println("   " + t.toString());
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
    }

    private boolean saveTasks() {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task task : tasks) {
                fw.write(task.toFileString());
            }

            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to save into file.\n" +
                    " Please make sure that \"lemonSaves.txt\" exists properly in\n" +
                    filePath);
            return false;
        }
    }
}
