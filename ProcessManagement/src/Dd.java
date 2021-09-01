import java.util.LinkedList;
import Quene.Quene;
import Quene.Node;

public class Dd {
	static int start=0;  //记录当前时间
	
	//运行进程
	public static int Execute(Node p)
	{	
		 p.starttime = start;
	     p.endtime = p.starttime + p.servertime;
	     p.turntime = p.endtime - p.arrivaltime;
	     p.dturntime = (double) p.turntime / (double) p.servertime;
	     //System.out.println("名字:" + p.pid + "、优先级:" + p.priority + "、运行时间:" + p.servertime + "、到达时间" + p.arrivaltime + "、开始时间:" + p.starttime + "、结束时间:" + p.endtime + "、周转时间:" + p.turntime + "、带权周转时间:" + p.dturntime);
	     Quene.finish.offer(p);
	     start=start+p.servertime;
	     return start;
	}
	
	//先来先服务
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
	
	//优先级调度算法
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
	
	//短作业优先
	public static int sjf(LinkedList<Node> p){
		Node min;
		//首先按照fcfs排序，并设置start为最小的arrivaltime
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
		//按照sjf排序
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