import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Echo {
    private String separator;
    private String message = "";

    private String indent = "      ";

    private ArrayList<IndividualTask> list = new ArrayList<IndividualTask>();

    private final String path = "./data/duke.txt";


    public Echo(String separator) {
        this.separator = separator;
        this.loadTasksFromFile();
    }

    //main methods

    public void echoMessage()  {

        Scanner scanner = new Scanner(System.in);
        while (!this.message.strip().equalsIgnoreCase(CommandType.BYE.toString().toLowerCase())) {
            System.out.println("Enter your message:");
            this.message = scanner.nextLine();
            if (this.message.strip().equalsIgnoreCase(CommandType.BYE.toString().toLowerCase())) {
                break;
            }
            if (this.message.strip().equalsIgnoreCase(CommandType.LIST.toString().toLowerCase())) {
                this.getTasks();
                continue;
            }
            else if (this.message.strip().toLowerCase().contains(CommandType.MARK.toString().toLowerCase())) {
                String message = this.message.strip().toLowerCase();
                String[] parts = message.split(" ");
                if (parts.length > 1) {
                    int number = Integer.parseInt(parts[parts.length - 1]);
                    String checkMarkOrUnmark = parts[0];
                    System.out.println("Extracted Task number: " + number);
                    IndividualTask curTask = list.get(number - 1);
                    if (checkMarkOrUnmark.equals("mark")) {
                        curTask.markOrUnmark("mark");
                        System.out.println(this.indent + "Okays! I've marked this task as done:" + "\n" + this.formatMessage(curTask));
                    } else if (checkMarkOrUnmark.equals("unmark")) {
                        curTask.markOrUnmark("unmark");
                        System.out.println(this.indent + "Okay! I've marked this task as not done:" + "\n" + this.formatMessage(curTask));
                    } else {
                        System.out.println("Not a valid command.");
                    }
                } else {
                    System.out.println("No Task found after 'mark'.");
                }
                this.saveTasksToFile();
                continue;
            } else if (this.message.strip().toLowerCase().contains(CommandType.DELETE.toString().toLowerCase())) {
                String message = this.message.strip().toLowerCase();
                String[] parts = message.split(" ");
                if (parts.length > 1 && parts[0].equals("delete")) {
                    int number = Integer.parseInt(parts[parts.length - 1]);
                    System.out.println("Extracted Task number: " + number);
                    IndividualTask curTask = list.get(number - 1);
                    list.remove(number - 1);
                    System.out.println(this.indent + "Alrighty! I will remove the task: " + "\n" + this.formatMessage(curTask));
                } else {
                    System.out.println("No Task found.");
                }
                continue;
            }
            if (!this.message.isEmpty()) {
                try {
                    processMessage(this.message);
                } catch (MentalHealthException e) {
                    System.out.println(this.indent + this.separator);
                    System.out.println(this.indent + "OOPS!!! " + e.getMessage());
                    System.out.println(this.indent + this.separator);
                }
            }

        }
        scanner.close();
    }

    public void processMessage(String msg) throws MentalHealthException {
        String[] message = msg.split(" ");
        if (message[0].equalsIgnoreCase(CommandType.TODO.toString().toLowerCase())) {
            if (message.length < 2 || message[1].trim().isEmpty()) {
                throw new MentalHealthException("The description of a todo cannot be empty.");
            }
            String type = "todo";
            String todo = this.message.substring(type.length()).trim();
            ToDo newTodo = new ToDo(todo);
            list.add(newTodo);
            System.out.println(this.indent + "Okays! I've added this task:" + "\n" + this.formatMessage(newTodo));
        }
        else if (message[0].equalsIgnoreCase(CommandType.DEADLINE.toString().toLowerCase())) {
            String[] parts = this.message.split(" /by ", 2);
            if (parts.length == 2) {
                String type = "deadline";
                String description = parts[0].substring(type.length()).trim(); // get the action
                String by = parts[1].trim(); // get the date
                Deadline newDeadline = new Deadline(description, by);
                list.add(newDeadline);
                System.out.println(this.indent + "Okays! I've added this task:" + "\n" + this.formatMessage(newDeadline));
            } else {
                throw new MentalHealthException("The string doesn't contain the expected format for a deadline.");
            }

        }
        else if (message[0].equalsIgnoreCase(CommandType.EVENT.toString().toLowerCase())) {
            String[] parts = this.message.split(" /from ", 2);
            if (parts.length == 2) {
                String type = "event";
                String description = parts[0].substring(type.length()).trim(); // get the action
                String[] secondPart = parts[1].split(" /to ", 2); //split at /to
                if (secondPart.length == 2) {
                    String from =  secondPart[0].trim();
                    String to = secondPart[1].trim();
                    Event newEvent = new Event(description, from, to);
                    list.add(newEvent);
                    System.out.println(this.indent + "Okays! I've added this task:" + "\n" + this.formatMessage(newEvent));
                } else {
                    throw new MentalHealthException("The string doesn't contain the '/to' part.");
                }
            } else {
                throw new MentalHealthException("The string doesn't contain the '/from' part.");
            }
        } else {
            throw new MentalHealthException("I'm sorry, but I don't know what that means :-(");
        }
        this.saveTasksToFile();
    }

    public void saveTasksToFile()  {
        try {
            FileWriter writer = new FileWriter(path);
            for (IndividualTask task : list) {
                writer.write(task.saveToFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error in saving task: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        File directory = new File("./data");
        File file = new File(path);
        if (!directory.exists()) {
            System.out.println("No existing directory found. Create a directory data and a file 'duke.txt' inside that directory. Starting with an empty task list.");
            return;
        }
        if (!file.exists()) {
            System.out.println("No existing data file found. Create a file 'duke.txt' in the data directory. Starting with an empty task list.");
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tasks = scanner.nextLine();
                String[] parts = tasks.split(" \\| ");
                String typeOfTask = parts[0];
                boolean isTaskDone = parts[1].equals("1");
                String descriptionOfTask = parts[2];
                IndividualTask task = switch (typeOfTask) {
                    case "T" -> new ToDo(descriptionOfTask);
                    case "D" -> new Deadline(descriptionOfTask, parts[3]);
                    case "E" -> new Event(descriptionOfTask, parts[3], parts[4]);
                    default -> null;
                };
                if (task != null) {
                    if (isTaskDone) {
                        task.markOrUnmark("mark");
                    }
                    list.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The data file not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("There was an error loading the data: " + e.getMessage());
        }
    }


    public void getTasks() {
        System.out.println(this.indent + this.separator);
        for (int i = 0; i < list.size(); i++) {
            String number = String.valueOf(i+1);
            String format = this.formatListMessage(number, list.get(i));
            System.out.println(format);
        }
        System.out.println(this.indent + this.separator);
    }



    //Helper methods
    public String formatMessage(IndividualTask task) {
        return this.indent + this.separator + "\n" + this.indent + task + "\n" + this.indent + "Now you have " + list.size() + " tasks in the list." + "\n" + this.indent  + this.separator;
    }

    public String formatListMessage(String number, IndividualTask task) {
        return this.indent + number + "." + task.toString();
    }


}
