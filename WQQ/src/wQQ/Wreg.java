package wQQ;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;
import wconn.Conn;
public class Wreg{
	InetAddress addip = null;
	public Wreg(){
		final JFrame Reg = new JFrame("注册");
		Reg.setFont(new Font("楷体", Font.PLAIN, 12)); 
		Reg.getContentPane().setLayout(null);
		Reg.setSize(300, 400);  //宽   高
		Reg.setLocationRelativeTo(null);  //居中
		Reg.setResizable(false);//不可放大
		Reg.getContentPane().setBackground(Color.magenta); 
		
		try {
			addip = InetAddress.getLocalHost();
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		String ip=addip.getHostAddress();
		final JTextField textip= new JTextField();
		textip.setText(ip);
		Reg.add(textip);
		
		final JLabel lblid = new JLabel("账号:");
		lblid.setBounds(50, 40, 80,40);//x,y,k,h
		Reg.add(lblid);
		final JLabel lblpass = new JLabel("密码:");
		lblpass.setBounds(50, 90, 80, 40);
		Reg.add(lblpass);
		final JLabel lblname = new JLabel("姓名:");
		lblname.setBounds(50, 140, 80,40);//x,y,k,h
		Reg.add(lblname);
		final JLabel lblage = new JLabel("年龄:");
		lblage.setBounds(50, 190, 80, 40);
		Reg.add(lblage);
		final JLabel lbladdress = new JLabel("地址:");
		lbladdress.setBounds(50, 240, 80,40);//x,y,k,h
		Reg.add(lbladdress);
		
		final JTextField textid = new JTextField();
		textid.setBounds(100, 50, 100, 22);
		Reg.add(textid);
		final JTextField textpass= new JTextField();
		textpass.setBounds(100, 100, 100, 22);
		Reg.add(textpass);
		final JTextField textname = new JTextField();
		textname.setBounds(100, 150, 100, 22);
		Reg.add(textname);
		final JTextField textage= new JTextField();
		textage.setBounds(100, 200, 100, 22);
		Reg.add(textage);
		final JComboBox cboaddres=new JComboBox();
		cboaddres.setBounds(100, 250, 100, 22);
		cboaddres.addItem("云南省");
		cboaddres.addItem("四川省");
		Reg.add(cboaddres);
		
		final JButton btnok = new JButton();
		btnok.setBounds(40, 320, 60, 20);//x,y,k,h
		btnok.setMnemonic(KeyEvent.VK_ENTER); //Alt1 +E
		btnok.setText("注册");
		btnok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==btnok){
					if(textid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "账号不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						textid.requestFocus();
						return;
					}
					if(textpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "密码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						textpass.requestFocus();
						return;
					}
					if(textname.getText().length()==0){
						JOptionPane.showMessageDialog(null, "姓名不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						textname.requestFocus();
						return;
					}
					if(textage.getText().equals("")){
						JOptionPane.showMessageDialog(null, "年龄不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
						textage.requestFocus();
						return;
					}
					try {
						if(Conn.Rrg(textid.getText(), textpass.getText(), textname.getText(), textage.getText(), cboaddres.getSelectedItem().toString(),textip.getText())){
							JOptionPane.showMessageDialog(null, "注册失败", "提示", JOptionPane.ERROR_MESSAGE);			
							textid.setText("");					
							textpass.setText("");
							textname.setText("");					
							textage.setText("");
						}else{
							JOptionPane.showMessageDialog(null, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							Reg.dispose();
							new Wlogin();
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Reg.add(btnok);
		final JButton btnbreak=new JButton();
		btnbreak.setBounds(180, 320, 60, 20);
		btnbreak.setMnemonic(KeyEvent.VK_END);
		btnbreak.setText("取消");
		btnbreak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Reg.dispose();
				System.exit(0);
			}
		});
		Reg.add(btnbreak);
		try {
			Thread.sleep(1000);
			Reg.setVisible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new Wreg();
	}
}
