import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import static java.lang.System.exit;

enum Commands {
    DEADLINE,
    EVENT,
    TODO,
    LIST,
    MARK,
    UNMARK,
    DELETE

}
public class Chatterbox {
    static StoredList taskList = new StoredList();

    public static void main(String[] args) {
        readFromSave();
        Scanner userInputReader = new Scanner(System.in);
        boolean isRunningProgram = true;
        while (isRunningProgram) {
            try {
                String userInput = userInputReader.nextLine();
                isRunningProgram = processCommand(userInput, true);
            }
            catch (ChatterBoxError e) {
                System.out.println(e.getMessage());
            }
        }
        writeToSave();
        exit(0);
    }

    public static boolean processCommand(String input, boolean toPrint) throws ChatterBoxError {
        String message = "";
        if(input.equals("bye")) {
            message = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________""";
            System.out.println(message);
            return false;
        } else {
            String[] command = input.split(" ", 2);
            try {
                Commands commandToExecute = Commands.valueOf(command[0].toUpperCase());
                switch (commandToExecute) {
                case LIST:
                    System.out.println(taskList);
                    break;
                case MARK:
                    if (command.length == 2) {
                        try {
                            int taskNum = Integer.parseInt(command[1]) - 1;
                            try {
                                message = Chatterbox.taskList.getItem(taskNum).setCompleted(true);
                                break;
                            } catch (NullPointerException e) {
                                throw new ChatterBoxNullTaskError();
                            }
                        } catch (NumberFormatException e) {
                            throw new ChatterBoxMarkError();
                        }
                    } else {
                        throw new ChatterBoxMarkError();
                    }
                case UNMARK:
                    if (command.length == 2) {
                        try {
                            int taskNum = Integer.parseInt(command[1]) - 1;
                            try {
                                message = Chatterbox.taskList.getItem(taskNum).setCompleted(false);
                                break;
                            } catch (NullPointerException e) {
                                throw new ChatterBoxNullTaskError();
                            }
                        } catch (NumberFormatException e) {
                            throw new ChatterBoxMarkError();
                        }
                    } else {
                        throw new ChatterBoxMarkError();
                    }
                case TODO:
                    if (command.length == 2) {
                        message = Chatterbox.taskList.addItem(new ToDos(command[1]));
                        break;
                    } else {
                        throw new ChatterBoxToDoError();
                    }
                case DEADLINE:
                    if (command.length == 2) {
                        try {
                            String[] details = command[1].split("/by ");
                            message = Chatterbox.taskList.addItem(new Deadline(details[0], details[1]));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new ChatterBoxDeadlineError();
                        }
                    } else {
                        throw new ChatterBoxDeadlineError();
                    }
                case EVENT:
                    if (command.length == 2) {
                        try {
                            String[] details = command[1].split("/from ");
                            String[] times = details[1].split("/to ");
                            message = Chatterbox.taskList.addItem(new Event(details[0], times[0], times[1]));
                            break;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new ChatterBoxEventError();
                        }
                    } else {
                        throw new ChatterBoxEventError();
                    }
                case DELETE:
                    if (command.length == 2) {
                        try {
                            message = taskList.removeItem(Integer.parseInt(command[1]) - 1);
                            break;
                        } catch (NumberFormatException e) {
                            throw new ChatterBoxDeleteError();
                        }
                    } else {
                        throw new ChatterBoxDeleteError();
                    }
                }
                if (toPrint) {
                    System.out.print(message);
                }
            } catch (IllegalArgumentException e) {
                throw new ChatterBoxError();
            }
        }
        return true;
    }

    public static void readFromSave() {
        File saveFile = new File("chatterbox_save.txt");
        try {
            Scanner fileReader = new Scanner(saveFile);
            int taskCount = 0;
            while (fileReader.hasNextLine()) {
                String lineData = fileReader.nextLine();
                String[] saveData = lineData.split(", ", 2);
                try {
                    processCommand(saveData[1], false);
                    taskCount++;
                    if (saveData[0].equals("done")) {
                        processCommand("mark " + taskCount, false);
                    }
                } catch (ChatterBoxError | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error reading line:  " + lineData);
                }
            }
            fileReader.close();
            System.out.println("Save File Loaded");
            String welcomeMessage = """
                ____________________________________________________________
                Hello! I'm Chatterbox
                Below is your current list!
                What can I do for you?
                ____________________________________________________________""";
            System.out.println(welcomeMessage);
            processCommand("list", true);
        } catch (FileNotFoundException e) {
            try {
                saveFile.createNewFile();
                readFromSave();
            } catch (IOException e1) {
                System.out.println("Error Reading Chatterbox save file");
            }
        } catch (ChatterBoxError e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToSave() {
        try {
            FileWriter fileWriter = new FileWriter("chatterbox_save.txt");
            for (int i = 0; i < Chatterbox.taskList.getSize(); i++) {
                fileWriter.write(Chatterbox.taskList.getItem(i).storeTask());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error Writing Chatterbox save file");
        }
    }
}