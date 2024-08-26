import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Boss {
    private static Storage storage;
    private static TaskList tasks;

    private static Ui ui;

    enum Types {
        TODO, DEADLINE, EVENT, NONE
    }

    public static void run(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        ui = new Ui();

        System.out.println("Hello! I'm the boss.");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);

        String task = myObj.nextLine();
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                try {
                    storage.printFileContents();
                } catch (FileNotFoundException e) {
                    ui.showLoadingError();
                    System.out.println(e.getMessage());
                }
            } else if (task.contains("unmark")) {
                String newFileData = tasks.unmark(task);
                try {
                    storage.writeToFile(newFileData, false);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            // need to ensure that the string contains a number!!!!
            else if (task.contains("mark")) {
                String newFileData = tasks.mark(task);
                try {
                    storage.writeToFile(newFileData, false);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            } else if (task.contains("delete")) {
                String newFileData = tasks.delete(task);
                try {
                    storage.writeToFile(newFileData, false);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            // it must be a command to create a task (of some type)
            else {
                Types taskType;
                if (task.contains("todo")) {
                    taskType = Types.TODO;
                } else if (task.contains("deadline")) {
                    taskType = Types.DEADLINE;
                } else if (task.contains("event")) {
                    taskType = Types.EVENT;
                } else {
                    taskType = Types.NONE;
                }

                switch (taskType) {
                    case TODO:
                        try {
                            String[] string = task.split(" ");
                            if (string.length == 1) {
                                throw new BossException("The description of todo cannot be empty!");
                            }
                            Task item = new Todo(task);
                            tasks.addTask(item);
                            int i = storage.numofTasks();
                            tasks.printabstraction(i);

                            storage.writeToFile(item + System.lineSeparator(), true);

                        } catch (BossException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    case DEADLINE:
                        try {
                            String[] string = task.split("/by ");
                            if (string.length == 1) {
                                throw new BossException("Please specify a deadline date!");
                            }
                            Task item = Deadline.of(string[0], string[1]);
                            tasks.addTask(item);
                            int i = storage.numofTasks();
                            tasks.printabstraction(i);

                            storage.writeToFile(item + System.lineSeparator(), true);
                        } catch (BossException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;

                    case EVENT:
                        try {
                            String[] string = task.split("/");
                            if (!(string.length == 3 && string[1].contains("from") && string[2].contains("to"))) {
                                throw new BossException("Wrong input! You must specify a description, start and end date for an event!");
                            }
                            String[] description = string[0].split(" ");
                            String from = string[1].split("from")[1];
                            String to = string[2].split("to")[1];
                            if (description.length == 1 || from.length() == 1 || to.length() == 1) {
                                throw new BossException("Invalid input!");
                            }
                            Task item = new Event(string[0], from, to);
                            tasks.addTask(item);
                            int i = storage.numofTasks();
                            tasks.printabstraction(i);

                            storage.writeToFile(item + System.lineSeparator(), true);
                        } catch (BossException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                    default:
                        try {
                            System.out.println("added: " + task);
                            Task item = new Task(task);
                            tasks.addTask(item);

                            storage.writeToFile(item + System.lineSeparator(), true);
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        break;
                }
            }
            task = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {

        run("src/main/data/boss.txt");
    }


}
