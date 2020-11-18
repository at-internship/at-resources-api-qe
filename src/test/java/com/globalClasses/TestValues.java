package com.globalClasses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.github.javafaker.Faker;

public class TestValues{
	Faker faker = new Faker();
	DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDate currentDate = LocalDate.now(ZoneId.of("GMT-5"));
	int randomPlus = (int) (Math.random()*(4-2+1)+2);
	public String randomPriority() {
		String priority = "";
		ArrayList<String> list = new ArrayList<String>();
		list.add("High");		list.add("Medium");		list.add("Low");
		priority = list.get((int) (Math.random()*(2-0+1)+0));
		return priority;
	}
	public String randomName() {
		String name = faker.name().title();
		return name;
	}
	public String randomString() {
		String string = faker.lorem().fixedString(99);
		return string;
	}
	public int randomStoryPoints() {
		int[] storyPoints = {1, 3, 5};
		return storyPoints[(int) (Math.random()*(2-0+1)+0)];
	}
	public int randomProgress() {
		int progress = (int) (Math.random()*(100-0+1)+0);
		return progress;
	}
	public LocalDate createDate() {
		LocalDate createDate = LocalDate.parse(currentDate.format(formater));
		return createDate;
	}
	public LocalDate randomStartDate() {
		LocalDate startDate = LocalDate.parse(currentDate.format(formater)).plusDays(randomPlus);
		return startDate;
	}
	public LocalDate randomDueDate() {
		LocalDate dueDate = LocalDate.parse(currentDate.format(formater)).plusMonths(randomPlus);
		return dueDate;
	}
	public int randomStatus() {
		int status = (int) (Math.random()*(1-0+1)+0);
		return status;
	}
	public String randomID() {
		String id = faker.lorem().characters(24, 25, true, true);
		return id;
	}
}