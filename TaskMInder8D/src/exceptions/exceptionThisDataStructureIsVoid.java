package exceptions;

public class exceptionThisDataStructureIsVoid extends Exception{

    public exceptionThisDataStructureIsVoid(){
        super("The data is empty");

    }
    public String getMessage(){
        return super.getMessage();
    }
}
