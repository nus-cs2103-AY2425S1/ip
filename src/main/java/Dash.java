import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class Dash {
    private static final String LOGO = " .----------------.  .----------------.  .----------------.  .----------------.\n" +
            "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
            "| |  ________    | || |      __      | || |    _______   | || |  ____  ____  | |\n" +
            "| | |_   ___ `.  | || |     /  \\     | || |   /  ___  |  | || | |_   ||   _| | |\n" +
            "| |   | |   `. \\ | || |    / /\\ \\    | || |  |  (__ \\_|  | || |   | |__| |   | |\n" +
            "| |   | |    | | | || |   / ____ \\   | || |   '.___`-.   | || |   |  __  |   | |\n" +
            "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  |`\\____) |  | || |  _| |  | |_  | |\n" +
            "| | |________.'  | || ||____|  |____|| || |  |_______.'  | || | |____||____| | |\n" +
            "| |              | || |              | || |              | || |              | |\n" +
            "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
            " '----------------'  '----------------'  '----------------'  '----------------'";
    private static final String HORIZONTAL_LINE = "________________________________________";
    private static final String GOODBYE = "   ___                _ _                  _ \n" +
            "  / _ \\___   ___   __| | |__  _   _  ___  / \\\n" +
            " / /_\\/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\/  /\n" +
            "/ /_\\\\ (_) | (_) | (_| | |_) | |_| |  __/\\_/ \n" +
            "\\____/\\___/ \\___/ \\__,_|_.__/ \\__, |\\___\\/   \n" +
            "                              |___/          ";
    public static void main(String[] args) {

        //Initialise scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Initialise array list to store date
        ArrayList<Task> list = new ArrayList<>();

        System.out.println(HORIZONTAL_LINE);

        //Check if file exists; else create file
        File f = new File("./data/dash.txt");
        try {
            //Load file if file exists
            if (f.exists()) {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] stringArray = line.split("\\|");
                    char type = line.charAt(0);
                    if (type == 'T') {
                        Todo t = new Todo(stringArray[2]);
                        if (stringArray[1].equals("X")) {
                            t.markTaskAsDone();
                        }
                        list.add(t);
                    } else if (type == 'D') {
                        Deadline d = new Deadline(stringArray[2], stringArray[3]);
                        if (stringArray[1].equals("X")) {
                            d.markTaskAsDone();
                        }
                        list.add(d);
                    } else if (type == 'E') {
                        System.out.println(stringArray[2]);
                        Event e = new Event(stringArray[2], stringArray[3], stringArray[4]);
                        if (stringArray[1].equals("X")) {
                            e.markTaskAsDone();
                        }
                        list.add(e);
                    } else {
                        throw new CorruptedFileException("File is corrupted.");
                    }
                }
                System.out.println("Existing data file found. Data has been loaded.");
            } else {
                if (f.getParentFile().exists()) {
                    f.createNewFile();
                } else {
                    f.getParentFile().mkdir();
                    f.createNewFile();
                }
                System.out.println("No existing data file found. New data file \"dash.txt\" has been created.");
            }
        } catch (IOException e) {
            System.out.println("I/O error has occurred when creating new file: " + e.getMessage());
        } catch (CorruptedFileException e) {
            System.out.println("Please erase data in file: " + e.getMessage());
        } finally {
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.println("Hello! I'm\n" + LOGO + "\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) { //list command
                    System.out.println(HORIZONTAL_LINE);
                    int length = list.size();
                    for (int i = 0; i < length; i++) {
                        Task t = list.get(i);
                        int num = i + 1;
                        System.out.println(num + "." + t);
                    }
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("mark")) { //mark command
                    String[] string = input.split(" ", 2);
                    int markNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (markNum > num || markNum < 1) {
                        System.out.println(HORIZONTAL_LINE);
                        throw new WrongIndexException("Index is out of bounds.");
                    } else {
                        Task t = list.get(markNum - 1);
                        t.markTaskAsDone();
                        writeToFile(f.getPath(), list.get(0).toFile() + System.lineSeparator());
                        for (int i = 1; i < num; i++) {
                            appendToFile(f.getPath(), list.get(i).toFile() + System.lineSeparator());
                        }
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(t);
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (input.startsWith("unmark")) {                       //unmark command
                    String[] string = input.split(" ", 2);
                    int unmarkNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (unmarkNum > num || unmarkNum < 1) {
                        System.out.println(HORIZONTAL_LINE);
                        throw new WrongIndexException("Index is out of bounds.");
                    } else {
                        Task t = list.get(unmarkNum - 1);
                        t.unmarkTask();
                        writeToFile(f.getPath(), list.get(0).toFile() + System.lineSeparator());
                        for (int i = 1; i < num; i++) {
                            appendToFile(f.getPath(), list.get(i).toFile() + System.lineSeparator());
                        }
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(t);
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else if (input.startsWith("todo")) {
                    System.out.println(HORIZONTAL_LINE);
                    String[] string = input.split(" ", 2);
                    if (string.length == 1) {
                        throw new EmptyDescriptionException("Description of todo command cannot be empty.");
                    }
                    Todo t = new Todo(string[1]);
                    list.add(t);
                    appendToFile(f.getPath(), t.toFile() + System.lineSeparator());
                    int num = list.size();
                    System.out.println("Got it. I've added this task:\n" + t);
                    System.out.println("Now you have " + num + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("deadline")) {
                    System.out.println(HORIZONTAL_LINE);
                    String[] string = input.split("/", 2); //"deadline XX" and "by XX"

                    //Exceptions
                    if (string.length == 1) {
                        throw new EmptyDescriptionException("Description of deadline command cannot be empty. " +
                                "Do remember to include '/'.");
                    }
                    String[] byString = string[1].split(" ", 2); //"by" and "XX"
                    if (!byString[0].equals("by")) {
                        throw new IncorrectCommandUseException("Please include the /by command.");
                    }
                    if (byString.length == 1) {
                        throw new IncorrectCommandUseException("Please include deadline!");
                    }

                    String date = byString[1];
                    String[] string2 = string[0].split(" ", 2); //"deadline" and "XX"
                    String desc = string2[1];
                    Deadline t = new Deadline(desc, date);
                    list.add(t);
                    appendToFile(f.getPath(), t.toFile() + System.lineSeparator());
                    int num = list.size();
                    System.out.println("Got it. I've added this task:\n" + t);
                    System.out.println("Now you have " + num + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("event")) {
                    System.out.println(HORIZONTAL_LINE);
                    String[] string = input.split("/", 3); //"event XX" and "from XX" and "to XX"

                    //Exceptions
                    if (string.length == 1) {
                        throw new EmptyDescriptionException("Description of todo command cannot be empty. " +
                                "Do remember to include '/'.");
                    } else if (string.length == 2) {
                        throw new IncorrectCommandUseException("Incorrect use of event command!");
                    }
                    String[] fromString = string[1].split(" ", 2); //"from" and "XX"
                    if (fromString.length == 1) {
                        throw new IncorrectCommandUseException("Incorrect use of event command!");
                    }
                    if (!fromString[0].equals("from")) {
                        throw new IncorrectCommandUseException("Please include the /from command.");
                    }
                    String[] toString = string[2].split(" ", 2); //"to" and "XX"
                    if (toString.length == 1) {
                        throw new IncorrectCommandUseException("Incorrect use of event command!");
                    }
                    if (!toString[0].equals("to")) {
                        throw new IncorrectCommandUseException("Please include the /to command.");
                    }

                    String from = fromString[1];
                    String to = toString[1];
                    String[] eventString = string[0].split(" ", 2); //"event" and "XX"
                    String desc = eventString[1];
                    Event t = new Event(desc, from, to);
                    list.add(t);
                    appendToFile(f.getPath(), t.toFile() + System.lineSeparator());
                    int num = list.size();
                    System.out.println("Got it. I've added this task:\n" + t);
                    System.out.println("Now you have " + num + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } else if (input.startsWith("delete")) {
                    String[] string = input.split(" ", 2);
                    int deleteNum = Integer.parseInt(string[1]);
                    int num = list.size();
                    if (deleteNum > num || deleteNum < 1) {
                        System.out.println(HORIZONTAL_LINE);
                        throw new WrongIndexException("Index is out of bounds.");
                    } else {
                        Task t = list.remove(deleteNum - 1);
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("Noted. I've removed this task.");
                        System.out.println(t);
                        System.out.println("Now you have " + (num - 1) + " tasks in the list.");
                        if (!list.isEmpty()) {
                            writeToFile(f.getPath(), list.get(0).toFile() + System.lineSeparator());
                            if (list.size() > 1) {
                                for (int i = 1; i < num - 1; i++) {
                                    appendToFile(f.getPath(), list.get(i).toFile() + System.lineSeparator());
                                }
                            }
                        } else {
                            writeToFile(f.getPath(), "");
                        }
                        System.out.println(HORIZONTAL_LINE);
                    }
                } else {
                    throw new UnknownCommandException("Unknown command.");
                }
            } catch (UnknownCommandException | EmptyDescriptionException
                     | IncorrectCommandUseException | WrongIndexException e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            } catch (Exception e) {
                System.out.println("UNEXPECTED ERROR: " + e.getMessage());
                System.out.println(HORIZONTAL_LINE);
            }
        }

        //Exit
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE);
        System.out.println(HORIZONTAL_LINE);

    }

    /**
     *
     * @param filePath Path to file to be written
     * @param textToAppend String text to be added to file
     * @throws IOException If error is encountered i.e. file cannot be found
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            System.out.println("Append to file failed: " + e.getMessage());
        }
    }

    /**
     *
     * @param filePath Path to file to be written
     * @param textToAdd String text to be written to file
     * @throws IOException If error is encountered i.e. file cannot be found
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Write to file failed: " + e.getMessage());
        }
    }
}