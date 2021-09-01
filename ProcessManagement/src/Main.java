import Quene.Quene;
import Quene.Node;
import java.util.*;

public class Main{
	static Scanner reader=new Scanner(System.in);
	static int sf;  //使用的调度算法
	static Node cur;   //当前运行进程
	static int pid;  
	static int nextpid=1;  //下一个要创建的进程pid
	static int time=0;  //当前时间
	//创建初始进程，并插入就绪队列
	public static void create_processes() {
		System.out.print("输入要创建的进程数：");
		int num=reader.nextInt();
		for(int i=0;i<num;i++) {
			System.out.printf("正在创建第%d个进程：\n",i+1);
			System.out.print("请输入进程优先级：");
			int priority=reader.nextInt();
			System.out.print("请输入进程服务时间：");
			int servertime=reader.nextInt();
			System.out.print("请输入进程到达时间：");
			int arrivaltime = reader.nextInt();
			//创建进程
			Node process = new Node(i+1, priority, servertime, arrivaltime);
			Quene.ready.offer(process);	
			nextpid++;
		}
	}
	//显示进程信息,node为当前运行进程
	public static void show_processes(Node node) {
		System.out.println("-----------当前运行进程------------------");
		System.out.printf("PID\t优先级\t服务时间\t到达时间\n");
		System.out.printf("%d\t%d\t%d\t%d\n",node.getPid(),node.getPriority(),node.getServertime(),node.getArrivaltime());
		Quene.show_ready();
		Quene.show_block();
	}
	
	//菜单操作
	public static int Menu(Node cur) {
		System.out.println("\n请选择需要的功能：");
		System.out.println("1.阻塞当前运行进程");
		System.out.println("2.唤醒进程");
		System.out.println("3.撤销进程");
		System.out.println("4.创建进程");
		System.out.println("5.继续运行");
		int opt=reader.nextInt();
		switch (opt) {
		case 1://阻塞当前运行进程
			cur.state=-1;
			Quene.block.offer(cur);
			System.out.printf("进程PID=%d已被阻塞!\n",cur.getPid());
			break;
		case 2://唤醒进程
			Quene.show_block();
			System.out.print("请输入要唤醒的进程的PID：");
			pid=reader.nextInt();
			//将pid从阻塞队列取出
			Node awake=null;  //存放要唤醒的进程
			for(int i=0;i<Quene.block.size();i++) {
				if(Quene.block.get(i).pid==pid) {
					awake=Quene.block.get(i);
					awake.state=0;
					Quene.block.remove(i);
					Quene.ready.offer(awake);
					System.out.printf("进程PID=%d已被唤醒!\n",awake.getPid());
				}
			}
			if(awake==null) {
				System.out.printf("PID为%d的阻塞进程不存在。\n",pid);	
			}
			break;
		case 3: //撤销进程,
			System.out.print("请输入要撤销的进程的PID：");
			pid=reader.nextInt();
			Node kill=null;  //存放要撤销的进程
			if(pid==cur.pid) {  //撤销运行进程
				kill=cur;
				cur.state=2;
				System.out.printf("进程PID=%d已被撤销!\n",cur.getPid());
			}
			//撤销阻塞进程
			for(int i=0;i<Quene.block.size();i++) {
				if(Quene.block.get(i).pid==pid) {
					kill=Quene.block.get(i);
					kill.state=2;
					Quene.block.remove(i);
					System.out.printf("进程PID=%d已被撤销!\n",pid);
				}
			}
			//撤销就绪进程
			for(int i=0;i<Quene.ready.size();i++) {
				if(Quene.ready.get(i).pid==pid) {
					kill=Quene.ready.get(i);
					kill.state=2;
					Quene.ready.remove(i);
					System.out.printf("进程PID=%d已被撤销!\n",pid);
				}
			}
			if(kill==null) {
				System.out.printf("PID为%d的进程不存在。\n",pid);	
			}
			break;
		case 4: //创建进程
			System.out.print("请输入进程优先级：");
			int priority=reader.nextInt();
			System.out.print("请输入进程服务时间：");
			int servertime=reader.nextInt();
			int arrivaltime;
			while(true) {
				System.out.print("请输入进程到达时间：");
				arrivaltime = reader.nextInt();
				if(arrivaltime>=time)   //防止到达时间早的进程还未被调度。
					break;
				else {
					System.out.printf("当前时间为%d，必须保证到达时间大于等于当前时间。\n",time);
				}
			}
			//创建进程，放入就绪队列
			Node process = new Node(nextpid, priority, servertime, arrivaltime);
			Quene.ready.offer(process);	
			System.out.printf("进程PID=%d已创建\n",nextpid);
			System.out.println(process);
			nextpid++;
			break;	
		case 5: //继续运行
			break;
		default:
			System.out.println("输入错误，请重新输入。\n");
			break;
		}
		return opt;
	}
	
	public static void main(String args[]) {
		create_processes();
		System.out.println("以下进程已成功创建：");
		Quene.show_ready();
		while(true) {
			System.out.println("请选择调度算法：");
			System.out.println("1.先来先服务调度算法");
			System.out.println("2.最高优先数优先调度算法");
			System.out.println("3.短作业优先调度算法");
			sf = reader.nextInt();
			if(sf==1 || sf==2 || sf==3)
				break;
			else
				System.out.println("输入错误，请重新输入\n");
		}
		//按照选择的算法调度一次
		switch (sf) {
		case 1:
			time=Dd.fcfs(Quene.ready);
			break;
		case 2:
			time=Dd.priority(Quene.ready);
			break;
		case 3:
			time=Dd.sjf(Quene.ready);
			break;
		}
		//取要运行的进程
		cur=Quene.ready.poll();
		cur.state=1;  //修改状态为运行
		//显示进程信息
		show_processes(cur);
		
		while(true) {
			int opt=0;
			while(opt!=5) {
				opt=Menu(cur);
			}
			//如果cur可以运行，则执行完成并放入完成队列
			if(cur.state==1) {
				time=Dd.Execute(cur);
			}
			//如果就绪队列为空，则运行结束
			if(Quene.ready.size()==0)
				break;
			//否则继续调度下一个进程
			switch (sf) {
			case 1:
				time=Dd.fcfs(Quene.ready);
				break;
			case 2:
				time=Dd.priority(Quene.ready);
				break;
			case 3:
				time=Dd.sjf(Quene.ready);
				break;
			}
			cur=Quene.ready.poll();
			cur.state=1;  //修改状态为运行
			//显示进程信息
			show_processes(cur);
		}
		//运行结束，显示完成进程的信息
		Quene.show_finish();
	}
}