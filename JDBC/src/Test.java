import java.time.LocalDate;
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
		printDataBase(outData);
		//test metody pobierania danych wybranego rekordu z bazy danych szukajacego po podaniu id
		System.out.println("metoda pobierania rekorku po podaniu id");
		outData=db.getEmployeeByEmployeeId(167);
		printDataBase(outData);

        //test metody dodawania uzytkownika

		//utworzenie nowego rekordu 
		Employee emp=new Employee();
		//TODO: generowanie unikalnego klucza employeeId - nie moze zostac w taki sposob -> teraz tylko do pierwszych testow, nalezy to poprawic
		int empID=300;
		emp.setEmployeeId(empID);
		emp.setFirstName("Janusz");
		emp.setLastName("Dodany");
		emp.setEmail("JDODANY");
		emp.setPhone("600300100");
		LocalDate date=LocalDate.parse("2018-04-01");
		emp.setHireDate(date);
		emp.setJobId("IT_PROG");
		emp.setSalary(2500);
		emp.setManagerId(103);
		emp.setDepartmentId(60);
		

		db.insertEmployee(emp);
		outData=db.getEmployees();
		System.out.println("test metody dodawania nowego rekordu");
		printDataBase(outData);

		//modyfikacja danej po podaniu ID
		//testowe dane do podmiany:
		emp.setFirstName("Kuba");
		emp.setLastName("Zmieniony");
		emp.setEmail("KZMIENIONY");
		emp.setPhone("303030303");
		date=LocalDate.parse("2012-03-21");
		emp.setHireDate(date);
		emp.setJobId("IT_PROG");
		emp.setSalary(13000);
		emp.setManagerId(103);
		emp.setDepartmentId(60);

		db.updateEmployee(empID, emp);
		outData=db.getEmployees();
		System.out.println("test metody modyfikacji danych");
		printDataBase(outData);
        
		
		// usuwanie rekordu po podaniu ID
		db.delEmployee(empID);
		outData=db.getEmployees();
		System.out.println("test metody usuwania rekordow");
		printDataBase(outData);
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
