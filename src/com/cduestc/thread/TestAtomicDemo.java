package com.cduestc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * ԭ�ӱ�����java.util.concurrent.atomic�����ṩ�˳��õ�ԭ�ӱ���
 * 	1��volatile��֤�ڴ�ɼ���
 * 	2��CAS�㷨��֤���ݵ�ԭ����
 * 	CAS�㷨��Ӳ�����ڲ��������������ݵ�֧��
 * 	CAS����������������
 * 	�ڴ�ֵV
 *  Ԥ��ֵA
 *  ����ֵB
 *  ��V==A ʱ��V=B���Ž��в��������򲻽����κβ���
 */
public class TestAtomicDemo {
	public static void main(String[] args) {
		AtomicDemo aDemo = new AtomicDemo();
		for(int i=0;i<10;i++){
			new Thread(aDemo).start();
		}
	}

}
class AtomicDemo implements Runnable{
	
	private AtomicInteger serialNumber =new AtomicInteger();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
	}

	public int getSerialNumber() {
		return serialNumber.getAndIncrement();
	}

	
}
