import org.sql2o.Sql2o;

// Clase para la base de datos
public class Sql2oDAO {
    protected static Sql2o sql2o;

    public static Sql2o getSql2o() {
        if (sql2o == null) {
            String path = Server.class.getResource("/DB/ventas.db").getPath();
            String myDataSource = "jdbc:sqlite:"+path;
            sql2o = new Sql2o(myDataSource,null,null);
        }
        return sql2o;
    }
}