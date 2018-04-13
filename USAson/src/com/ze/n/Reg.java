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
		final JFrame reg=new JFrame("ע��");
		reg.setFont(new Font("GB2312",Font.PLAIN,18));
		reg.getContentPane().setLayout(null);
		reg.setSize(300, 380);
		reg.setLocationRelativeTo(null);
		reg.setResizable(false);
		reg.getContentPane().setBackground(Color.GRAY); //������ɫ
		
		
		final JLabel lblid=new JLabel("�˻���:");
		lblid.setBounds(40, 50, 80, 40);
		reg.add(lblid);
		final JTextField txtid=new JTextField();
		txtid.setBounds(100, 60, 120, 20);
		reg.add(txtid);
		
		final JLabel lblname=new JLabel("����:");
		lblname.setBounds(50,100,80,40);
		reg.add(lblname);
		final JTextField txtname=new JTextField();
		txtname.setBounds(100, 110, 120, 20);
		reg.add(txtname);
		
		final JLabel lblpass = new JLabel();
		lblpass.setText("����:");
		lblpass.setBounds(50, 150, 80, 40);
		reg.add(lblpass);
		final JPasswordField txtpass= new JPasswordField();//�����
		txtpass.setBounds(100, 160, 120, 20);
		reg.add(txtpass);
		
		final JLabel lblsex=new JLabel("�Ա�:");
		lblsex.setBounds(50,200,80,40);
		reg.add(lblsex);
		final JTextField txtsex=new JTextField();
		txtsex.setBounds(100, 210, 120, 20);
		reg.add(txtsex);
		
		final JButton btnok=new JButton("ע��");
		btnok.setBounds(50, 280, 60, 20);
		reg.add(btnok);
		btnok.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnok){
					String sql="insert into lzeze value ('"+txtid.getText()+"','"+txtname.getText()+"','"+txtpass.getText()+"','"+txtsex.getText()+"','"+1+"')";
					if(txtid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "�˻�������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						txtid.requestFocus();
						return;
					}
					if(txtname.getText().length()==0){
						JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
						txtname.requestFocus();
						return;
					}
					if(txtpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						txtpass.requestFocus(true);
						return;
					}
					if(txtsex.getText().equals("")){
						JOptionPane.showMessageDialog(null, "�Ա���Ϊ�գ�");
						txtsex.requestFocus();
						return;
					}
					try {
						if(Getcon.getdosql(sql)){
							reg.dispose();
							new Main();
						}else{
							JOptionPane.showMessageDialog(null, "���������û������ڣ�", "��ʾ", JOptionPane.ERROR_MESSAGE);			
							txtid.setText("");					
							txtpass.setText("");
						}
					} catch (HeadlessException e1) {
						
					} catch (SQLException e1) {
						
					}
				}
			}
		});
		
		final JButton btnexit=new JButton("����");
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
