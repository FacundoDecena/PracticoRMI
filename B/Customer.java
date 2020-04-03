import java.rmi.RemoteException;

public class Customer implements InterfazCustomer{

    private static final long serialVersionUID = 4843287082162759756L;
    private String surname;
    private String name;
    private String DNIType;
    private long DNINumber;
    private String ticketType;
    private double ticketPrice;

    public Customer(String surname, String name, String type, long number, String ticket, double price) throws RemoteException{
        this.surname = surname;
        this.name = name;
        this.DNIType = type;
        this.DNINumber = number;
        this.ticketType = ticket;
        this.ticketPrice = price;
    }

    public String getSurname()throws RemoteException{return this.surname;}
    public String getName()throws RemoteException{return this.name;}
    public String getDNIType()throws RemoteException{return this.DNIType;}
    public long getDNINumber()throws RemoteException{return this.DNINumber;}
    public String getTicketType()throws RemoteException{return this.ticketType;}
    public double getTticketPrice()throws RemoteException{return this.ticketPrice;}

    public void setSurname(String surname)throws RemoteException{this.surname = surname;}
    public void setName(String name)throws RemoteException{this.name = name;}
    public void setDNIType(String type)throws RemoteException{this.DNIType = type;}
    public void setDNINumber(long number)throws RemoteException{this.DNINumber = number;}

    public String toString(){
        return this.surname + " " + this.name + " " + this.DNIType + " " + this.DNINumber + " Entrada " + 
        this.ticketType + " de valor $" + this.ticketPrice;
    }
}