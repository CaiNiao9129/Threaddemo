package com.cduestc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
 * ����ִ���̵߳ķ�ʽ����ʵ��Callable�ӿ� �����ʽ�����з���ֵ�����ҿ����׳��쳣
 */
public class TestCallable {
	public static void main(String[] args) {
		
		ThreadDemo1 td = new ThreadDemo1();
		
		//	ִ��Callable ��ʽ����ҪFutureTask ʵ�����֧�֣����ڽ���������
		//	FutureTask ��Future�ӿڵ�ʵ����
		FutureTask<Integer> resultFutureTask = new FutureTask<Integer>(td);
	
		new Thread(resultFutureTask).start();
	
		//2�����߳�����Ľ��
		try {
			Integer sum = resultFutureTask.get();//FutureTask �����ڱ���
			System.out.println(sum);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
class ThreadDemo1 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		int sum=0;
		for(int i=0;i<=100;i++){
			System.out.println(i);
			sum+=i;
		}
		return sum;
	}
	
}
