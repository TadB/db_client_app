import java.util.Vector;
public class Test {
	public static void main(String[] args) {
		String oracleURL="jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
		String userName="epolansk";
		String passwd="epolansk";

		OraConn connection=new OraConn();
		connection.setOracleURL(oracleURL);
		connection.setPasswd(passwd);
		connection.setUserName(userName);

        // test otwierania pliku
		connection.open();
		//utworzenie obiektu obslugujacego operacje na bazie danych 
		EmployeesDAL db= new EmployeesDAL(connection.getConnect());
		//test metody pobierania wszystkich rekordow z tabeli employees
		Vector<Employee> outData=db.getEmployees();
//		Iterator<Employee> iter=outData.iterator();
		int iter=0;
		printDataBase(outData);
		//test metody pobierania danych wybranego rekordu z bazy danych szukajacego po podaniu id
		System.out.println("metoda pobierania rekorku po podaniu id");
		outData=db.getEmployeeByEmployeeId(167);
		printDataBase(outData);

        //test metody dodawania uzytkownika

		

        // test zamykania pliku
		connection.close();



}


public static void printDataBase(Vector<Employee> outData){
	//TODO: przekazac przez referencje wektor
	int iter=0;
		while(iter<outData.size()){
			System.out.println(
					outData.get(iter).getAll()
					);
			iter++;
		}
}
};
