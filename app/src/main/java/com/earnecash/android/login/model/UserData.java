package com.earnecash.android.login.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class UserData{

	@SerializedName("profession")
	private String profession;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private String phone;

	@SerializedName("other_profession")
	private String otherProfession;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("unique_code")
	private String uniqueCode;

	@SerializedName("email")
	private String email;

	@SerializedName("refer_code")
	private String referCode;

	@SerializedName("status")
	private String status;

	public void setProfession(String profession){
		this.profession = profession;
	}

	public String getProfession(){
		return profession;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setOtherProfession(String otherProfession){
		this.otherProfession = otherProfession;
	}

	public String getOtherProfession(){
		return otherProfession;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUniqueCode(String uniqueCode){
		this.uniqueCode = uniqueCode;
	}

	public String getUniqueCode(){
		return uniqueCode;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setReferCode(String referCode){
		this.referCode = referCode;
	}

	public String getReferCode(){
		return referCode;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UserData{" + 
			"profession = '" + profession + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",other_profession = '" + otherProfession + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",unique_code = '" + uniqueCode + '\'' + 
			",email = '" + email + '\'' + 
			",refer_code = '" + referCode + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}