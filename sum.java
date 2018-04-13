import java.util.*;
public class sum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char sel='1';
		while(sel!='5')
		{
			System.out.println("请选择难度《1》.困难    《2》.简单  《3》.容易    《4》.入门   《5》.退出\n:");
			@SuppressWarnings("resource")
			Scanner in=new Scanner(System.in);
	        int as=in.nextInt();
	        sum ss = new sum();
	        sel=(char) as;
	        switch(sel)
	        {
	        case 1: ss.print();
	        break;
	        case 2: ss.prints();
	        break;
	        case 3: ss.printss();
	        break;
	        case 4: ss.printsss();
	        break;
	        case 5: ss.exit();
	        break;
	        default:System.out.println("错误！");
	        System.exit(sel);
	        	}
	        }
	}
	public void print(){
		Random sum=new Random();
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int i=1,temp=0,e;
		int[] zh;
		int[] ch;
		zh=new int[999];
		ch=new int[999];
		boolean isContinued = false;
		do
		{
			isContinued = false;
			System.out.println("请输入你要测试的题目数量:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(100);
				int b=sum.nextInt(100);
				int c=sum.nextInt(100);
				System.out.printf("第"+i+"题为"+a+"+"+b+"+"+c+"=");
				int j=in.nextInt();
				rel=a+b+c;
		        zh[d]=rel;
		        ch[d]=j;
		        if(a+b+c==j)
		        temp=temp+1;
			}
			e=(temp*100)/n;
		    if(e>=0&&e<60)
		    {
		    	System.out.printf("\t共%d题得:%d分.你太牛了……\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t共%d题得:%d分.继续努力……\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t共%d题得:%d分.不错……\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t共%d题得:%d分.太屌了！\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("第%d题答案是:%d\n",k,zh[k]);
		        }
			}
			System.out.println("亲，你是否要继续测试(Y/N)?");
			char input = in.next().charAt(0);
        	if (input == 'Y'||input=='y') 
        	{
            	isContinued = true;
        	}
        	else if (input == 'N'||input=='n')
        	{
            	isContinued = false;
        	}
        	else
        	{
            	System.out.println("您的输入无法被识别，默认结束！");
        	}
    	} while (isContinued);
	}
	public void prints()
	{
		Random sum=new Random();
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int i=1,temp=0,e;
		int[] zh;
		int[] ch;
		zh=new int[999];
		ch=new int[999];
		boolean isContinued = false;
		do
		{
			isContinued = false;
			System.out.println("请输入你要测试的题目数量:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(100);
				int b=sum.nextInt(100);
				System.out.printf("第"+i+"题为"+a+"+"+b+"=");
				int j=in.nextInt();
				rel=a+b;
		        zh[d]=rel;
		        ch[d]=j;
		        if(a+b==j)
		        temp=temp+1;
			}
			e=(temp*100)/n;
		    if(e>=0&&e<60)
		    {
		    	System.out.printf("\t共%d题得:%d分.你牛……\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t共%d题得:%d分.继续努力……\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t共%d题得:%d分.不错……\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t共%d题得:%d分.太屌了！\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("第%d题答案是:%d\n",k,zh[k]);
		        }
			}
			System.out.println("亲，你是否要继续测试(Y/N)?");
			char input = in.next().charAt(0);
        	if (input=='y'||input=='Y') 
        	{
            	isContinued = true;
        	}
        	else if (input=='n'||input=='N')
        	{
            	isContinued = false;
        	}
        	else
        	{
            	System.out.println("您的输入无法被识别，默认结束！");
        	}
    	} while (isContinued);
	}
	public void printss()
	{
		Random sum=new Random();
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int i=1,temp=0,e;
		int[] zh;
		int[] ch;
		zh=new int[999];
		ch=new int[999];
		boolean isContinued = false;
		do
		{
			isContinued = false;
			System.out.println("请输入你要测试的题目数量:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(50);
				int b=sum.nextInt(50);
				System.out.printf("第"+i+"题为"+a+"+"+b+"=");
				int j=in.nextInt();
				rel=a+b;
		        zh[d]=rel;
		        ch[d]=j;
		        if(a+b==j)
		        temp=temp+1;
			}
			e=(temp*100)/n;
		    if(e>=0&&e<60)
		    {
		    	System.out.printf("\t共%d题得:%d分.你牛……\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t共%d题得:%d分.继续努力……\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t共%d题得:%d分.不错……\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t共%d题得:%d分.太屌了！\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("第%d题答案是:%d\n",k,zh[k]);
		        }
			}
			System.out.println("亲，你是否要继续测试(Y/N)?");
			char input = in.next().charAt(0);
        	if (input=='y'||input=='Y') 
        	{
            	isContinued = true;
        	}
        	else if (input=='n'||input=='N')
        	{
            	isContinued = false;
        	}
        	else
        	{
            	System.out.println("您的输入无法被识别，默认结束！");
        	}
    	} while (isContinued);
	}
	public void printsss()
	{
		Random sum=new Random();
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		int i=1,temp=0,e;
		int[] zh;
		int[] ch;
		zh=new int[999];
		ch=new int[999];
		boolean isContinued = false;
		do
		{
			isContinued = false;
			System.out.println("请输入你要测试的题目数量:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(10);
				int b=sum.nextInt(10);
				System.out.printf("第"+i+"题为"+a+"+"+b+"=");
				int j=in.nextInt();
				rel=a+b;
		        zh[d]=rel;
		        ch[d]=j;
		        if(a+b==j)
		        temp=temp+1;
			}
			e=(temp*100)/n;
		    if(e>=0&&e<60)
		    {
		    	System.out.printf("\t共%d题得:%d分.你牛……\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t共%d题得:%d分.继续努力……\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t共%d题得:%d分.不错……\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t共%d题得:%d分.太屌了！\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("第%d题答案是:%d\n",k,zh[k]);
		        }
			}
			System.out.println("亲，你是否要继续测试(Y/N)?\n");
			char input = in.next().charAt(0);
        	if (input=='y'||input=='Y') 
        	{
            	isContinued = true;
        	}
        	else if (input=='n'||input=='N')
        	{
            	isContinued = false;
        	}
        	else
        	{
            	System.out.println("您的输入无法被识别，默认结束！");
        	}
    	} while (isContinued);
	}
	public void exit(){
		System.exit(0);
		System.out.println();
	}
	
}
