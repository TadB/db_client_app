import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class EmployeesDAL {
	private SQLException ex;
    Connection connect;
    public SQLException getSQLException() {
        return ex;
    }
    public EmployeesDAL(Connection connectionInfo){
        connect= connectionInfo;
    }
    public Vector <Employee> getEmployees(){
       Vector <Employee> employees=new Vector <Employee>();
        try(Statement statement=connect.createStatement();){
            String query = "SELECT * FROM EMPLOYEES ORDER BY EMPLOYEE_ID";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
            	employees.add(rs2Employee(resultSet));
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return employees;
    }
    public Vector <Employee> getEmployeeByEmployeeId(int employeeId){
    	Vector <Employee> employees=new Vector <Employee>();
        try(Statement statement=connect.createStatement();){
            String query = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID =" + employeeId;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                employees.add(rs2Employee(resultSet));
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        return employees;
    }
    public void insertEmployee(Employee emp){
        try (Statement statement = connect.createStatement();){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            String hireDate = dtf.format(emp.getHireDate());
            //czy to wgl dziala ta data?
            //System.out.println(hireDate);
            //--------------------------------
            String query="INSERT INTO EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) VALUES ("
                        + emp.getEmployeeId()+ ","
                        + "'" + emp.getFirstName() + "',"
                        + "'" + emp.getLastName() + "',"
                        + "'" + emp.getEmail() + "',"
                        + "'" + emp.getPhone() + "',"
                        + "to_date('"+ hireDate + "', 'yyyyMMdd'), "
                        + "'" + emp.getJobId() + "',"
                        +emp.getSalary()+ ","
                        +emp.getManagerId()+ ","
                        +emp.getDepartmentId()+ ")";

            statement.executeUpdate(query);
            connect.commit();

        }
        catch(SQLException ex) {
        	this.ex = ex;
        	return;
        }
    }
    public void updateEmployee(int employeeId, Employee emp){
        try (Statement statement =
            connect.createStatement();){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String hireDate = dtf.format(emp.getHireDate());

        String query="UPDATE EMPLOYEES SET "
                    + "LAST_NAME = '" + emp.getLastName() + "',"
                    + "FIRST_NAME = '" + emp.getFirstName() + "',"
                    + "EMAIL = '" + emp.getEmail() + "',"
                    + "PHONE_NUMBER = '" + emp.getPhone() + "',"
                    + "JOB_ID = '" + emp.getJobId() + "',"
                    + "SALARY = " +emp.getSalary() + ","
                    + "MANAGER_ID = " +emp.getManagerId() + ","
                    + "DEPARTMENT_ID =  " +emp.getDepartmentId()+ ","
                    + "HIRE_DATE = to_date('"+ hireDate + "', 'yyyyMMdd')"
                   	+ "WHERE EMPLOYEE_ID=" +employeeId;

        //int affectedRows=statement.executeUpdate(query);
        statement.executeUpdate(query);
        connect.commit();
        //return affectedRows;

        }
        catch(SQLException ex) {
        	this.ex = ex;
        	//return 0;
        }
    }
    public void delEmployee(int employeeId){
        try (Statement statement = connect.createStatement();){
            String query = "DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID="
                        + employeeId;
            statement.executeQuery(query);
            connect.commit();
        }
        catch(SQLException ex) {
            this.ex = ex;
            return;
        }
    }
    private Employee rs2Employee(ResultSet resultSet){
        Employee emp=null;
        try{
            int col=1;
            emp = new Employee(resultSet.getInt(col++));
            //emp.setEmployeeId(resultSet.getInt(col++));
            emp.setFirstName(resultSet.getNString(col++));
            emp.setLastName(resultSet.getNString(col++));
            emp.setEmail(resultSet.getNString(col++));
            emp.setPhone(resultSet.getNString(col++));
            emp.setHireDate(resultSet.getDate(col++).toLocalDate());
            emp.setJobId(resultSet.getNString(col++));
            emp.setSalary(resultSet.getInt(col++));
            col++;
            emp.setManagerId(resultSet.getInt(col++));
            emp.setDepartmentId(resultSet.getInt(col++));
        }
        catch(SQLException ex){
            this.ex=ex;
        }
        return emp;
    }
}
