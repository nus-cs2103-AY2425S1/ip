import java.io.File;
import java.io.IOException;
import java.util.List;

public class Parser {

    public static boolean validNum(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void parse (String fullCommand, TaskList tasks, Storage storage, Ui ui) throws Exception {
        String[] inputArray = fullCommand.split("\\s+");
        String command = inputArray[0];
        if (fullCommand.isBlank()) {
            return;
        } else if (fullCommand.equals("bye")) {
            String filePath = "./save.txt";
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    // Attempt to create the file
                    if (file.createNewFile()) {
                        System.out.println("Save file created successfully.");
                    } else {
                        System.out.println("Failed to create the file.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                    e.printStackTrace();
                    return;
                }
            }
            storage.writeToFile("./save.txt", tasks);
            ui.bye();
            return;
        } else if (fullCommand.equals("list")) {
            if (tasks.size() == 0) {
                System.out.println("    You've got not tasks yet bro. Add todo, deadline, or event tasks.");
            }
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println("    " + i + ": " + tasks.getTask(i - 1).toString());
            }
        } else if (inputArray[0].equals("mark") && inputArray[1].matches("\\d+")) {
            int index = Integer.parseInt(inputArray[1]) - 1;
            System.out.println("    Yo I've marked this thingy as done");
            tasks.getTask(index).markDone();
            System.out.println("    " + tasks.getTask(index).toString());
        } else if (inputArray[0].equals("unmark") && inputArray[1].matches("\\d+")) {
            int index = Integer.parseInt(inputArray[1]) - 1;
            System.out.println("    Aights now it's unmarked again");
            tasks.getTask(index).unmarkDone();
            System.out.println("    " + tasks.getTask(index).toString());
        } else if (command.equals("delete")) {
            if (inputArray.length < 2) {
                throw new IllegalArgumentException ("Please provide the index of the task to delete");
            }
            if (!Parser.validNum(inputArray[1])) {
                throw new IllegalArgumentException("Please provide a valid index");
            }
            int index = Integer.parseInt(inputArray[1]) - 1;
            String temp = tasks.getTask(index).toString();
            tasks.remove(index);
            System.out.println("    Alright bro I deleted that for you");
            System.out.println("    deleted: " + temp);
            System.out.println("    You now have " + tasks.size() + " tasks");
        }else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {

            Task t;
            if (inputArray[0].equals("todo")) {
                if (inputArray.length < 2 || fullCommand.length() < 5) {
                    throw new TodoException("Bruh todo task cannot be empty.\nExample:\n    todo homework");
                }
                t = new ToDo(fullCommand.substring(5));

                tasks.add(t);
            } else if (inputArray[0].equals("deadline")) {
                String[] split = fullCommand.split("/by");
                if (split.length < 2) {
                    System.out.println("invalid format, require /by <date-time>");
                    return;
                }
                String bef = split[0].substring(9);
                String aft = split[1];
                t = new Deadline(bef, aft.strip());
                tasks.add(t);

            } else {
                String[] split1 = fullCommand.split("/start");
                if (split1.length < 2) {
                    System.out.println("    Invalid Format man. You need a /start and a /end");
                    return;
                }
                String[] split2 = split1[1].split("/end");
                if (split1.length < 2 || split2.length < 2) {
                    System.out.println("    Invalid Format man. You need a /start and a /end");
                    return;
                }
                String start = split2[0];
                String end = split2[1];
                String description = split1[0].substring(6);
                t = new Event(description, start.strip(), end.strip());
                tasks.add(t);

            }
            System.out.println("    Got it. I've added this task:");
            System.out.println("        " + t.toString());
            System.out.println("    You now have " + tasks.size() + " tasks");
        } else {
            throw new Exception("Ehhh not sure what this is man");
        }
    }

}
