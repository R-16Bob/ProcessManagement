import java.util.LinkedList;
import Quene.Quene;
import Quene.Node;

public class Dd {
	static int start=0;  //��¼��ǰʱ��
	
	//���н���
	public static int Execute(Node p)
	{	
		 p.starttime = start;
	     p.endtime = p.starttime + p.servertime;
	     p.turntime = p.endtime - p.arrivaltime;
	     p.dturntime = (double) p.turntime / (double) p.servertime;
	     //System.out.println("����:" + p.pid + "�����ȼ�:" + p.priority + "������ʱ��:" + p.servertime + "������ʱ��" + p.arrivaltime + "����ʼʱ��:" + p.starttime + "������ʱ��:" + p.endtime + "����תʱ��:" + p.turntime + "����Ȩ��תʱ��:" + p.dturntime);
	     Quene.finish.offer(p);
	     start=start+p.servertime;
	     return start;
	}
	
	//�����ȷ���
	public static int fcfs(LinkedList<Node> p){		
		Node min;
		for(int i=0;i<p.size();i++){
			min=p.get(i);
			for(int j=i+1;j<p.size();j++){
				if(p.get(j).getArrivaltime()<min.getArrivaltime()){
					min=p.get(j);
					p.set(j, p.get(i));
					p.set(i, min);
				}
			}
			if(min.arrivaltime>start&&i==0) {
				start=min.arrivaltime;
			}
		}
		return start;
	}
	
	//���ȼ������㷨
	public static int priority(LinkedList<Node> p){	
		Node max;
		Node min;
		for(int i=0;i<p.size();i++){
			min=p.get(i);
			for(int j=i+1;j<p.size();j++){
				if(p.get(j).getArrivaltime()<min.getArrivaltime()){
					min=p.get(j);
					p.set(j, p.get(i));
					p.set(i, min);
				}
			}
			if(min.arrivaltime>start&&i==0) {
				start=min.arrivaltime;
			}
		}
		
		for(int i=0;i<p.size();i++){
			max=p.get(i);
			for(int j=i+1;j<p.size();j++){
				if(p.get(j).getPriority()>max.getPriority()&&p.get(j).arrivaltime<=start){
					max=p.get(j);
					p.set(j, p.get(i));
					p.set(i, max);
				}
			}
		}
		return start;
	}
	
	//����ҵ����
	public static int sjf(LinkedList<Node> p){
		Node min;
		//���Ȱ���fcfs���򣬲�����startΪ��С��arrivaltime
		for(int i=0;i<p.size();i++) {
			min=p.get(i);
			for(int j=i+1;j<p.size();j++) {
				if(p.get(j).arrivaltime<=p.get(i).arrivaltime) {
					min=p.get(j);
					p.set(j,p.get(i));
					p.set(i, min);
				}
			}
			if(min.arrivaltime>start&&i==0) {
				start=min.arrivaltime;
			}
		}
		//����sjf����
		for(int i=0;i<p.size();i++) {
			min=p.get(i);
			for(int j=i+1;j<p.size();j++) {
				if(p.get(j).servertime<=min.servertime&&p.get(j).arrivaltime<=start) {
					min=p.get(j);
					p.set(j,p.get(i));
					p.set(i, min);
				}
			}
		}
		return start;
	}
}