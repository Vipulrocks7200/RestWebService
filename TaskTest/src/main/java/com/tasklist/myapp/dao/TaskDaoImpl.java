package com.tasklist.myapp.dao;

import java.util.List;

import com.tasklist.myapp.model.TaskList;

public interface TaskDaoImpl {

	List<TaskList> getAllTasks();

	TaskList getTask(int id);

	void addTask(TaskList task);

	void updateTask(TaskList task);

	void deleteTask(int id);

}
