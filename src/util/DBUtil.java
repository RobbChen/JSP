package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {
		private static final String url="jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=utf-8";
		private static final String username="root";
		private static final String password="chen3612";
		private static Connection conn=null;
		
		static{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn=DriverManager.getConnection(url,username,password);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(SQLException e){
					e.printStackTrace();
				}
			} 
		public static Connection getConnection(){
			return conn;
		}
public static void main(String[] args) {
	
	try
	{
	   Connection conn = DBUtil.getConnection();
	   if(conn!=null)
	   {
		   System.out.println("数据库连接正常！");
	   }
	   else
	   {
		   System.out.println("数据库连接异常！");
	   }
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}
}

