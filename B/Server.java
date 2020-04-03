import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server implements Venta{
    private List<Customer> sales;
    public Server(){
        sales = new ArrayList<>();
    }

    public String newSale(Customer customer)throws java.rmi.RemoteException{
        sales.add(customer);
        System.out.println("Venta de una entrada " + customer.getTicketType() + " realizada");
        return("Se ha añadido el cliente " + customer);
    }

    public List<Customer> getSales() throws java.rmi.RemoteException{
        System.out.println("Consulta de ventas realizada");
        return sales;
    }

    public static void main(String args[]){
        try{
            Server obj = new Server();
            Venta stub = (Venta)UnicastRemoteObject.exportObject(obj, 0);
                        
            Registry registry = LocateRegistry.getRegistry("localhost", 3001);
            registry.bind("Venta", stub);
            System.err.println("Server ready");

        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}