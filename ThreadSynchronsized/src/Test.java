/**
 * ���´���ִ�й��̣�
 * 1��sst1��sst2�����е�sm����ָ�����ͬһ����
 * 2����sst1.start()ִ��ʱ����ִ��b������sst1�����sm������ָ�Ķ������������sst2�߳���Ҫ�ȴ�����ִ��b������
 * 3����sst1�߳�ִ����bʱ���ͷ�sm������ָ�Ķ��������Ȼ��sst2�̻߳����sm������ָ�Ķ��������ִ��b������
 * 4����Ϊsst2ִ��b����ʱ��ֻ����˶����������a������StaticMothod.class�������ܶ�������Ӱ�죬���Կ���sst1���Ի�ö���������ִ��a������
 * 5��sst2ִ����b�������ͷŶ�������sst1ִ����a�������ͷ�StaticMothod.class����
 * 6��sst2���StaticMothod.class����ִ��a������sst1�ȴ�StaticMothod.class��ִ��a1������
 * 7��sst2ִ����a�������ͷ�StaticMothod.class��,Ȼ��sst2���StaticMothod.class����ִ��a1������
 * 8��sst2ִ����a1�������ͷ�StaticMothod.class����
 * 9��sst2��ö�������ִ��b1������sst1���StaticMothod.class����ִ��a1������
 * 10��sst2ִ����b1�������ͷŶ�������sst1ִ����a1��������ö�������ִ��b1������
 * ���ۣ�
 * 1����ǰ�̵߳������ͬ����̬����ʱ�������߳̿��Խ������ʵ���������Ǿ�̬���������ܽ������������̬������
 * 2����ǰ����ͬ����ʵ������ʱ�������߳̿��Խ������ľ�̬���������ǲ��ܽ������������Ǿ�̬������
 * ԭ��
 * ��̬����ͬ���ǻ�ȡStaticSynTest.class������ʵ��������ȡ���Ǹá�ʵ�����󡱵��������ǻ������档
 * @author Administrator
 *
 */
public class Test extends Thread{

		 StaticMothod sm;
		 public Test(StaticMothod sm){
		  this.sm = sm;
		 }
		 public static void main(String[] args) {
		  StaticMothod sm1 = new StaticMothod();
		  Test sst1 = new Test(sm1);
		  Test sst2 = new Test(sm1);
		  Test sst3 = new Test(sm1);
		  sst1.setName("sst1");
		  sst2.setName("sst2");
		  sst3.setName("sst3");
		  
		  sst1.start();
		  sst2.start();
		  //sst3.start();
		 }
		 
		 public void run(){
		  this.sm.b(Thread.currentThread().getName());
		  StaticMothod.a(Thread.currentThread().getName());
		  StaticMothod.a1(Thread.currentThread().getName());
		  this.sm.b1(Thread.currentThread().getName());
		  //StaticMothod.a(Thread.currentThread().getName());
		  
		  
		 }
}
class StaticMothod{
	 public synchronized static void a(String name){
	  System.out.println(name+":a in");
	  try{
	   Thread.sleep(2000);
	   System.out.println("");
	  }
	  catch(InterruptedException e){
	   
	  }
	  System.out.println(name+":a out");
	 }
	 public synchronized static void a1(String name){
	  System.out.println(name+":a1 in");
	  try{
	   Thread.sleep(2000);
	   System.out.println("");
	  }
	  catch(InterruptedException e){
	   
	  }
	  System.out.println(name+":a1 out");
	 }
	 public synchronized void b(String name){
	  System.out.println(name+":b in");
	  try{
	   Thread.sleep(2000);
	   System.out.println("");
	  }
	  catch(InterruptedException e){
	   
	  }
	  System.out.println(name+":b out");
	 }
	 public synchronized void b1(String name){
	  System.out.println(name+":b1 in");
	  try{
	   Thread.sleep(2000);
	   System.out.println("");
	  }
	  catch(InterruptedException e){
	   
	  }
	  System.out.println(name+":b1 out");
	 }
}