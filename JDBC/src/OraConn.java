import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {
static int errorNr;
static String errorDesc;
static Connection connect = null;
static String oracleURL, userName, passwd;
public OraConn() {
	
}

public static void open() {
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
                    connect = DriverManager.getConnection(oracleURL, userName, passwd);
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
public static void close() {
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

}
//getters and setters

public static String getOracleURL() {
	return oracleURL;
}
public static Connection getConnect() {
	return connect;
}

public static void setOracleURL(String oracleURL) {
	OraConn.oracleURL = oracleURL;
}
public static String getUserName() {
	return userName;
}
public static void setUserName(String userName) {
	OraConn.userName = userName;
}
public static String getPasswd() {
	return passwd;
}
public static void setPasswd(String passwd) {
	OraConn.passwd = passwd;
}
public static int getErrorNr() {
	return errorNr;
}
public static String getErrorDesc() {
	return errorDesc;
}
}
