import java.util.Scanner;

public class WansBot {

    public static void emptyInput(String userInput) throws InputEmptyException {
        if (userInput.strip().equalsIgnoreCase("todos") ||
            userInput.strip().equalsIgnoreCase("deadline") ||
            userInput.strip().equalsIgnoreCase("event") ||
            userInput.strip().equalsIgnoreCase("mark") ||
            userInput.strip().equalsIgnoreCase("unmark")) {
            throw new InputEmptyException(userInput);
        }
    }

    public static void notNumInput(String userInput, int taskListSize) throws NumberFormatException
    , NotANumMarkingException {
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
        }
    }

    public static void missingInputDeadline(String userInput) {
        String[] splitUser = userInput.split( " /by ", 2);
        if (splitUser.length < 2) {
            throw new InputEmptyException(userInput, "/by");
        }
    }

    public static void missingInputEvent(String userInput) {
        String[] splitUserStartDate = userInput.split(" /from ", 3);
        if (splitUserStartDate.length < 2) {
            throw new InputEmptyException(userInput, "/from");
        }
        String[] splitUserEndDate = splitUserStartDate[1].split( " /to ", 2);
        if (splitUserEndDate.length < 2) {
            throw new InputEmptyException(userInput, "/to");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList userTaskList = new TaskList();
        String logo ="                 __"
                + "\n|  |  /\\  |\\ | /__` "
                + "\n|/\\| /~~\\ | \\| .__/\n";
        String hr = "----------------------------------------------------------------------";
        int numTasks = 0;

        System.out.println(hr + "\nWans:\n"
                + "Hey, I'm\n"
                + logo
                + "\nCan I help? (I can only manage a todo list so...)\n" + hr);

        while (true) {
            System.out.println("User: ");
            String userInput = sc.nextLine();
            try {
                emptyInput(userInput);
            } catch (InputEmptyException e) {
                System.out.println(hr + "\nWans:\n"
                        + "You need to input something after " + userInput
                        + "!\n" + hr);
                continue;
            }

            // User can open list of numbered Tasks
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(hr + "\nWans:"
                        + "\nHere are your tasks!\n"
                        + userTaskList.toString());
                System.out.println("You have " + numTasks + " tasks!" + "\n" + hr);
                // User can mark Tasks
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                try {
                    notNumInput(userInput, numTasks);
                } catch (NumberFormatException e) {
                    System.out.println(hr + "\nWans:\n"
                            + "You need to input a single space, followed by a number after mark"
                            + "!\n" + hr);
                    continue;
                } catch (NotANumMarkingException e) {
                    System.out.println(hr + "\nWans:\n"
                            + "You need to input a valid number that exists in your TaskList!"
                            + "\n" + hr);
                    continue;
                }
                int posTask = Integer.parseInt(userInput.substring(5)) - 1;
                userTaskList.number(posTask).finish();
                System.out.println(hr + "\nWans:"
                        + "\nNice! I've marked\n"
                        + userTaskList.number(posTask).toString()
                        + " as completed\n" + hr);
                // User can unmark tasks
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                try {
                    notNumInput(userInput, numTasks);
                } catch (NumberFormatException e) {
                    System.out.println(hr + "\nWans:\n"
                            + "You need to input a single space, followed by a number after mark"
                            + "!\n" + hr);
                    continue;
                } catch (NotANumMarkingException e) {
                    System.out.println(hr + "\nWans:\n"
                            + "You need to input a valid number that exists in your TaskList!"
                            + "\n" + hr);
                    continue;
                }
                int posTask = Integer.parseInt(userInput.substring(7)) - 1;
                userTaskList.number(posTask).unfinish();
                System.out.println(hr + "\nWans:"
                        + "\nOkay, so you lied! I've marked\n"
                        + userTaskList.number(posTask).toString()
                        + " as uncompleted\n" + hr);
                // User can add a todos to list
            } else if (userInput.toLowerCase().startsWith("todos ")) {
                Todos newTodo = new Todos(userInput.substring(5));
                userTaskList.add(newTodo);
                System.out.println(hr + "\nWans:\n"
                        + "Ok! I've added " + newTodo.toString()
                        + "\n" + hr);
                numTasks++;
                // User can add a deadline task to list
            } else if (userInput.toLowerCase().startsWith("deadline ")) {
                try {
                    missingInputDeadline(userInput);
                } catch(InputEmptyException e) {
                    System.out.println(hr + "\nWans:\n"
                            + "You need to input deadline, followed by /by, then the deadline!"
                            + "\n" + hr);
                    continue;
                }
                String[] splitUser = userInput.split( " /by ", 2);
                Deadlined newDeadlined = new Deadlined(splitUser[0].substring(8)
                , splitUser[1]);
                userTaskList.add(newDeadlined);
                System.out.println(hr + "\nWans:\n"
                        + "Ok! I've added " + newDeadlined.toString()
                        + "\n" + hr);
                numTasks++;
                // User can add a task with start and end date
            } else if (userInput.toLowerCase().startsWith("event ")) {
                try {
                    missingInputEvent(userInput);
                } catch (InputEmptyException e) {
                    System.out.println(hr + "\nWans:\n"
                            + "You need to input event, followed by /from, then your start time, then /to, then " +
                            "your end time!"
                            + "\n" + hr);
                    continue;
                }
                String[] splitUserStartDate = userInput.split(" /from ", 3);
                String[] splitUserEndDate = splitUserStartDate[1].split( " /to ", 2);
                Events newEvent = new Events(splitUserStartDate[0].substring(5),
                        splitUserEndDate[0], splitUserEndDate[1]);
                userTaskList.add(newEvent);
                System.out.println(hr + "\nWans:\n"
                        + "Ok! I've added " + newEvent.toString()
                        + "\n" + hr);
                numTasks++;
                // User can say goodbye
            } else if (userInput.equalsIgnoreCase("bye")) {
                String exit = "|  _ \\ \\   / /  ____|"
                        + "\n| |_) \\ \\_/ /| |__"
                        + "\n|  _ < \\   / |  __|"
                        + "\n| |_) | | |  | |____"
                        + "\n|____/  |_|  |______";
                System.out.println(hr + "\nWans: \n"
                        + exit
                        + "\nI'll miss you :( (I really wanna go home)\n" + hr);
                System.exit(0);
                // Bot doesn't recognize the command
            } else {
                System.out.println(hr + "\nWans: \n"
                                + "I'm sorry I'm not that useful I don't know what "
                                + userInput + " means!!!" + "\n" + hr);
            }
        }
    }
}
