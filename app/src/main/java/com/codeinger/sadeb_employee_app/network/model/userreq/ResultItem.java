package com.codeinger.sadeb_employee_app.network.model.userreq;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("date")
	private String date;

	@SerializedName("image")
	private String image;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("service_name")
	private String serviceName;

	@SerializedName("provider_user_id")
	private String providerUserId;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("end_time")
	private String endTime;

	@SerializedName("lon")
	private String lon;

	@SerializedName("provider_details")
	private ProviderDetails providerDetails;

	@SerializedName("start_time")
	private String startTime;

	@SerializedName("date_time")
	private String dateTime;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("service_id")
	private String serviceId;

	@SerializedName("provider_id")
	private String providerId;

	@SerializedName("provider_user_name")
	private String providerUserName;

	@SerializedName("id")
	private String id;

	@SerializedName("email")
	private String email;

	@SerializedName("lat")
	private String lat;

	@SerializedName("status")
	private String status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}

	public String getServiceName(){
		return serviceName;
	}

	public void setProviderUserId(String providerUserId){
		this.providerUserId = providerUserId;
	}

	public String getProviderUserId(){
		return providerUserId;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public String getEndTime(){
		return endTime;
	}

	public void setLon(String lon){
		this.lon = lon;
	}

	public String getLon(){
		return lon;
	}

	public void setProviderDetails(ProviderDetails providerDetails){
		this.providerDetails = providerDetails;
	}

	public ProviderDetails getProviderDetails(){
		return providerDetails;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public String getStartTime(){
		return startTime;
	}

	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}

	public String getDateTime(){
		return dateTime;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setServiceId(String serviceId){
		this.serviceId = serviceId;
	}

	public String getServiceId(){
		return serviceId;
	}

	public void setProviderId(String providerId){
		this.providerId = providerId;
	}

	public String getProviderId(){
		return providerId;
	}

	public void setProviderUserName(String providerUserName){
		this.providerUserName = providerUserName;
	}

	public String getProviderUserName(){
		return providerUserName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
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
			"ResultItem{" + 
			"date = '" + date + '\'' + 
			",image = '" + image + '\'' + 
			",user_name = '" + userName + '\'' + 
			",service_name = '" + serviceName + '\'' + 
			",provider_user_id = '" + providerUserId + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",end_time = '" + endTime + '\'' + 
			",lon = '" + lon + '\'' + 
			",provider_details = '" + providerDetails + '\'' + 
			",start_time = '" + startTime + '\'' + 
			",date_time = '" + dateTime + '\'' + 
			",user_id = '" + userId + '\'' + 
			",service_id = '" + serviceId + '\'' + 
			",provider_id = '" + providerId + '\'' + 
			",provider_user_name = '" + providerUserName + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",lat = '" + lat + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}