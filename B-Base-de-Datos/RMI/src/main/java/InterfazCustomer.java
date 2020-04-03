import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazCustomer extends Serializable{
    public String getSurname() throws RemoteException;
    public String getName() throws RemoteException;
    public String getDNIType() throws RemoteException;
    public long getDNINumber() throws RemoteException;
    public String getTicketType() throws RemoteException;
    public double getTticketPrice() throws RemoteException;

    public void setSurname(String surname) throws RemoteException;
    public void setName(String name) throws RemoteException;
    public void setDNIType(String type) throws RemoteException;
    public void setDNINumber(long number) throws RemoteException;

    public String toString();
}