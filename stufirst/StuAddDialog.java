/***
 * 添加学生窗口
 */
package stufirst;

import java.awt.Dialog;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class StuAddDialog extends JDialog implements ActionListener{
	
	JPanel jp1,jp2,jp3;
	JTextField jt1,jt2;
	JLabel jl1,jl2;
	JButton jb1,jb2;
	
	

	public StuAddDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		// TODO Auto-generated constructor stub
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jl1 = new JLabel("id");
		jl2 = new JLabel("name");
		
		jt1=new JTextField();
		jt2=new JTextField();
		
		jb1=new JButton("添加");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
		jb2.addActionListener(this);
		
		jp1.add(jl1);
		jp1.add(jl2);
		
		jp2.add(jt1);
		jp2.add(jt2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		jp1.setLayout(new GridLayout(2,1));
		jp2.setLayout(new GridLayout(2,1));
		
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		this.setSize(200,100);
		this.setVisible(true);
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{

			PreparedStatement ps=null;
			Connection ct = null;
			ResultSet rs=null;
		
			//1、加载驱动
			try {
				Class.forName("com.mysql.jdbc.Driver");
				ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?characterEncoding=UTF-8", "root", "123456");
				ps=ct.prepareStatement("insert into stu values(?,?)");
				//rs=ps.executeQuery();
				ps.setString(1, jt1.getText());
				ps.setString(2, jt2.getText());
				ps.executeUpdate();
				this.dispose();
				
			} catch (Exception e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
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
		if(e.getSource()==jb2)
		{
			this.setVisible(false);
		}
	}
	

}
