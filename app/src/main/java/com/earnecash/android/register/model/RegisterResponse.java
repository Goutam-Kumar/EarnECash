package com.earnecash.android.register.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class RegisterResponse{

	@SerializedName("result")
	private String result;

	@SerializedName("msg")
	private String msg;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"result = '" + result + '\'' + 
			",msg = '" + msg + '\'' + 
			"}";
		}
}