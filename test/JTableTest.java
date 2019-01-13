package test;

import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class JTableTest extends JFrame{
	
	Vector rowdata,columnnames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	public static void main(String[] args)
	{
		JTableTest ts=new JTableTest();
	}
	
	public JTableTest()
	{
		columnnames=new Vector();
		//设置列名
		columnnames.add("学号");
		columnnames.add("姓名");
		
		rowdata=new Vector();
		Vector hang=new Vector();
		hang.add("001");
		hang.add("李四");
		
		rowdata.add(hang);
		
		jt=new JTable(rowdata,columnnames);
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
