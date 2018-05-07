import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConn {
 int errorNr;
 String errorDesc;
 Connection connect = null;
 String oracleURL, userName, passwd;
public OraConn() {
	
}

public  void open() {
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
public  void close() {
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

public  String getOracleURL() {
	return oracleURL;
}
public  Connection getConnect() {
	return connect;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPasswd() {
	return passwd;
}

public void setPasswd(String passwd) {
	this.passwd = passwd;
}

public int getErrorNr() {
	return errorNr;
}

public String getErrorDesc() {
	return errorDesc;
}

public void setConnect(Connection connect) {
	this.connect = connect;
}

public void setOracleURL(String oracleURL) {
	this.oracleURL = oracleURL;
}

}