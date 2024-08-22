import java.util.Scanner;
import java.util.ArrayList;

public class Cypher {
    private enum Commands {
        LIST, TODO, EVENT, DEADLINE, MARK, UNMARK, BYE, HELP, DELETE
    }

    private static ArrayList<Task> taskList;

    private static void lineBreak() {
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }

    private static void greet() {
        Cypher.lineBreak();
        System.out.println("Hello! I am Cypher\nWhat can I do for you!");
        Cypher.lineBreak();
    }

    private static void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Cypher.lineBreak();
    }

    private static void addToList(Task task) {
        taskList.add(task);
        Cypher.lineBreak();
        System.out.println("Got it. I have added this task:\n  " + task);
        System.out.printf("Now you have %d task in the list%n", Cypher.taskList.size());
        Cypher.lineBreak();
    }

    private static void printTaskList() {
        Cypher.lineBreak();
        if (Cypher.taskList.isEmpty()) {
            System.out.println("You have no items in your list:");
        } else {
            System.out.println("Here are the items in your list:");
            for (int i = 0; i < Cypher.taskList.size(); i++) {
                System.out.println((i + 1) + ". " + Cypher.taskList.get(i));
            }
        }
        Cypher.lineBreak();
    }

    private static void markTask(int i) {
        Task task = Cypher.taskList.get(i);
        task.completeTask();
        Cypher.lineBreak();
        System.out.println("Nice! I have marked this task as completed:\n " + task);
        Cypher.lineBreak();
    }

    private static void unmarkTask(int i) {
        Task task = Cypher.taskList.get(i);
        task.incompleteTask();
        Cypher.lineBreak();
        System.out.println("Ok! I have marked this task as incomplete:\n " + task);
        Cypher.lineBreak();
    }

    private static void delTask(int i) {
        Task task = Cypher.taskList.remove(i);
        Cypher.lineBreak();
        System.out.println("Noted! I have removed this task:\n " + task);
        System.out.printf("Now you have %d task in the list%n", Cypher.taskList.size());
        Cypher.lineBreak();
    }

    public static void main(String[] args) {
        Cypher.greet();
        Cypher.taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Boolean stayIn = true;
        while (stayIn) {
            try {
                String input = scanner.nextLine();
                String[] command = input.split(" ", 2);

                // Need to use switch cases
                switch (Commands.valueOf(command[0].toUpperCase())) {
                    case LIST:
                        Cypher.printTaskList();
                        break;
                    case TODO:
                        if (command.length != 2 || command[1].isEmpty()) {
                            throw new CypherException("No task is given. The format of the todo command is:\n todo <Description of task>");
                        }
                        Task todo = new ToDo(command[1]);
                        Cypher.addToList(todo);
                        break;
                    case DEADLINE:
                        String[] deadlineSplit = command[1].split("/by", 2);

                        if (deadlineSplit[0].isEmpty()) {
                            throw new CypherException("No task is given. The format of the deadline command is:\n deadline <Description of task> /by <your preferred deadline>");
                        }
                        else if (deadlineSplit.length != 2 || deadlineSplit[1].trim().isEmpty()) {
                            throw new CypherException("No deadline is given. The format of the deadline command is:\n deadline <Description of task> /by <your preferred deadline>");
                        }
                        Task deadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                        Cypher.addToList(deadline);
                        break;
                    case EVENT:
                        String[] eventSplit = command[1].split("/from|/to ", 3);
                        if (eventSplit[0].isEmpty()) {
                            throw new CypherException("No task is given. The format of the event command is:\n event <Description of task> /from <from time> /to <to time>");
                        }
                        else if (eventSplit.length != 3 || eventSplit[1].trim().isEmpty() || eventSplit[2].trim().isEmpty()) {
                            throw new CypherException("To/from is not given properly. The format of the deadline command is:\n event <Description of task> /from <from time> /to <to time>");
                        }
                        Task task = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                        Cypher.addToList(task);
                        break;
                    case MARK:
                        // Need check if that is number
                        int markVal = Integer.parseInt(command[1]) - 1;
                        if (markVal >= Cypher.taskList.size()) {
                            throw new CypherException(String.format("You have %d items in your list. Enter a valid integer or add more items to your list", Cypher.taskList.size()));
                        } else if (markVal < 0) {
                            throw new CypherException("Enter a value above 0");
                        }
                        Cypher.markTask(markVal);
                        break;
                    case UNMARK:
                        // Need check if that is number
                        int unmarkVal = Integer.parseInt(command[1]) - 1;
                        if (unmarkVal >= Cypher.taskList.size()) {
                            throw new CypherException(String.format("You have %d items in your list. Enter a valid integer or add more items to your list", Cypher.taskList.size()));
                        } else if (unmarkVal < 0) {
                            throw new CypherException("Enter a value above 0");
                        }
                        Cypher.unmarkTask(unmarkVal);
                        break;
                    case BYE:
                        Cypher.lineBreak();
                        scanner.close();
                        stayIn = false;
                        break;
                    case HELP:
                        Cypher.lineBreak();
                        System.out.println("<<UNDER CONSTRUCTION>>");
                        Cypher.lineBreak();
                        break;
                    case DELETE:
                        int delVal = Integer.parseInt(command[1]) - 1;
                        if (delVal >= Cypher.taskList.size()) {
                            throw new CypherException(String.format("You have %d items in your list. Enter a valid integer or add more items to your list", Cypher.taskList.size()));
                        } else if (delVal < 0) {
                            throw new CypherException("Enter a value above 0");
                        }
                        Cypher.delTask(delVal);
                        break;
                    default:
                        System.out.printf("\"%s\" is not a valid command. Type --help in order to see the list of valid commands (This feature is still under construction)\n", command[0]);
                }
            } catch (CypherException exp) {
                Cypher.lineBreak();
                System.out.println(exp);
                Cypher.lineBreak();
            } catch (NumberFormatException exp) {
                Cypher.lineBreak();
                System.out.println("That is not a valid command. You need to enter a valid integer. Type --help in order to see the list of valid commands (This feature is still under construction)");
                Cypher.lineBreak();
            }
            catch (IllegalArgumentException exp) {
                Cypher.lineBreak();
                System.out.println("That is not a valid command. Type --help in order to see the list of valid commands (This feature is still under construction)");
                Cypher.lineBreak();
            }
        }

        Cypher.goodBye();
    }
}
