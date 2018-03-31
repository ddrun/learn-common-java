package com.example.djran.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回对象
 *
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "统一返回对象")
public class DataRet<T> implements Serializable {

    @ApiModelProperty(notes = "错误编码,只有错误时候返回")
    private String code;
    @ApiModelProperty(notes = "处理结果信息")
    private String message;
    @ApiModelProperty(notes = "返回结果")
    private T body;
    @ApiModelProperty(notes = "登录token，正常接口该字段为空")
    private String token;

    public DataRet(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DataRet(T body) {
        this.body = body;
    }

    @ApiModelProperty(notes = "处理结果是否正常，异常时请看异常码", required = true)
    public boolean isSuccess() {
        return code == null;
    }

}
