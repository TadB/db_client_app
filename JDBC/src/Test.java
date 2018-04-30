public class Test {

	public static void main(String[] args) {
		String oracleURL="jconnectionc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
		String userName="epolansk";
		String passwd="epolansk";

		OraConn connection=new OraConn();
		connection.setOracleURL(oracleURL);
		connection.setPasswd(passwd);
		connection.setUserName(userName);

        // test otwierania pliku
		connection.open();
		//utworzenie obiektu obslugujacego operacje na bazie danych 
		EmployeesDAL db= new EmployeesDAL(connection);
		//test metody pobierania wszystkich rekordow z tabeli employees
		Vector<Employee> outData=db.getEmployees();
		Iterator<Employee> iter=outData.iterator();
		while(iter.hasNext()){
			System.out.println(
					//TODO: wpisac brakujaca funkcje wypisujaca rekordy
					);
		}
        //test metody dodawania uzytkownika

		

        // test zamykania pliku
		connection.close();



}}
