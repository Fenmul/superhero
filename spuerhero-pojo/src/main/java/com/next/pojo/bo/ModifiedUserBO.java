package com.next.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户修改信息对象", description="用户修改的昵称/生日/性别等数据封装在此entity中")
public class ModifiedUserBO {

    @ApiModelProperty(value="用户id",name="userId",example="20190101oweidjaw", required=true)
    private String userId;

    @ApiModelProperty(value="性别",name="sex",example="1", required=false)
    private Integer sex;
    @ApiModelProperty(value="昵称",name="nickName",example="NEXT", required=false)
    private String nickname;
    @ApiModelProperty(value="生日",name="birthday",example="1900-01-01", required=false)
    private String birthday;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
