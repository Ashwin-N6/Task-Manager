package com.uttara.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StartApp 
{
		public static void main(String[] args) {

			try
			{
				int choice = 0;
			
				Scanner sc1 = new Scanner(System.in);
				Scanner sc2 = new Scanner(System.in);
				
				TaskModel model = new TaskModel();
				TaskBean tbean = null;
			
				String nameOfCategory, taskName, task_desc, status, tags;
				Date start_date;
				Date end_date;
				int priority;
				String search;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				TaskBean bean = null;
				
				
				while(choice != 5)
				{
					System.out.println("Press 1 to create Category");
					System.out.println("Press 2 to Load Category");
					System.out.println("Press 3 to Search");
					System.out.println("Press 4 to List");
					System.out.println("Press 5 to Exit");
					System.out.println("Enter Choice :");
					
					while(!sc1.hasNextInt())
					{
						System.out.println("Please enter the correct input choice from 1 to 5");
						sc1.next();
					}
					
					choice = sc1.nextInt();
					
					outer : switch(choice)
					{
					
						case 1 :
								System.out.println("Selected choice : " + choice + " to Create Category..");
								System.out.println("enter the name of category");
								nameOfCategory = sc2.nextLine();
								
								if(!TaskUtil.validateName(nameOfCategory))
								{
									System.out.println("Invalid name, enter the valid name (start with letter, single word, no special chars) ");
									break;
								}
								else
								{
									if(model.checkIfCategoryExists(nameOfCategory))
									{
										System.out.println("Category name " + nameOfCategory + " already exists! Choose other..");
										break;
									}
									else
									{
										//System.out.println("Category name doesn't exist..");
										
										while(choice != 6)
										{
											System.out.println("Press 1 to Add a Task");
											System.out.println("Press 2 to Edit a Task");
											System.out.println("Press 3 to Remove a Task");
											System.out.println("Press 4 to List the Task");
											System.out.println("Press 5 to Search");
											System.out.println("Press 6 to Go back");
											System.out.println("Enter choice :");
											
											
											choice = sc1.nextInt();
											
											switch(choice)
											{
													case 1 :
															System.out.println("Enter the task name");
															taskName = sc2.nextLine();
															
															if(!TaskUtil.validateName(taskName))
															{
																System.out.println("Invalid task name, enter the valid name (start with letter, single word, no special chars) ");
																break;
															}
															else
															{
																
																if(model.checkIfTaskNameExist(taskName)==true)
																{
																	System.out.println("task name " + taskName + " already exist.. Task name should be unique..");
																	break;
																}
																else
																{
																	System.out.println("Enter description of " + taskName);
																	task_desc = sc2.nextLine();
																			
																	start_date = new Date();
																			
																	System.out.println("Enter the end date in the format dd/MM/yyyy");
																	String date2 = sc2.nextLine();
																	end_date = sdf.parse(date2);
																	
																	if(TaskUtil.dateValidate(date2)==false)
																	{
																		System.out.println(" Invalid date...!!! Enter the valid date format(dd/mm/yyyy)");
																		break;
																	}
																	else
																	{		
																		System.out.println("Enter the priority");
																		priority = sc1.nextInt();
																	
																		if(TaskUtil.priorityCheck(priority)==false)
																		{
																			System.out.println("priority should be in the range 1-10");
																			break;
																		}
																		else
																		{
																			System.out.println("Enter the status of " + taskName);
																			status = sc2.nextLine();
																		
																			if(TaskUtil.statusCheck(status)==false)
																			{
																				System.out.println("status should be single word (new, pending, completed)..");
																			}
																			else
																			{
																				System.out.println("Enter the tags(comma separated)");
																				tags = sc2.nextLine();
																			
																				bean = new TaskBean(taskName, task_desc, status, priority, start_date, end_date, tags);
																	
																				if(model.addTask(bean, nameOfCategory).equals("success"))
																					System.out.println("Task added successfully..");
																				else
																					System.out.println("Oops tasks cannot be added successfully..");
																			}
																		}
													
																	}
																}
		
															}
															break;
															
															
													case 2 :
														System.out.println("Selected choice " + choice + " to edit the task");
														System.out.println("enter the task name to edit");
														taskName = sc2.nextLine();
														
														//if(model.checkIfTaskNameExist(taskName)==false)
														//{
															//System.out.println("Task doesn't exist..!! You can only edit the task that exists..!!");
														//}
														//else
														//{
															System.out.println(model.getTaskBean(taskName, nameOfCategory));
															tbean = model.getTaskBean(taskName, nameOfCategory);
														
															while(choice != 4)
															{
																System.out.println("Press 1 to change description!");
																System.out.println("Press 2 to change status");
																System.out.println("Press 3 to change end date");
																System.out.println("Press 4 to go back");
																System.out.println("Enter choice :");
																
																while(!sc1.hasNextInt())
																{
																	System.out.println("Enter the number 1-4");
																	sc1.next();
																}
																
																choice = sc1.nextInt();
																
																switch(choice)
																{
																	case 1 :
																			System.out.println("Enter the description..");
																			task_desc = sc2.nextLine();
																			TaskBean tbean2 = new TaskBean(tbean.getName(), task_desc, tbean.getStatus(), tbean.getPriority(), tbean.getCr_dt(), tbean.getEnd_dt(), tbean.getTags());
																			System.out.println(model.update(tbean, tbean2));
																			break;
																		
																	case 2 :
																			System.out.println("Enter the status..");
																			status = sc2.nextLine();
																			TaskBean tbean3 = new TaskBean(tbean.getName(), tbean.getDesc(), status, tbean.getPriority(), tbean.getCr_dt(), tbean.getEnd_dt(), tbean.getTags());
																			System.out.println(model.update(tbean, tbean3));
																			break;
																		
																	case 3 :
																			System.out.println("Enter the end date");
																			String e_date = sc2.nextLine();
																			end_date = sdf.parse(e_date);
																			
																			TaskBean tbean4 = new TaskBean(tbean.getName(), tbean.getDesc(), tbean.getStatus(), tbean.getPriority(), tbean.getCr_dt(), end_date, tbean.getTags());
																			
																			if(model.update(tbean, tbean4).equals("success"))
																				System.out.println("end date is successfully updated..!");
																			else
																				System.out.println("Oops end date could not be updated successfully..");
																			
																			break;
																		
																	case 4 :
																			System.out.println("going back");
																			break;
																	
																	default :
																			System.out.println("enter the choice 1-5");
																			break;
																}
															}
														//}
														
														break;
															
														
													case 3 :
															System.out.println("Selected choice : " + choice + " to remove the task");
															System.out.println(" Enter the task name to remove..");
															taskName = sc2.nextLine();
															
															if(model.checkIfTaskNameExist(taskName)==false)
															{
																System.out.println("Task name doesnot exist..! you can only remove the task that exists..!!!");
															}
															else
															{
																if(model.deleteTask(taskName, nameOfCategory).equals("success"))
																{
																	System.out.println("Task is successfully removed..!");
																	break;
																}
																else
																	System.out.println("Oops., Task could not be removed successfully..");
																
															}
															break;
															
														
													case 4 :
															System.out.println("Selected choice  " + choice + " to list the tasks" );
															
															while(choice !=5)
															{
																System.out.println("Press 1 to list tasks by alphabetical listing by name");
																System.out.println("Press 2 to list tasks by due date!");
																System.out.println("Press 3 to list tasks by created date");
																System.out.println("Press 4 to list tasks by longest time");
																System.out.println("Press 5 to go back");
																System.out.println("Enter choice :");
																
																while(! sc1.hasNextInt())
																{
																	System.out.println("Enter only numeric number 1-5..");
																	sc1.next();
																}
																
																choice = sc1.nextInt();
																
																switch(choice)
																{
																	case 1 :
																				System.out.println("listing tasks by alphabetical order by name");
																				System.out.println(model.getTaskBasedOnNames(nameOfCategory));
																				break;
																				
																	case 2 :
																				System.out.println("listing tasks by due date");
																				System.out.println(model.getTaskBasedOnDueDate(nameOfCategory));
																				break;
																
																	case 3 :
																				System.out.println("listing tasks by created date");
																				System.out.println(model.getTaskBasedOnCrDate(nameOfCategory));
																				break;
																				
																	case 4 :
																				System.out.println("listing tasks by long time");
																				System.out.println(model.getTaskBasedOnTimePending(nameOfCategory));
																				break;
																				
																	case 5 :
																				System.out.println("going back..");
																				break;
																				
																	default :
																				System.out.println("enter the choice 1-5");
																				break;
																}
															}
															break;
																
														
													case 5 :
															System.out.println("Selected choice : " + choice + "to search..");
															System.out.println("Enter the String to search");
															search = sc2.nextLine();
															List<TaskBean> tb = model.search(search, nameOfCategory);
															int count = 0, countName = 0, countDesc = 0, countTags = 0;
															String listName = "", listDesc = "", listTags = "";
															
															for(TaskBean t : tb)
															{
																//count++;
																
																if(t.getName().contains(search));
																{
																	count++;
																	countName++;
																	listName = listName + " " + t.getName();
																	
																}
																if(t.getDesc().contains(search));
																{
																	count++;
																	countDesc++;
																	listDesc = listDesc+ " "+ t.getName()+"--"+ " " + t.getDesc()+" ";
																}
																if(t.getTags().contains(search))
																{
																	count++;
																	countTags++;
																	listTags = listTags + " " + t.getName()+"--"+t.getTags()+" ";
																}
																	
																
															}
															
															//System.out.println(model.search(search, nameOfCategory));
															
															System.out.println("Total number of occurances : " + count);
															System.out.println("Number of occurances in description : " + countDesc);
															System.out.println("Matches found! : "  );
															System.out.println(listDesc);
															
															System.out.println("Number of occurances in name : " + countName);
															System.out.println(listName);
															
															System.out.println("Number of occurrances in tags : " + countTags);
															System.out.println(listTags);
													
															break;
														
													case 6 :
															break outer;
														
													default :
															System.out.println("Enter the choice from 1-6..");
															break;
													
											}
										}
									}	
								}
								break;
												
								
						case 2 :
								System.out.println("Selected choice : " + choice + " loading Category..");
								System.out.println("Enter the name of the category you wish to load..");
								nameOfCategory = sc2.nextLine();
								if(!model.checkIfCategoryExists(nameOfCategory))
								{
									System.out.println("category " + nameOfCategory + "doesn't exist.. provide a existing category name");
									break;
								}
								else
								{
									while(choice != 6)
									{
										System.out.println("Press 1 to Add a Task");
										System.out.println("Press 2 to Edit a Task");
										System.out.println("Press 3 to Remove a Task");
										System.out.println("Press 4 to List the Task");
										System.out.println("Press 5 to Search");
										System.out.println("Press 6 to Go back");
										System.out.println("Enter choice :");	
										
										while(! sc1.hasNextInt())
										{
											System.out.println("Enter only numeric choice from 1-6..");
											sc1.next();
										}
										
										choice = sc1.nextInt();
										
										switch(choice)
										{
												case 1 :
														System.out.println("Enter the task name");
														taskName = sc2.nextLine();
														
														if(!TaskUtil.validateName(taskName))
														{
															System.out.println("Invalid task name, enter the valid name (start with letter, single word, no special chars) ");
															break;
														}
														else
														{
															
															if(model.checkIfTaskNameExist(taskName)==true)
															{
																System.out.println("task name " + taskName + " already exist.. Task name should be unique..");
																break;
															}
															else
															{
																System.out.println("Enter description of " + taskName);
																task_desc = sc2.nextLine();
														
																start_date = new Date();
														
																System.out.println("Enter the end date in the format dd/MM/yyyy");
																String date2 = sc2.nextLine();
																end_date = sdf.parse(date2);
																
																if(TaskUtil.dateValidate(date2)==false)
																{
																	System.out.println(" Invalid date...!!! Enter the valid date format(dd/mm/yyyy)");
																	break;
																}
																else
																{
														
																	System.out.println("Enter the priority");
																	priority = sc1.nextInt();
																	
																	if(TaskUtil.priorityCheck(priority)==false)
																	{
																		System.out.println("priority should be in the range 1-10");
																		break;
																	}
																	else
																	{
														
																		System.out.println("Enter the status of " + taskName);
																		status = sc2.nextLine();
														
																		System.out.println("Enter the tags(comma separated)");
																		tags = sc2.nextLine();
														
																		bean = new TaskBean(taskName, task_desc, status, priority, start_date, end_date, tags);
																		System.out.println(" Task added : " + model.addTask(bean, nameOfCategory));
																	}
																}
															}
														}
														
														break;
														
												case 2 :
														System.out.println("Selected choice " + choice + " to edit the task");
														System.out.println("enter the task name to edit");
														taskName = sc2.nextLine();
														
														System.out.println(model.getTaskBean(taskName, nameOfCategory));
														tbean = model.getTaskBean(taskName, nameOfCategory);
														
														while(choice != 4)
														{
															System.out.println("Press 1 to change description!");
															System.out.println("Press 2 to change status");
															System.out.println("Press 3 to change end date");
															System.out.println("Press 4 to go back");
															System.out.println("Enter choice :");
															
															while(!sc1.hasNextInt())
															{
																System.out.println("only numeric inputs are allowed..!");
																sc1.next();
															}
															
															choice = sc1.nextInt();
															
															switch(choice)
															{
																case 1 :
																		System.out.println("Enter the description..");
																		task_desc = sc2.nextLine();
																		TaskBean tbean2 = new TaskBean(tbean.getName(), task_desc, tbean.getStatus(), tbean.getPriority(), tbean.getCr_dt(), tbean.getEnd_dt(), tbean.getTags());
																		System.out.println(model.update(tbean, tbean2));
																		break;
																	
																case 2 :
																		System.out.println("Enter the status..");
																		status = sc2.nextLine();
																		TaskBean tbean3 = new TaskBean(tbean.getName(), tbean.getDesc(), status, tbean.getPriority(), tbean.getCr_dt(), tbean.getEnd_dt(), tbean.getTags());
																		System.out.println(model.update(tbean, tbean3));
																		break;
																	
																case 3 :
																		System.out.println("Enter the end date");
																		String e_date = sc2.nextLine();
																		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
																		end_date = sdf.parse(e_date);
																		TaskBean tbean4 = new TaskBean(tbean.getName(), tbean.getDesc(), tbean.getStatus(), tbean.getPriority(), tbean.getCr_dt(), end_date, tbean.getTags());
																		System.out.println(model.update(tbean, tbean4));
																		break;
																	
																case 4 :
																		System.out.println("going back..");
																		break;
																
																default :
																		System.out.println("enter the choice from 1-5");
																		break;
															}
														}
														
														break;
														
													
												case 3 :
														System.out.println("Selected choice " + choice + " to remove the task");
														System.out.println("Enter the task name to delete");
														taskName = sc2.nextLine();
														
														if(model.checkIfTaskNameExist(taskName)==false)
														{
															System.out.println("Task name doesnot exist..! you can only remove the task that exists..!!!");
														}
														else
														{
															if(model.deleteTask(taskName, nameOfCategory).equals("success"))
															{
																System.out.println("task removed successfully..!!");
															}
															else
																System.out.println("task not deleted successfully");
														}
															
													
														break;
													
												case 4 :
														System.out.println("Selected choice  " + choice + " to list the tasks" );
														
														while(choice !=5)
														{
															System.out.println("Press 1 to list tasks by alphabetical listing by name");
															System.out.println("Press 2 to list tasks by due date!");
															System.out.println("Press 3 to list tasks by created date");
															System.out.println("Press 4 to list tasks by longest time");
															System.out.println("Press 5 to go back");
															System.out.println("Enter choice :");
															
															while(! sc1.hasNextInt())
															{
																System.out.println("Enter only numeric values from 1-5..");
																sc1.next();
															}
															
															choice = sc1.nextInt();
															
															switch(choice)
															{
																case 1 :
																			System.out.println("listing tasks by alphabetical order by name");
																			System.out.println(model.getTaskBasedOnNames(nameOfCategory));
																			break;
																			
																case 2 :
																			System.out.println("listing tasks by due date");
																			System.out.println(model.getTaskBasedOnDueDate(nameOfCategory));
																			break;
															
																case 3 :
																			System.out.println("listing tasks by created date");
																			System.out.println(model.getTaskBasedOnCrDate(nameOfCategory));
																			break;
																			
																case 4 :
																			System.out.println("listing tasks by long time");
																			System.out.println(model.getTaskBasedOnTimePending(nameOfCategory));
																			break;
																			
																case 5 :
																			System.out.println("going back..");
																			break;
																			
																default :
																			System.out.println("enter the choice from 1-5");
																			break;
															}
														}
														
														break;
															
											case 5 :
													System.out.println("Selected choice : " + choice + "to search..");
													System.out.println("Enter the task name to search");
													search = sc2.nextLine();
													System.out.println(model.search(search, nameOfCategory));
													
													List<TaskBean> tb = model.search(search, nameOfCategory);
													int count = 0, countName = 0, countDesc = 0, countTags = 0;
													String listName = "", listDesc = "", listTags = "";
													
													for(TaskBean t : tb)
													{
														//count++;
														
														if(t.getName().contains(search));
														{
															count++;
															countName++;
															listName = listName + " " + t.getName();
															
														}
														if(t.getDesc().contains(search));
														{
															count++;
															countDesc++;
															listDesc = listDesc+ " "+ t.getName()+"--"+ " " + t.getDesc()+" ";
														}
														if(t.getTags().contains(search))
														{
															count++;
															countTags++;
															listTags = listTags + " " + t.getName()+"--"+t.getTags()+" ";
														}
															
														
													}
													
													//System.out.println(model.search(search, nameOfCategory));
													
													System.out.println("Total number of occurances : " + count);
													System.out.println("Number of occurances in description : " + countDesc);
													System.out.println("Matches found! : "  );
													System.out.println(listDesc);
													
													System.out.println("Number of occurances in name : " + countName);
													System.out.println(listName);
													
													System.out.println("Number of occurrances in tags : " + countTags);
													System.out.println(listTags);
													break;
												
											case 6 :
													System.out.println("going back..");
													break;
												
											default :
													System.out.println("Enter the choice from 1-6 ");
													break;
											
									
										}
									}
								}
								break;
								
						case 3 :
								System.out.println("Selected choice : " + choice + " searching...");
								System.out.println("Enter the String name to search");
								search = sc2.nextLine();
								System.out.println(model.search(search));
								
								
								break;
								
						case 4 :
								System.out.println("Selected choice : " + choice +  " listing...");
								while(choice !=5)
								{
									System.out.println("Press 1 to list tasks by alphabetical listing by name");
									System.out.println("Press 2 to list tasks by due date!");
									System.out.println("Press 3 to list tasks by created date");
									System.out.println("Press 4 to list tasks by longest time");
									System.out.println("Press 5 to go back");
									System.out.println("Enter choice :");
									
									while(sc1.hasNextInt())
									{
										System.out.println(" Enter only numeric number from 1-5 ");
										sc1.next();
									}
									
									choice = sc1.nextInt();
									
									switch(choice)
									{
										case 1 :
													System.out.println("listing tasks by alphabetical order by name");
													System.out.println(model.getTaskBasedOnNames());
													break;
													
										case 2 :
													System.out.println("listing tasks by due date");
													System.out.println(model.getTaskBasedOnDueDate());
													break;
									
										case 3 :
													System.out.println("listing tasks by created date");
													System.out.println(model.getTaskBasedOnCrDate());
													break;
													
										case 4 :
													System.out.println("listing tasks by long time");
													System.out.println(model.getTaskBasedOnTimePending());
													break;
													
										case 5 :
													System.out.println("going back..");
													break;
													
										default :
													System.out.println("enter the choice from 1-5");
													break;
									}
								}
								break;
								
						case 5 :
								System.out.println("Bye Bye..!!");
								break;	
								
						default :
								System.out.println("enter the choice from 1-5");
								break;
							
					}
				}
			}
			catch(Exception e)
			{			
				e.printStackTrace();
				System.out.println("Oops something bad happened!! " + e.getMessage());
				
				Logger.getInstance().log(e.toString());
			}

		}

	}
