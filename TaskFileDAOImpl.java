package com.uttara.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TaskFileDAOImpl implements TaskDAO{
	
	@Override
	public boolean checkIfCategoryExists(String name) {

		File f = new File(Constants.PATH+name+".tasks");
		
		return f.exists();
	}
	
	@Override
	public boolean checkIfTaskNameExist(String taskName) 
	{
		
		BufferedReader br = null;		
		
		try
		{
			File f = new File(Constants.PATH);
			
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
				
				
				for(File ff : fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					
					String line;
					
					
					while((line = br.readLine()) != null)
					{
						String[] sa = line.split(":");
						if(sa[0].equals(taskName))
							return true;
					}
				}
				
			}
			
			return false;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return false;
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public String loadCategory(String catName) {

		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader(Constants.PATH+catName+".tasks"));
			
			return Constants.SUCCESS;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public String addTask(TaskBean bean, String catName) {
	
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(Constants.PATH+catName+".tasks", true));
			bw.write(bean.getName()+":"+bean.getDesc()+":"+bean.getStatus()+":"+bean.getPriority()+":"+bean.getS_dt()+":"+bean.getE_dt()+":"+bean.getTags()+":"+ new Date().getTime());
			bw.newLine();
			bw.flush();
			
			return Constants.SUCCESS;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return "Oops something bad happened..! " + e.getMessage();
		}
		finally
		{
			if(bw != null)
				try 
				{
					bw.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		
	}
	
	@Override
	
	
	public List<TaskBean> getTaskBasedOnNames(String catName) {

		List<TaskBean> list = new ArrayList<TaskBean>();
		
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader(Constants.PATH+catName+".tasks"));
			String line;
			
			while((line = br.readLine()) != null)
			{
				String[] sa = line.split(":");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
				Date s1 = null;
				try {
					s1 = sdf1.parse(sa[4]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date s2 = null;
				try {
					s2 = sdf2.parse(sa[5]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
				
				list.add(bean);
			}
						
			
			Collections.sort(list);		
			
			return list;
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	@Override
	public List<TaskBean> getTaskBasedOnDueDate(String catName) {
	
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader(Constants.PATH+catName+".tasks"));
			String line;
			
			while((line = br.readLine()) != null)
			{
				String[] sa = line.split(":");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
				Date s1 = null;
				try {
					s1 = sdf1.parse(sa[4]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date s2 = null;
				try {
					s2 = sdf2.parse(sa[5]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				
				TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
				
				list.add(bean);
			}
			
			Collections.sort(list, new DueDateComparator());
			
			return list;
			
			
		}		
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null; 
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public List<TaskBean> getTaskBasedOnCrDate(String catName) {

		List<TaskBean> list = new ArrayList<TaskBean>();
		
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader(Constants.PATH+catName+".tasks"));
			String line;
			
			while((line = br.readLine()) != null)
			{
				String[] sa = line.split(":");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
				Date s1 = null;
				try {
					s1 = sdf1.parse(sa[4]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date s2 = null;
				try {
					s2 = sdf2.parse(sa[5]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				
				TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
				
				list.add(bean);
			}
			
			Collections.sort(list, new CreatedDateComparator());
			
			return list;
			
			
		}		
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null; 
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
	}
	
	@Override
	public List<TaskBean> getTaskBasedOnTimePending(String catName) {
	
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader(Constants.PATH+catName+".tasks"));
			String line;
			
			while((line = br.readLine()) != null)
			{
				String[] sa = line.split(":");
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
				Date s1 = null;
				try {
					s1 = sdf1.parse(sa[4]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date s2 = null;
				try {
					s2 = sdf2.parse(sa[5]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
				
				TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
				
				list.add(bean);
			}
			
			Collections.sort(list, new LongestTimeComparator());
			
			return list;
			
			
		}		
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null; 
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
		
		
	}
	
	@Override
	public String deleteTask(String taskName, String catName) 
	{
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		List<TaskBean> list = new ArrayList<TaskBean>();
		TaskBean bean = null;
		
		File f = new File(Constants.PATH+catName+".tasks");
		
		try
		{
			br = new BufferedReader(new FileReader(f));
			
			String line;
			
			while((line = br.readLine()) != null)
			{
				if(!(line.startsWith(taskName)))
				{
					String[] sa = line.split(":");
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				
					Date d1 = null;
					try {
						d1 = sdf1.parse(sa[4]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Date d2 = null;
					try {
						d2 = sdf2.parse(sa[5]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), d1, d2, sa[5]);
					
					list.add(bean);
					
				}
			}
			
			bw = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
			
			for(TaskBean tb : list)
			{
				bw.write(tb.getName()+":"+tb.getDesc()+":"+tb.getStatus()+":"+tb.getPriority()+":"+tb.getS_dt()+":"+tb.getE_dt()+":"+tb.getTags()+":"+new Date().getTime());
				bw.newLine();
				bw.flush();
			}
		
			return Constants.SUCCESS;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try 
				{
					br.close();
				} 
				catch (IOException e) 
				{
						// TODO Auto-generated catch block
						e.printStackTrace();						
				}
			if(bw != null)
				try 
				{
					bw.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
		
	
	@Override
	public List<TaskBean> search(String str) 
	{
		
		BufferedReader br = null;
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		try
		{			
			File f = new File(Constants.PATH);
			
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
				
				for(File ff : fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					String line;
					
					while((line = br.readLine()) != null)
					{
						if(line.contains(str))
						{
							String[] sa = line.split(":");
							
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
							SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
				
							Date s1 = null;
							try 
							{
								s1 = sdf1.parse(sa[4]);
							} 
							catch (ParseException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Date s2 = null;
							try 
							{
								s2 = sdf2.parse(sa[5]);
							} 
							catch (ParseException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
										
							
							TaskBean tbean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
							
							list.add(tbean);
						}					
					}
					
				}						
				
				
			}
			return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public List<TaskBean> search(String str, String catName) {
		
		BufferedReader br = null;
		
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		File f = new File(Constants.PATH+catName+".tasks");
		
		try
		{
			if(f.exists() && f.isFile() && f.getName().endsWith(".tasks"))
			{
				br = new BufferedReader(new FileReader(f));
				
				String line;
				
				while((line = br.readLine()) != null)
				{
					if(line.contains(str))
					{
						String[] sa = line.split(":");
						
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
						Date s1 = null;
						try 
						{
							s1 = sdf1.parse(sa[4]);
						} 
						catch (ParseException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						Date s2 = null;
						try 
						{
							s2 = sdf2.parse(sa[5]);
						} 
						catch (ParseException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
						
						TaskBean tbean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
						
						list.add(tbean);
					}
				}
			}
			return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public List<TaskBean> getTaskBasedOnCrDate() {
		
		BufferedReader br = null;
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		File f = new File(Constants.PATH);
		
		try
		{
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
			
				for(File ff : fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					
					String line;
					
					while((line = br.readLine()) != null)
					{
						String[] sa = line.split(":");
						
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
						Date s1 = null;
						try {
							s1 = sdf1.parse(sa[4]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Date s2 = null;
						try {
							s2 = sdf2.parse(sa[5]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
						
						TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
						
						list.add(bean);
						
					}
				
				}
			}
			
			Collections.sort(list, new CreatedDateComparator());
			
			return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	
	@Override
	public List<TaskBean> getTaskBasedOnNames() {
		
		BufferedReader br = null;
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		File f = new File(Constants.PATH);
		
		try
		{
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
			
				for(File ff : fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					
					String line;
					
					while((line = br.readLine()) != null)
					{
						String[] sa = line.split(":");
						
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
						Date s1 = null;
						try {
							s1 = sdf1.parse(sa[4]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Date s2 = null;
						try {
							s2 = sdf2.parse(sa[5]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
						
						TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
						
						list.add(bean);
						
					}
				
				}
			}
			
			Collections.sort(list);
			
			return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public List<TaskBean> getTaskBasedOnDueDate() {
		
		BufferedReader br = null;
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		File f = new File(Constants.PATH);
		
		try
		{
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
			
				for(File ff : fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					
					String line;
					
					while((line = br.readLine()) != null)
					{
						String[] sa = line.split(":");
						
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
						Date s1 = null;
						try {
							s1 = sdf1.parse(sa[4]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Date s2 = null;
						try {
							s2 = sdf2.parse(sa[5]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
						
						TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
						
						list.add(bean);
						
					}
				
				}
			}
			
			Collections.sort(list, new DueDateComparator());
			
			return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public List<TaskBean> getTaskBasedOnTimePending() {
		
		BufferedReader br = null;
		List<TaskBean> list = new ArrayList<TaskBean>();
		
		File f = new File(Constants.PATH);
		
		try
		{
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
			
				for(File ff : fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					
					String line;
					
					while((line = br.readLine()) != null)
					{
						String[] sa = line.split(":");
						
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
						Date s1 = null;
						try {
							s1 = sdf1.parse(sa[4]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Date s2 = null;
						try {
							s2 = sdf2.parse(sa[5]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
						
						TaskBean bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
						
						list.add(bean);
						
					}
				
				}
			}
			
			Collections.sort(list, new LongestTimeComparator());
			
			return list;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public TaskBean getTaskBean(String taskName, String catName) {
		
		BufferedReader br = null;
		
		TaskBean bean = null;
		
		try
		{
			br = new BufferedReader(new FileReader(Constants.PATH+catName+".tasks"));
			
			String line = null;
			
			while((line = br.readLine()) != null)
			{
				if(line.startsWith(taskName))
				{
					String[] sa = line.split(":");
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		
					Date s1 = null;
					try 
					{
						s1 = sdf1.parse(sa[4]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Date s2 = null;
					try 
					{
						s2 = sdf2.parse(sa[5]);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
								
					
					bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
				}
			}
						
			return bean;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	
	@Override
	public String update(TaskBean old, TaskBean newTask) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		List<TaskBean> list = new ArrayList<TaskBean>();
		TaskBean bean = null;
		File f = new File(Constants.PATH);
		
		try
		{
			
			if(f.exists() && f.isDirectory())
			{
				File[] fa = f.listFiles();
				
				for(File ff :  fa)
				{
					br = new BufferedReader(new FileReader(ff.getAbsolutePath()));
					//bw = new BufferedWriter(new FileWriter(ff.getAbsolutePath()));
					
					String line;
					
					while((line = br.readLine()) != null)
					{
						String[] sa = line.split(":");
						
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			
						Date s1 = null;
						try 
						{
							s1 = sdf1.parse(sa[4]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Date s2 = null;
						try 
						{
							s2 = sdf2.parse(sa[5]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
									
						
						bean = new TaskBean(sa[0], sa[1], sa[2], Integer.parseInt(sa[3]), s1, s2, sa[6]);
						
						if(bean.getName().equals((newTask).getName()) || bean.getName().equals((newTask).getName()) || bean.getE_dt().equals((newTask).getE_dt()))
						{
							list.add(newTask);
						}
						else
							list.add(bean);
						
					}
					bw = new BufferedWriter(new FileWriter(ff.getAbsolutePath()));
					
					for(TaskBean tb : list)
					{
						bw.write(tb.getName()+":"+tb.getDesc()+":"+tb.getStatus()+":"+tb.getPriority()+":"+tb.getS_dt()+":"+tb.getE_dt()+":"+tb.getTags()+":"+new Date().getTime());
						bw.newLine();
						bw.flush();
					}
				}
			}
			
			
			return Constants.SUCCESS;
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			return null;
		}
		finally
		{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	
	
	
	
}
