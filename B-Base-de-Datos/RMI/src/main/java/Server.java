import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server implements Venta {
    private List<Customer> sales;

    public Server() {
        sales = new ArrayList<>();
    }

    /**
     * newSale agrega la venta de la entrada tanto a la memoria como a la base de
     * datos
     * 
     * @param customer es un cliente con todos los datos
     */
    public String newSale(Customer customer) throws java.rmi.RemoteException {
        sales.add(customer);
        VentaManager.insertSale(customer);
        System.out.println("Venta de una entrada " + customer.getTicketType() + " realizada");
        return ("Se ha añadido el cliente " + customer);
    }

    /**
     * getSales devuelve todos las ventas de la base de datos
     */
    public List<Customer> getSales() throws java.rmi.RemoteException {
        System.out.println("Consulta de ventas realizada");
        List<Customer> sale = VentaManager.getSales();
        return sale;
    }

    /**
     * getSalesToday devuelve las ventas que se encuentran en memoria
     */
    public List<Customer> getSalesToday() throws java.rmi.RemoteException {
        System.out.println("Consulta de ventas realizada");
        return sales;
    }

    public static void main(String args[]) {
        try {
            Server obj = new Server();
            Venta stub = (Venta) UnicastRemoteObject.exportObject(obj, 0);
                        
            Registry registry = LocateRegistry.getRegistry("localhost", 3001);
            registry.bind("Venta", stub);
            System.err.println("Server ready");
            while(true){}

        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}