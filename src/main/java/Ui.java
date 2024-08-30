public class Ui {
    static String line = "    _____________________________________________";

    public Ui(){

    }
    public void welcome(){
        System.out.println(line + "\n" + "    Hello! I'm Tars\n" + "    What can I do for you" + "\n" + line);
    }

    /**
     * @return
     */
    public void bye(){
        System.out.println(line + "\n" + "    Bye. Hope to see you again soon!" + "\n" + line);
    }

}
