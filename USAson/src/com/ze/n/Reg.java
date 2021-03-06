package com.ze.n;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ConnData;
import com.conn.ConnUtils;

public class Reg {
	public Reg(){
		final JFrame reg=new JFrame("注册");
		reg.setFont(new Font("GB2312",Font.PLAIN,18));
		reg.getContentPane().setLayout(null);
		reg.setSize(300, 380);
		reg.setLocationRelativeTo(null);
		reg.setResizable(false);
		reg.getContentPane().setBackground(Color.GRAY); //背景颜色
		
		
		final JLabel lblname=new JLabel("用户名:");
		lblname.setBounds(40, 40, 80, 40);
		reg.add(lblname);
		final JTextField txtname=new JTextField();
		txtname.setBounds(100, 50, 120, 20);
		reg.add(txtname);
		
		final JLabel lblsex=new JLabel("性别:");
		lblsex.setBounds(50,90,80,40);
		reg.add(lblsex);
		final JTextField txtsex=new JTextField();
		txtsex.setBounds(100, 100, 120, 20);
		reg.add(txtsex);
		
		final JLabel lblage = new JLabel("年龄:");
		lblage.setBounds(50, 140, 80, 40);
		reg.add(lblage);
		final JTextField txtage= new JTextField();//密码框
		txtage.setBounds(100, 150, 120, 20);
		reg.add(txtage);
		
		final JLabel lbladress=new JLabel("地址:");
		lbladress.setBounds(50,190,80,40);
		reg.add(lbladress);
		final JTextField txtadress=new JTextField();
		txtadress.setBounds(100, 200, 120, 20);
		reg.add(txtadress);
		
		final JLabel lblpass=new JLabel("密码:");
		lblpass.setBounds(50,240,80,40);
		reg.add(lblpass);
		final JPasswordField txtpass=new JPasswordField();
		txtpass.setBounds(100, 250, 120, 20);
		reg.add(txtpass);
		
		final JButton btnok=new JButton("注册");
		btnok.setBounds(50, 280, 60, 20);
		reg.add(btnok);
		btnok.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnok){
					String sql="insert into userinfo(name, sex, age, adress, password) value ('"+txtname.getText()+"','"
							+txtsex.getText()+"','"+txtage.getText()+"','"+txtadress.getText()+"','"+txtpass.getText()+"')";
					if(txtname.getText().length()==0){
						JOptionPane.showMessageDialog(null, "用户名不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						txtname.requestFocus();
						return;
					}
					if(txtsex.getText().length()==0){
						JOptionPane.showMessageDialog(null, "性别不能为空！");
						txtsex.requestFocus();
						return;
					}
					if(txtage.getText().equals("")){
						JOptionPane.showMessageDialog(null, "年龄不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						txtage.requestFocus(true);
						return;
					}
					if(txtadress.getText().equals("")){
						JOptionPane.showMessageDialog(null, "地址不能为空！");
						txtadress.requestFocus();
						return;
					}
					if(txtpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "密码不能为空！");
						txtpass.requestFocus();
						return;
					}
					try {
						if(ConnUtils.isSuccess(sql)){
							reg.dispose();
							new Main();
						}else{
							JOptionPane.showMessageDialog(null, "注册失败！", "提示", JOptionPane.ERROR_MESSAGE);			
						}
					} catch (HeadlessException e1) {
						JOptionPane.showMessageDialog(null, "注册失败！"+e1.getMessage(), "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		final JButton btnexit=new JButton("返回");
		btnexit.setBounds(180, 280, 60, 20);
		reg.add(btnexit);
		btnexit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				reg.dispose();
				new Login();
				//System.exit(0);
			}
		});
		
		
		
		
		
		
		reg.setVisible(true);
	}
	public static void main(String[] args) {
		new Reg();
	}

}