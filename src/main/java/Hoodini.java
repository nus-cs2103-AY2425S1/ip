import java.util.Scanner;

public class Hoodini {

    public Hoodini() {
        start();

    }

    public void start() {
        System.out.println("Hello I am Hoodini!, how may I assist you?");
        handleInput();

    }

    private void handleInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            Input input = new Input(sc.nextLine());
            input.output();
            if(input.checker()) {
                break;
            }
        }

    }



}
