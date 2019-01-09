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
		
		
		//1、加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem", "root", "123456");
			ps=ct.prepareStatement("select * from stu");
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
