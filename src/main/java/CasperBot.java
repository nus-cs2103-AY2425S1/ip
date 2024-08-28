package main.java;
import exception.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CasperBot {
    private enum CommandType {
        CREATE, TASK;
    }
    private enum CreateCommand {
        EVENT, TODO, DEADLINE;
    }

    private enum TaskCommand {
        MARK, UNMARK, DELETE;
    }
    private static final List<Task> TASK_LIST = new ArrayList<>();
    private static final String FILE_PATH = "chatbot.txt";
    public static void main(String[] args) throws CasperBotException {
        openFile();
        line();
        System.out.println("Hello! I'm CasperBot.\n" +
                "What can I do for you?");
        line();
        echo();
    }

    private static void line() {
        System.out.println("------------------------------------------");
    }

    private static void echo() throws CasperBotException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            String[] inputArray = splitInputIntoTwo(input);
            line();
            try {
                if (inputArray[0].equalsIgnoreCase("list")) {
                    if (TASK_LIST.isEmpty()) {
                        System.out.println("You currently have no tasks in your list!");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < TASK_LIST.size(); i++) {
                            System.out.printf("%d. %s%n", i + 1, TASK_LIST.get(i));
                        }
                    }
                }
                else if (isValidCommand(inputArray[0], CommandType.TASK)) {
                    try {
                        int index = Integer.parseInt(inputArray[1]) - 1;
                        if (index >= TASK_LIST.size()) {
                            throw new CasperBotOutOfBoundsException();
                        }
                        TaskCommand taskCommand = TaskCommand.valueOf(inputArray[0].trim().toUpperCase());
                        Task task = TASK_LIST.get(index);
                        switch (taskCommand) {
                        case MARK:
                            task.markAsDone();
                            updateDoneStatus(index, true);
                            System.out.println("Nice! I've marked this task as done:");
                            break;
                        case UNMARK:
                            task.markAsNotDone();
                            updateDoneStatus(index, false);
                            System.out.println("OK, I've marked this task as not done yet:");
                            break;
                        case DELETE:
                            TASK_LIST.remove(task);
                            deleteFromFile(index);
                            System.out.println("Noted. I've removed this task:");
                            break;
                        }
                        System.out.println("  " + task);
                        if (taskCommand == TaskCommand.DELETE) {
                            printTaskListLength();
                        }
                    } catch (NumberFormatException e) {
                        throw new CasperBotNumberFormatException();
                    }
                }
                else if (isValidCommand(inputArray[0], CommandType.CREATE)) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    parseBySlash(inputArray[1], hashMap);
                    CreateCommand command = CreateCommand.valueOf(inputArray[0].trim().toUpperCase());
                    try {
                        switch (command) {
                        case TODO:
                            String todoDescription = hashMap.get("description");
                            if (todoDescription.isEmpty()) {
                                throw new CasperBotMissingInputException("description", "ToDo");
                            }
                            ToDo newToDo = new ToDo(todoDescription, false);
                            TASK_LIST.add(newToDo);
                            writeToFile(newToDo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newToDo);
                            break;
                        case DEADLINE:
                            String deadlineDescription = hashMap.get("description");
                            if (deadlineDescription.isEmpty()) {
                                throw new CasperBotMissingInputException("description", "Deadline");
                            }
                            String deadline = hashMap.get("by");
                            if (deadline == null || deadline.isEmpty()) {
                                throw new CasperBotMissingInputException("/by", "Deadline");
                            }
                            LocalDate dateOfDeadline = LocalDate.parse(deadline);
                            Deadline newDeadline = new Deadline(deadlineDescription, false, dateOfDeadline);
                            TASK_LIST.add(newDeadline);
                            writeToFile(newDeadline);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newDeadline);
                            break;
                        case EVENT:
                            String eventDescription = hashMap.get("description");
                            if (eventDescription.isEmpty()) {
                                throw new CasperBotMissingInputException("description", "Event");
                            }
                            String start = hashMap.get("from");
                            if (start == null || start.isEmpty()) {
                                throw new CasperBotMissingInputException("/from", "Event");
                            }
                            LocalDate dateOfStart = LocalDate.parse(start);
                            String end = hashMap.get("to");
                            if (end == null || end.isEmpty()) {
                                throw new CasperBotMissingInputException("/to", "Event");
                            }
                            LocalDate dateOfEnd = LocalDate.parse(end);
                            Event newEvent = new Event(eventDescription, false, dateOfStart, dateOfEnd);
                            TASK_LIST.add(newEvent);
                            writeToFile(newEvent);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + newEvent);
                            break;
                        }
                        printTaskListLength();
                    } catch (DateTimeParseException e) {
                        throw new CasperBotInvalidDateException();
                    }
                } else if (inputArray[0].equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                } else {
                    throw new CasperBotInvalidCommandException();
                }
            } catch (CasperBotException e) {
                System.out.println(e.getMessage());
            } finally {
                line();
            }
        }
        scanner.close();
    }

    private static String[] splitInputIntoTwo(String input) {
        int firstSpaceIndex = input.indexOf(" ");

        // If there is no space, return the original string as the first element
        if (firstSpaceIndex == -1) {
            return new String[] { input, "" };
        }

        // Split the string into two parts
        String part1 = input.substring(0, firstSpaceIndex).trim();  // Before the first space
        String part2 = input.substring(firstSpaceIndex + 1).trim(); // After the first space

        return new String[] { part1, part2 };
    }

    private static void parseBySlash(String input, HashMap<String, String> hashMap) {
        String[] parsedInput = input.split("/");
        hashMap.put("description", parsedInput[0].trim());
        for (int i = 1; i < parsedInput.length; i++) {
            String[] inputPart = splitInputIntoTwo(parsedInput[i]);
            hashMap.put(inputPart[0].trim(), inputPart[1].trim());
        }
    }

    private static boolean isValidCommand(String command, CommandType commandType) {
        try {
            switch (commandType) {
                case CREATE -> {
                    CreateCommand.valueOf(command.trim().toUpperCase());
                }
                case TASK -> {
                    TaskCommand.valueOf(command.trim().toUpperCase());
                }
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static void printTaskListLength() {
        if (TASK_LIST.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", TASK_LIST.size());
        }
    }

    private static void openFile() throws CasperBotIOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {  // Read each line until end of file
                String[] values = line.split("\\|");
                boolean isDone = Boolean.parseBoolean(values[1]);
                String description = values[2];
                switch (values[0]) {
                case "T":
                    TASK_LIST.add(new ToDo(description, isDone));
                    break;
                case "D":
                    LocalDate deadline = LocalDate.parse(values[3]);
                    TASK_LIST.add(new Deadline(description, isDone, deadline));
                    break;
                case "E":
                    LocalDate start = LocalDate.parse(values[3]);
                    LocalDate end = LocalDate.parse(values[4]);
                    TASK_LIST.add(new Event(description, isDone, start, end));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(FILE_PATH);
                if (file.createNewFile()) {
                    System.out.println("New file created successfully!");
                }
            } catch (IOException ioException) {
                throw new CasperBotIOException();
            }
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }

    private static void writeToFile(Task task) throws CasperBotIOException {
        try {
            FileWriter writer = new FileWriter("chatbot.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            boolean isDone = task.getStatusIcon().equals("X");
            String description = task.getDescription();
            String taskString = isDone + "|" + description;
            switch (task.getTaskType()) {
            case "T":
                bufferedWriter.write("T|" + taskString);
                break;
            case "D":
                Deadline deadline = (Deadline) task;
                bufferedWriter.write("D|" + taskString + "|" + deadline.getDeadline());
                break;
            case "E":
                Event event = (Event) task;
                bufferedWriter.write("D|" + taskString + "|" + event.getStart() + "|" + event.getEnd());
                break;
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }

    private static void deleteFromFile(int line) throws CasperBotIOException, CasperBotOutOfBoundsException {
         // Path to your file

        try {
            Path path = Paths.get(FILE_PATH);
            List<String> lines = Files.readAllLines(path);

            if (line >= 0 && line < lines.size()) {
                lines.remove(line);
            } else {
                throw new CasperBotOutOfBoundsException();
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }

    private static void updateDoneStatus(int line, boolean isDone) throws CasperBotIOException, CasperBotOutOfBoundsException {
        try {
            Path path = Paths.get(FILE_PATH);
            List<String> lines = Files.readAllLines(path);

            if (line >= 0 && line < lines.size()) {
                // Retrieve the line and update the done status
                String previous = lines.remove(line);
                String[] previousParts = previous.split("\\|");
                previousParts[1] = String.valueOf(isDone);
                String updated = String.join("|", previousParts);
                lines.add(line, updated);
            } else {
                throw new CasperBotOutOfBoundsException();
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }
}
