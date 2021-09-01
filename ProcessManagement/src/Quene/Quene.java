package Quene;

import java.util.LinkedList;

public  class Quene {
	public static  LinkedList<Node> ready=new LinkedList<Node>();       //定义就绪队列
	public static LinkedList<Node> block=new LinkedList<>();       //定义阻塞队列
	public static LinkedList<Node> finish=new LinkedList<>();       //定义完成队列
	
	public static void show_ready() {
		if(ready.size()!=0) {
			System.out.println("-----------就绪队列------------------");
			System.out.printf("PID\t优先级\t服务时间\t到达时间\n");
			for(Node node:ready) {
				System.out.printf("%d\t%d\t%d\t%d\n",node.getPid(),node.getPriority(),node.getServertime(),node.getArrivaltime());
			}
		}
		else {
			System.out.println("当前就绪队列为空。");
		}
	}
	public static void show_block() {
		if(block.size()!=0) {
			System.out.println("-----------阻塞队列------------------");
			System.out.printf("PID\t优先级\t服务时间\t到达时间\n");
			for(Node node:block) {
				System.out.printf("%d\t%d\t%d\t%d\n",node.getPid(),node.getPriority(),node.getServertime(),node.getArrivaltime());
			}
		}
		else {
			System.out.println("当前阻塞队列为空。");
		}
	}
	public static void show_finish() {
		System.out.println("---------------------所有进程运行完成----------------------");
		if(finish.size()!=0) {
			int count=finish.size();
			int turns=0;
			double dturns=0;
			System.out.printf("PID\t优先级\t服务时间\t到达时间\t开始时间\t结束时间\t周转时间\t带权周转时间\n");
			for(Node node:finish) {
				System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%.2f\n",node.getPid(),
						node.getPriority(),node.getServertime(),node.getArrivaltime(),node.starttime,node.endtime,node.turntime,node.dturntime);
				turns+=node.turntime;
				dturns+=node.dturntime;
			}
			System.out.printf("平均周转时间：%.2f，平均带权周转时间：%.2f\n",(double)turns/(double)count,(double)dturns/(double)count);
			
		}
		else {
			System.out.println("当前完成队列为空。");
		}
	}
}
