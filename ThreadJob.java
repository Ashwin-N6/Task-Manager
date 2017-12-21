package com.uttara.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ThreadJob implements Runnable
{

		String data;
		
		public ThreadJob(String data)
		{
			this.data = data;
		}
		
		@Override
		public void run() {
		
			BufferedWriter bw = null;
			
			try
			{
				bw = new BufferedWriter(new FileWriter(Constants.LOGPATH, true));
					bw.write(new Date() + ":" + " Thread name : " + Thread.currentThread().getName() + data);
					bw.newLine();
					bw.flush();
				
				if(Constants.LOGTOMONITOR)
					System.out.println(data);
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(bw != null )
					try 
					{
						bw.close();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
			
			}
		}
		
}
