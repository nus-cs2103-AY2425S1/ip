import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;


public class Bob {
    private static List<Task> memory = new ArrayList<Task>();
    public static void main(String[] args) {

        try {
            Files.createDirectories(STORAGE_FILE_PATH.getParent());

            if (!Files.exists(STORAGE_FILE_PATH)) {
                Files.createFile(STORAGE_FILE_PATH);
            }
        } catch (IOException e) {
            Bob.print("I'm having trouble initialising my memory :(");
        }
        try {
            Scanner fileReader = new Scanner(STORAGE_FILE_PATH.toFile());
            while (fileReader.hasNext()) {
                String record = fileReader.nextLine();
                Task task = Task.from(record);
                memory.add(task);
            }
        } catch (FileNotFoundException e) {
            Bob.print("Seems like I'm missing my memory (still)");
        }

        Bob.print("Hello! I'm your chatbot, Bob.\n" +
                "How may I assist you?\n\n" +
                Bob.HELP_MESSAGE);

        Scanner reader = new Scanner(System.in);
        String response;

        while (!(response = Bob.readUserInput(reader)).equals("bye")) {
            try {
                if (response.startsWith("list")) {
                    if (response.equals("list")) {
                        Bob.print(String.format("These are your tasks:\n%s", Bob.memoryToString()));
                    } else {
                        Bob.print("Did you mean 'list'?");
                    }
                    continue;
                }

                if (response.startsWith("mark")) {
                    try {
                        String arg = response.substring("mark".length()).trim();
                        if (arg.isEmpty()) {
                            throw new EmptyFieldException();
                        }
                        int taskNumber = Integer.parseInt(arg);
                        Task task = memory.get(taskNumber - 1);
                        task.mark();
                        Bob.remember();
                        Bob.print(String.format("Nice! I've marked this task as done:\n\t%s", task));
                    } catch (IndexOutOfBoundsException e) {
                        Bob.print("Nice try but there's no such task.");
                    }
                    continue;
                }

                if (response.startsWith("unmark")) {
                    try {
                        String arg = response.substring("unmark".length()).trim();
                        if (arg.isEmpty()) {
                            throw new EmptyFieldException();
                        }
                        int taskNumber = Integer.parseInt(arg);
                        Task task = memory.get(taskNumber - 1);
                        task.unmark();
                        Bob.remember();
                        Bob.print(String.format("Oh well, this task has been marked undone:\n\t%s", task));
                    } catch (IndexOutOfBoundsException e) {
                        Bob.print("There's no such task!");
                    }
                    continue;
                }

                if (response.equals("I need help.")) {
                    Bob.print("Here are the list of commands:\n" +
                            "1. list\n\t- lists tasks\n" +
                            "2. mark <task number>\n\t- marks the task as done\n" +
                            "3. unmark <task number>\n\t- unmarks the task\n" +
                            "4. deadline <task description> /by <by>\n\t- Creates a deadline\n" +
                            "5. todo <task description>\n\t- Creates a todo\n" +
                            "6. event <task description> /from <from> /to <to>\n\t- Creates an event\n" +
                            "7. delete <task number>\n\t- deletes the task\n" +
                            "8. bye\n\t- exits the program");
                    continue;
                }

                if (response.startsWith("delete")) {
                    try {
                        String arg = response.substring("delete".length()).trim();
                        if (arg.isEmpty()) {
                            throw new EmptyFieldException();
                        }
                        int taskNumber = Integer.parseInt(arg);
                        Task task = Bob.memory.remove(taskNumber - 1);
                        Bob.remember();
                        Bob.print(String.format("""
                                Oof. I have removed the requested task:
                                \t%s
                                Now you have %s tasks in the list""", task, Bob.memory.size()));
                    } catch (IndexOutOfBoundsException e) {
                        Bob.print("Hm, you don't seem to have that task");
                    }
                    continue;
                }

                Task task;
                if (response.startsWith("deadline")) {
                    String by = response.substring(response.indexOf("/by") + "/by".length()).trim();


                    String description = response.substring(
                            "deadline".length(),
                            response.indexOf("/by")).trim();
                    if (by.isEmpty() || description.isEmpty()) {
                        throw new EmptyFieldException();
                    }
                    task = new Deadline(description, parseDatetime(by));
                } else if (response.startsWith("todo")) {
                    String description = response.substring(
                            "todo".length()).trim();
                    if (description.isEmpty()) {
                        throw new EmptyFieldException();
                    }
                    task = new ToDo(description);
                } else if (response.startsWith("event")) {
                    String from = response.substring(
                            response.indexOf("/from") + "/from".length(),
                            response.indexOf("/to")).trim();
                    String to = response.substring(response.indexOf("/to") + "/to".length()).trim();
                    String description = response.substring(
                            "event".length(),
                            response.indexOf("/from")).trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyFieldException();
                    }
                    task = new Event(description, parseDatetime(from), parseDatetime(to));

                } else {
                    throw new InvalidCommandException();
                }
                Bob.memory.add(task);
                Bob.remember();
                Bob.print(
                        String.format("Here's the added task:\n" +
                        "\t%s\n" +
                        "Now you have %s tasks in the list.", task, Bob.memory.size())
                );
            } catch(InvalidCommandException e) {
                Bob.print("I don't recognise that command :( Try again.\n" +
                        Bob.HELP_MESSAGE
                );
            } catch(NumberFormatException e) {
                Bob.print("Seems like at least one of the arguments to this command was\n" +
                        "not a number when it should have been.\n" + Bob.HELP_MESSAGE);
            } catch(StringIndexOutOfBoundsException e) {
                Bob.print("Seems like the command keyed wasn't appropriately used. You may have\n" +
                        "given insufficient information. Also check that the order in which\n" +
                        "the information was given is correct.\n" + Bob.HELP_MESSAGE);
            } catch(EmptyFieldException e) {
                Bob.print("Field(s) may not be blank." + "\n" + Bob.HELP_MESSAGE);
            } catch(IOException e) {
                Bob.print("I can't remember that for some reason T T");
            } catch(DateTimeParseException e) {
                Bob.print("Sorry, I only accept datetime inputs of yyyy-MM-dd HHmm");
            }
        }        Bob.print("Bye.");

    }

    private static String readUserInput(Scanner s) {
        System.out.print("> ");
        return s.nextLine();
    }

    private static void print(String s) {
        System.out.println("____________________________________________________________\n" +
                s + "\n____________________________________________________________"

        );
    }

    private static String memoryToString() {
        String list = "";
        for (int i = 0; i < Bob.memory.size(); i++){
            list += (i + 1) +":" + Bob.memory.get(i);
            if (i < Bob.memory.size() - 1) {
                 list += "\n";
            }
        }
        return list.isEmpty() ? "No tasks :(" : list;
    }

    private static void writeToFile(Path filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath.toFile());
        fw.write(text);
        fw.close();
    }

    private static void remember() throws IOException {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < Bob.memory.size(); i++) {
            text.append(Bob.memory.get(i).toText()).append("\n");
        }
        writeToFile(STORAGE_FILE_PATH, text.toString());
    }

    private static LocalDateTime parseDatetime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }
    private static final String HELP_MESSAGE = "Key in \"I need help.\" for additional help.";
    private static final Path STORAGE_FILE_PATH = Paths.get("./data/bob.txt");
}
