package com.uttara.project;

import java.util.Comparator;

public class LongestTimeComparator implements Comparator<TaskBean> 
{
	public int compare(TaskBean task1, TaskBean task2) {

		long d1 = task1.getCr_dt().getTime();
		long dd1 = task1.getEnd_dt().getTime();
		
		long a = d1-dd1;
		
		long d2 = task2.getCr_dt().getTime();
		long dd2 = task2.getEnd_dt().getTime();
		
		long b = d2-dd2;
		
		return (int)(a-b);

	}
}
