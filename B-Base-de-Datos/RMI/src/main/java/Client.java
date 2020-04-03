import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Client {
    private Client() {}
    public static void main(String[] args) {
        String host = (args.length < 1) ? "localhost" : args[0];
        try {
            System.out.printf("\n\n\n\n\n");
            Scanner scan = new Scanner(System.in);
            Customer cliente;
            Registry registry = LocateRegistry.getRegistry(host, 3001);
            Venta stub = (Venta) registry.lookup("Venta");
            // Respuesta al alta de una venta por el servidor
            String newSaleMessage;
            // Opcion elegida
            int option = 0;
            System.out.println("======== Ventas ========");
            System.out.println("1 - Platea $500");
            System.out.println("2 - General $300");
            System.out.println("3 - Preferencial $1000");
            System.out.println("4 - Ver ventas \"hoy\"");
            System.out.println("5 - Ver todas las ventas");
            System.out.println("Cualquier tecla - Salir");

            try {option = scan.nextInt();}

            catch (Exception e) {option = 0;}

            finally {
                switch (option) {
                    case 1:
                        cliente = buyTicket("Platea", 500.0);
                        newSaleMessage = stub.newSale(cliente);
                        System.out.println(newSaleMessage);
                        break;
                    case 2:
                        cliente = buyTicket("General", 300.0);
                        newSaleMessage = stub.newSale(cliente);
                        System.out.println(newSaleMessage);
                        break;
                    case 3:
                        cliente = buyTicket("Preferencial", 1000.0);
                        newSaleMessage = stub.newSale(cliente);
                        System.out.println(newSaleMessage);
                        break;
                    case 4:
                        // Las ventas "de hoy" son las que estan en memoria
                        List<Customer> ventas = stub.getSalesToday();
                        System.out.println("Ventas realizadas hoy");
                        if(ventas.size() > 0){
                            ventas.forEach(venta->{
                                System.out.println(venta);
                            });
                        } else
                            System.out.println("No hay ventas");break;
                    case 5: 
                        // Las ventas totales son todas las que estan en la base de datos
                        // esto incluye las de hoy
                        List<Customer> ventasTotales = stub.getSales();
                        System.out.println("Ventas total realizadas");
                        if(ventasTotales.size() > 0){
                            ventasTotales.forEach(venta->{
                                System.out.println(venta);
                            });
                        } else
                            System.out.println("No hay ventas");
                }
                System.out.printf("\n\n\n\n\n");
            }
            scan.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    // "Interfaz grafica" para obtener datos del cliente
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