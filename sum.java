import java.util.*;
public class sum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char sel='1';
		while(sel!='5')
		{
			System.out.println("��ѡ���Ѷȡ�1��.����    ��2��.��  ��3��.����    ��4��.����   ��5��.�˳�\n:");
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
	        default:System.out.println("����");
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
			System.out.println("��������Ҫ���Ե���Ŀ����:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(100);
				int b=sum.nextInt(100);
				int c=sum.nextInt(100);
				System.out.printf("��"+i+"��Ϊ"+a+"+"+b+"+"+c+"=");
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
		    	System.out.printf("\t��%d���:%d��.��̫ţ�ˡ���\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t��%d���:%d��.����Ŭ������\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t��%d���:%d��.������\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t��%d���:%d��.̫���ˣ�\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("��%d�����:%d\n",k,zh[k]);
		        }
			}
			System.out.println("�ף����Ƿ�Ҫ��������(Y/N)?");
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
            	System.out.println("���������޷���ʶ��Ĭ�Ͻ�����");
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
			System.out.println("��������Ҫ���Ե���Ŀ����:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(100);
				int b=sum.nextInt(100);
				System.out.printf("��"+i+"��Ϊ"+a+"+"+b+"=");
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
		    	System.out.printf("\t��%d���:%d��.��ţ����\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t��%d���:%d��.����Ŭ������\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t��%d���:%d��.������\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t��%d���:%d��.̫���ˣ�\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("��%d�����:%d\n",k,zh[k]);
		        }
			}
			System.out.println("�ף����Ƿ�Ҫ��������(Y/N)?");
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
            	System.out.println("���������޷���ʶ��Ĭ�Ͻ�����");
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
			System.out.println("��������Ҫ���Ե���Ŀ����:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(50);
				int b=sum.nextInt(50);
				System.out.printf("��"+i+"��Ϊ"+a+"+"+b+"=");
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
		    	System.out.printf("\t��%d���:%d��.��ţ����\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t��%d���:%d��.����Ŭ������\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t��%d���:%d��.������\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t��%d���:%d��.̫���ˣ�\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("��%d�����:%d\n",k,zh[k]);
		        }
			}
			System.out.println("�ף����Ƿ�Ҫ��������(Y/N)?");
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
            	System.out.println("���������޷���ʶ��Ĭ�Ͻ�����");
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
			System.out.println("��������Ҫ���Ե���Ŀ����:");
			int n=in.nextInt();
			int d,rel;
			for(i=1,d=1;i<=n;i++,d++)
			{
				int a=sum.nextInt(10);
				int b=sum.nextInt(10);
				System.out.printf("��"+i+"��Ϊ"+a+"+"+b+"=");
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
		    	System.out.printf("\t��%d���:%d��.��ţ����\n",n,e);
		    }
		    else if(e>=60&&e<90)
		    {
		    	System.out.printf("\t��%d���:%d��.����Ŭ������\n",n,e);
		    }
		   else if(e>=90&&e<100)
		    {
			   System.out.printf("\t��%d���:%d��.������\n",n,e);
		    }
		   else if(e==100)
		   {
			   System.out.printf("\t��%d���:%d��.̫���ˣ�\n",n,e);
		    }
		    for(int k=0;k<100;k++)
		   {
				if(zh[k]!=ch[k])
				{
					System.out.printf("��%d�����:%d\n",k,zh[k]);
		        }
			}
			System.out.println("�ף����Ƿ�Ҫ��������(Y/N)?\n");
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
            	System.out.println("���������޷���ʶ��Ĭ�Ͻ�����");
        	}
    	} while (isContinued);
	}
	public void exit(){
		System.exit(0);
		System.out.println();
	}
	
}
