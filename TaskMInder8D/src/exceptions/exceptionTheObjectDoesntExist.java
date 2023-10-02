package exceptions;

public class exceptionTheObjectDoesntExist extends Exception{
    public exceptionTheObjectDoesntExist(String key){
        super("The identity with "+key+" doesn't exist");
    }
    public String getMessage(){
        return super.getMessage();
    }
}
