import Quene.Quene;
import Quene.Node;
import java.util.*;

public class Main{
	static Scanner reader=new Scanner(System.in);
	static int sf;  //ʹ�õĵ����㷨
	static Node cur;   //��ǰ���н���
	static int pid;  
	static int nextpid=1;  //��һ��Ҫ�����Ľ���pid
	static int time=0;  //��ǰʱ��
	//������ʼ���̣��������������
	public static void create_processes() {
		System.out.print("����Ҫ�����Ľ�������");
		int num=reader.nextInt();
		for(int i=0;i<num;i++) {
			System.out.printf("���ڴ�����%d�����̣�\n",i+1);
			System.out.print("������������ȼ���");
			int priority=reader.nextInt();
			System.out.print("��������̷���ʱ�䣺");
			int servertime=reader.nextInt();
			System.out.print("��������̵���ʱ�䣺");
			int arrivaltime = reader.nextInt();
			//��������
			Node process = new Node(i+1, priority, servertime, arrivaltime);
			Quene.ready.offer(process);	
			nextpid++;
		}
	}
	//��ʾ������Ϣ,nodeΪ��ǰ���н���
	public static void show_processes(Node node) {
		System.out.println("-----------��ǰ���н���------------------");
		System.out.printf("PID\t���ȼ�\t����ʱ��\t����ʱ��\n");
		System.out.printf("%d\t%d\t%d\t%d\n",node.getPid(),node.getPriority(),node.getServertime(),node.getArrivaltime());
		Quene.show_ready();
		Quene.show_block();
	}
	
	//�˵�����
	public static int Menu(Node cur) {
		System.out.println("\n��ѡ����Ҫ�Ĺ��ܣ�");
		System.out.println("1.������ǰ���н���");
		System.out.println("2.���ѽ���");
		System.out.println("3.��������");
		System.out.println("4.��������");
		System.out.println("5.��������");
		int opt=reader.nextInt();
		switch (opt) {
		case 1://������ǰ���н���
			cur.state=-1;
			Quene.block.offer(cur);
			System.out.printf("����PID=%d�ѱ�����!\n",cur.getPid());
			break;
		case 2://���ѽ���
			Quene.show_block();
			System.out.print("������Ҫ���ѵĽ��̵�PID��");
			pid=reader.nextInt();
			//��pid����������ȡ��
			Node awake=null;  //���Ҫ���ѵĽ���
			for(int i=0;i<Quene.block.size();i++) {
				if(Quene.block.get(i).pid==pid) {
					awake=Quene.block.get(i);
					awake.state=0;
					Quene.block.remove(i);
					Quene.ready.offer(awake);
					System.out.printf("����PID=%d�ѱ�����!\n",awake.getPid());
				}
			}
			if(awake==null) {
				System.out.printf("PIDΪ%d���������̲����ڡ�\n",pid);	
			}
			break;
		case 3: //��������,
			System.out.print("������Ҫ�����Ľ��̵�PID��");
			pid=reader.nextInt();
			Node kill=null;  //���Ҫ�����Ľ���
			if(pid==cur.pid) {  //�������н���
				kill=cur;
				cur.state=2;
				System.out.printf("����PID=%d�ѱ�����!\n",cur.getPid());
			}
			//������������
			for(int i=0;i<Quene.block.size();i++) {
				if(Quene.block.get(i).pid==pid) {
					kill=Quene.block.get(i);
					kill.state=2;
					Quene.block.remove(i);
					System.out.printf("����PID=%d�ѱ�����!\n",pid);
				}
			}
			//������������
			for(int i=0;i<Quene.ready.size();i++) {
				if(Quene.ready.get(i).pid==pid) {
					kill=Quene.ready.get(i);
					kill.state=2;
					Quene.ready.remove(i);
					System.out.printf("����PID=%d�ѱ�����!\n",pid);
				}
			}
			if(kill==null) {
				System.out.printf("PIDΪ%d�Ľ��̲����ڡ�\n",pid);	
			}
			break;
		case 4: //��������
			System.out.print("������������ȼ���");
			int priority=reader.nextInt();
			System.out.print("��������̷���ʱ�䣺");
			int servertime=reader.nextInt();
			int arrivaltime;
			while(true) {
				System.out.print("��������̵���ʱ�䣺");
				arrivaltime = reader.nextInt();
				if(arrivaltime>=time)   //��ֹ����ʱ����Ľ��̻�δ�����ȡ�
					break;
				else {
					System.out.printf("��ǰʱ��Ϊ%d�����뱣֤����ʱ����ڵ��ڵ�ǰʱ�䡣\n",time);
				}
			}
			//�������̣������������
			Node process = new Node(nextpid, priority, servertime, arrivaltime);
			Quene.ready.offer(process);	
			System.out.printf("����PID=%d�Ѵ���\n",nextpid);
			System.out.println(process);
			nextpid++;
			break;	
		case 5: //��������
			break;
		default:
			System.out.println("����������������롣\n");
			break;
		}
		return opt;
	}
	
	public static void main(String args[]) {
		create_processes();
		System.out.println("���½����ѳɹ�������");
		Quene.show_ready();
		while(true) {
			System.out.println("��ѡ������㷨��");
			System.out.println("1.�����ȷ�������㷨");
			System.out.println("2.������������ȵ����㷨");
			System.out.println("3.����ҵ���ȵ����㷨");
			sf = reader.nextInt();
			if(sf==1 || sf==2 || sf==3)
				break;
			else
				System.out.println("�����������������\n");
		}
		//����ѡ����㷨����һ��
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
		//ȡҪ���еĽ���
		cur=Quene.ready.poll();
		cur.state=1;  //�޸�״̬Ϊ����
		//��ʾ������Ϣ
		show_processes(cur);
		
		while(true) {
			int opt=0;
			while(opt!=5) {
				opt=Menu(cur);
			}
			//���cur�������У���ִ����ɲ�������ɶ���
			if(cur.state==1) {
				time=Dd.Execute(cur);
			}
			//�����������Ϊ�գ������н���
			if(Quene.ready.size()==0)
				break;
			//�������������һ������
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
			cur.state=1;  //�޸�״̬Ϊ����
			//��ʾ������Ϣ
			show_processes(cur);
		}
		//���н�������ʾ��ɽ��̵���Ϣ
		Quene.show_finish();
	}
}