import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeesDAL {
	private SQLException ex;
    Connection connect;
    public SQLException getSQLException() {
        return ex;
    }
    public EmployeesDAL(Connection connectionInfo){
        connect= connectionInfo;
    }
    public Employee getEmployees(){
        Employee employees = new Employee();
        try(Statement statement=connect.createStatement();){
            String query = "SELECT * FROM EMPLOYEES";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                employees.add(rs2Employee(resultSet));
            }
        }
        catch(SQLException ex){System.out.println(ex);}
        return employees;
    }
    public int udpateEmployee(Employee emp){
        try (Statement statement =
            connect.createStatement();){
        DateTimeFormatter dtf = DateTimeFormatter.ofPatter("yyyyMMdd");    
        }
    }
    private Employee rs2Employee(ResultSet resultSet){
        Employee emp=null;
        try{
            int col=1;
            emp = new Employee(resultSet.getInt(col++));
            emp.setEmployeeId(resultSet.getInt(col++));
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
