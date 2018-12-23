package com.tasklist.myapp.service;

import java.util.List;

import com.tasklist.myapp.model.TaskList;

public interface TaskListServiceImpl {

	List<TaskList> getAllTasks();

	TaskList getTask(int id);

	void addTask(TaskList task);

	void updateTask(TaskList task);

	void deleteTask(int id);

}
