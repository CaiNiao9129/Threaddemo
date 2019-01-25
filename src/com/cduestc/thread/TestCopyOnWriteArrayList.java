package com.cduestc.thread;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/*
 * CopyOnWriteArrayListд�벢����(�ʺϵ�����ѯ,���ʺϺܶ���Ӳ���)
 */
public class TestCopyOnWriteArrayList {
	public static void main(String[] args) {
		HelloThread hThread = new HelloThread();
		for(int i=0;i<10;i++){
			new Thread(hThread).start();
		}
	}
}
class HelloThread implements Runnable{
	
//	private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
	private static List<String> list = new CopyOnWriteArrayList<String>();
	
	static{
		list.add("aaaa");
		list.add("bbbb");
		list.add("ccccc");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Iterator<String> iterator = list.iterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
			list.add("AAAA");
		}
	}
	
	
}
