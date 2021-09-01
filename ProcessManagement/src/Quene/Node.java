package Quene;

public class Node {  
	public int pid;  //进程id
	public int arrivaltime; //到达时间
	public int servertime;  //服务时间
	public int priority; //优先级
	public int starttime=0; //开始时间
	public int endtime=0; //结束时间
	public int turntime=0; //周转时间
	public int state=0;  //进程状态，0就绪，1运行,-1阻塞，2撤销。
	public double dturntime=0; //带权周转时间

	public Node(int pid, int priority, int servertime,int arrivaltime ) {
		this.pid = pid;
		this.arrivaltime = arrivaltime;
		this.servertime = servertime;
		this.priority = priority;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(int arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	public int getServertime() {
		return servertime;
	}
	public void setServertime(int servertime) {
		this.servertime = servertime;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "到达时间：" + arrivaltime + ", 服务时间：" + servertime + ", 优先级："
				+ priority;
	}
	
}

