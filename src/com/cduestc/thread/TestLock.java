package com.cduestc.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ���ڽ�����̰߳�ȫ����ķ�ʽ
 * synchronized:
 * 1��ͬ�������:
 * 2��ͬ��������
 * 
 * jdk 1.5��
 * 3��ͬ����Lock
 * ��һ����ʾ������Ҫͨ��lock()��������������ͨ��unlock()���������ͷ���
 * 
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket,"һ�Ŵ���").start();
		new Thread(ticket,"���Ŵ���").start();
		new Thread(ticket,"���Ŵ���").start();
		new Thread(ticket,"�ĺŴ���").start();
	}
}
class Ticket implements Runnable{

	private int ticket =100;
	
	private Lock lock = new ReentrantLock();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			lock.lock();//����
			
			try {
				if(ticket>0){
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				
					System.out.println(Thread.currentThread().getName()+" �����Ʊ�����Ϊ: "+--ticket);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				lock.unlock();//�ͷ���
				}
				
			}
		}
}	

