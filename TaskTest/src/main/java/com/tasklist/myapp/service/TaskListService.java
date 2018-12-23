package com.tasklist.myapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasklist.myapp.dao.TaskDaoImpl;
import com.tasklist.myapp.model.TaskList;

@Service("TaskListService")
public class TaskListService implements TaskListServiceImpl {

	@Autowired
	TaskDaoImpl taskDaoImpl;
	
	@Transactional
	public List<TaskList> getAllTasks() {
		return taskDaoImpl.getAllTasks();
	}

	@Transactional
	public TaskList getTask(int id) {
		return taskDaoImpl.getTask(id);
	}

	@Transactional
	public void addTask(TaskList task) {
		taskDaoImpl.addTask(task);
	}

	@Transactional
	public void updateTask(TaskList task) {
		taskDaoImpl.updateTask(task);

	}

	@Transactional
	public void deleteTask(int id) {
		taskDaoImpl.deleteTask(id);
	}
}
