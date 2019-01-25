package com.cduestc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * �����ߺ������߰���
 */
public class TestProductAndConsumer2 {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		ProductThread pt = new ProductThread(clerk);
		ConsumerThread ct = new ConsumerThread(clerk);
		new Thread(pt).start();
		new Thread(ct).start();
	
	}
}
class Clerk1{
	private int product =0;
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public Clerk1() {
		
	}
	//��������
	public  void getProduct(){
		lock.lock();
		try {
			while(product>=10){
				System.out.println("��Ʒ����");
				try {
					
//					this.wait(); lock�ӿ�����condition�ӿ�ʵ��this.wait�Ĵ���
					condition.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.println(Thread.currentThread().getName()+" : "+ ++product);
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	
	}
	//�ۻ�����
	public void saleProduct(){
		
		lock.lock();
		try {
			while(product<=0){
				System.out.println("û�л���");
				try {
					condition.await();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.println(Thread.currentThread().getName()+"  : "+ --product);
			condition.signalAll();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
		
	}
}

class ProductThread1 implements Runnable{
	
	private Clerk1 clerk;
	public ProductThread1(Clerk1 clerk) {
		// TODO Auto-generated constructor stub
		 this.clerk = clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("��ʼ������Ʒ");
//			try {
//				new Thread().sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			clerk.getProduct();
		}
	}
	
}

class ConsumerThread1 implements Runnable{
	private Clerk1 clerk;
	
	public ConsumerThread1(Clerk1 clerk){
		this.clerk =clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("��ʼ���Ѳ�Ʒ");
//			try {
//				new Thread().sleep(10);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			clerk.saleProduct();
		}
	}
}



