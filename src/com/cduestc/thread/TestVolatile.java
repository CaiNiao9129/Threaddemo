package com.cduestc.thread;
/*
 * volatile:�ؼ��� 1:������߳̽��в����������ݵ�ͬʱ�����Ա�֤�ڴ��е����ݿɼ�
 *  �����synchronized ��һ�ֽ�Ϊ��������ͬ������
 *  
 *  ע�⣺
 *  1��volatile�����л�����
 *  2��volatile ���ܱ�֤������ԭ���ԣ����пɼ��Ժ�˳����
 * 	
 */
public class TestVolatile {
	public static void main(String[] args) {
		ThreadDemo tDemo = new ThreadDemo();
		new Thread(tDemo).start();
		
		while(true){
			if(tDemo.isFlag()){
				System.out.println("**************");
				break;
			}
		}
	}
}
class ThreadDemo implements Runnable{
	private volatile boolean flag =false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag=true;
		System.out.println("flag=" + isFlag());
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
