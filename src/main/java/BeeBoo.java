
import java.util.ArrayList;
import java.util.Scanner;

public class BeeBoo {

    //Arraylist for the tasklist
    ArrayList<Tasks> list;

    //Initalise tasklist when beeboo is created
    public BeeBoo() {
        list = new ArrayList<>();
    }

    //Adding to the tasklist
    private String addList(Tasks task) {
        list.add(task);
        return "added: " + task;
    }

    //Marks item of specified index as done
    private void markDone(int index) {
        Tasks task = list.get(index);
        task.markDone();
        chatBox("Nice! I've marked this task as done:\n" + task.completionIcon() + task);
        ;
    }

    //Marks item of specified index as not done
    private void unmarkDone(int index) {
        Tasks task = list.get(index);
        task.unmarkDone();
        chatBox("OK, I've marked this task as not done yet:\n" + task.completionIcon() + task);
    }

    //Returns list when prompted
    private String produceList() {
        String result = "";
        for (Tasks task : list) {
            result = result + (list.indexOf(task) + 1) + ". " + task.completionIcon() + " " + task + "\n";
        }
        return result;
    }

    //Creating chatbox

    private void chatBox(String str) {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("" + str);
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
                //Getting the index from the user input
                String number = "";
                for (int i = 7; i < text.length(); i++) {
                    number = number + text.charAt(i);
                }
                int index = Integer.parseInt(number);
                //Marking item at index as completed
                beeBoo.unmarkDone(index - 1);
            } else {
                //Add item to list
                beeBoo.chatBox(beeBoo.addList(new Tasks(text)));
            }
            text = input.nextLine().trim().toLowerCase();
        }
        beeBoo.chatBox("Bye. Hope to see you again soon!");
        input.close();
    }
}
