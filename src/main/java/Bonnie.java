import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import src.main.java.*;

public class Bonnie {

    // Variables that are
    private static String username;
    private static ArrayList<Task> tasklist = new ArrayList<>();

    public static void main(String[] args) throws EmptyTodoException, UnknownCommandException, DeadlineFormatException, IOException {
        System.out.println("Hello I'm Bonnie, what is your name?");

        File f = new File("ip/src/main/java/Files/tasks.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            } else {
                initialiseFile(f);
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating file..");
            e.printStackTrace();
        }

        Scanner scannerObj = new Scanner(System.in);
        String my_username = scannerObj.nextLine();
        System.out.println(String.format(
                "Hey %s! Welcome to the Bonnie chat bot! Please input a command to continue!\n" +
                "1. bye : leaves the conversation with Bonnie\n" +
                "2. list: displays the list of tasks that you have, with the task number\n" +
                "3. mark/unmark {task number}: marks or unmarks that task as done\n" +
                "4. todo {task name}: Bonnie adds a todo task into your task list\n" +
                "5. deadline {task name} /by {deadline}: Bonnie adds a task with a deadline to your task list.\n" +
                "6. event {task name} /from {start} /to {end}: Bonnie adds an event with a start/end time to your task list.\n" +
                "7. delete {task number}: Bonnie deletes the task with that number from your task list.\n" +
                "Bonnie wants to remind you that you should substitute items with curly braces with the actual information.\n" +
                "Also, do remember to use the forward slashes! \"/from\" is valid but \"from\" is NOT valid!\n" +
                "Example: \"event clean floor /from 18th September 5pm /to 18th September 6pm\" is a valid command\n", my_username));
        username = my_username;

        while (true) {
            String input = scannerObj.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye " + username + "\n");
                return;
            } else if (input.equals("list")) {
                String list = "Your current tasks are\n";
                for (int i = 1; i <= tasklist.size(); i++) {
                    list += String.format("%d. %s\n", i, tasklist.get(i - 1));
                }
                System.out.println(list);
            } else if (check_mark_command(input)) {
                String[] arr = input.split(" ", 2);
                Integer taskNum = Integer.valueOf(arr[1]);
                if (arr[0].equals("mark")) {
                    // Mark task "taskNum" as done
                    // Note that the actual task is off by -1 in the list!
                    tasklist.get(taskNum - 1).markAsDone();
                } else if (arr[0].equals("unmark")) {
                    // Mark task "taskNum" as not done
                    tasklist.get(taskNum - 1).unmarkAsDone();
                }
            } else if (check_delete_command(input)) {
                String[] arr = input.split(" ", 2);
                Integer taskNum = Integer.valueOf(arr[1]);
                System.out.println(String.format("You have successfully deleted task %d!", taskNum));
                tasklist.remove(taskNum - 1);
            } else {
                // Want to parse and add task into task list
                parseAndAddTask(input);
            }

            // Want to update file of tasks after every new command (which could possibly change the tasks involved)
            updateFile();
        }
    }

    public static boolean check_mark_command(String targetString) {
        String[] arr = targetString.split(" ");
        if ((arr[0].equals("mark") || arr[0].equals("unmark")) && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum <= tasklist.size() && taskNum >= 1) {
                    return true;
                } else {
                    System.out.println("You might have tried to mark or unmark a task that does not exist.\n" +
                                       "If so, please delete this wrongly added task using the delete command.\n");
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean check_delete_command(String targetString) {
        String[] arr = targetString.split(" ");
        if (arr[0].equals("delete") && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum <= tasklist.size() && taskNum >= 1) {
                    return true;
                } else {
                    System.out.println("You might have tried to delete a task that does not exist.\n");
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void parseAndAddTask(String input) throws EmptyTodoException, UnknownCommandException, DeadlineFormatException {
        // Want to split the string according to spaces 1st
        String[] splitString = input.split(" ", 2);
        String name;
        if (splitString[0].equals("todo")) {
            if (splitString.length == 1) {
                throw new EmptyTodoException();
            }
            String taskName = splitString[1];
            tasklist.add(new Todo(taskName));
            name = taskName;
        } else if (splitString[0].equals("deadline")) {
            String[] components = splitString[1].split(" /by ", 2);
            if (components.length < 2) {
                throw new DeadlineFormatException();
            }
            tasklist.add(new Deadline(components[0], components[1]));
            name = components[0];
        } else if (splitString[0].equals("event")) {
            // Idea is that original string is in the form {event_name} /from {start} /to {end}
            // Hence first split will get event name and the {start} /to {end}
            // Second split will split the {start} /to {end} to get the actual start and end
            String[] component1 = splitString[1].split(" /from ", 2);
            String[] component2 = component1[1].split(" /to ", 2);
            tasklist.add(new Event(component1[0], component2[0], component2[1]));
            name = component1[0];
        } else {
            throw new UnknownCommandException(input);
        }
        System.out.println(String.format("Hey %s, I have added \"%s\" into your task list!\n" +
                                         "You now have %d tasks to complete!\n", username, name, tasklist.size()));
    }

    /**
     * Updates the task list file after every new command by updating the task list from the ArrayList of tasks
     *
     */
    public static void updateFile() {
        try {
            FileWriter writer = new FileWriter("ip/src/main/java/Files/tasks.txt");
            for (int i = 0; i < tasklist.size(); i++) {
                writer.write(String.format("%s\n", tasklist.get(i).toString()));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error updating tasks file!");
            e.printStackTrace();
        }
    }

    public static void initialiseFile(File file) {
        try {
            // Idea is to copy the stuff from the tasks.txt to populate the tasklist array (so the app has the tasks)
            Scanner myReader = new Scanner(file);
            ArrayList<ArrayList<String>> results = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<String> subresult = new ArrayList<>();
                Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                Matcher matcher = pattern.matcher(data);

                while (matcher.find()) {
                    subresult.add(matcher.group(1));
                }
                results.add(subresult);
            }
            myReader.close();

            for (ArrayList<String> lst : results) {
                if (lst.get(0).equals("ToDo")) {
                    tasklist.add(new Todo(lst.get(2)));
                } else if (lst.get(0).equals("Deadline")) {
                    tasklist.add(new Deadline(lst.get(2), lst.get(3)));
                } else if (lst.get(0).equals("Event")) {
                    tasklist.add(new Event(lst.get(2), lst.get(3), lst.get(4)));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when starting the app.");
            e.printStackTrace();
        }
    }
}
