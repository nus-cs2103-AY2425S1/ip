import java.util.Scanner;
import tasks.Deadlined;
import tasks.Events;
import tasks.InputEmptyException;
import tasks.NotANumMarkingException;
import tasks.TaskList;
import tasks.Todos;

public class WansBot {
    private static final String HR = "----------------------------------------------------------------------";
    private static int numTasks = 0;
    private static TaskList userTaskList = new TaskList();
    // Method that deals with empty inputs by throwing tasks.InputEmptyException
    private static void emptyInput(String userInput) throws InputEmptyException {
        if (userInput.strip().equalsIgnoreCase("todos") ||
            userInput.strip().equalsIgnoreCase("deadline") ||
            userInput.strip().equalsIgnoreCase("event") ||
            userInput.strip().equalsIgnoreCase("mark") ||
            userInput.strip().equalsIgnoreCase("unmark") ||
            userInput.strip().equalsIgnoreCase("remove")) {
            throw new InputEmptyException(userInput);
        }
    }

    // Method that throws NumberFormatException and custom tasks.NotANumMarkingException
    private static void notNumInput(String userInput, int taskListSize) throws NumberFormatException,
            NotANumMarkingException {
        if (userInput.startsWith("unmark")) {
            int posTask = Integer.parseInt(userInput.substring(7));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        } else if (userInput.startsWith("mark")) {
            int posTask = Integer.parseInt(userInput.substring(5));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        } else if (userInput.startsWith("remove")) {
            int posTask = Integer.parseInt(userInput.substring(7));
            if (posTask > taskListSize || posTask < 1) {
                throw new NotANumMarkingException(posTask);
            }
        }
    }

    // Method that throws custom tasks.InputEmptyException for deadlineds
    private static void missingInputDeadline(String userInput) {
        String[] splitUser = userInput.split( " /by ", 2);
        if (splitUser.length < 2) {
            throw new InputEmptyException(userInput, "/by");
        }
    }

    // Method that throws custom tasks.InputEmptyException for events
    private static void missingInputEvent(String userInput) {
        String[] splitUserStartDate = userInput.split(" /from ", 3);
        if (splitUserStartDate.length < 2) {
            throw new InputEmptyException(userInput, "/from");
        }
        String[] splitUserEndDate = splitUserStartDate[1].split( " /to ", 2);
        if (splitUserEndDate.length < 2) {
            throw new InputEmptyException(userInput, "/to");
        }
    }

    private static void introduceToUser() {
        String logo ="                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";

        System.out.println(HR + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + HR);
    }

    private static void listTasks() {
        System.out.println(HR + "\nWans:"
                + "\nHere are your tasks!\n"
                + userTaskList.toString());
        System.out.println("You have " + numTasks + " tasks!" + "\n" + HR);
    }

    private static void markTasks(String userInput) {
        int posTask = Integer.parseInt(userInput.substring(5)) - 1;
        userTaskList.number(posTask).finish();
        System.out.println(HR + "\nWans:"
                + "\nNice! I've marked\n"
                + userTaskList.number(posTask).toString()
                + " as completed\n" + HR);
    }

    private static void unmarkTasks(String userInput) {
        int posTask = Integer.parseInt(userInput.substring(7)) - 1;
        userTaskList.number(posTask).unfinish();
        System.out.println(HR + "\nWans:"
                + "\nOkay, so you lied! I've marked\n"
                + userTaskList.number(posTask).toString()
                + " as uncompleted\n" + HR);
    }

