import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {
    private Client() {
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? "localhost" : args[0];
        Scanner scan = new Scanner(System.in);
        Customer cliente;

        try {
            Registry registry = LocateRegistry.getRegistry(host, 3001);
            Venta stub = (Venta) registry.lookup("Venta");
            
            int option = 0;
            System.out.println("======== Ventas ========");
            System.out.println("1 - Platea $500");
            System.out.println("2 - General $300");
            System.out.println("3 - Preferencial $1000");
            System.out.println("4 - Ver ventas");
            System.out.println("Cualquier tecla - Salir");
            try {
                option = scan.nextInt();
            } catch (Exception e) {
                option = 0;
            } finally {
                switch (option) {
                    case 1:
                        cliente = buyTicket("Platea", 500.0);
                        stub.newSale(cliente);
                        break;
                    case 2:
                        cliente = buyTicket("General", 300.0);
                        stub.newSale(cliente);
                        break;
                    case 3:
                        cliente = buyTicket("Preferencial", 1000.0);
                        stub.newSale(cliente);
                        break;
                    case 4: 
                        List<Customer> ventas = stub.getSales();
                        System.out.println("Ventas realizadas");
                        if(ventas.size() > 0){
                            ventas.forEach(venta->{
                                System.out.println(venta);
                            });
                        } else
                            System.out.println("No hay ventas");
                }
            }
            scan.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static Customer buyTicket(String ticket, double price) {
        String nombre, apellido, tipoDNI;
        long numeroDNI = 0;
        boolean error = true;
        Scanner scan = new Scanner(System.in);

        System.out.println("======== Ventas ========");
        System.out.println("Paso 1 - Apellido");
        nombre = scan.nextLine();
        System.out.println("Paso 2 - Nombre");
        apellido = scan.nextLine();
        System.out.println("Paso 3 - Tipo de documento");
        tipoDNI = scan.nextLine();
        while (error) {
            System.out.println("Paso 4 - Numero de documento");
            try {
                numeroDNI = scan.nextLong();
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un numero, sin puntos ni espacios");
                scan.nextLine();
            }
        }
        Customer c;
        try {
            c = new Customer(apellido, nombre, tipoDNI, numeroDNI, ticket, price);
        } catch (RemoteException e) {
            e.printStackTrace();
            c = null;
        }
        scan.nextLine();
        scan.close();
        return c;
    }

}