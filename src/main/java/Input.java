public class Input {

    private String input;
    private boolean done;


    public Input(String input) {
        this.input = input;
        this.done = false;

    }

    public boolean empty() {
        return this.input.isEmpty();
    }

    public void markdone() {
        this.done = true;
    }



    public void done() {
        if(this.done) {
            System.out.println("This has already been marked");
        } else {
            this.done = true;
            System.out.print("I have marked the task as done:\n" + this + "\n");
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
        if (done) {
            String str = "[X] " + this.input;
            return str;
        } else {
            String str = "[ ] " + this.input;
            return str;
        }
    }
}
