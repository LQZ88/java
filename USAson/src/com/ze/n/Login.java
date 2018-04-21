package com.ze.n;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.conn.MysqlConn;
public class Login {
	public Login(){
		final JFrame Log = new JFrame("登录");
		Log.setFont(new Font("楷体", Font.PLAIN, 12)); 
		Log.getContentPane().setLayout(null);
		Log.setSize(300, 300);  //宽   高
		Log.setLocationRelativeTo(null);  //居中
		Log.setResizable(false);//不可放大
		Log.getContentPane().setBackground(Color.PINK); //背景颜色
		
		final JLabel lblid = new JLabel();
		lblid.setText("账号:");
		lblid.setBounds(50, 50, 80,40);//x左,y上,k宽,h长
		Log.add(lblid);
		
		final JLabel lblpass = new JLabel();
		lblpass.setText("密码:");
		lblpass.setBounds(50, 100, 80, 40);
		Log.add(lblpass);
		
		final JTextField txtid = new JTextField();//账号框
		txtid.setBounds(100, 60, 95, 20);
		Log.add(txtid);
		
		final JPasswordField txtpass= new JPasswordField();//密码框
		txtpass.setBounds(100, 110, 95, 20);
		Log.add(txtpass);
		
		final JButton btnok = new JButton();
		btnok.setBounds(50, 200, 60, 20);//x,y,k,h
		btnok.setMnemonic('E'); //Alt1 +E
		btnok.setText("登录");
		btnok.addActionListener(new ActionListener(){//提交登录
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==btnok){
					if(txtid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "账号不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						txtid.requestFocus();
						return;
					}
					if(txtpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						txtpass.requestFocus(true);
						return;
					}
					if(MysqlConn.toLogin(txtid.getText(),txtpass.getText())){
						Log.dispose();
						new Main();
					}else{
						JOptionPane.showMessageDialog(null, "密码错误或用户不存在！", "提示", JOptionPane.ERROR_MESSAGE);			
						txtid.setText("");					
						txtpass.setText("");
					}
				}
			}
		});
		Log.add(btnok);
		final JLabel lblreslut = new JLabel();
		lblreslut.setText("注 册");
		lblreslut.setBounds(220, 100, 80,40);//x,y,k,h
		lblreslut.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				Log.dispose();
				new Reg();
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		Log.add(lblreslut);
		final JButton btnbreak=new JButton();
		btnbreak.setBounds(150, 200, 60, 20);
		btnbreak.setMnemonic('B');
		btnbreak.setText("关闭");
		btnbreak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnbreak){
					Log.dispose();//关闭
					System.exit(0);
				}
			}
		});
		Log.add(btnbreak);
		Log.setVisible(true);  //启动窗体
	}
	
	
	
	
	
	public static void main(String[] args) {
		new Login();
	}
}