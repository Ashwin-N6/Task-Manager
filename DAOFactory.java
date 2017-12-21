package com.uttara.project;

public class DAOFactory {

	public static TaskDAO getDAO()
	{
		switch(Constants.DAOTYPE)
		{
			case "file" :
					return new TaskFileDAOImpl();
					
					
			/*
			 * case "db" :
			 *		return new TaskDataBaseDAOImpl();
			 */
		}
		return null;
	}
}