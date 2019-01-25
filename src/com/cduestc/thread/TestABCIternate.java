package com.cduestc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ��дһ�����򣬿��������̣߳��������̵߳�ID�ֱ���A,B,C,
 * ÿ���߳̽��Լ���ID����Ļ�ϴ�ӡ10�飬Ҫ������Ľ�����밴��˳����ʾ
 * �� abcabcabc��������
 * 
 */
public class TestABCIternate {
	public static void main(String[] args) {
		AlternateDemo aDemo = new AlternateDemo();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++)
					aDemo.loopA();
			}
		},"A").start();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++)
					aDemo.loopB();
			}
		},"B").start();
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<20;i++)
					aDemo.loopC();
			}
		},"C").start();
	}
}
class AlternateDemo{
	private int number =1;
	
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	
	public void loopA(){
		lock.lock();
		try {
			if(number!=1){
				condition1.await();
			}
			System.out.println(Thread.currentThread().getName()+"\t");
			
			number=2;
			condition2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	public void loopB(){
		lock.lock();
		try {
			if(number!=2){
				condition2.await();
			}
			System.out.println(Thread.currentThread().getName()+"\t");
			
			number=3;
			condition3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	public void loopC(){
		lock.lock();
		try {
			if(number!=3){
				condition3.await();
			}
			System.out.println(Thread.currentThread().getName()+"\t");
			
			number=1;
			condition1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}