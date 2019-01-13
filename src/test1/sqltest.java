/***
 * 从数据库中取出信息
 */

package test1;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;

public class sqltest extends JFrame{
	
	//定义
	Vector rowdata,columnnames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	//
	PreparedStatement ps=null;
	Connection ct = null;
	ResultSet rs=null;
	
	public static void main(String[] args) throws SQLException
	{
		sqltest sq=new sqltest();
	}
	
	public sqltest() throws SQLException
	{
		columnnames=new Vector();
		//设置列名
		columnnames.add("学号");
		columnnames.add("姓名");
		
		rowdata=new Vector();
		
		
		
		try {
			//1、加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//通过DriverManager获取数据库连接
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem", "root", "123456");
			//通过Connection对象创建Statement对象，Connection创建Statement对象的方法有3个：
			//1）createStatement(String sql):创建基本的statement对象；
			//2)prepareStatement(String sql):根据传染的SQL语句创建预编译的statement对象；
			//3)prepareCall(String sql):根据传入的SQL语句创建CallableStatement对象；
			ps=ct.prepareStatement("select * from stu");
			//使用Statement执行SQL语句
			//1)execute():可以执行任何SQL语句，但是比较麻烦；
			//2)executeUpdate():主要执行DML和DDL语句。执行DML语句返回受SQL影响的行数，执行DDL语句返回0
			//3)executeQuery():只能执行查询语句，执行后返回代表查询的ResultSet对象
			rs=ps.executeQuery();
			while(rs.next())
			{
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				rowdata.add(hang);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		
		jt=new JTable(rowdata,columnnames);
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
