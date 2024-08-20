
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

    public static void main(String[] args) {
        //Initialise the chatbot and scanner
        BeeBoo beeBoo = new BeeBoo();
        Scanner input = new Scanner(System.in);

        //Getting user input
        beeBoo.chatBox("Hello! I'm BeeBoo\nWhat can i do for you?");
        String text = input.nextLine().trim().toLowerCase();

        //Gets user input till user types bye
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                //Produces list when user types list
                beeBoo.chatBox(beeBoo.produceList());
            } else if (text.startsWith("mark")) {
                //Getting the index from the user input
                String number = "";
                for (int i = 5; i < text.length(); i++) {
                    number = number + text.charAt(i);
                }
                int index = Integer.parseInt(number);
                //Marking item at index as completed
                beeBoo.markDone(index - 1);
            } else if (text.startsWith("unmark")) {
                String number = "";
                for (int i = 7; i < text.length(); i++) {
                    number = number + text.charAt(i);
                }
                int index = Integer.parseInt(number);
                //Marking item at index as not done
                beeBoo.unmarkDone(index - 1);

            } else if (text.startsWith("deadline")) {
                //Getting the ending index of the description ending at /
                int descriptionEnd = text.indexOf('/');
                //Creating a substring of description
                String description = text.substring(9, descriptionEnd - 1);
                //Creating substring of date
                String date = text.substring(descriptionEnd + 1, text.length());
                //Adding Deadline to list and then chatboxing it
                beeBoo.chatBox(beeBoo.addList(new Deadlines(description, date)));

            } else if (text.startsWith("event")) {
                //Getting the ending index of the description ending at /
                int descriptionEnd = text.indexOf('/');
                //Creating substring of description
                String description = text.substring(6, descriptionEnd - 1);

                //Creating Date substring
                String dateSubstring = text.substring(descriptionEnd + 6, text.length());

                int startDateEnd = dateSubstring.indexOf('/');
                //Creating startDate substring
                String startDate = dateSubstring.substring(0, startDateEnd - 1);
                //Creating endDate substring
                String endDate = dateSubstring.substring(startDateEnd + 4, dateSubstring.length());

                //Adding Event to list and then chatboxing it
                beeBoo.chatBox(beeBoo.addList(new Events(description, startDate, endDate)));

            } else {
                //Creating substring of description
                String description = text.substring(5, text.length());
                //Adding ToDo to list and then chatboxing it
                beeBoo.chatBox(beeBoo.addList(new ToDos(description)));
            }
            text = input.nextLine().trim().toLowerCase();
        }
        beeBoo.chatBox("Bye. Hope to see you again soon!");
        input.close();
    }
}
