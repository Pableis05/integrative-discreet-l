package exceptions;

public class exceptionThisDataStructureIsVoid extends Exception{

    public exceptionThisDataStructureIsVoid(){
        super("The data structure is empty");

    }
    public String getMessage(){
        return super.getMessage();
    }
}
