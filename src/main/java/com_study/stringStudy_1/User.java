package com_study.stringStudy_1;

import java.util.Date;

public class User {

//	private static final int BASIC = 1;
//	private static final int SILVER = 2;
//	private static final int GOLD = 3;

	int level;

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	Date lastUpgraded;
	
	public void setLastUpgraded(Date lastUpgraded) {
		this.lastUpgraded = lastUpgraded;
	}
	

	private String name;
	private String id;
	private String password;
	
	// 5장 서비스 추상화 리스트 5-1 정수형 상수 값으로 정의한 사용자 레벨
	Level LevelInt;
	int Login;
	int Recommend;
	private String email;
	
	public Level getLevelInt() {
		return LevelInt;
	}

	public void setLevelInt(Level LevelInt) {
		this.LevelInt = LevelInt;
	}

	public int getLogin() {
		return Login;
	}

	public void setLogin(int Login) {
		this.Login = Login;
	}

	public int getRecommend() {
		return Recommend;
	}

	public void setRecommend(int recommend) {
		this.Recommend = recommend;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email= email;
	}

	public User(String id, String name, String password, Level LevelInt, int Login, int Recommend, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.LevelInt = LevelInt;
		this.Login = Login;
		this.Recommend = Recommend;
		this.email = email;
	}

	public User() {
		// 자바빈의 규약을 따르는 클래스에 생성자를 명시적으로 추가됐을 때는 파라미터가 없는 디폴트
		// 생성자도 함께 정의해주는 것을 잊지 말자!!!
	}

	@Override
	public String toString() {
		return "User [level=" + level + ", name=" + name + ", id=" + id + ", password=" + password + ", LevelInt="
				+ LevelInt + ", Login=" + Login + ", Recommend=" + Recommend +", email ="+ email + "]";
	}

	// 5장 서비스 추상화 리스트 5-27

	public void upgradeLevel() {
		Level nextLevel = this.LevelInt.nextLevel();
		if (nextLevel == null) {
			throw new IllegalArgumentException(this.LevelInt + "은 업그레이드가 불가합니다.");
		} else {
			this.LevelInt = nextLevel;
			this.lastUpgraded = new Date();
		}

	}

}
