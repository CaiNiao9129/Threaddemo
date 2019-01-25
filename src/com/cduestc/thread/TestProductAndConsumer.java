package com.cduestc.thread;

/*
 * �����ߺ������߰���
 */
public class TestProductAndConsumer {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		ProductThread pt = new ProductThread(clerk);
		ConsumerThread ct = new ConsumerThread(clerk);
		new Thread(pt).start();
		new Thread(ct).start();
	
	}
}
class Clerk{
	private int product =0;
	
	public Clerk() {
		
	}
	//��������
	public synchronized void getProduct(){
		//������ٻ�������
		while(product >=1){
			System.out.println("����");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//(if else ���������)
		System.out.println(Thread.currentThread().getName()+ " : "+ ++product);
		this.notifyAll();
		
		
	}
	//�ۻ�����
	public synchronized void saleProduct(){
		
		while(product<=0){
			System.out.println("û�л���");
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println(Thread.currentThread().getName()+" : "+ --product);
		this.notifyAll();
	}
}

class ProductThread implements Runnable{
	
	private Clerk clerk;
	public ProductThread(Clerk clerk) {
		// TODO Auto-generated constructor stub
		 this.clerk = clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("��ʼ������Ʒ");
			try {
				new Thread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.getProduct();
		}
	}
	
}

class ConsumerThread implements Runnable{
	private Clerk clerk;
	
	public ConsumerThread(Clerk clerk){
		this.clerk =clerk;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("��ʼ���Ѳ�Ʒ");
			try {
				new Thread().sleep(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
			clerk.saleProduct();
		}
	}
}



