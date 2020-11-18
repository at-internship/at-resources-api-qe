package com.globalClasses;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class MongoDBUtils {
    public static boolean existID(String env, String mDataBase, String collection, String id) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        boolean exist = db.compareID(collection, id);
        db.close();
        return exist;
    }

    public static String getRandomID(String env, String mDataBase, String collection) {
        MongoDBConnection db = new MongoDBConnection(env, mDataBase);
        String id = db.foundRandomID(collection);
        db.close();
        return id;
    }

    public static String setFormatDate(String dates) {
        Long mili_date = Long.parseLong(dates);
        Date date = new Date(mili_date);
        SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");
        format_date.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date_formated = format_date.format(date);

        return date_formated;
    }

    public static JSONObject cleanJson(JSONObject mongoStory) {
        if (mongoStory.has("_id")) {
            String id = mongoStory.getJSONObject("_id").getString("$oid");
            mongoStory.remove("_id");
            mongoStory.put("id", id);
        } else {
            mongoStory.put("id", JSONObject.NULL);
        }

        if (mongoStory.has("sprint_id")) {
            String sprint_id = mongoStory.getJSONObject("sprint_id").getString("$oid");
            mongoStory.remove("sprint_id");
            mongoStory.put("sprintId", sprint_id);
        } else {
            mongoStory.put("sprintId", JSONObject.NULL);
        }


        if (mongoStory.has("user_id")) {
            String user_id = mongoStory.getJSONObject("user_id").getString("$oid");
            mongoStory.remove("user_id");
            mongoStory.put("userId", user_id);
        } else {
            mongoStory.put("userId", JSONObject.NULL);
        }

        if (mongoStory.has("story_points")) {
            int story_points = (int) mongoStory.get("story_points");
            mongoStory.remove("story_points");
            mongoStory.put("storyPoints", story_points);
        } else {
            mongoStory.put("storyPoints", JSONObject.NULL);
        }

        if (mongoStory.has("create_date")) {
            String create_date = MongoDBUtils.setFormatDate(mongoStory.getJSONObject("create_date").get("$date").toString());
            mongoStory.remove("create_date");
            mongoStory.put("createDate", create_date);
        } else {
            mongoStory.put("createDate", JSONObject.NULL);
        }

        if (mongoStory.has("due_date")) {
            String due_date = MongoDBUtils.setFormatDate(mongoStory.getJSONObject("due_date").get("$date").toString());
            mongoStory.remove("due_date");
            mongoStory.put("dueDate", due_date);
        } else {
            mongoStory.put("dueDate", JSONObject.NULL);
        }

        if (mongoStory.has("start_date")) {
            String start_date = MongoDBUtils.setFormatDate(mongoStory.getJSONObject("start_date").get("$date").toString());
            mongoStory.remove("start_date");
            mongoStory.put("startDate", start_date);
        } else {
            mongoStory.put("startDate", JSONObject.NULL);
        }

        if (mongoStory.has("priority")) {
            String[] priorityList = {"HIGH", "MEDIUM", "LOW"};
            String priority = priorityList[parseInt(mongoStory.get("priority").toString()) - 1];
            mongoStory.remove("priority");
            mongoStory.put("priority", priority);
        } else {
            mongoStory.put("priority", JSONObject.NULL);
        }


        if (mongoStory.has("status")) {
            int status = (int) mongoStory.get("status");
            mongoStory.remove("status");
            mongoStory.put("status", status);
        } else {
            mongoStory.put("status", JSONObject.NULL);
        }

        if (mongoStory.has("acceptance_criteria")) {
            String acceptance_criteria = mongoStory.get("acceptance_criteria").toString();
            mongoStory.remove("acceptance_criteria");
            mongoStory.put("acceptanceCriteria", acceptance_criteria);
        } else {
            mongoStory.put("acceptanceCriteria", JSONObject.NULL);
        }

        if (mongoStory.has("name")) {
            String name = mongoStory.getString("name");
            mongoStory.remove("name");
            mongoStory.put("name", name);
        } else {
            mongoStory.put("name", JSONObject.NULL);
        }

        mongoStory.remove("_class");
        return mongoStory;
    }
}
