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
        g.drawString("音乐播放中。。。。。。。。。。。",150,15);
        for(int i=0;i<5;i++){
            g.drawImage(img[i],25,25,200,200,this);
            try{
                Thread.sleep(1000);//此处设置图片显示的快慢
            }catch(InterruptedException e){}
        }
        g.drawString("人月神话！！",5,15);
    }
    
    public void run() {
    	
    }
}
