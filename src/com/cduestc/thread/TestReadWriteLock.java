package com.cduestc.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * ��д��ReadWriteLock
 * дд/��д����
 */
public class TestReadWriteLock {
	public static void main(String[] args) {
		ReadWriteLockDemo rw = new ReadWriteLockDemo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<5;i++)
					rw.set((int) Math.random());
			}
		},"Write:").start();
		
		for(int i=0;i<100;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					rw.get();
				}
			},"read:").start();
		}
	}
}
class ReadWriteLockDemo{
	private int number;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	//������
	public void get(){
//		����
		lock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+" : "+number);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.readLock().unlock();
		}
	}
	
	//д����
	public void set(int number){
		//д��
		lock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+" : ");
			this.number = number;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.writeLock().unlock();
		}
	}
	
}