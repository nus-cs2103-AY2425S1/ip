import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.File;
import java.time.LocalDateTime;

public class Duke {
    public static void main(String[] args) {
        String savedListPath = "." + File.separator + "data" + File.separator + "saved.txt";
        String lineBreak = "____________________________________________________________\n";
        String intro = lineBreak + "Hello! I'm Light\nWhat can I do for you?\n" + lineBreak;
        String exit = lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak;
        System.out.println(intro);
        String command;

        Scanner scanner = new Scanner(System.in);
        String[] splitedBySpace;
        String[] splitedBySlash;
        Task event;
        ArrayList<Task> list = new ArrayList<>();
        //fetch the savedlist
        list = readFromFile(savedListPath);
        while (scanner.hasNextLine()) {
            event = null;
            command = scanner.nextLine();
            splitedBySpace = command.split("\\s+", 2);
            switch (splitedBySpace[0]) {
                case "bye":
                    System.out.println(exit);
                    return;
                case "mark":
                    try {
                        int itemNumber = Integer.parseInt(splitedBySpace[1]);
                        list.get(itemNumber - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n" + list.get(itemNumber - 1));
                        writeToFile(savedListPath, arrayToNumberedString(list));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    break;
                case "unmark":
                    try {
                        int itemNumber = Integer.parseInt(splitedBySpace[1]);
                        list.get(itemNumber - 1).markAsUndone();
                        System.out.println("Nice! I've marked this task as undone:\n" + list.get(itemNumber - 1));
                        writeToFile(savedListPath, arrayToNumberedString(list));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    break;
                case "list":
                    System.out.print(lineBreak);
                    System.out.println(arrayToNumberedString(list));
                    System.out.print(lineBreak);
                    break;
                case "delete":
                    try {
                        int itemNumber = Integer.parseInt(splitedBySpace[1]);
                        list.remove(itemNumber - 1);
                        System.out.println(lineBreak + "Noted. I've removed this task:\n" + list.get(itemNumber - 1) + "\nNow you have " + list.size() + " tasks in the list.\n" + lineBreak);
                        writeToFile(savedListPath, arrayToNumberedString(list));
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    switch (splitedBySpace[0]) {
                        case "todo":
                            try {
                                event = new Todo(splitedBySpace[1]);
                            } catch (LightException e) {
                                System.out.println(e);
                            }

                            break;
                        case "deadline":
                            try {
                                splitedBySlash = splitedBySpace[1].split("/by ");
                                event = new Deadline(splitedBySlash[0], splitedBySlash[1]);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Not enough arguments");
                            } catch (LightException e) {
                                System.out.println(e);
                            }

                            break;
                        case "event":
                            try {
                                splitedBySlash = splitedBySpace[1].split("/from ");
                                String[] splitedBySlashTo = splitedBySpace[1].split("/to ");
                                event = new Event(splitedBySlash[0], splitedBySlash[1].substring(0,splitedBySlash[1].indexOf("/to ")).stripTrailing(), splitedBySlashTo[1]);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Not enough arguments");
                            } catch (LightException e) {
                                System.out.println(e);
                            }

                            break;

                        default:
                            System.out.println(new LightException("Please key in a valid input"));
                    }
                    if (event != null && list.add(event)) {
                        System.out.println(lineBreak + "Got it. I've added this task:\n" +
                                event +
                                "\nNow you have " + list.size() + " tasks in the list.\n" + lineBreak);
                        writeToFile(savedListPath, arrayToNumberedString(list));
                    }

            }
        }


    }
    public static void writeToFile(String filePath, String textToAdd) {
        try {
            File fileObj = new File(filePath);
            if (!fileObj.getParentFile().exists()) {
                fileObj.getParentFile().mkdirs();
            }
            if (fileObj.createNewFile()) {
                //file created
                System.out.println("Created file");
            } else {
                //file already exist
                System.out.println("File exist");
            }
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(textToAdd);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public static <T> String arrayToNumberedString(ArrayList<T> array) {
        return IntStream.range(0, array.size()).mapToObj(number -> number + 1 + ". " + array.get(number)).map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }
    public static ArrayList<Task> readFromFile(String filePath) {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File fileObj = new File(filePath);
            if (!fileObj.getParentFile().exists()) {
                fileObj.getParentFile().mkdirs();
            }
            if (fileObj.createNewFile()) {
                //file created
            } else {
                //file already exist
            }
            Scanner scanner = new Scanner(fileObj);
            Task event = null;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Boolean marked = data.charAt(7) == 'X';
                switch (data.charAt(4)) {
                    case 'T':
                        try {
                            event = new Todo(data.substring(10));
                        } catch (LightException e) {
                            System.out.println(e);
                        }

                        break;
                    case 'D':
                        try {
                            String betweenBrackets = data.substring(data.lastIndexOf("(")+1, data.lastIndexOf(")"));
                            String byDate = betweenBrackets.replace("by: ", "");
                            event = new Deadline(data.substring(10, data.indexOf("(by:")), byDate);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Not enough arguments");
                        } catch (LightException e) {
                            System.out.println(e);
                        }

                        break;
                    case 'E':
                        try {

                            String betweenBrackets = data.substring(data.lastIndexOf("(")+1, data.lastIndexOf(")"));
                            String fromDate = betweenBrackets.substring(betweenBrackets.indexOf("from: ")+6, betweenBrackets.indexOf("to: ")).stripTrailing();
                            String toDate = betweenBrackets.substring(betweenBrackets.indexOf("to: ")+4);
                            event = new Event(data.substring(10, data.indexOf("(from:")), fromDate, toDate);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Not enough arguments");
                        } catch (LightException e) {
                            System.out.println(e);
                        }

                        break;
                }
                if (event != null && list.add(event)) {
                    if (marked) {
                        event.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(arrayToNumberedString(list));
        return list;
    }

    public static LocalDateTime dateTimeParser(String input, DateTimeFormatter format) {
        //Define the input format
        try {
            // Parse the input string to a LocalDateTime object
            // Return the LocalDateTime object
            return LocalDateTime.parse(input, format);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use the format: " + format.toString());
            return null;
        }
    }
}

