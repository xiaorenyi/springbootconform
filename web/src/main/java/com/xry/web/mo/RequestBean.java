package com.xry.web.mo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuqinggen
 * @create 2018-09-12 20:34
 */
@Data
public class RequestBean {

    @ApiModelProperty(value = "姓名", required = true)
    private String beanName;
}
