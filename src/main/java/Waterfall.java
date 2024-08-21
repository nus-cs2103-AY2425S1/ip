import java.util.ArrayList;
import java.util.List;
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
        List<Task> taskList = new ArrayList<>();
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
                        Task task = taskList.get(i);
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
                    try {
                        if (nextInput.startsWith("mark ") && (nextInput.substring(5).matches("\\d+"))) {
                            int index = Integer.parseInt(nextInput.substring(5)) - 1;
                            Task task = taskList.get(index);
                            if (task == null) {
                                throw new WaterfallException("Why are you trying to edit a waterfall task that does not exist?");
                            }
                            task.setDone(true);
                            String markResponse = ("____________________________________________________________\n"
                                    + "Huluhuluhulu, I've marked this task as done:\n  "
                                    + task.toString() + "\n"
                                    + "____________________________________________________________\n").indent(indentSpace + 1);
                            System.out.println(markResponse);
                        } else if (nextInput.startsWith("unmark ") && (nextInput.substring(7).matches("\\d+"))) {
                            int index = Integer.parseInt(nextInput.substring(7)) - 1;
                            Task task = taskList.get(index);
                            if (task == null) {
                                throw new WaterfallException("Why are you trying to edit a waterfall task that does not exist?");
                            }
                            task.setDone(false);
                            String unmarkResponse = ("____________________________________________________________\n"
                                    + "Hohohohohoho, I've marked this task as not done yet:\n  "
                                    + task.toString() + "\n"
                                    + "____________________________________________________________\n").indent(indentSpace + 1);
                            System.out.println(unmarkResponse);
                        } else if (nextInput.startsWith("delete ") && (nextInput.substring(7).matches("\\d+"))) {
                            int index = Integer.parseInt(nextInput.substring(7)) - 1;
                            Task task = taskList.get(index);
                            if (task == null) {
                                throw new WaterfallException("Why are you trying to edit a waterfall task that does not exist?");
                            }
                            taskList.remove(index);
                            num--;
                            String unmarkResponse = ("____________________________________________________________\n"
                                    + "Hohohohohoho, I've deleted this task:\n  "
                                    + task.toString() + "\n"
                                    + "____________________________________________________________\n").indent(indentSpace + 1);
                            System.out.println(unmarkResponse);
                        } else {
                            Task newTask;
                            if (nextInput.startsWith("todo ")) {
                                String title = nextInput.substring(5);
                                if (title.isEmpty()) {
                                    throw new WaterfallException("Bruh what is this empty title are you kidding me!");
                                }
                                newTask = new ToDo(title);
                            } else if (nextInput.startsWith("deadline ")) {
                                int index = nextInput.indexOf("/");
                                String title = nextInput.substring(9, index);
                                if (title.isEmpty()) {
                                    throw new WaterfallException("Bruh what is this empty title are you kidding me!");
                                }
                                String description = nextInput.substring(index + 1);
                                if (!description.startsWith("by ")) {
                                    throw new WaterfallException("unrecognised comment: " + description);
                                }
                                newTask = new Deadline(title, description.substring(3));
                            } else if (nextInput.startsWith("event ")) {
                                String[] inputs = nextInput.split("/");
                                if (inputs.length != 3) {
                                    throw new WaterfallException("invalid event format: An event must contain only from and to comments");
                                }
                                String title = inputs[0].substring(6);
                                if (title.isEmpty()) {
                                    throw new WaterfallException("Bruh what is this empty title are you kidding me!");
                                }
                                String from;
                                String to;
                                if (inputs[1].startsWith("from ")) {
                                    from = inputs[1].substring(5);
                                    if (inputs[2].startsWith("to ")) {
                                        to = inputs[2].substring(3);
                                    } else {
                                        throw new WaterfallException("invalid format: missing to comment");
                                    }
                                } else if (inputs[1].startsWith("to")) {
                                    to = inputs[1].substring(3);
                                    if (inputs[2].startsWith("from ")) {
                                        from = inputs[2].substring(5);
                                    } else {
                                        throw new WaterfallException("invalid format: missing from comment");
                                    }
                                } else {
                                    throw new WaterfallException("invalid format: missing to and from comment");
                                }
                                if (to.isEmpty()) {
                                    throw new WaterfallException("Bruh what is this empty to command are you kidding me!");
                                }
                                if (from.isEmpty()) {
                                    throw new WaterfallException("Bruh what is this empty from command are you kidding me!");
                                }
                                newTask = new Event(title, from, to);
                            } else {
                                throw new WaterfallException("Sorry man whatchu yapping!");
                            }
                            taskList.add(newTask);
                            num++;
                            String echoString = ("____________________________________________________________\n"
                                    + "Nice man, I have added the following task: \n" + newTask.toString() + "\n"
                                    + "Now the waterfall has " + num + " tasks flowing! \n"
                                    + "____________________________________________________________\n").indent(indentSpace);
                            System.out.println(echoString);
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
            }
        }
    }
}
