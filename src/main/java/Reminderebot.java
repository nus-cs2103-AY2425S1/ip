import java.util.Arrays;
import java.util.Scanner;
public class Reminderebot {
    private Task[] tasks = new Task[100];
    enum commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT
    }
    private static final String topBuffer = "____________________________________________________________\n";
    private static final String bottomBuffer = "____________________________________________________________";
    static int index = 0;
    private static final String greetingText = topBuffer +
            " Hello! I'm [Reminderebot]\n" +
            " What can I do for you?\n" +
            topBuffer;

    private static final String goodbyeText = topBuffer +
            " Bye. Hope to see you again soon!\n" +
            bottomBuffer;

    public static void main(String[] args) {
        Reminderebot reminderebot = new Reminderebot();
        Scanner scan = new Scanner(System.in);
        Scanner scan2;
        reminderebot.greeting();

        while (true) {
            // Seperate the commands from the arguments
            String input = scan.nextLine();
            scan2 = new Scanner(input);
            String command = scan2.next().toUpperCase();
            String str;

            System.out.println("");
            // Check if the command is mark or unmark
            int idx; // used to mark task as done or undone

            // handle invalid input by getting user-input and validate against enum
            try{
                // executes command
                System.out.println(topBuffer);
                switch (commands.valueOf(command)) {
                    case BYE:
                        reminderebot.goodbye();
                        break;
                    case LIST:
                        reminderebot.printTasks();
                        break;
                    case MARK:
                        try{
                            str = scan2.nextLine();
                            idx = Integer.parseInt(str.split(" ")[1]);
                            reminderebot.markTask(idx);
                            System.out.println("Nice! I've marked this task as done:\n" + reminderebot.tasks[idx - 1]);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case UNMARK:
                        try{
                            str = scan2.nextLine();
                            idx = Integer.parseInt(str.split("")[1]);
                            reminderebot.unmarkTask(idx);
                            System.out.println("OK, I've marked this task as not done yet:\n" +
                                    reminderebot.tasks[idx - 1]);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case TODO:
                        try{
                            str = scan2.nextLine();
                            ToDo task = new ToDo(str);
                            reminderebot.addToDo(task);
                            System.out.println("Got it. I've added this task: \n" +
                                    task.toString() +
                                    "\nNow you have " + index + " tasks in the list."
                            );
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case DEADLINE:
                        try{
                            str = scan2.nextLine();
                            String[] info = str.split("/by ");
                            Deadline task = new Deadline(info[0], info[1]);
                            reminderebot.addDeadline(task);
                            System.out.println("Got it. I've added this task: \n" +
                                    task.toString() +
                                    "\nNow you have " + index + " tasks in the list."
                            );
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case EVENT:
                        try{
                            str = scan2.nextLine();
                            String[] info = str.split("/from ");
                            String[] timing = info[1].split("/to ");
                            Event task = new Event(info[0], timing[0], timing[1]);
                            reminderebot.addEvent(task);
                            System.out.println("Got it. I've added this task: \n" +
                                    task.toString() +
                                    "\nNow you have " + index + " tasks in the list."
                            );
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    default:
                        System.out.println("Oops! I'm sorry, but I don't know what that means :-(");
                }
                if (commands.valueOf(command).equals(commands.BYE)) {
                    System.out.println(bottomBuffer);
                    break;
                }
                System.out.println(topBuffer);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Oops! I'm sorry, but I don't know what that means :-(");
                System.out.println(topBuffer);
            }
        }
    }

    private void greeting() {
        System.out.println(greetingText);
    }

    private void goodbye() {
        System.out.println(goodbyeText);
    }

    private void printTasks() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (int i=0; i<index; i++) {
            output.append(i+1).append(".").append(tasks[i]).append("\n");
        }
        String taskList = output.toString();
        System.out.println(taskList);
    }

    private void addToDo(ToDo task) {
        tasks[index] = task;
        index++;
    }

    private void addDeadline(Deadline task) {
        tasks[index] = task;
        index++;
    }

    private void addEvent(Event task) {
        tasks[index] = task;
        index++;
    }

    private void markTask(int idx) {
        if (idx > 0 && idx < index+1) {
            Task task = tasks[idx-1];
            task.markAsDone();
        }
    }

    private void unmarkTask(int idx) {
        if (idx > 0 && idx <= index) {
            Task task = tasks[idx - 1];
            task.markAsUndone();
        }
    }
}
