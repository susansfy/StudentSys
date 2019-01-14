/***
 * 创建主界面，显示学生信息
 * @author susan
 *
 */
package stumodel2fix;

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
	
	StuModel sm;
	
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
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
				
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		//创建一个数据模型对象
		sm=new StuModel();
		String[] params2 = {"1"};
		sm.QueryStu("select * from stu where 1=?", params2);
		//初始化JTable
		jt=new JTable(sm);
		
		//jt=new JTable(sm.rowdata,sm.columnNames);
		jsp=new JScrollPane(jt);
		
		
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//查询按钮
		if(e.getSource()==jb1)
		{
			
			//因为吧对表的数据封装到model中，可以比较简单完成查询
			String name=this.jtf.getText().trim();
			System.out.println(name);
			//String name1="\""+name+ "\"";
			//String sql= "select * from stu where stuname="+name1+ "";
			String sql;
			String[] params;
			if(name.equals(""))
			{
				sql= "select * from stu where 1=?";
				params =new  String[] {"1"};
				System.out.println(sql);
				
			}else {
				sql= "select * from stu where stuname=?";
				params =new String[]{name};
				
			}
			//构造新的数据模型类，并更新
			sm=new StuModel();
			sm.QueryStu(sql,params);
			//更新JTable
			jt.setModel(sm);
				
		}
		//添加按钮
		if(e.getSource()==jb2)
		{
			StuAddDialog sad=new StuAddDialog(this,"添加学生",true);
			//构造新的数据模型类，并更新
			sm=new StuModel();
			String[] params2 = {"1"};
			sm.QueryStu("select * from stu where 1=?", params2);
			//更新JTable
			jt.setModel(sm);
		}
		
		//删除按钮
		if(e.getSource()==jb4)
		{
			int selectedrow=this.jt.getSelectedRow();
			
			if(selectedrow==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行数据");
				return;
			}else
			{	
				String stuid=sm.getValueAt(selectedrow, 0).toString();
				System.out.println(stuid);	
				StuModel temp = new StuModel();
				String sql = "delete from stu where stuId=?";
				String[] params={stuid};
				temp.upStu(sql, params);
				
				sm=new StuModel();
				String[] params2 = {"1"};
				sm.QueryStu("select * from stu where 1=?", params2);
				//更新JTable
				jt.setModel(sm);
			}
				
		}
		
		//修改按钮
		if(e.getSource()==jb3)
		{
			int selectedrow=this.jt.getSelectedRow();
			
			if(selectedrow==-1)
			{
				JOptionPane.showMessageDialog(this, "请选择一行数据");
				return;
			}else{
			StuUpdateDialog sud = new StuUpdateDialog(this,"修改学生信息",true,sm,selectedrow);
			sm=new StuModel();
			String[] params2 = {"1"};
			sm.QueryStu("select * from stu where 1=?", params2);
			//更新JTable
			jt.setModel(sm);
			}
		}
	}
}
