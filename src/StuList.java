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
	JTable jt = null;
	
	
	
	
	public  static void main(String[] args){
		StuList sl=new StuList();
		
	}
	
	public StuList(){
		
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
				
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		//创建一个数据模型对象
		StuModel sm=new StuModel();
		//System.out.println(sm.getColumnCount());
		System.out.println(sm.getColumnName(1));
		//初始化JTable
		jt=new JTable(sm);
		
		//jt=new JTable(rowdata,columnnames);
		jsp=new JScrollPane(jt);
		//jsp=new JScrollPane();
		
		
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
			
			//因为吧对表的数据封装到model中，可以比较简单完成查询
			String name=this.jtf.getText().trim();
			//System.out.println(name);
			String sql= "select * from stu where stuname='"+name+"'";
			//System.out.println(sql);
			//构造新的数据模型类，并更新
			StuModel sm=new StuModel(sql);;

			//更新JTable
			jt.setModel(sm);
			//System.out.println("hello");
		}
	}

}
