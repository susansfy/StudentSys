/***
 * 添加学生窗口
 */
package stumodel2fix;

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
			StuModel temp = new StuModel();
			String sql = "insert into stu values(?,?)";
			String[] params={jt1.getText(),jt2.getText()};
			if(!temp.upStu(sql, params))
			{
				JOptionPane.showMessageDialog(this, "添加失败");
			}
			this.dispose();
		}
		if(e.getSource()==jb2)
		{
			this.setVisible(false);
		}
	}
	

}
