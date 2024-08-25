import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class OuiOuiBaguette {
    public static void main(String[] args) {

        String greetings = formatBotSpeech(new String[] {
                "Bone-jaw! I'm Oui Oui Baguette.",
                "What can I do for you? Oui Oui"});

        String farewell = formatBotSpeech("Bye. Hope to see you soon! Oui Oui");
    
        // Say greetings
        System.out.println(greetings);

        // Initialize scanner
        Scanner sc = new Scanner(System.in); 

        // Initialize ArrayList of tasks
        // ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Task> tasks = readSavedTasks();

        // Main event loop
        while (true) {
            // Read input
            String cmd = sc.nextLine();

            try {
                // "bye" command exits loop
                if (cmd.equals("bye")) {
                    break;
                }

                if (cmd.equals("list")) {
                    // List tasks
                    // Convert ArrayList to array of strings
                    String[] tasksArr = new String[tasks.size()];

                    for (int i = 0; i < tasks.size(); i++) {
                        Task currTask = tasks.get(i);
                        tasksArr[i] = (i + 1) + ". " + currTask;
                    }

                    System.out.println(formatBotSpeech(tasksArr));

                } else if (cmd.startsWith("mark")) {
                    // Parse command to get index of task
                    int taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    
                    Task taskMarked = tasks.get(taskIndex);

                    taskMarked.mark();

                    // Update tasks on disk
                    saveTasks(tasks);

                    System.out.println(formatBotSpeech(new String[]{
                            "Nice! I've marked this task as done: ",
                            "  " + taskMarked}));

                } else if (cmd.startsWith("unmark")) {
                    // Parse command to get index of task
                    int taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    
                    Task taskUnmarked = tasks.get(taskIndex);

                    taskUnmarked.unmark();

                    // Update tasks on disk
                    saveTasks(tasks);

                    System.out.println(formatBotSpeech(new String[]{
                            "OK, I've marked this task as not done yet: ",
                            "  " + taskUnmarked}));

                } else if (cmd.startsWith("todo")) {
                    // Add ToDo
                    if (cmd.length() <= ("todo ").length()) {
                        throw new ToDoException("The description of a todo cannot be empty.");
                    }

                    String desc = cmd.substring(("todo ").length());

                    ToDo todo = new ToDo(desc);

                    tasks.add(todo);

                    // Save task to disk
                    saveTask(todo);

                    System.out.println(formatBotSpeech(new String[]{
                        "Got it. I've added this task:",
                        "  " + todo,
                        "Now you have " + tasks.size() + " tasks in the list."
                    }));

                } else if (cmd.startsWith("deadline")) {
                    // Add Deadline
                    if (cmd.length() <= ("deadline ").length()) {
                        throw new DeadlineException("The description of a deadline cannot be empty.");
                    }

                    String descAndDate = cmd.substring(("deadline ").length());

                    // Check if format is correct
                    if (!descAndDate.contains(" /by ")) {
                        throw new DeadlineException("""
                            The format entered is wrong.
                            \t Please follow the format: deadline <description> /by <due date>""");
                    }

                    String desc = descAndDate.split(" /by ")[0];
                    // Check if there is a valid description
                    if (desc.length() == 0) {
                        throw new DeadlineException("The description of a deadline cannot be empty.");
                    } 

                    // Check if there is a valid due date
                    if (descAndDate.split(" /by ").length < 2) {
                        throw new DeadlineException("The due date of a deadline cannot be empty.");
                    } 
                    String date = descAndDate.split(" /by ")[1];

                    Deadline deadline = new Deadline(desc, date);

                    tasks.add(deadline);

                    // Save task to disk
                    saveTask(deadline);

                    System.out.println(formatBotSpeech(new String[]{
                        "Got it. I've added this task:",
                        "  " + deadline,
                        "Now you have " + tasks.size() + " tasks in the list."
                    }));

                } else if (cmd.startsWith("event")) {
                    // Add Event
                    if (cmd.length() <= ("event ").length()) {
                        throw new EventException("The description of a deadline cannot be empty.");
                    }

                    String descAndStartEnd = cmd.substring(("event ").length());
                    
                    // Check if format is correct
                    if (!descAndStartEnd.contains(" /from ") || !descAndStartEnd.contains(" /to ")) {
                        throw new EventException("""
                            The format entered is wrong.
                            \t Please follow the format: event <description> /from <start> /to <end>""");
                    }

                    String desc = descAndStartEnd.split(" /from ")[0];
                    // Check if there is a valid description
                    if (desc.length() == 0) {
                        throw new EventException("The description of an event cannot be empty.");
                    } 

                    // Check if there is a valid start
                    if (descAndStartEnd.indexOf("/from ") + ("/from ").length()
                            >= descAndStartEnd.indexOf(" /to")) {
                        throw new EventException("The start of an event cannot be empty.");
                            }
                    String start = descAndStartEnd.substring(
                            descAndStartEnd.indexOf("/from ") + ("/from ").length(),
                            descAndStartEnd.indexOf(" /to"));

                    // Check if there is a valid end
                    if (descAndStartEnd.split(" /to ").length < 2) {
                        throw new EventException("The end of an event cannot be empty.");
                    }
                    String end = descAndStartEnd.split(" /to ")[1];

                    Event event = new Event(desc, start, end);

                    tasks.add(event);

                    // Save task to disk
                    saveTask(event);

                    System.out.println(formatBotSpeech(new String[]{
                        "Got it. I've added this task:",
                        "  " + event,
                        "Now you have " + tasks.size() + " tasks in the list."
                    }));

                } else if (cmd.startsWith("delete")) {
                    // Parse command to get index of task
                    int taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;

                    Task taskDeleted = tasks.remove(taskIndex);

                    // Update tasks on disk
                    saveTasks(tasks);

                    System.out.println(formatBotSpeech(new String[] {
                            "Noted. I've removed this task:",
                            " " + taskDeleted,
                            "Now you have " + tasks.size() + " tasks in the list."}));

                } else {
                    // Unknown command
                    throw new UnknownCommandException();
                }

            } catch (OuiOuiBaguetteException e) {
                System.out.println(formatBotSpeech(e.getMessage()));
            }

            
        }

        System.out.println(farewell);

        sc.close();
    }


    /**
     * Returns a message formatted with for a CLI chat response
     *
     * @param s response message
     * @return response delimited with horizontal lines and indented
     */
    public static String formatBotSpeech(String s) {
        return formatBotSpeech(new String[]{s});
    }


    /**
     * Returns a message formatted with for a CLI chat response
     *
     * @param strs list of response message
     * @return response delimited with horizontal lines and indented
     */
    public static String formatBotSpeech(String[] strs) {
        StringBuilder res = new StringBuilder("\t____________________________________________________________");

        for (String s : strs) {
            res.append("\n\t " + s);
        }

        res.append("\n\t____________________________________________________________\n");
        return res.toString();
    }


    public static ArrayList<Task> readSavedTasks() {
        // Check if data folder exists
        if (Files.notExists(Paths.get("data"))) {
            try {
                Files.createDirectory(Paths.get("data"));

            } catch (IOException ioException) {
                System.out.println("Could not create data folder at path " + "data" 
                        + "\n" + ioException.getMessage());
                
                return new ArrayList<Task>();
            }
        }

        // Initialize file
        File f = new File("data/tasks.txt");

        if (!f.exists()) {
            // File not found
            // Create file
            try {
                f.createNewFile();

            } catch (IOException ioException) {
                System.out.println("Could not create task save file at path " + f.getAbsolutePath() 
                        + "\n" + ioException.getMessage());

            }
        }

        // Read file
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            Scanner fs = new Scanner(f);

            while (fs.hasNextLine()) {
                String line = fs.nextLine();

                // Escape |
                String[] taskParts = line.split(" \\| ");

                Task task = null;

                if (taskParts[0].equals("todo")) {
                    task = new ToDo(taskParts[2]);

                    if (Integer.parseInt(taskParts[1]) == 1) {
                        task.mark();
                    }

                } else if (taskParts[0].equals("deadline")) {
                    task = new Deadline(taskParts[2], taskParts[3]);

                    if (Integer.parseInt(taskParts[1]) == 1) {
                        task.mark();
                    }

                } else if (taskParts[0].equals("event")) {
                    task = new Event(taskParts[2], taskParts[3], taskParts[4]);

                    if (Integer.parseInt(taskParts[1]) == 1) {
                        task.mark();
                    }

                } else {
                    // Do nth
                }

                // Add task to return result
                if (task != null) {
                    tasks.add(task);
                }

            }

            fs.close();

            return tasks;

        } catch (FileNotFoundException fileNotFoundException) {
            // Do nth
        }

        return new ArrayList<Task>();
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        // Check if data folder exists
        if (Files.notExists(Paths.get("data"))) {
            try {
                Files.createDirectory(Paths.get("data"));

            } catch (IOException ioException) {
                System.out.println("Could not create data folder at path " + "data" 
                        + "\n" + ioException.getMessage());
                
            }
        }

        // Initialize file
        File f = new File("data/tasks.txt");

        if (!f.exists()) {
            // File not found
            // Create file
            try {
                f.createNewFile();

            } catch (IOException ioException) {
                System.out.println("Could not create task save file at path " + f.getAbsolutePath() 
                        + "\n" + ioException.getMessage());

            }
        }

        // Write to file
        try {
            // Overwrite previous save
            FileWriter fw = new FileWriter("data/tasks.txt");

            for (Task t : tasks) {
                fw.write(t.toDataFormat() + "\n");
            }
            fw.close();

        } catch (IOException ioException) {
            System.out.println("Could not save task at path " + f.getAbsolutePath() 
                    + "\n" + ioException.getMessage());

        }


    }


    public static void saveTask(Task t) {
        // Check if data folder exists
        if (Files.notExists(Paths.get("data"))) {
            try {
                Files.createDirectory(Paths.get("data"));

            } catch (IOException ioException) {
                System.out.println("Could not create data folder at path " + "data" 
                        + "\n" + ioException.getMessage());
                
            }
        }

        // Initialize file
        File f = new File("data/tasks.txt");

        if (!f.exists()) {
            // File not found
            // Create file
            try {
                f.createNewFile();

            } catch (IOException ioException) {
                System.out.println("Could not create task save file at path " + f.getAbsolutePath() 
                        + "\n" + ioException.getMessage());

            }
        }

        // Write to file
        try {
            // Append to previous save
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            fw.write(t.toDataFormat() + "\n");
            fw.close();

        } catch (IOException ioException) {
            System.out.println("Could not save task at path " + f.getAbsolutePath() 
                    + "\n" + ioException.getMessage());

        }
    }
}
