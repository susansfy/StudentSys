/***
 * 这是一个表模型
 * @author susan
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.*;
public class StuModel extends AbstractTableModel{
	
	//rowdata用来存放行数据；
	//columnnames存放列名；
	Vector rowdata,columnNames;
	//
	PreparedStatement ps=null;
	Connection ct = null;
	ResultSet rs=null;
	
	public void init(String sql)
	{
		if(sql.equals(""))
		{
			sql="select * from stu";
		}
		
		columnNames=new Vector();
		//设置列名
		//？？？为什么没出列名？？
		columnNames.add("id");
		columnNames.add("name");
		
		rowdata=new Vector();
		//1、加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsystem", "root", "123456");
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				rowdata.add(hang);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	
	//通过传递的SQL语句来获得数据模型
	public StuModel(String sql)
	{
		this.init(sql);
	}
			
	//构造函数，用于初始化数据模型
	public StuModel() 
	{
		this.init("");
	}

	//得到共有多少列
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowdata.size();
	}

	//得到共有多少行
	public int getColumnCount() {
		// TODO Auto-generated method stub
		
		return this.columnNames.size();
	}

	//得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		//System.out.println(rowIndex+"="+columnIndex);
		return ((Vector)this.rowdata.get(rowIndex)).get(columnIndex);
		//return null;
	}

}
