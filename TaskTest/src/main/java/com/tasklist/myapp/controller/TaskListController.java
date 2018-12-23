package com.tasklist.myapp.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.myapp.model.TaskList;
import com.tasklist.myapp.service.TaskListServiceImpl;


@RestController
public class TaskListController {
	
	
	@Autowired
	TaskListServiceImpl taskListServiceImpl;
	
	@RequestMapping(value = "/getAllTasks", method = RequestMethod.GET)
	public List<TaskList> getTasks(@RequestHeader("authorization") String authString) {
		try {
			if(isUserAuthenticated(authString)){
				List<TaskList> listOftasks = taskListServiceImpl.getAllTasks();
				return listOftasks;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getTask/{id}", method = RequestMethod.GET)
	public TaskList getTaskById(@PathVariable("id") int id,@RequestHeader("authorization") String authString) {
		try {
			if(isUserAuthenticated(authString)){
				return taskListServiceImpl.getTask(id);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id);
		return null;
	}

	@RequestMapping(value = "/addTask", method = RequestMethod.POST, headers = "Accept=application/json")
	public void addTask(@RequestBody TaskList task,@RequestHeader("authorization") String authString) {
		try {
			if(!isUserAuthenticated(authString)){
				taskListServiceImpl.addTask(task);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@RequestMapping(value = "/updateTask", method = RequestMethod.PUT, headers = "Accept=application/json")
	public void updateTask(@RequestBody TaskList task,@RequestHeader("authorization") String authString) {
		try {
			if(!isUserAuthenticated(authString)){
				taskListServiceImpl.updateTask(task);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@RequestMapping(value = "/deleteTask/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id,@RequestHeader("authorization") String authString) {
		taskListServiceImpl.deleteTask(id);		
	}	
	
	
	 private boolean isUserAuthenticated(String authString) throws IOException{
         
	        String decodedAuth = "";
	        String[] authParts = authString.split("\\s+");
	        String authInfo = authParts[1];
	        ResourceBundle mybundle = ResourceBundle.getBundle("application");
	        byte[] bytes = null;
	        bytes = Base64.getDecoder().decode(authInfo);
	        decodedAuth = new String(bytes);
	        String[] auth=decodedAuth.split(":");
	        if(auth[0].equalsIgnoreCase(mybundle.getString("username"))&&auth[1].equalsIgnoreCase(mybundle.getString("password")))
	        return true;
	        
			return false;
	    }
}
