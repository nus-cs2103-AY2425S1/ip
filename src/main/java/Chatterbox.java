import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                            try {
                                message = Chatterbox.taskList.addItem(
                                        new Deadline(details[0], processDateTime(details[1])));
                            } catch (ChatterBoxInvalidDateError | ChatterBoxInvalidDateTimeError e) {
                                System.out.println(e.getMessage());
                            }
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
                            try {
                                message = Chatterbox.taskList.addItem(
                                        new Event(details[0], processDateTime(times[0]), processDateTime(times[1])));
                            } catch (ChatterBoxInvalidDateError | ChatterBoxInvalidDateTimeError e) {
                                System.out.println(e.getMessage());
                            }
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
        String currentDirectory = System.getProperty("user.dir");
        Path saveDirectoryPath = Paths.get(currentDirectory, "data");
        Path saveFilePath = Paths.get(saveDirectoryPath.toString(), "chatterbox_save.txt");
        try {
            Files.createDirectories(saveDirectoryPath);
        } catch (IOException e) {
            System.out.println("Could not create directory: " + saveDirectoryPath);
        }
        File saveFile = new File(saveFilePath.toString());
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
                System.out.println("No save file found");
                String welcomeMessage = """
                ____________________________________________________________
                Hello! I'm Chatterbox
                Below is your current list!
                What can I do for you?
                ____________________________________________________________""";
                System.out.println(welcomeMessage);
            } catch (IOException e1) {
                System.out.println("Error Reading Chatterbox save file");
            }
        } catch (ChatterBoxError e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToSave() {
        String currentDirectory = System.getProperty("user.dir");
        Path saveDirectoryPath = Paths.get(currentDirectory, "data");
        Path saveFilePath = Paths.get(saveDirectoryPath.toString(), "chatterbox_save.txt");
        try {
            FileWriter fileWriter = new FileWriter(saveFilePath.toString());
            for (int i = 0; i < Chatterbox.taskList.getSize(); i++) {
                fileWriter.write(Chatterbox.taskList.getItem(i).storeTask());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error Writing Chatterbox save file");
        }
    }

    public static LocalDateTime processDateTime(String dateString) throws ChatterBoxInvalidDateError, ChatterBoxInvalidDateTimeError {
        DateTimeFormatter dateTimeFormatter;
        boolean containsTime;
        if (dateString.trim().contains(" ")) { // Contains time
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            containsTime = true;
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            containsTime = false;
        }
        try {
            if (containsTime) {
                return LocalDateTime.parse(dateString.trim(), dateTimeFormatter);
            } else {
                return LocalDate.parse(dateString.trim(), dateTimeFormatter).atStartOfDay();
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            if (containsTime) {
                throw new ChatterBoxInvalidDateTimeError();
            } else {
                throw new ChatterBoxInvalidDateError();
            }
        }
    }
}