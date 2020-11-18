package com.domain;

import org.json.JSONObject;

import lombok.Data;

@Data
public class RequestPOST {
	private String priority; 
	private String name; 
	private String description;
	private String aCriteria;
	private String createDate;
	private String startDate;
	private String dueDate;
	private int sPoints;
	private int progress;
	private int status;

	public String requestBody() { 
		JSONObject obj = new JSONObject();
		obj.put("priority", priority);
		obj.put("name", name);
		obj.put("description", description);
		obj.put("acceptanceCriteria", aCriteria);
		obj.put("storyPoints", sPoints);
		obj.put("progress", progress);
		obj.put("startDate", startDate);
		obj.put("dueDate", dueDate);
		obj.put("createDate", createDate);
		obj.put("status", status);
		return obj.toString();
	}
}
