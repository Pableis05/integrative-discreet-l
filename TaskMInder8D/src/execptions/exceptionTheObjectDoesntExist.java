package execptions;

public class exceptionTheObjectDoesntExist extends Exception{
    public exceptionTheObjectDoesntExist(String key){
        super("The identity with the key "+key+" doesn't exist");
    }
    public String getMessage(){
        return super.getMessage();
    }
}
