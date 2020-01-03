package com.next.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="微信小程序用户对象", description="从客户端，获取到微信用户的数据封装在此entity中")
public class MPWXUserBO {

    @ApiModelProperty(value="昵称",name="nickName",example="NEXT", required=true)
    private String nickName;
    @ApiModelProperty(value="头像",name="avatarUrl",example="http://next.com/abc.png", required=true)
    private String avatarUrl;

    @ApiModelProperty(value="使用哪个小程序[0:NEXT超英预告][1:超英预告][2:NEXT学院电影预告]",name="whichMP",example="0", required=false)
    private Integer whichMP;

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public Integer getWhichMP() {
        return whichMP;
    }
    public void setWhichMP(Integer whichMP) {
        this.whichMP = whichMP;
    }
}
