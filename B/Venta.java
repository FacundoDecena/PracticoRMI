import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface Venta extends Remote {
    String newSale(Customer customer) throws RemoteException;
    List<Customer> getSales() throws RemoteException;
}