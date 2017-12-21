package com.uttara.project;

public class TaskUtil 
{
	public static boolean validateName(String name)
	{
		if(name.split(" ").length > 1)
			return false;
		
		if(!Character.isLetter(name.charAt(0)))
			return false;
		
		for(int i = 1; i < name.length(); i++)
		{
			if(!(Character.isLetter(name.charAt(i)) || Character.isDigit(name.charAt(i))))
				return false;
		}
		
		return true;
	}
	
	public static boolean priorityCheck(int priority)
	{
		if(priority <= 0 || priority > 10)
			return false;
		
		else
			return true;
	}
	
	public static boolean statusCheck(String status)
	{
		if(status==null || status.trim().equals(""))
			return false;
		
		if(status.split(" ").length > 1)
			return false;
		
		if(!(status.equalsIgnoreCase("new") || status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("completed")))
			return false;
		
		return true;
	}
	
	public static boolean dateValidate(String str_date)
	{
		String[] sa = str_date.split("/");
		
		int date = Integer.parseInt(sa[0]);
		int month = Integer.parseInt(sa[1]);
		int year = Integer.parseInt(sa[2]);
		
		if(date < 0 || date > 31)
			return false;
		
		if(month == 2 && date > 29)
			return false;
		
		if(month < 0 || month > 12)
			return false;
		
		if(year < 2016)
			return false;
		
		return true;
	}

}
