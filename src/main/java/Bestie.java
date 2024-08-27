
import java.util.Scanner;



public class Bestie {
    // load the bestie.txt file in the same directory
    //private static final String FILE_PATH = "bestie.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    Scanner sc = new Scanner(System.in);

    public Bestie(String filePath) {
        this.ui = new Ui(sc);
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasksFromFile());

    }

    public void run() {

        // greet the user
        this.ui.welcome();
        boolean isExit = false;

        while(!isExit) {
            String userInput = ui.readNextCommand();
            Command instruction = Parser.parse(userInput);
            instruction.execute(tasks, ui, storage);
            if (instruction instanceof ExitCommand) {
                isExit = true;
            }
        }

    }
    public static void main(String[] args){
        new Bestie("bestie.txt").run();
    }



// OLD
    //        String userInput;
//        ArrayList<Task> tasks = new ArrayList<>();
//        storage.loadTasksFromFile(); // will modify the arraylist of tasks directly

    // echoes commands entered by the user, and exits when the user types the command "bye"
//        while (!isExit) {
//            userInput = sc.nextLine();
//            ui.readNextCommand();
//            if (userInput.equals("bye")) {
//                // done
//                break;
//            }
//
//            if (userInput.equals("list")) {
//                // DONE
//                // display the list of all items entered by the user
//                System.out.println("Sure! Here are the tasks in your list:");
//                for (int i = 0; i < tasks.size(); i++){
//                    int index = i + 1;
//                    System.out.println(index +"." + tasks.get(i).toString());
//                }
//            } else if (userInput.startsWith("mark")) {
//                // done
//                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
//                if (index >= 0 && index < tasks.size()) {
//                    tasks.get(index).markTaskDone();
//                    System.out.println("Awesome work! I've marked this task as done.");
//                    System.out.println("  " + tasks.get(index).toString());
//                } else {
//                    Bestie.indexOutOfBoundsMessage(tasks.size());
//                }
//
//
//            } else if (userInput.startsWith("unmark")) {
//                //done
//                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
//                if (index >= 0 && index < tasks.size()) {
//                    tasks.get(index).markUndone();
//                    System.out.println("Noted! I've marked this task as not done yet:");
//                    System.out.println("  " + tasks.get(index).toString());
//                } else {
//                    Bestie.indexOutOfBoundsMessage(tasks.size());
//
//                }
//
//            } else if (userInput.startsWith("delete")) {
//                // get index of task to be deleted
//                // done, transferred
//                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
//                if (index >= 0 && index < tasks.size()) {
//                    tasks.remove(index);
//                    System.out.println("Noted! The task has been removed.");
//
//                    if (tasks.size() == 1) {
//                        System.out.println("You now have 1 task in your list.");
//                    } else {
//                        System.out.println("You now have " + tasks.size() + " tasks in your list.");
//                    }
//                } else {
//                    Bestie.indexOutOfBoundsMessage(tasks.size());
//                }
//
//
//            } else {
//                Task newTask;
//                if (userInput.startsWith("todo")) {
//                    try {
//                        String description = userInput.substring(5);
//                        newTask = new Todo(description);
//                        tasks.add(newTask);
//                        System.out.println("added: " + newTask.toString());
//                        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
//                    } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
//                        System.out.println("The description of a todo cannot be empty. Please input your todo again!");
//                    }
//
//                } else if (userInput.startsWith("deadline")) {
//                    try {
//                        String[] parts = userInput.split(" /by ");
//                        String description = parts[0].substring(9);
//                        String deadline = parts[1];
//                        newTask = new Deadline(description, deadline);
//                        tasks.add(newTask);
//                        System.out.println("added: " + newTask.toString());
//                        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
//                    } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
//                        System.out.println("You did not input the deadline in a valid format.");
//                        System.out.println("Please follow the format \"deadline (name of task) /by (deadline)\"");
//
//                    } catch (DateTimeParseException e) {
//                        System.out.println("You did not input the date and time in the correct format.");
//                        System.out.println("Please stick to the correct format: YYYY-MM-DD HHMM");
//                    }
//
//                } else if (userInput.startsWith("event")) {
//
//                    try {
//                        String[] parts = userInput.split("/");
//                        String description = parts[0].substring(6).trim();
//                        String start = parts[1].substring(5).trim();
//                        String end = parts[2].substring(3).trim();
//                        newTask = new Event(description, start, end);
//                        tasks.add(newTask);
//                        System.out.println("added: " + newTask.toString());
//                        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
//
//                    } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
//                        System.out.println("You did not input the event in a valid format.");
//                        System.out.println("Please follow the format \"event (name of event) /from (start time) /to (end time)\"");
//                    } catch (DateTimeParseException e) {
//                        System.out.println("You did not input the date and time in the correct format.");
//                        System.out.println("Please stick to the correct format: YYYY-MM-DD HHMM");
//                    }
//
//
//
//
//                } else {
//                    System.out.println("Invalid command! Please remember to start with \"todo\", \"deadline\" " +
//                            "or \"event\".\nDouble check your spelling for other common commands like \"unmark\" or \"list\".");
//
//                }
//            }
//
//        }
    // save the tasks still in the list right before exiting

//    private static void indexOutOfBoundsMessage(int taskSize) {
//        if (taskSize == 1) {
//            System.out.println("That task does not exist. There is only 1 task in your list!");
//        } else {
//            System.out.println("That task does not exist. There are only " + taskSize
//                    + " tasks in your list!");
//        }
//        System.out.println("Please key in a valid index.");
//    }

    // Saves all tasks currently in the arraylist to the txt file
//    private static void saveTasksToFile(ArrayList<Task> tasks) {
//        // want to save tasks to bestie.txt file
//        try {
//            File f = new File(FILE_PATH);
//            FileWriter fw = new FileWriter(f); // f or file path?
//
//            for (Task task: tasks) {
//                // store each task in the save format
//                fw.write(task.toSaveFormat() + System.lineSeparator());
//            }
//            // must call close() method of filewriter object for writing operation to be completed
//            fw.close();
//        } catch (IOException e) {
//            // must handle the checked exception from creating a new FileWriter instance, IOException
//            System.out.println("An error occurred while attempting to save tasks to file.");
//        }
//
//    }

//    private static void loadTasksFromFile(ArrayList<Task> tasks) {
//
//        File f = new File(FILE_PATH);
//        try {
//            // creates new file if and only if file does not yet exist
//            f.createNewFile();
//            Scanner sc = new Scanner(f); // create scanner using file as source
//
//
//            while (sc.hasNextLine()) {
//                // load the next task in the file in its stored format
//                String nextTask = sc.nextLine();
//                String[] parts = nextTask.split(" \\| ");
//                String taskType = parts[0]; // either T, D, or E, depending on task
//                // check if task is completed
//                boolean isCompleted = parts[1].equals("1");
//                String description = parts[2]; // description of task
//                Task newTask = null;
//                switch (taskType) {
//
//                case ("T"): // next task is a todo
//                    newTask = new Todo(description);
//                    tasks.add(newTask);
//                    break;
//
//                case ("D"):
//
//                    String deadline = parts[3];
//                    newTask = new Deadline(description, deadline);
//                    tasks.add(newTask);
//                    break;
//
//                case ("E"):
//                    String start = parts[3];
//                    String end = parts[4];
//                    newTask = new Event(description, start, end);
//                    tasks.add(newTask);
//                    break;
//                }
//
//                if (newTask != null) {
//                    if (isCompleted) {
//                        newTask.markTaskDone();
//                    }
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("An error occured.");
//        }
//
//    }
}
