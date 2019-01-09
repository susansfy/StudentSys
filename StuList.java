/***
 * 创建主界面，显示学生信息
 * @author susan
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class StuList extends JFrame implements ActionListener{
	
	JPanel jp1,jp2;	
	JLabel jl1;
	JTextField jtf;
	JButton jb1,jb2,jb3,jb4;
	JScrollPane jsp;
	
	JTable jta = null;
	
	Vector rowdata,columnnames;
	JTable jt = null;
	//JScrollPane jsp = null;
	
	//
	PreparedStatement ps=null;
	Connection ct = null;
	ResultSet rs=null;
	
	
	public  static void main(String[] args) throws SQLException {
		new StuList();
		
	}
	
	public StuList() throws SQLException {
		
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入名字");
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		jb2=new JButton("添加");
		jb3=new JButton("修改");
		jb4=new JButton("删除");
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		

		
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
		
		
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		
		jt=new JTable(rowdata,columnnames);
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			System.out.println("hello");
		}
	}

}
