import java.util.ArrayList;

public class Storage {
    private ArrayList<Input> input;
    private static int counter = 0;

    public Storage() {
        this.input = new ArrayList<>();
    }

    public void store(Input input) {
        if(input.empty()) {
            System.out.println("Whoopsie! Please enter a task");
        } else {
            this.input.add(input);
            counter++;
            System.out.println("Noted. I have added this task:");
            System.out.println(input);
            System.out.println("You have " + counter + " tasks in the list.");
        }


    }

    public void output() {
        System.out.println("Here are the list of tasks that needs to be completed: ");
        for(int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + input.get(i));
        }
    }

    public void mark(String str) {
        int i = Integer.parseInt(str.substring(5));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input.get(i-1).done();

        }


    }

    public void delete(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to delete");
        } else {
            System.out.println("Noted. I have deleted this task:");
            System.out.println(input.get(i-1));
            input.remove(i-1);
            counter--;
            System.out.println("You have " + counter + " tasks in the list.");
        }
    }

    public void empty() {

        System.out.println("Whoopsie! Please enter a task");
    }

    public void unmark(String str) {
        int i = Integer.parseInt(str.substring(7));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input.get(i-1).undone();

        }

    }

}
