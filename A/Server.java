import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello{
    public Server(){}

    public String sayHello(){
        return("Hello World!");
    }

    public static void main(String args[]){
        try{
            Server obj = new Server();
            Hello stub = (Hello)UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 3001);
            registry.bind("Hello", stub);
            System.err.println("Server ready");

        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}