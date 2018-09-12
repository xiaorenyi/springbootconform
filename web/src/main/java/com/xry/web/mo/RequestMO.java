package com.xry.web.mo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuqinggen
 * @create 2018-09-12 20:21
 */
@Data
public class RequestMO {

    @ApiModelProperty(value = "姓名")
    private String name;


    @ApiModelProperty(value = "bean", required = true)
    private RequestBean requestBean;
}
