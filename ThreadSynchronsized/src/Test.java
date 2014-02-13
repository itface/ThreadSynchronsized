/**
 * 以下代码执行过程：
 * 1、sst1和sst2对象中的sm属性指向的是同一对象。
 * 2、当sst1.start()执行时，先执行b方法，sst1获得了sm属性所指的对象的锁，所以sst2线程需要等待才能执行b方法。
 * 3、当sst1线程执行完b时，释放sm属性所指的对象的锁，然后sst2线程获得了sm属性所指的对象的锁，执行b方法。
 * 4、因为sst2执行b方法时，只获得了对象的锁，而a方法是StaticMothod.class锁，不受对象锁的影响，所以可以sst1可以获得对象锁，并执行a方法。
 * 5、sst2执行完b方法，释放对象锁，sst1执行完a方法，释放StaticMothod.class锁。
 * 6、sst2获得StaticMothod.class锁，执行a方法，sst1等待StaticMothod.class锁执行a1方法。
 * 7、sst2执行完a方法，释放StaticMothod.class锁,然后sst2获得StaticMothod.class锁，执行a1方法。
 * 8、sst2执行完a1方法，释放StaticMothod.class锁。
 * 9、sst2获得对象锁，执行b1方法，sst1获得StaticMothod.class锁，执行a1方法。
 * 10、sst2执行完b1方法，释放对象锁，sst1执行完a1方法，获得对象锁，执行b1方法。
 * 结论：
 * 1、当前线程调用类的同步静态方法时，其它线程可以进入该类实例的其它非静态方法，不能进入类的其它静态方法。
 * 2、当前进入同步的实例方法时，其它线程可以进入该类的静态方法，但是不能进入该类的其它非静态方法。
 * 原理
 * 静态方法同步是获取StaticSynTest.class锁，而实例方法获取的是该“实例对象”的锁，它们互不干涉。
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