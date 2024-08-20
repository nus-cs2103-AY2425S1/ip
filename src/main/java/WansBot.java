import java.util.Scanner;

public class WansBot {

    public static void emptyInput(String userInput) throws InputEmptyException{
        if (userInput.split(" ")[0].trim().equalsIgnoreCase("todos") ||
            userInput.split(" ")[0].trim().equalsIgnoreCase("deadline") ||
            userInput.split(" ")[0].trim().equalsIgnoreCase("event") ||
            userInput.split(" ")[0].trim().equalsIgnoreCase("mark") ||
            userInput.split(" ")[0].trim().equalsIgnoreCase("unmark")) {
            throw new InputEmptyException(userInput);
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
                int posTask = Integer.parseInt(userInput.substring(4).strip()) - 1;
                userTaskList.number(posTask).finish();
                System.out.println(hr + "\nWans:"
                        + "\nNice! I've marked\n"
                        + userTaskList.number(posTask).toString()
                        + " as completed\n" + hr);
                // User can unmark tasks
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                int posTask = Integer.parseInt(userInput.substring(6).strip()) - 1;
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
