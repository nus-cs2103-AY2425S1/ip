import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GreetBot {
    public static void main(String[] args) {
        new GreetBot().run();
    }

    /* solution below inspired by main function in:
    https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java
    */
    private void run() {
        try {
            ArrayList<Task> list = new ArrayList<>();

            //create file and directory for data storage
            String workingDir = System.getProperty("user.dir");
            Path dataPath = java.nio.file.Paths.get(workingDir, "data", "greetbot.txt");
            File dir = new File(dataPath.getParent().toString());
            dir.mkdir();
            if (!Files.exists(dataPath)) {
                FileWriter createFileWriter = new FileWriter(dataPath.toString(), false);
                createFileWriter.close();
            }

            //read and load from database
            Scanner databaseReader = new Scanner(dataPath);
            while (databaseReader.hasNextLine()) {
                String[] data = databaseReader.nextLine().split(" \\|");
                if (data[0].equals("T")) {
                    String command = "todo" + data[2];
                    try {
                        Task.decideTask(command, list);

                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (RandomInputException e) {
                        System.out.println(e.getMessage());
                    }
                    if (data[1].equals(" 1")) {
                        list.get(list.size() - 1).mark();
                    }
                } else if (data[0].equals("D")) {
                    String command = "deadline" + data[2] + "/by" + data[3];
                    try {
                        Task.decideTask(command, list);

                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (RandomInputException e) {
                        System.out.println(e.getMessage());
                    }
                    if (data[1].equals(" 1")) {
                        list.get(list.size() - 1).mark();
                    }
                } else {
                    String command = "event" + data[2] + "/from" + data[3] + " /to" + data[4];
                    try {
                        Task.decideTask(command, list);

                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (RandomInputException e) {
                        System.out.println(e.getMessage());
                    }
                    if (data[1].equals(" 1")) {
                        list.get(list.size() - 1).mark();
                    }
                }
            }
            databaseReader.close();

            /*
            String filePath2 = "data/test.txt";
            FileWriter testWriter = new FileWriter(filePath2, false);
            testWriter.write("jdafnkj");
            testWriter.close();
            BufferedWriter bw = new BufferedWriter(testWriter);
            bw.write("1234");
            bw.write(System.lineSeparator());

            File storage = new File(filePath);
            //FileWriter writer = new FileWriter(filePath, false);
            try {
                Scanner fileScanner = new Scanner(storage);
            } catch (FileNotFoundException e) {
                storage.createNewFile();
            }
*/
            System.out.println("Hello! I'm GreetBot");
            System.out.println("What can I do for you?");
            Scanner scanner = new Scanner(System.in);



            while (scanner.hasNext()) {

                String currentCommand = scanner.nextLine();

                if (currentCommand.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (currentCommand.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("%d.%s", i + 1, list.get(i)));
                    }
                } else if (currentCommand.startsWith("mark")) {
                    int index = Integer.parseInt(currentCommand.substring(currentCommand.indexOf(" ") + 1));
                    list.get(index - 1).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index - 1).toString());
                } else if (currentCommand.startsWith("unmark")) {
                    int index = Integer.parseInt(currentCommand.substring(currentCommand.indexOf(" ") + 1));
                    list.get(index - 1).unmark();
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(list.get(index - 1).toString());
                } else {

                    try {
                        int before = list.size();
                        Task.decideTask(currentCommand, list);
                        if (before < list.size()) {
                            System.out.println("Got it. I've added this task:");
                            System.out.println(list.get(list.size() - 1).toString());
                            System.out.println(String.format("Now you have %s tasks in the list.", list.size()));
                        }
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (RandomInputException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }


            scanner.close();

        } catch (IOException e) {
            System.out.println("reached");
            e.printStackTrace();
        }
    }
}
