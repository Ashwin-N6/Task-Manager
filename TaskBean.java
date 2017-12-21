package com.uttara.project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class TaskBean implements Comparable<TaskBean>, Serializable{
	
	private String name;
	private String desc;
	private String s_dt;
	private String e_dt;
	private Date cr_dt;
	private Date end_dt;
	private int due_dt;
	private int priority;
	private String status;
	private Set<String> tags = new HashSet<String>();
	
	public String getS_dt()
	{
		return s_dt;
	}
	
	public String getE_dt()
	{
		return e_dt;
	}
	
	public void getE_dt(Date d2)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s2 = sdf.format(d2);
		this.e_dt = s2;		
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public void setDesc(String desc) 
	{
		this.desc = desc;
	}

	public void setCr_dt(Date cr_dt) 
	{
		this.cr_dt = cr_dt;
	}

	public void setEnd_dt(Date end_dt) 
	{
		this.end_dt = end_dt;
	}

	public void setDue_dt(int due_dt) 
	{
		this.due_dt = due_dt;
	}

	public void setPriority(int priority) 
	{
		this.priority = priority;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public void setTags(Set<String> tags) 
	{
		this.tags = tags;
	}	

	public String getName() 
	{
		return name;
	}
	
	public String getDesc() 
	{
		return desc;
	}
	
	public Date getCr_dt() 
	{
		return cr_dt;
	}
	
	public Date getEnd_dt() 
	{
		return end_dt;
	}
	
	public int getPriority() 
	{
		return priority;
	}
	
	public String getStatus() 
	{
		return status;
	}
	
	public int getDue_dt() 
	{
		return due_dt;
	}

	public String getTags() 
	{
		String a = "";
		for(String s : tags)
			a = a+s+",";
		
		return a;
		
	}

	//public no-arg const
	public TaskBean() 
	{
		super();

	}

	//param const
	public TaskBean(String name, String desc, String status, int priority, Date start_dt, Date end_date, String tags)
	{
		super();
		this.name = name;
		this.desc = desc;
		this.cr_dt = start_dt;
		this.end_dt = end_date;
		this.priority = priority;
		this.status = status;
		
		String[] sa = tags.split(",");
		
		for(String s : sa)
		{
			this.tags.add(s);
		}
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		String s1 = sdf1.format(start_dt);
		this.s_dt = s1;
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		String s2 = sdf2.format(end_date);
		this.e_dt = s2;		
		
		validate();
	}
	
	@Override
	public boolean equals(Object arg0) {
		
		if(arg0 instanceof TaskBean)
		{
			TaskBean bean = (TaskBean)arg0;
			
			if(this.name.equals(bean.name) && this.desc.equals(bean.desc) && this.cr_dt==bean.cr_dt && this.end_dt==bean.end_dt 
					&& this.priority==bean.priority && this.status.equals(bean.status) )
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	@Override
	public int hashCode() {

		return (name+desc+cr_dt.toString()+end_dt.toString()+priority+status).hashCode();
	}
	
	@Override
	public String toString() {
			
		return " TaskBean : [ " +name +" , "+desc+" , "+cr_dt.toString()+ " , "+end_dt.toString()+" , "+priority+" , "+status+" , "+ tags+ " ] "; 
	}
	
	@Override
	public int compareTo(TaskBean t) 
	{	
		return (this.name.compareTo(t.getName()));
	}
	
	private void validate()
	{
		if(name != null && name.trim().equals(""))
			throw new IllegalArgumentException("bad input");
		
		if(desc.isEmpty())
			throw new IllegalArgumentException("desc cannot be blank or null");
		
		if(status != null && status.trim().equals(""))
			throw new IllegalArgumentException("bad input status");
		
		if(priority < 0 || priority > 10)
			throw new IllegalArgumentException("priority should be in between 1-10");
		
		if(cr_dt == null)
			throw new IllegalArgumentException("start date cannot be null");
		
		if(end_dt == null)
			throw new IllegalArgumentException("end date cannot be null");
			
	}
}