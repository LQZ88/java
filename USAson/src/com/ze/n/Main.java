package com.ze.n;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.oracle.cn.DBuser;
import com.oracle.cn.Getcon;

public class Main {
	public Main(){
		final JFrame min = new JFrame("��ӭ��¼");
		min.setFont(new Font("����", Font.PLAIN, 12)); 
		min.getContentPane().setLayout(null);
		min.setSize(480,400);  //��   ��
		min.setLocationRelativeTo(null);  //����
		min.setResizable(false);//���ɷŴ�
		min.getContentPane().setBackground(Color.white); //������ɫ
		
		//final 
        
		final List txtlist=new List();
        txtlist.setEnabled(false);
        ArrayList<DBuser> list;
		list = Getcon.getselect();
		txtlist.add("�û�ID", 0);
		txtlist.add("�û�ID"+"����"+"����"+"�Ա�"+"��¼����");
		for(Iterator<DBuser> it=list.iterator();it.hasNext();){
			DBuser s=it.next();
			txtlist.add(s.getTxtid()+s.getTxtname()+s.getTxtpwd()+s.getTxtsex()+s.getTxtcount());
		}
        final JScrollPane slistname=new JScrollPane(txtlist,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//���ù�����,ˮƽ�ʹ�ֱ������ʼ����ʾ
        slistname.setBounds(30, 20, 250, 250);//�������λ�ü���С
        min.add(slistname);//������
        
		
        final JButton btnok=new JButton("");
		btnok.setBounds(80, 300, 60, 20);
		min.add(btnok);
		btnok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});
		
		
		final JButton btnexit=new JButton("�ر�");
		btnexit.setBounds(350, 300, 60, 20);
		min.add(btnexit);
		btnexit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
		
		
		
		
		
		try {
			Thread.sleep(1000);//1�������
			min.setVisible(true);  //��������
		} catch (InterruptedException e) {
			System.out.println("����ʧ��"+e.getMessage());
		}
	}
	
	public static void main(String[] args){
		new Main();
	}
}
