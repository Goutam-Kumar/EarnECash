package com.earnecash.android.login.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class LoginResponse{

	@SerializedName("result")
	private int result;

	@SerializedName("msg")
	private String msg;

	@SerializedName("user_data")
	private UserData userData;

	public void setResult(int result){
		this.result = result;
	}

	public int getResult(){
		return result;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setUserData(UserData userData){
		this.userData = userData;
	}

	public UserData getUserData(){
		return userData;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			",user_data = '" + userData + '\'' + 
			"}";
		}
}