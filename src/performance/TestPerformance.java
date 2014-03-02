package performance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

public class TestPerformance {

	@Test
	public void test_Array_LinkedList()
	{
		int size = 10000000;
		
		long addToArrayListTime=0,addToLinkedListTime=0;
		
		long iteratorToArrayListTime=0,iteratorToLinkedListTime=0;
		
		ArrayList<Integer> arraylist = new ArrayList<Integer>(10);
		LinkedList<Integer> linkedlist = new LinkedList<Integer>();
		long starttime=0,endtime=0;
		starttime = System.currentTimeMillis();
		for(int index=0;index<size;index++)
		{
			arraylist.add(index);
		}
		endtime = System.currentTimeMillis();
		
		addToArrayListTime = endtime-starttime;
		
		
		starttime = System.currentTimeMillis();
		for(int index=0;index<size;index++)
		{
			linkedlist.push(index);
		}
		endtime = System.currentTimeMillis();
		
		addToLinkedListTime = endtime-starttime;
		
		
		starttime = System.currentTimeMillis();
		for(int index=0;index<10;index++)
		{
			arraylist.get(index);
		}
		endtime = System.currentTimeMillis();
		
		
		iteratorToArrayListTime = endtime-starttime;
		
		starttime = System.currentTimeMillis();
		/*
		Iterator<Integer> iterator = linkedlist.iterator();
		while(iterator.hasNext())
		{
			iterator.next();
		}*/
		while(!linkedlist.isEmpty())
		{
			linkedlist.pop();
		}
		endtime = System.currentTimeMillis();
		
		iteratorToLinkedListTime = endtime-starttime;
		
		
		System.out.println("addToArrayListTime:"+addToArrayListTime+"\n"+
							"addToLinkedListTime:"+addToLinkedListTime+"\n"+
							"iteratorToArrayListTime:"+iteratorToArrayListTime+"\n"+
							"iteratorToLinkedListTime:"+iteratorToLinkedListTime);
		
		
	}
}
