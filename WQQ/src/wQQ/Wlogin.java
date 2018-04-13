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
		final JFrame Login = new JFrame("��¼");
		Login.setFont(new Font("����", Font.PLAIN, 12)); 
		Login.getContentPane().setLayout(null);
		Login.setSize(300, 300);  //��   ��
		Login.setLocationRelativeTo(null);  //����
		Login.setResizable(false);//���ɷŴ�
		Login.getContentPane().setBackground(Color.gray); //������ɫ
		
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
		lblid.setText("�˺�:");
		lblid.setBounds(50, 40, 80,40);//x,y,k,h
		Login.add(lblid);
		final JLabel lblpass = new JLabel();
		lblpass.setText("����:");
		lblpass.setBounds(50, 80, 80, 40);
		Login.add(lblpass);
		JLabel lbly = new JLabel("��֤��");		
		lbly.setBounds(50, 120, 100, 40);
		Login.add(lbly);
		final JTextField textid = new JTextField();
		textid.setBounds(100, 50, 95, 20);
		Login.add(textid);
		final JPasswordField textpass= new JPasswordField();
		textpass.setBounds(100, 90, 95, 20);
		Login.add(textpass);
		final JTextField texty = new JTextField();	//��֤�������	
		texty.setBounds(100,130, 95, 20);
		Login.add(texty);
		final JButton btny = new JButton("��ȡ��");//��֤����ʾ��ť
		btny.setBounds(210, 130, 70, 20);		
		Login.add(btny);
		btny.addActionListener(new ActionListener() {//�����ť ��Ĵ�����				
			public void actionPerformed(ActionEvent e) {
				String array="qaz1wsx2edc3rfv4tgb5yhn6ujm7ik8ol9p";
				StringBuffer sbf=new StringBuffer(array);
				int strLength=sbf.length();
				Random randomGen=new Random();
				for(int i=0;i<4;i++)
				{
					int a=randomGen.nextInt(strLength);
					char ch=array.charAt(a);
					code+=String.valueOf(ch); //char תΪ String
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
		btnok.setText("��¼");
		btnok.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==btnok){
					if(textid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						textid.requestFocus();
						return;
					}
					if(textpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						textpass.requestFocus(true);
						return;
					}
					if(texty.getText().equals("")){
						JOptionPane.showMessageDialog(null, "��֤�벻��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						texty.requestFocus();
						return;
					}
					try {
						if(Conn.Login(textid.getText(), textpass.getText())){
							if(btny.getText().equals(texty.getText())&&texty.getText().length()!=0&&btny.getText().length()!=0){
								Conn.update(textid.getText(),textip.getText() );
								JOptionPane.showMessageDialog(null, "��½�ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
								Login.dispose();
								new Wmain();
							}else{
								JOptionPane.showMessageDialog(null, "��֤��������������룡ע���Сд", "��ʾ", JOptionPane.ERROR_MESSAGE);
								btny.setText("");
								btny.setText("");
								code="";
							}
						}else{
							JOptionPane.showMessageDialog(null, "���������û������ڣ�", "��ʾ", JOptionPane.ERROR_MESSAGE);			
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
		lblreslut.setText("ע ��");
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
		btnbreak.setText("ȡ��");
		btnbreak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnbreak){
					Login.dispose();//�ر�
					System.exit(0);
				}
			}
		});
		Login.add(btnbreak);
		try {
			Thread.sleep(2000);//2�������
			Login.setVisible(true);  //��������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new Wlogin();
	}
}