import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDate;

public class Snowy {





    private static ArrayList<Task> tasks = new ArrayList<>();

    private static File file;

    private static boolean isRunning = true;









    private static void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toFileStorage() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
        }

    }

    public static void main(String[] args) {
        Scanner fileScanner;
        Scanner scanner = new Scanner(System.in);

        file = new File("data/snowy.txt");
        initializeFile();


        System.out.print(GREETING);
        while (isRunning) {
            String lastInput = scanner.nextLine();

            int spaceIndex = lastInput.indexOf(" ");

            String command = (spaceIndex == -1 ? lastInput: lastInput.substring(0, spaceIndex)).toLowerCase();

            String description = spaceIndex == -1 ? "": lastInput.substring(spaceIndex + 1);

            Task newTask;

            switch (command) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    System.out.println(tasks.toString());
                    break;

                case "mark":
                    try {
                        int index = Integer.parseInt(description);
                        markTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                case "unmark":
                    try {
                        int index = Integer.parseInt(description);
                        unmarkTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                case "todo":
                    addToDo(description);
                    break;

                case "deadline":
                    addDeadline(description);
                    break;

                case "event":
                    addEvent(description);
                    break;

                case "delete":
                    try {
                        int index = Integer.parseInt(description);
                        deleteEvent(index)
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index format. Please try again");
                    }
                    break;

                default:
                    System.out.println("Command not recognized. Please try again");
                    break;
            }

            System.out.print(LINE);

        }

        updateFile();
        System.out.print(ENDING);
    }
}
