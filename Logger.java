package com.uttara.project;

public class Logger {
	private Logger()
	{
		
	}
	
	private static Logger obj = null;
	public synchronized static Logger getInstance()
	{
		if(obj == null)
		{
			obj = new Logger();
		}
		return obj;
	}
	
	public void log(final String data)
	{
		ThreadJob job = new ThreadJob(data);
		Thread t = new Thread(job);
		t.start();
		
	}
}
