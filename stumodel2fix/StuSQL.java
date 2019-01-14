package stumodel2fix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StuSQL {
	
	PreparedStatement ps=null;
	Connection ct = null;
	ResultSet rs=null;
	
	String driver = "com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/studentsystem?characterEncoding=UTF-8";
	String user="root";
	String passwd="123456";
	
	public boolean updateSQL(String sql,String[] params) {
		boolean b=true;
		//1、加载驱动
		try {
			Class.forName(driver);
			ct=DriverManager.getConnection(url,user, passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			if(ps.executeUpdate()!=1)
			{
				b=false;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b=false;
			e.printStackTrace();
		}finally {
			this.close();
		}
		return b;
	}
	
	public ResultSet QuerySQL(String sql,String[] params)
	{
		//1、加载驱动
		try {
			Class.forName(driver);
			ct=DriverManager.getConnection(url,user , passwd);
			ps=ct.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void close()
	{
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
			// TODO: handle exception
		}
	}
	
	

}
