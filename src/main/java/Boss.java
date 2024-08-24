import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Boss {
    //start at 1 to match list representation

    private static void writeToFile(String filePath, String textToAdd, boolean appendorNot) throws IOException {
        FileWriter fw = new FileWriter(filePath, appendorNot);
        fw.write(textToAdd);
        fw.close();
    }

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        while (s.hasNext()) {
            String str = s.nextLine();
            System.out.println(i + ". " + str);
            i++;
        }
    }

    private static String reWrite(ArrayList<Task> tasks, int index, String toReplace) {
        String s = "";
        int i = 1;
        for (Task str : tasks) {
            s += "" + str + '\n';
        }
        return s;
    }


    private static int numofTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        while (s.hasNext()) {
            String str = s.nextLine();
            i++;
        }
        return i;
    }


    public static void main(String[] args) {
        try {
            String fileData = "src/main/data/boss.txt";
            ArrayList<Task> tasks = new ArrayList<>();

            List<String> lst = Files.readAllLines(Path.of(fileData));

            for (String str : lst) {
                String[] arr = str.split("] ");
                String s = arr[arr.length-1];
                if (arr[0].contains("X") || arr[1].contains("X")) {
                    tasks.add(new Task(s, true));
                } else {
                    tasks.add(new Task(s));
                }
            }

            enum Types {
                TODO, DEADLINE, EVENT, NONE;
            }

            System.out.println("Hello! I'm the boss.");
            System.out.println("What can I do for you?");
            Scanner myObj = new Scanner(System.in);

            String task = myObj.nextLine();
            while (!task.equals("bye")) {
                if (task.equals("list")) {
                    try {
                        printFileContents(fileData);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (task.contains("unmark")) {
                    // replace all characters with nothing, in order to extract number!
                    String taskNum = task.replaceAll("[^0-9]", "");
                    try {
                        if (taskNum.equals("")) {
                            throw new BossException("Please specify a task number to unmark");
                        }
                        int num = Integer.parseInt(taskNum);

                        if (tasks.size() < num) {
                            throw new BossException("Task " + num + " does not exist yet");
                        }
                        Task item = tasks.get(num - 1);
                        item.markAsUnDone();
                        String toReplace = item.getDescription();
                        String newFileData = reWrite(tasks, num, toReplace);
                        writeToFile(fileData, newFileData, false);

                        System.out.println("Ok! I have marked this task as not done yet!");
                        System.out.println(tasks.get(num - 1));
                    } catch (BossException e) {
                        System.out.println(e.getMessage());
                    }
                }
                // need to ensure that the string contains a number!!!!
                else if (task.contains("mark")) {
                    // replace all characters with nothing, in order to extract number!
                    String taskNum = task.replaceAll("[^0-9]", "");

                    try {
                        if (taskNum.equals("")) {
                            throw new BossException("Please specify a task number to mark");
                        }
                        int num = Integer.parseInt(taskNum);
                        if (tasks.size() < num) {
                            throw new BossException("Task " + num + " does not exist yet");
                        }
                        Task item = tasks.get(num - 1);
                        item.markAsDone();

                        String toReplace = item.getDescription();
                        String newFileData = reWrite(tasks, num, toReplace);
                        writeToFile(fileData, newFileData, false);


                        System.out.println("Nice! I have marked this task as done!");
                        System.out.println(tasks.get(num - 1));
                    } catch (BossException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (task.contains("delete")) {
                    try {
                        String taskNum = task.replaceAll("[^0-9]", "");
                        if (taskNum.equals("")) {
                            throw new BossException("Please specify a task number to delete");
                        }
                        int num = Integer.parseInt(taskNum);
                        if (tasks.size() < num) {
                            throw new BossException("Task " + num + " does not exist");
                        }
                        Task item = tasks.remove(num - 1);

                        String newFileData = reWrite(tasks, num, "");
                        writeToFile(fileData, newFileData, false);

                        System.out.println("Ok. This task has been removed!");
                        System.out.println(item);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    } catch (BossException e) {
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
                                tasks.add(item);
                                int i = numofTasks(fileData);
                                printabstraction(tasks, i);

                                writeToFile(fileData, item + System.lineSeparator(), true);

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
                                tasks.add(item);
                                int i = numofTasks(fileData);
                                printabstraction(tasks, i);

                                writeToFile(fileData, item + System.lineSeparator(), true);
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
                                tasks.add(item);
                                int i = numofTasks(fileData);
                                printabstraction(tasks, i);

                                writeToFile(fileData, item + System.lineSeparator(), true);
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
                                tasks.add(item);

                                writeToFile(fileData, item + System.lineSeparator(), true);
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                            break;
                    }
                }
                task = myObj.nextLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }



    //for abstraction mainly
    public static void printabstraction(ArrayList<Task> tasks, int len) {
        System.out.println("Got it! I've added this task now");
        int size = tasks.size();
        System.out.println(tasks.get(size-1));
        System.out.println("Now you have " + len + " tasks in the list.");
    }
    
}
