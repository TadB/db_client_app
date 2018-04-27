import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {
static int errorNr;
static String errorDesc;
//TODO: co to znaczy ze jest static zmienna? -> sprawdz to
public static void open(Connection connect, String url, String user, String pass) {
    if (connect == null) {
            try{
                    //rejestracja sterownika Oracle JDBC
                    Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ex) {
                    System.out.println("Brak sterownika Oracle JDBC.");
            }
            System.out.println("sterowni oracle jdbc zostal zarejestrowany");

            //nawiazanie polaczenia:
            try{
                    // String oracleURL=url;
                    // String userName=user;
                    // String passwd=pass;
                    connect = DriverManager.getConnection(url, user, pass);
            } catch(SQLException ex) {
                    System.out.println("Błąd połączenia z bazą danych "+ ex.getMessage());
                    errorNr = ex.getErrorCode();
                    errorDesc= ex.getMessage();
                    return;
            }
            System.out.println("polaczenie jest otwarte");
    }
    else {
            System.out.println("polaczenie jest juz otwarte");
            return;
    }


}
public static void close(Connection connect) {
    if (connect != null) {
            try {
                    //zamkniecie polaczenia
                    connect.close();
                    System.out.println("polaczenie jest zamkniete");
            } catch(SQLException ex) {
                    errorNr = ex.getErrorCode();
                    errorDesc= ex.getMessage();
                    ex.printStackTrace();
                    return;
            }
    }
    else {
            System.out.println("polaczenie nie jest otwarte");
            return;
    }

};
}
