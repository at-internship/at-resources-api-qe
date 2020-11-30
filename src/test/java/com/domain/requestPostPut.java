package com.domain;

import org.json.JSONObject;

import lombok.Data;

@Data
public class requestPostPut {
	private String id;
	private String priority; 
	private String name; 
	private String description;
	private String acceptanceCriteria;
	private String createDate;
	private String startDate;
	private String dueDate;
	private int storyPoints;
	private int progress;
	private int status;

	public String requestBody() { 
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("priority", priority);
		obj.put("name", name);
		obj.put("description", description);
		obj.put("acceptanceCriteria", acceptanceCriteria);
		obj.put("storyPoints", storyPoints);
		obj.put("progress", progress);
		obj.put("startDate", startDate);
		obj.put("dueDate", dueDate);
		obj.put("createDate", createDate);
		obj.put("status", status);
		return obj.toString();
	}
}
