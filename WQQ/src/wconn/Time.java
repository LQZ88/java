package wconn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;//时间类
public class Time {
	public Time(JLabel time){//构造方法
		this.setTimer(time);//设置时间
	}
public void setTimer(JLabel time){ //设置时间方法
	final JLabel varTime = time; //传递组件
	Timer timeAction = new Timer(1000, new ActionListener() { //时间监听
		public void actionPerformed(ActionEvent e) {
			long timemillis = System.currentTimeMillis(); //得到系统时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //转换日期显示格式
			varTime.setText(df.format(new Date(timemillis))); //输出得到的时间
		}
	});
	timeAction.start(); //开启线程
	}
}