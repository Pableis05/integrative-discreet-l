package exceptions;

/**
 * The code defines a custom exception class called `exceptionThisDataStructureIsVoid` that is used to handle cases where a data structure is empty.
 */
public class exceptionThisDataStructureIsVoid extends Exception{

    /**The code is defining a custom exception class called `exceptionThisDataStructureIsVoid`. */ 
    public exceptionThisDataStructureIsVoid(){
        super("The data is empty");

    }
   /**
    * The function returns the message associated with an exception.
    * 
    * @return The getMessage() method is returning the message from the superclass.
    */
    public String getMessage(){
        return super.getMessage();
    }
}
