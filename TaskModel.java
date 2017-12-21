package com.uttara.project;

import java.util.List;


public class TaskModel implements Tasks{
	

		public boolean checkIfCategoryExists(String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
				return dao.checkIfCategoryExists(catName);		
			
		}
		
		//to add task
		public String addTask(TaskBean bean, String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.addTask(bean, catName);
		}
		
		public TaskBean getTaskBean(String taskName, String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBean(taskName, catName);
		}
		
		public List<TaskBean> getTaskBasedOnNames(String catName) 
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnNames(catName);
		}
		
		public List<TaskBean> getTaskBasedOnNames()
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnNames();
		}
		
		
		public List<TaskBean> search(String str)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.search(str);
		}
		
		public List<TaskBean> search(String str, String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.search(str, catName);
		}
		
		public String update(TaskBean old, TaskBean newBean)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.update(old, newBean);
		}
		
		public String deleteTask(String taskName, String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.deleteTask(taskName, catName);
		}
		
		public List<TaskBean> getTaskBasedOnCrDate(String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnCrDate(catName);
		}
		
		public List<TaskBean> getTaskBasedOnCrDate()
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnCrDate();
		}	
		
		public List<TaskBean> getTaskBasedOnDueDate(String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnDueDate(catName);
		}	
		
		public List<TaskBean> getTaskBasedOnDueDate()
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnDueDate();
		}	
		
		public List<TaskBean> getTaskBasedOnTimePending(String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnTimePending(catName);
		}
		
		public List<TaskBean> getTaskBasedOnTimePending()
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.getTaskBasedOnTimePending();
		}
		
		public String loadCategory(String catName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.loadCategory(catName);
		}
		
		public boolean checkIfTaskNameExist(String taskName)
		{
			TaskDAO dao = DAOFactory.getDAO();
			return dao.checkIfTaskNameExist(taskName);
		}
		
	}
