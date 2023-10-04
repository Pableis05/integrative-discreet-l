package exceptions;

public class exceptionNotDateFormat extends Exception {

    public exceptionNotDateFormat(String date){
        super("The date "+date+" is not a valid date");
    }
    public String getMessage(){
        return super.getMessage();
    }


}
