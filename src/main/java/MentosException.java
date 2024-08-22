public class MentosException extends Exception{
    protected String err;

    public MentosException(String err){
        this.err = err;
    }

    @Override
    public String toString(){
        return this.err;
    }
}
