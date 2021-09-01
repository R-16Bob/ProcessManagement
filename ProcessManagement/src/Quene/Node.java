package Quene;

public class Node {  
	public int pid;  //����id
	public int arrivaltime; //����ʱ��
	public int servertime;  //����ʱ��
	public int priority; //���ȼ�
	public int starttime=0; //��ʼʱ��
	public int endtime=0; //����ʱ��
	public int turntime=0; //��תʱ��
	public int state=0;  //����״̬��0������1����,-1������2������
	public double dturntime=0; //��Ȩ��תʱ��

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
		return "����ʱ�䣺" + arrivaltime + ", ����ʱ�䣺" + servertime + ", ���ȼ���"
				+ priority;
	}
	
}

