package com.ze;

import java.applet.*;
import java.awt.*;
@SuppressWarnings("serial")
public class Movie extends Applet implements Runnable {
    Image img[];
    AudioClip aa;
    Thread t;
    
    public void init() {
        img=new Image[5];
        for(int i=0;i<5;i++)
            img[i]=getImage(getCodeBase(),(i+1)+".jpg");
        aa=getAudioClip(getCodeBase(),"birthday.au");
        aa.play();
    }
    public void start(){
        if(t==null){
            t=new Thread(this);
            t.start();
        }
    }
    public void paint(Graphics g) {
        g.drawString("���ֲ����С���������������������",150,15);
        for(int i=0;i<5;i++){
            g.drawImage(img[i],25,25,200,200,this);
            try{
                Thread.sleep(1000);//�˴�����ͼƬ��ʾ�Ŀ���
            }catch(InterruptedException e){}
        }
        g.drawString("�����񻰣���",5,15);
    }
    
    public void run() {
    	
    }
}
