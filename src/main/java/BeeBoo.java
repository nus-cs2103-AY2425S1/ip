
import java.util.ArrayList;
import java.util.Scanner;

public class BeeBoo {

    //Arraylist for the tasklist
    ArrayList<Tasks> list;

    //Initialise tasklist when beeboo is created
    public BeeBoo() {
        list = new ArrayList<>();
    }

    //Adding to the tasklist
    private String addList(Tasks task) {
        list.add(task);
        return ("added: " + task + "\n" + "You have " + list.size() + " tasks in the list");
    }

    //Marks item at specified index as done
    private void markDone(int index) {
        Tasks task = list.get(index);
        task.markDone();
        chatBox("Nice! I've marked this task as done:\n" + task);
    }

    //Unchecks item and mark as not done
    private void unmarkDone(int index) {
        Tasks task = list.get(index);
        task.unmarkDone();
        chatBox("OK, I've marked this task as not done yet:\n" + task);
    }

    //Deletes item
    private void deleteItem(int index) {
        Tasks item = list.get(index - 1);
        list.remove(index - 1);
        chatBox("Ok i have removed the following item\n" + item + "\n" + "You have " + list.size() + " tasks left");
    }

    //Returns list when prompted
    private String produceList() {
        String result = "";
        for (Tasks task : list) {
            result = result + (list.indexOf(task) + 1) + ". " + " " + task + "\n";
        }
        return result;
    }

    //Create chatbox
    private void chatBox(String str) {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(str);
        System.out.println();
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    //To create ToDos
    private void createToDo(String text) throws NoDescriptionException {
        String description = text.substring(4).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }
        chatBox(addList(new ToDos(description)));
    }

    //To create Deadlines
    private void createDeadlines(String text) throws NoDescriptionException {
        int descriptionEnd = text.indexOf('/');
        String description = text.substring(9, descriptionEnd - 1).trim();
        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }
        String date = text.substring(descriptionEnd + 1);
        chatBox(addList(new Deadlines(description, date)));
    }

    //Creates events
    private void createEvent(String text) throws NoDescriptionException {
        int descriptionEnd = text.indexOf('/');
        String description = text.substring(6, descriptionEnd - 1);
        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }
        String dateSubstring = text.substring(descriptionEnd + 6);
        int startDateEnd = dateSubstring.indexOf('/');
        String startDate = dateSubstring.substring(0, startDateEnd - 1);
        String endDate = dateSubstring.substring(startDateEnd + 4);
        chatBox(addList(new Events(description, startDate, endDate)));
    }

    //Checks the command the user uses and returns a number for chatbot to check against
    private int checkCommand(String text) {
        if (text.startsWith("mark")) {
            return 1;
        } else if (text.startsWith("unmark")) {
            return 2;
        } else if (text.equals("list")) {
            return 3;
        } else if (text.startsWith("delete")) {
            return 4;
        } else if (text.startsWith("deadline") || text.startsWith("event") || text.startsWith("todo")) {
            return 5;
        } else {
            return -1;
        }
    }

    //Creates the tasks
    private void createTasks(String text) throws NoDescriptionException {
        if (text.startsWith("deadline")) {
            createDeadlines(text);
        } else if (text.startsWith("event")) {
            createEvent(text);
        } else {
            createToDo(text);
        }
    }

    //Handles the command the user uses
    private void handleCommands(String text) throws InvalidCommandException, NoDescriptionException {
        int commandCheck = checkCommand(text);
        if (commandCheck == 1) {
            //Command is mark so chatbot marks the indexed item
            String number = "";
            for (int i = 5; i < text.length(); i++) {
                number = number + text.charAt(i);
            }
            int index = Integer.parseInt(number);
            markDone(index - 1);
        } else if (commandCheck == 2) {
            //Command is unmark so the chatbot unmarks the indexed item
            String number = "";
            for (int i = 7; i < text.length(); i++) {
                number += text.charAt(i);
            }
            int index = Integer.parseInt(number);
            unmarkDone(index - 1);
        } else if (commandCheck == 3) {
            //Command is list so the chatbot lists out the items
            chatBox(produceList());
        } else if (commandCheck == 4) {
            //Command is delete so chatpot deletes indexed item
            String number = text.substring(6, text.length()).trim();
            int index = Integer.parseInt(number);
            deleteItem(index);
        } else if (commandCheck == 5) {
            //Command is task creation so the chatbot creates tasks
            createTasks(text);
        } else {
            //Unknown command so the chatbot throws invalid command exception
            throw new InvalidCommandException("Invalid Command");
        }
    }

    public static void main(String[] args) {

        //Initialise the chatbot
        BeeBoo beeBoo = new BeeBoo();
        Scanner input = new Scanner(System.in);
        String text;

        beeBoo.chatBox("Hello! I'm BeeBoo\nWhat can i do for you?");
        text = input.nextLine().trim().toLowerCase();

        //Prompts user while user doesn't enter bye
        while (!text.equals("bye")) {
            try {
                beeBoo.handleCommands(text);
            } catch (InvalidCommandException e) {
                beeBoo.chatBox("Invalid Command!Me no understand");
            } catch (NoDescriptionException e) {
                beeBoo.chatBox("No description silly");
            }

            text = input.nextLine().trim().toLowerCase();
        }
        beeBoo.chatBox("Bye. Hope to see you again soon!");
        input.close();
    }
}