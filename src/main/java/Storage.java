public class Storage {
    private Input[] input;
    private static int counter = 0;

    public Storage() {
        this.input = new Input[100];
    }

    public void store(Input input) {
        if(input.empty()) {
            System.out.println("Whoopsie! Please enter a task");
        } else {
            this.input[counter] = input;
            counter++;
            System.out.println("Noted. I have added this task:");
            System.out.println(input);
            System.out.println("You have " + counter + " tasks in the list.");
        }


    }

    public void output() {
        System.out.println("Here are the list of tasks that needs to be completed: ");
        for(int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + input[i]);
        }
    }

    public void mark(String str) {
        int i = Integer.parseInt(str.substring(5));
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input[i-1].done();

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
            input[i-1].undone();

        }

    }

}
