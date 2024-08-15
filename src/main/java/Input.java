public class Input {

    private String input;
    private boolean done;


    public Input(String input) {
        this.input = input;
        this.done = false;

    }



    public boolean exit() {
        return input.equalsIgnoreCase("bye");
    }

    public boolean list() {
        return input.equalsIgnoreCase("list");
    }

    public boolean empty() {
        return input.isEmpty();
    }

    public boolean marking() {
        return input.startsWith("mark");
    }

    public int markint() {
        return Integer.parseInt(input.substring(5));
    }

    public int unmarkint() {
        return Integer.parseInt(input.substring(7));
    }

    public boolean unmarking() {
        return input.startsWith("unmark");
    }

    public void done() {
        if(this.done) {
            System.out.println("This has already been marked");
        } else {
            this.done = true;
            System.out.print("I have marked the task as done: \n" + this + "\n");
        }
    }

    public void undone() {
        if(!this.done) {
            System.out.println("This has already been unmarked");
        } else {
            this.done = false;
            System.out.print("I have marked the task as undone: \n" + this +"\n");

        }
    }



    @Override
    public String toString() {
        if(done) {
            String str = "[X] " + this.input;
            return str;
        } else {
            String str = "[ ] " + this.input;
            return str;
        }
    }
}
