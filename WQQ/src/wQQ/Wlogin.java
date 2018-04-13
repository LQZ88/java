package wQQ;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;

import wconn.*;
public class Wlogin {
	private static String code="";
	InetAddress addip = null;
	public Wlogin(){
		final JFrame Login = new JFrame("登录");
		Login.setFont(new Font("楷体", Font.PLAIN, 12)); 
		Login.getContentPane().setLayout(null);
		Login.setSize(300, 300);  //宽   高
		Login.setLocationRelativeTo(null);  //居中
		Login.setResizable(false);//不可放大
		Login.getContentPane().setBackground(Color.gray); //背景颜色
		
		try {
			addip = InetAddress.getLocalHost();
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		String ip=addip.getHostAddress();
		final JTextField textip= new JTextField();
		textip.setText(ip);
		Login.add(textip);
		final JLabel lblid = new JLabel();
		lblid.setText("账号:");
		lblid.setBounds(50, 40, 80,40);//x,y,k,h
		Login.add(lblid);
		final JLabel lblpass = new JLabel();
		lblpass.setText("密码:");
		lblpass.setBounds(50, 80, 80, 40);
		Login.add(lblpass);
		JLabel lbly = new JLabel("验证码");		
		lbly.setBounds(50, 120, 100, 40);
		Login.add(lbly);
		final JTextField textid = new JTextField();
		textid.setBounds(100, 50, 95, 20);
		Login.add(textid);
		final JPasswordField textpass= new JPasswordField();
		textpass.setBounds(100, 90, 95, 20);
		Login.add(textpass);
		final JTextField texty = new JTextField();	//验证码输入框	
		texty.setBounds(100,130, 95, 20);
		Login.add(texty);
		final JButton btny = new JButton("获取处");//验证码显示按钮
		btny.setBounds(210, 130, 70, 20);		
		Login.add(btny);
		btny.addActionListener(new ActionListener() {//点击按钮 后的处理动作				
			public void actionPerformed(ActionEvent e) {
				String array="qaz1wsx2edc3rfv4tgb5yhn6ujm7ik8ol9p";
				StringBuffer sbf=new StringBuffer(array);
				int strLength=sbf.length();
				Random randomGen=new Random();
				for(int i=0;i<4;i++)
				{
					int a=randomGen.nextInt(strLength);
					char ch=array.charAt(a);
					code+=String.valueOf(ch); //char 转为 String
				}
				btny.setText("");
				btny.setText("");
				btny.setText(code);
				code="";
				}		
			});
		final JButton btnok = new JButton();
		btnok.setBounds(50, 200, 60, 20);//x,y,k,h
		btnok.setMnemonic('E'); //Alt1 +E
		btnok.setText("登录");
		btnok.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==btnok){
					if(textid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "账号不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						textid.requestFocus();
						return;
					}
					if(textpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						textpass.requestFocus(true);
						return;
					}
					if(texty.getText().equals("")){
						JOptionPane.showMessageDialog(null, "验证码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						texty.requestFocus();
						return;
					}
					try {
						if(Conn.Login(textid.getText(), textpass.getText())){
							if(btny.getText().equals(texty.getText())&&texty.getText().length()!=0&&btny.getText().length()!=0){
								Conn.update(textid.getText(),textip.getText() );
								JOptionPane.showMessageDialog(null, "登陆成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								Login.dispose();
								new Wmain();
							}else{
								JOptionPane.showMessageDialog(null, "验证码错误，请重新输入！注意大小写", "提示", JOptionPane.ERROR_MESSAGE);
								btny.setText("");
								btny.setText("");
								code="";
							}
						}else{
							JOptionPane.showMessageDialog(null, "密码错误或用户不存在！", "提示", JOptionPane.ERROR_MESSAGE);			
							textid.setText("");					
							textpass.setText("");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Login.add(btnok);
		final JLabel lblreslut = new JLabel();
		lblreslut.setText("注 册");
		lblreslut.setBounds(220, 80, 80,40);//x,y,k,h
		lblreslut.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				Login.dispose();
				new Wreg();
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
		Login.add(lblreslut);
		final JButton btnbreak=new JButton();
		btnbreak.setBounds(150, 200, 60, 20);
		btnbreak.setMnemonic('B');
		btnbreak.setText("取消");
		btnbreak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnbreak){
					Login.dispose();//关闭
					System.exit(0);
				}
			}
		});
		Login.add(btnbreak);
		try {
			Thread.sleep(2000);//2秒后启动
			Login.setVisible(true);  //启动窗体
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new Wlogin();
	}
}