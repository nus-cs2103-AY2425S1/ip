package tars;

public class Ui {
    static String LINE = "    _____________________________________________";

    public Ui(){

    }
    public void welcome(){
        System.out.println(LINE + "\n" + "    Hello! I'm Tars\n"
                            + "    What can I do for you" + "\n" + LINE);
    }

    /**
     * @return
     */
    public void bye(){
        System.out.println(LINE + "\n" + "    Bye. Hope to see you again soon!" + "\n" + LINE);
    }

}
