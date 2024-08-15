public class Storage {
    private Input[] input;
    private static int counter = 0;

    public Storage() {
        this.input = new Input[100];
    }

    public void store(Input input) {
        this.input[counter] = input;
        counter++;
        System.out.println("added: " + input);

    }

    public void output() {
        for(int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + input[i]);
        }
    }

    public void mark(int i) {
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input[i-1].done();

        }


    }

    public void empty() {
        System.out.println("Enter a valid input");
    }

    public void unmark(int i) {
        if (i > counter) {
            System.out.println("Invalid number, enter a valid number to mark");
        } else {
            input[i-1].undone();

        }

    }

}
