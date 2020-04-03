import java.util.List;

import org.sql2o.Connection;

// Clase para interactuar con la base de datos
public class VentaManager {
    public static void insertSale(Customer c){
        String insertSql = "insert into VENTA(SURNAME, NAME, DNITYPE, DNINUMBER, TICKETTYPE, TICKETPRICE) " +
        "values (:surname, :name, :DNIType, :DNINumber, :ticketType, :ticketPrice)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(insertSql)
                .addParameter("surname", c.getSurname())
                .addParameter("name", c.getName())
                .addParameter("DNIType", c.getDNIType())
                .addParameter("DNINumber", c.getDNINumber())
                .addParameter("ticketType", c.getTicketType())
                .addParameter("ticketPrice", c.getTticketPrice())
                .executeUpdate()
                .getKey();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static List<Customer> getSales(){
        String sql =
            "SELECT SURNAME, NAME, DNITYPE, DNINUMBER, TICKETTYPE, TICKETPRICE " +
            "FROM VENTA";
    
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Customer.class);
        }
    }
}