    private static void addTodos(String userInput) {
        Todos newTodo = new Todos(userInput.substring(5));
        userTaskList.add(newTodo);
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've added " + newTodo.toString()
                + "\n" + HR);
        numTasks++;
    }

    private static void addDeadlined(String userInput) {
        String[] splitUser = userInput.split( " /by ", 2);
        Deadlined newDeadlined = new Deadlined(splitUser[0].substring(8)
                , splitUser[1]);
        userTaskList.add(newDeadlined);
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've added " + newDeadlined.toString()
                + "\n" + HR);
        numTasks++;
    }

    private static void addEvent(String userInput) {
        String[] splitUserStartDate = userInput.split(" /from ", 3);
        String[] splitUserEndDate = splitUserStartDate[1].split( " /to ", 2);
        Events newEvent = new Events(splitUserStartDate[0].substring(5),
                splitUserEndDate[0], splitUserEndDate[1]);
        userTaskList.add(newEvent);
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've added " + newEvent.toString()
                + "\n" + HR);
        numTasks++;
    }

    private static void removeTask(String userInput) {
        int posTask = Integer.parseInt(userInput.substring(7)) - 1;
        System.out.println(HR + "\nWans:\n"
                + "Ok! I've removed " + userTaskList.number(posTask)
                + "\n" + HR);
        userTaskList.removeTask(posTask);
        numTasks--;
    }

    private static void sayGoodbye() {
        String exit = "|  _ \\ \\   / /  ____|"
                + "\n| |_) \\ \\_/ /| |__"
                + "\n|  _ < \\   / |  __|"
                + "\n| |_) | | |  | |____"
                + "\n|____/  |_|  |______";
        System.out.println(HR + "\nWans: \n"
                + exit
                + "\nI'll miss you :( (I really wanna go home)\n" + HR);
        System.exit(0);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        introduceToUser();

        while (true) {
            System.out.println("User: ");
            String userInput = sc.nextLine();
            try {
                emptyInput(userInput);
            } catch (InputEmptyException e) {
                System.out.println(HR + "\nWans:\n"
                        + "You need to input something after " + userInput
                        + "!\n" + HR);
                continue;
            }

            // User can open list of numbered Tasks
            if (userInput.equalsIgnoreCase("list")) {
                listTasks();
                // User can mark Tasks
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                try {
                    notNumInput(userInput, numTasks);
                } catch (NumberFormatException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input a single space, followed by a number after mark"
                            + "!\n" + HR);
                    continue;
                } catch (NotANumMarkingException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input a valid number that exists in your tasks.TaskList!"
                            + "\n" + HR);
                    continue;
                }
                markTasks(userInput);
                // User can unmark tasks
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                try {
                    notNumInput(userInput, numTasks);
                } catch (NumberFormatException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input a single space, followed by a number after mark"
                            + "!\n" + HR);
                    continue;
                } catch (NotANumMarkingException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input a valid number that exists in your tasks.TaskList!"
                            + "\n" + HR);
                    continue;
                }
                unmarkTasks(userInput);
                // User can add a todos to list

            } else if (userInput.toLowerCase().startsWith("todos ")) {
                addTodos(userInput);
                // User can add a deadline task to list

            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                try {
                    missingInputDeadline(userInput);
                } catch(InputEmptyException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input deadline, followed by /by, then the deadline!"
                            + "\n" + HR);
                    continue;
                }
                addDeadlined(userInput);

                // User can add a task with start and end date
            } else if (userInput.toLowerCase().startsWith("event ")) {
                try {
                    missingInputEvent(userInput);
                } catch (InputEmptyException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input event, followed by /from, then your start time, then /to, then " +
                            "your end time!"
                            + "\n" + HR);
                    continue;
                }
                addEvent(userInput);

                // User can say goodbye
            } else if (userInput.equalsIgnoreCase("bye")) {
                sayGoodbye();

                // User can remove tasks
            } else if (userInput.startsWith("remove ")) {
                try {
                    notNumInput(userInput, numTasks);
                } catch (NumberFormatException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input a single space, followed by a number after remove"
                            + "!\n" + HR);
                    continue;
                } catch (NotANumMarkingException e) {
                    System.out.println(HR + "\nWans:\n"
                            + "You need to input a valid number that exists in your tasks.TaskList!"
                            + "\n" + HR);
                    continue;
                }
                removeTask(userInput);

                // Bot doesn't recognize command
            } else {
                System.out.println(HR + "\nWans: \n"
                                + "I'm sorry I'm not that useful I don't know what "
                                + userInput + " means!!!" + "\n" + HR);
            }
        }
    }
}
