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

import com.oracle.cn.Getcon;

public class Reg {
	public Reg(){
		final JFrame reg=new JFrame("注册");
		reg.setFont(new Font("GB2312",Font.PLAIN,18));
		reg.getContentPane().setLayout(null);
		reg.setSize(300, 380);
		reg.setLocationRelativeTo(null);
		reg.setResizable(false);
		reg.getContentPane().setBackground(Color.GRAY); //背景颜色
		
		
		final JLabel lblid=new JLabel("账户名:");
		lblid.setBounds(40, 50, 80, 40);
		reg.add(lblid);
		final JTextField txtid=new JTextField();
		txtid.setBounds(100, 60, 120, 20);
		reg.add(txtid);
		
		final JLabel lblname=new JLabel("姓名:");
		lblname.setBounds(50,100,80,40);
		reg.add(lblname);
		final JTextField txtname=new JTextField();
		txtname.setBounds(100, 110, 120, 20);
		reg.add(txtname);
		
		final JLabel lblpass = new JLabel();
		lblpass.setText("密码:");
		lblpass.setBounds(50, 150, 80, 40);
		reg.add(lblpass);
		final JPasswordField txtpass= new JPasswordField();//密码框
		txtpass.setBounds(100, 160, 120, 20);
		reg.add(txtpass);
		
		final JLabel lblsex=new JLabel("性别:");
		lblsex.setBounds(50,200,80,40);
		reg.add(lblsex);
		final JTextField txtsex=new JTextField();
		txtsex.setBounds(100, 210, 120, 20);
		reg.add(txtsex);
		
		final JButton btnok=new JButton("注册");
		btnok.setBounds(50, 280, 60, 20);
		reg.add(btnok);
		btnok.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnok){
					String sql="insert into lzeze value ('"+txtid.getText()+"','"+txtname.getText()+"','"+txtpass.getText()+"','"+txtsex.getText()+"','"+1+"')";
					if(txtid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "账户名不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						txtid.requestFocus();
						return;
					}
					if(txtname.getText().length()==0){
						JOptionPane.showMessageDialog(null, "姓名不能为空！");
						txtname.requestFocus();
						return;
					}
					if(txtpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						txtpass.requestFocus(true);
						return;
					}
					if(txtsex.getText().equals("")){
						JOptionPane.showMessageDialog(null, "性别不能为空！");
						txtsex.requestFocus();
						return;
					}
					try {
						if(Getcon.getdosql(sql)){
							reg.dispose();
							new Main();
						}else{
							JOptionPane.showMessageDialog(null, "密码错误或用户不存在！", "提示", JOptionPane.ERROR_MESSAGE);			
							txtid.setText("");					
							txtpass.setText("");
						}
					} catch (HeadlessException e1) {
						
					} catch (SQLException e1) {
						
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
