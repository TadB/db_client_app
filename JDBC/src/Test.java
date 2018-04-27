public class Test {

	public static void main(String[] args) {
		String oracleURL="jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
		String userName="epolansk";
		String passwd="epolansk";
		
		OraConn db=new OraConn();
		db.setOracleURL(oracleURL);
		db.setPasswd(passwd);
		db.setUserName(userName);
		
		db.open();
		db.close();
		
		
		
}}
	
			
