package com.cduestc.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
public class TestScheduledThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService poolExecutorService =Executors.newScheduledThreadPool(5);
		
		for(int i=0;i<10;i++){
			Future<Integer> result = 
					poolExecutorService.schedule(new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
							// TODO Auto-generated method stub
							int num = new Random().nextInt(100);
							System.out.println(Thread.currentThread().getName()+" : "+num);
							return num;
						}
					}, 3, TimeUnit.SECONDS);
			System.out.println(result.get());
		}
		
		poolExecutorService.shutdown();
	}
}
