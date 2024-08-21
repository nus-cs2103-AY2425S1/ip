public class NotStringException extends Exception{
    String desc = "";
    public NotStringException(String desc){
        this.desc = desc;
    }

    public void print(){
        System.out.println("The input is not a String!" + desc);
    }
}
