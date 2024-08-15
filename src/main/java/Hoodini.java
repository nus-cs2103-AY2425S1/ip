import java.util.Scanner;

public class Hoodini {

    public Hoodini() {
        start();

    }

    public void start() {
        System.out.println("Hello I am Hoodini!, how may I assist you?");
        handleInput();

    }

    public void end() {
        System.out.println("Bye! Come back to Hoodini soon!");
    }

    private void handleInput() {
        Scanner sc = new Scanner(System.in);
        Storage store = new Storage();
        while(true) {
            Input input = new Input(sc.nextLine());
            if(input.empty()) {
                store.empty();
            } else if(input.exit()) {
                end();
                break;
            } else if(input.list()) {
                store.output();
            } else if(input.marking()) {
                store.mark(input.markint());

            } else if(input.unmarking()) {
                store.unmark(input.unmarkint());

            } else {
                store.store(input);
            }

        }

    }



}
