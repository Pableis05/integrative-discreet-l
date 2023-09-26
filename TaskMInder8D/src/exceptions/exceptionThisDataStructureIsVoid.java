package exceptions;

public class exceptionThisDataStructureIsVoid extends Exception{

    public exceptionThisDataStructureIsVoid(){
        super("The list is void");

    }
    public String getMessage(){
        return super.getMessage();
    }
}
