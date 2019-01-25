package com.cduestc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * �̳߳أ��ṩһ���̶߳��У������б��������еȴ�״̬���̡߳�
 * �����˴��������ٶ��⿪��
 * �����̳߳ص���ϵ�ṹ
 * 	java.util.concurrent.Executor:�����̵߳�ʹ������ȵĸ��ӿ�
 * 		|--ExecutorService �ӽӿڣ��̳߳ص���Ҫ�ӿ�
 * 			|--ThreadPoolExecutor �̳߳ص�ʵ����
 * 			|--ScheduleExecutorService �ӽӿڣ������̵߳ĵ���
 * 				|--ScheduledThreadPoolExecutor:�̳���ThreadPoolExecutor��ʵ����ScheduleExecutorService 
 * 
 * ����������Executors
 * ExecutorService newFixedThreadPool():�����̶���С���̳߳�
 * ExecutorService newCachedThreadPool()�������̳߳أ��̳߳ص��������̶������Ը�����Ҫ�Զ��ĸ�������
 * ExecutorService newSingleThreadExecutor():��������̳߳أ��̳߳���ֻ��һ���߳�
 * 
 * ScheduledExecutorService newScheduledThreadPool():�����̶���С���̣߳������ӳٻ�ʱ��ִ������
 * 
 */
public class TestThreadPool {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//����һ���̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(5);
		ThreadPoolDemo tpd = new ThreadPoolDemo();
		
		//�����̷߳�ʽ1��Ϊ�̳߳��е��̷߳�������
		pool.submit(tpd);
		
		//�����̷߳�ʽ2����������߳�
		List<Future<Integer>> futurelList = new ArrayList<Future<Integer>>();
		for(int i=0;i<10;i++){
			
			Future<Integer> future = pool.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					int sum =0;
					for(int i=0;i<100;i++){
						sum+=i;
					}
					return sum;
				}
			});
			futurelList.add(future);
		}
		for(Future<Integer> future:futurelList){
			
			System.out.println(future);
		}
		
		//�ر��̳߳�
//		pool.shutdownNow();�����ر��̳߳أ������߳�����ִ�������
		pool.shutdown();//�ȴ��߳�����ִ������ٹر��̳߳�
	}

}
class ThreadPoolDemo implements Runnable{
	
	private int i=0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(i<100){
			System.out.println(Thread.currentThread().getName()+" : "+ i++);
		}
		
	}
	
	
}