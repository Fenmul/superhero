package com.next.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户对象", description="从客户端，由用户传入的数据封装在此entity中")
public class RegistLoginUsersBO {

	@ApiModelProperty(value="用户名",name="username",example="jack", required=true)
    private String username;

	@ApiModelProperty(value="密码",name="password",example="123456", required=true)
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}