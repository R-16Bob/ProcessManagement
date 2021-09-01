package Quene;

import java.util.LinkedList;

public  class Quene {
	public static  LinkedList<Node> ready=new LinkedList<Node>();       //�����������
	public static LinkedList<Node> block=new LinkedList<>();       //������������
	public static LinkedList<Node> finish=new LinkedList<>();       //������ɶ���
	
	public static void show_ready() {
		if(ready.size()!=0) {
			System.out.println("-----------��������------------------");
			System.out.printf("PID\t���ȼ�\t����ʱ��\t����ʱ��\n");
			for(Node node:ready) {
				System.out.printf("%d\t%d\t%d\t%d\n",node.getPid(),node.getPriority(),node.getServertime(),node.getArrivaltime());
			}
		}
		else {
			System.out.println("��ǰ��������Ϊ�ա�");
		}
	}
	public static void show_block() {
		if(block.size()!=0) {
			System.out.println("-----------��������------------------");
			System.out.printf("PID\t���ȼ�\t����ʱ��\t����ʱ��\n");
			for(Node node:block) {
				System.out.printf("%d\t%d\t%d\t%d\n",node.getPid(),node.getPriority(),node.getServertime(),node.getArrivaltime());
			}
		}
		else {
			System.out.println("��ǰ��������Ϊ�ա�");
		}
	}
	public static void show_finish() {
		System.out.println("---------------------���н����������----------------------");
		if(finish.size()!=0) {
			int count=finish.size();
			int turns=0;
			double dturns=0;
			System.out.printf("PID\t���ȼ�\t����ʱ��\t����ʱ��\t��ʼʱ��\t����ʱ��\t��תʱ��\t��Ȩ��תʱ��\n");
			for(Node node:finish) {
				System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%.2f\n",node.getPid(),
						node.getPriority(),node.getServertime(),node.getArrivaltime(),node.starttime,node.endtime,node.turntime,node.dturntime);
				turns+=node.turntime;
				dturns+=node.dturntime;
			}
			System.out.printf("ƽ����תʱ�䣺%.2f��ƽ����Ȩ��תʱ�䣺%.2f\n",(double)turns/(double)count,(double)dturns/(double)count);
			
		}
		else {
			System.out.println("��ǰ��ɶ���Ϊ�ա�");
		}
	}
}
