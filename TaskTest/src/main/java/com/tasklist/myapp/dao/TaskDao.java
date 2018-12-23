package com.tasklist.myapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tasklist.myapp.model.TaskList;


@Repository
public class TaskDao implements TaskDaoImpl{

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List<TaskList> getAllTasks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<TaskList> TaskList = session.createQuery("from TaskList").list();
		return TaskList;
	}

	public TaskList getTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		TaskList task = (TaskList) session.load(TaskList.class, new Integer(id));
		return task;
	}

	public void addTask(TaskList task) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(task);
		
	}

	public void updateTask(TaskList task) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(task);
	}

	public void deleteTask(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		TaskList p = (TaskList) session.load(TaskList.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}	
}
