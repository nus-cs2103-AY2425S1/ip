import java.util.ArrayList;
import java.util.Scanner;

public class Boss {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm the boss.");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);

        String task = myObj.nextLine();
        while(!task.equals("bye")) {
            if (task.equals("list")) {
                int i = 1;
                while (i < tasks.size() + 1) {
                    System.out.println(i + ". " + tasks.get(i-1));
                    i++;
                }
            }
            else if (task.contains("unmark")) {
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
                    System.out.println("Ok! I have marked this task as not done yet!");
                    System.out.println(tasks.get(num-1));
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
                    item.markAsUnDone();
                    System.out.println("Nice! I have marked this task as done!");
                    System.out.println(tasks.get(num-1));
                } catch (BossException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (task.contains("delete")) {
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
                    System.out.println("Ok. This task has been removed!");
                    System.out.println(item);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                } catch (BossException e) {
                    System.out.println(e.getMessage());
                }

            }
            else if (task.contains("todo")) {
                try {
                    String[] string = task.split(" ");
                    if (string.length == 1) {
                        throw new BossException("The description of todo cannot be empty!");
                    }
                    tasks.add(new Todo(task));
                    printabstraction(tasks);
                } catch (BossException e) {
                    System.out.println(e.getMessage());
                }

            } else if (task.contains("deadline")) {
                try {
                    String[] string = task.split("/by");
                    if (string.length == 1) {
                        throw new BossException("Please specify a deadline date!");
                    }
                    tasks.add(new Deadline(string[0], string[1]));
                    printabstraction(tasks);
                } catch (BossException e) {
                    System.out.println(e.getMessage());
                }

            } else if (task.contains("event")) {
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
                    tasks.add(new Event(string[0], from, to));
                    printabstraction(tasks);
                } catch (BossException e) {
                    System.out.println(e.getMessage());
                }

            }
            else {
                System.out.println("added: " + task);
                tasks.add(new Task(task));
            }
            task = myObj.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }




    //for abstraction mainly
    public static void printabstraction(ArrayList<Task> tasks) {
        System.out.println("Got it! I've added this task now");
        int size = tasks.size();
        System.out.println(tasks.get(size-1));
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    
}
