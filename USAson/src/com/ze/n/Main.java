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
		final JFrame min = new JFrame("欢迎登录");
		min.setFont(new Font("楷体", Font.PLAIN, 12)); 
		min.getContentPane().setLayout(null);
		min.setSize(480,400);  //宽   高
		min.setLocationRelativeTo(null);  //居中
		min.setResizable(false);//不可放大
		min.getContentPane().setBackground(Color.white); //背景颜色
		
		//final 
        
		final List txtlist=new List();
        txtlist.setEnabled(false);
        ArrayList<DBuser> list;
		list = Getcon.getselect();
		txtlist.add("用户ID", 0);
		txtlist.add("用户ID"+"姓名"+"密码"+"性别"+"登录次数");
		for(Iterator<DBuser> it=list.iterator();it.hasNext();){
			DBuser s=it.next();
			txtlist.add(s.getTxtid()+s.getTxtname()+s.getTxtpwd()+s.getTxtsex()+s.getTxtcount());
		}
        final JScrollPane slistname=new JScrollPane(txtlist,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置滚动条,水平和垂直滚动条始终显示
        slistname.setBounds(30, 20, 250, 250);//设置组件位置及大小
        min.add(slistname);//添加组件
        
		
        final JButton btnok=new JButton("");
		btnok.setBounds(80, 300, 60, 20);
		min.add(btnok);
		btnok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
			}
		});
		
		
		final JButton btnexit=new JButton("关闭");
		btnexit.setBounds(350, 300, 60, 20);
		min.add(btnexit);
		btnexit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		
		
		
		
		
		
		try {
			Thread.sleep(1000);//1秒后启动
			min.setVisible(true);  //启动窗体
		} catch (InterruptedException e) {
			System.out.println("启动失败"+e.getMessage());
		}
	}
	
	public static void main(String[] args){
		new Main();
	}
}
