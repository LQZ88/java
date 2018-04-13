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
import com.oracle.*;
public class Login {
	public Login(){
		final JFrame Log = new JFrame("��¼");
		Log.setFont(new Font("����", Font.PLAIN, 12)); 
		Log.getContentPane().setLayout(null);
		Log.setSize(300, 300);  //��   ��
		Log.setLocationRelativeTo(null);  //����
		Log.setResizable(false);//���ɷŴ�
		Log.getContentPane().setBackground(Color.PINK); //������ɫ
		
		final JLabel lblid = new JLabel();
		lblid.setText("�˺�:");
		lblid.setBounds(50, 50, 80,40);//x��,y��,k��,h��
		Log.add(lblid);
		
		final JLabel lblpass = new JLabel();
		lblpass.setText("����:");
		lblpass.setBounds(50, 100, 80, 40);
		Log.add(lblpass);
		
		final JTextField txtid = new JTextField();//�˺ſ�
		txtid.setBounds(100, 60, 95, 20);
		Log.add(txtid);
		
		final JPasswordField txtpass= new JPasswordField();//�����
		txtpass.setBounds(100, 110, 95, 20);
		Log.add(txtpass);
		
		final JButton btnok = new JButton();
		btnok.setBounds(50, 200, 60, 20);//x,y,k,h
		btnok.setMnemonic('E'); //Alt1 +E
		btnok.setText("��¼");
		btnok.addActionListener(new ActionListener(){//�ύ��¼
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==btnok){
					if(txtid.getText().length()==0){
						JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						txtid.requestFocus();
						return;
					}
					if(txtpass.getText().equals("")){
						JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						txtpass.requestFocus(true);
						return;
					}
					if(Getcon.Login(txtid.getText(),txtpass.getText())){
						Log.dispose();
						new Main();
					}else{
						JOptionPane.showMessageDialog(null, "���������û������ڣ�", "��ʾ", JOptionPane.ERROR_MESSAGE);			
						txtid.setText("");					
						txtpass.setText("");
					}
				}
			}
		});
		Log.add(btnok);
		final JLabel lblreslut = new JLabel();
		lblreslut.setText("ע ��");
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
		btnbreak.setText("�ر�");
		btnbreak.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnbreak){
					Log.dispose();//�ر�
					System.exit(0);
				}
			}
		});
		Log.add(btnbreak);
		Log.setVisible(true);  //��������
	}
	
	
	
	
	
	public static void main(String[] args) {
		new Login();
	}
}
