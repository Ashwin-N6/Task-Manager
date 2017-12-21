package com.uttara.project;

import java.util.List;

public interface Tasks {
		 
		//abstract methods
		
		public boolean checkIfCategoryExists(String name);
		public boolean checkIfTaskNameExist(String taskName);
		public String addTask(TaskBean t, String catName);
		public TaskBean getTaskBean(String taskName, String catName);
		public List<TaskBean> search(String str);
		public List<TaskBean> search(String str, String catName);
		public String update(TaskBean old, TaskBean newTask);
		public String deleteTask(String taskName, String catName);
		public List<TaskBean> getTaskBasedOnNames(String catName);
		public List<TaskBean> getTaskBasedOnNames();
		public List<TaskBean> getTaskBasedOnCrDate(String catName);
		public List<TaskBean> getTaskBasedOnCrDate();
		public List<TaskBean> getTaskBasedOnDueDate(String catName);
		public List<TaskBean> getTaskBasedOnDueDate();
		public List<TaskBean> getTaskBasedOnTimePending(String catName);
		public List<TaskBean> getTaskBasedOnTimePending();
		public String loadCategory(String catName);

		
	}


