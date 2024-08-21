import java.util.Arrays;
import java.util.Scanner;


public class Waterfall {
    public static void main(String[] args) {
        String chatBotName = "Waterfall";
        int indentSpace = 4;
        String welcomeMessage =
            ("____________________________________________________________\n"
            + "Hualalalalala I'm " + chatBotName + "\n"
            + "What can I do for you?\n"
            + "____________________________________________________________\n").indent(indentSpace);
        String byeMessage =
            ("____________________________________________________________\n"
            + "Shhhhhhhhhhhh. Hope to see you again soon!\n"
            + "____________________________________________________________\n").indent(indentSpace);
        Task[] taskList = new Task[100];
        int num = 0;
        System.out.println(welcomeMessage);
        Scanner userInput = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String nextInput = userInput.nextLine();
            switch (nextInput) {
                case "bye":
                    System.out.println(byeMessage);
                    exit = true;
                    break;
                case "list":
                    System.out.println(" ".repeat(indentSpace) + "___________________________________________________________");
                    System.out.println(" ".repeat(indentSpace) + "Here's the tasks in your waterfall hualalala");
                    for (int i = 0; i < num; i++) {
                        Task task = taskList[i];
                        if (task != null) {
                            String taskString = " ".repeat(indentSpace + 1) +
                                    Integer.toString(i + 1) + "."
                                    + task.toString();
                            System.out.println(taskString);
                        } else {
                            break;
                        }
                    }
                    System.out.println("____________________________________________________________".indent(indentSpace));
                    break;
                default:
                    if (nextInput.startsWith("mark ") && (nextInput.substring(5).matches("\\d+"))) {
                        int index = Integer.parseInt(nextInput.substring(5)) - 1;
                        if (index >= 100 || index < 0) {
                            System.out.println("Only range from 1 to 100 is possible!");
                            break;
                        }
                        if (taskList[index] == null) {
                            System.out.println("Oops, this task does not exist");
                            break;
                        }
                        Task task = taskList[index];
                        task.setDone(true);
                        String markResponse = ("____________________________________________________________\n"
                                + "Huluhuluhulu, I've marked this task as done:\n  "
                                + task.toString() + "\n"
                                + "____________________________________________________________\n").indent(indentSpace + 1);
                        System.out.println(markResponse);
                    } else if (nextInput.startsWith("unmark ") && (nextInput.substring(7).matches("\\d+"))) {
                        int index = Integer.parseInt(nextInput.substring(7)) - 1;
                        if (index >= 100 || index < 0) {
                            System.out.println("Only range from 1 to 100 is possible!");
                            break;
                        }
                        if (taskList[index] == null) {
                            System.out.println("Oops, this task does not exist");
                            break;
                        }
                        Task task = taskList[index];
                        task.setDone(false);
                        String unmarkResponse = ("____________________________________________________________\n"
                                + "Hohohohohoho, I've marked this task as not done yet:\n  "
                                + task.toString() + "\n"
                                + "____________________________________________________________\n").indent(indentSpace + 1);
                        System.out.println(unmarkResponse);
                    } else {
                        Task newTask;
                        if (nextInput.startsWith("todo ")) {
                            newTask = new ToDo(nextInput.substring(5));
                        } else if (nextInput.startsWith("deadline ")) {
                            int index = nextInput.indexOf("/");
                            String title = nextInput.substring(9, index);
                            String description = nextInput.substring(index + 1);
                            if (!description.startsWith("by ")) {
                                System.out.println("unrecognised comment: " + description);
                                break;
                            }
                            newTask = new Deadline(title, description.substring(3));
                        } else if (nextInput.startsWith("event ")) {
                            String[] inputs = nextInput.split("/");
                            if (inputs.length != 3) {
                                System.out.println("invalid event format: An event must contain only from and to comments");
                                break;
                            }
                            String title = inputs[0].substring(6);
                            String from;
                            String to;
                            if (inputs[1].startsWith("from ")) {
                                from = inputs[1].substring(5);
                                if (inputs[2].startsWith("to ")) {
                                    to = inputs[2].substring(3);
                                } else {
                                    System.out.println("invalid format: missing to comment");
                                    break;
                                }
                            } else if (inputs[1].startsWith("to")) {
                                to = inputs[1].substring(3);
                                if (inputs[2].startsWith("from ")) {
                                    from = inputs[2].substring(5);
                                } else {
                                    System.out.println("invalid format: missing from comment");
                                    break;
                                }
                            } else {
                                System.out.println("invalid format: missing to and from comment");
                                break;
                            }
                            newTask = new Event(title, from, to);
                        } else {
                            newTask = new Task(nextInput);
                        }
                        taskList[num] = newTask;
                        num++;
                        String echoString = ("____________________________________________________________\n"
                                + "Nice man, I have added the following task: \n" + newTask.toString() + "\n"
                                + "Now the waterfall has " + num + " tasks flowing! \n"
                                + "____________________________________________________________\n").indent(indentSpace);
                        System.out.println(echoString);
                    }
            }
        }
    }
}
