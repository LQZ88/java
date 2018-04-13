package wQQ;
import wconn.Time;
import wconn.Conn;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
public class Wmain{
	DatagramSocket socket=null;
	InetAddress addip = null;
    public Wmain() {
    	try {
			socket=new DatagramSocket(5000);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
    	final JFrame Myl = new JFrame("主页");
		Myl.setFont(new Font("楷体", Font.PLAIN, 12)); 
		Myl.getContentPane().setLayout(null);
		Myl.setSize(600, 500);  //宽   高
		Myl.setLocationRelativeTo(null);  //居中
		Myl.setResizable(false);//不可放大
		Myl.getContentPane().setBackground(Color.WHITE); //背景颜色
		ImageIcon image=new ImageIcon(getClass().getResource(""));//创建一个未初始化的图像图标
		final JLabel lblimage=new JLabel(image);
		lblimage.setBounds(0, 0, 600, 500);//设置组件位置及大小  
		Myl.getContentPane().add(lblimage);//*/
    	final MenuBar menuBar = new MenuBar();
    	Menu menuFile = new Menu();
    	MenuItem menuFileExit = new MenuItem();
        menuFile.setLabel("菜单");
        menuFileExit.setLabel("退出");
        menuFileExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Myl.dispose();
				if(socket.isConnected()){
					socket.close(); 
					}
				System.exit(0);
			}
        });
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        Myl.setMenuBar(menuBar);
        
        final JLabel lblname=new JLabel("聊天记录:");//标签实例化,文本左对齐
		lblname.setBounds(5, 0, 100, 20);//设置组件位置及大小
        Myl.add(lblname);//添加组件
        final List lisname=new List();
        lisname.setBounds(10, 20, 300, 300);
        lisname.setEnabled(false);
        Myl.add(lisname);
        
        final JLabel lbllist=new JLabel("用户列表:");//标签实例化,文本左对齐
        lbllist.setBounds(330, 0, 100, 20);//设置组件位置及大小
        Myl.add(lbllist);//添加组件
        final List textlistname=new List();
        textlistname.setEnabled(false);
        ArrayList<Conn> list;
		try {
			list = Conn.getList1("SELECT * FROM WQQ");
			for(Iterator<Conn> it=list.iterator();it.hasNext();){
	        	Conn s=it.next();
	        	textlistname.add(s.getsname());
	        }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        final JScrollPane slistname=new JScrollPane(textlistname,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//设置滚动条,水平和垂直滚动条始终显示
        slistname.setBounds(330, 20, 250, 300);//设置组件位置及大小
        Myl.add(slistname);//添加组件
        
		final JLabel lblnames = new JLabel("内容:");
		lblnames.setBounds(5, 330, 80,20);//x,y,k,h
		Myl.add(lblnames);
		
		final JTextField textname = new JTextField();
		textname.setBounds(40, 330, 200, 20);
		Myl.add(textname);
		
		final JLabel lblip = new JLabel("ip:");
		lblip.setBounds(5, 400, 80,40);//x,y,k,h
		Myl.add(lblip);
		final JTextField textip = new JTextField();
		textip.setBounds(20, 410, 95, 20);
		Myl.add(textip);
		
		final JButton btnok=new JButton("发送");
		btnok.setBounds(250, 330, 60, 20);
		btnok.setMnemonic(KeyEvent.VK_ENTER);
		Myl.add(btnok);
		final JCheckBox cbox = new JCheckBox("群聊");
		cbox.setBounds(5, 350, 60, 20);
		//Myl.add(cbox);
		
		btnok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				/*if(cbox.isSelected()==true){//群聊
				 	//JOptionPane.showMessageDialog(null, "进入群聊模式!", "提示", JOptionPane.INFORMATION_MESSAGE);
					new Thread(new Runnable(){
						@Override
						public void run() {
							if(cbox.isBackgroundSet()==false){//负责群聊的线程
								try { 
									while (true) { 
										byte[] buf=new byte[1024];
										addip=null; 
										MulticastSocket sockets = null; 
										int port=5000;
										addip = InetAddress.getByName("255.255.255.0"); 
										sockets = new MulticastSocket(port); 
										((MulticastSocket) sockets).joinGroup(addip); 
										byte[] buffer = new byte[8192]; 
										DatagramPacket packet=null; 
										packet = new DatagramPacket(buffer, buffer.length, addip, port);
										sockets.receive(packet); 
										String message = new String(packet.getData(), 0, packet.getLength()); 
										System.out.println(message);
										packet = new DatagramPacket(buffer, buffer.length); 
										lisname.add(new String(buf,0,packet.getLength())+"   发送    "+packet.getAddress().getHostAddress()+":"+packet.getPort(),0);
									} 
								} 
								catch (Exception e) { }
								}
							textname.setText("");
						}
						}).start();
					return;
				}*/
				if(cbox.isSelected()==false){//私聊
					new Thread(new Runnable(){
					public void run() {
			        		byte[] buf=new byte[1024];
			        		DatagramPacket packet=new DatagramPacket(buf,1024);
			        		while(true){
			        			try{
			        				if(socket.isConnected()){
			        					socket.close(); 
			        					return; 
			        				}
			        				socket.receive(packet);
			        				lisname.add(packet.getAddress().getHostAddress()+" 说：  "+new String(buf,0,packet.getLength()),0);
			        			}
			        			catch(Exception e){
			        				e.printStackTrace();
			        			}
			        		}
			        	}
			        }).start();
					try{
				        byte[] buf=new byte[1024];
				        buf=textname.getText().getBytes();                    
				        DatagramPacket packet=new DatagramPacket(buf,buf.length,
				        InetAddress.getByName(textip.getText()),5000);
				        socket.send(packet); 
				        lisname.add("我说："+new String(buf,0,packet.getLength()),0);       
				    }catch(Exception x){
				         x.printStackTrace();
				    }
				    textname.setText("");
				}
				return;//*/
			}
		});
		/*textname.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try
	              {
	            	  byte[] buf=new byte[1024];
	            	  buf=textname.getText().getBytes();                    
	            	  DatagramPacket packet=new DatagramPacket(buf,buf.length,
	                  InetAddress.getByName(textip.getText()),5000);
	            	  socket.send(packet); 
	            	  lisname.add(new String(buf,0,packet.getLength())+"   发送    "+packet.getAddress().getHostAddress()+":"+packet.getPort(),0);       
	              }
	              catch(Exception x)
	              {
	            	  x.printStackTrace();
	              }
	              textname.setText("");
	              }
			});*/
		
		/*try {
	    	addip = InetAddress.getLocalHost();
			String t=addip.getHostAddress();
		    textip.setText(t);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}*/
		final JLabel lbltime=new JLabel("当前时间:");
		lbltime.setBounds(400, 440, 90, 20);
		lbltime.setEnabled(false);
		Myl.add(lbltime);
        final JLabel lbtime=new JLabel();//标签实例化,文本左对齐 
        lbtime.setBounds(460, 440, 150, 20);
        lbtime.setEnabled(false);
        Myl.add(lbtime);//添加组件
        new Time(lbtime);
        Myl.setVisible(true);
      }
	public static void main(String[] args) {
    	  new Wmain();
      }
}
