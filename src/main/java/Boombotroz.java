import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Boombotroz {

    private static void printTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            System.out.println(line);
            // Process the line for tasks

            if (line.startsWith("[T]")) {
                String toDoTask = line.substring(7);
                if (line.substring(3).startsWith("[X]")) {
                    new ToDo(true, toDoTask);
                } else if (line.substring(3).startsWith("[ ]")) {
                    new ToDo(false, toDoTask);
                }

            } else if (line.startsWith("[D]")) {
                String dlTask = line.substring(7).split(" \\(by: ")[0];
                String time = line.substring(7).split(" \\(by: ")[1];

                time = time.substring(0, time.length() - 1);
                if (line.substring(3).startsWith("[X]")) {
                    new Deadline(true, dlTask, time);
                } else if (line.substring(3).startsWith("[ ]")) {
                    new Deadline(false, dlTask, time);
                }

            } else {
                String eventTask = line.substring(7).split(" \\(from: ")[0];
                String time_start = line.substring(7).split(" \\(from: ")[1]
                        .split(" to: ")[0];
                String time_end = line.substring(7).split(" \\(from: ")[1]
                        .split(" to: ")[1];
                time_end = time_end.substring(0, time_end.length() - 1);
                if (line.substring(3).startsWith("[X]")) {
                    new Event(true, eventTask, time_start, time_end);
                } else if (line.substring(3).startsWith("[ ]")) {
                    new Event(false, eventTask, time_start, time_end);
                }
            }

        }
        s.close();
    }

    private static void writeTasks(String filePath, String task) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(task);
        fw.close();
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        System.out.println("Hello! I'm Boombotroz" +
                "\nWhat can I do for you?");
        final String FILE_PATH = "../data.txt";
        printTasks(FILE_PATH);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {

                    // print all task using static method in Task class
                    Task.printAll();
                    input = scanner.nextLine();

                } else if (input.startsWith("mark ")) {

                    // mark task using static method in Task class
                    Task.mark_task(input);
                    writeTasks(FILE_PATH, Task.getAll());
                    input = scanner.nextLine();

                } else if (input.startsWith("unmark ")) {

                    // unmark task using static method in Task class
                    Task.unmark_task(input);
                    writeTasks(FILE_PATH, Task.getAll());
                    input = scanner.nextLine();

                } else if (input.startsWith("delete ")) {

                    // delete task using static method in Task class
                    Task.delete_task(input);
                    writeTasks(FILE_PATH, Task.getAll());
                    input = scanner.nextLine();

                } else if (input.startsWith("todo ")) {
                    String toDoTask = input.substring(5);

                    // if there is no task to do
                    if (toDoTask.isEmpty()) {
                        throw new BoombotrozException(
                                "You can't do nothing !!");
                    }

                    Task created_task = new ToDo(false, toDoTask);
                    created_task.addTaskWithMessage();
                    writeTasks(FILE_PATH, Task.getAll());
                    input = scanner.nextLine();

                } else if (input.startsWith("deadline ")) {
                    String dlTaskTime = input.substring(9);

                    // if there is no task
                    if (dlTaskTime.isEmpty()) {
                        throw new BoombotrozException(
                                "You can't do nothing !!");
                    }

                    // if there is no deadline
                    if (!dlTaskTime.contains(" /by ")) {
                        throw new BoombotrozException(
                                "You need a deadline !!");
                    }

                    String dlTask = dlTaskTime.split(" /by ")[0];
                    String time = dlTaskTime.split(" /by ")[1];
                    Task created_task = new Deadline(false, dlTask, time);
                    created_task.hasDate();
                    created_task.addTaskWithMessage();
                    writeTasks(FILE_PATH, Task.getAll());
                    input = scanner.nextLine();

                } else if (input.startsWith("event ")) {
                    String eventTaskTime = input.substring(6);

                    // if there is no task
                    if (eventTaskTime.isEmpty()) {
                        throw new BoombotrozException(
                                "You can't do nothing !!");
                    }

                    // if there is not both a start and end time
                    if (!eventTaskTime.contains(" /from ")
                            || !eventTaskTime.contains(" /to ")) {
                        throw new BoombotrozException(
                                "You need a start and end !!");
                    }
                    String eventTask = eventTaskTime.split(" /from ")[0];
                    String time_start = eventTaskTime.split(" /from ")[1]
                            .split(" /to ")[0];
                    String time_end = eventTaskTime.split(" /from ")[1]
                            .split(" /to ")[1];
                    Task created_task = new Event(false, eventTask,
                            time_start, time_end);
                    created_task.hasDate();
                    created_task.addTaskWithMessage();
                    writeTasks(FILE_PATH, Task.getAll());
                    input = scanner.nextLine();

                } else {
                    throw new BoombotrozException("That's nonsense !!");
                }

            } catch (BoombotrozException e) {
                System.out.println(e.getMessage());
                input = scanner.nextLine();

            } catch (FileNotFoundException e) {
                System.out.println("File not found");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

