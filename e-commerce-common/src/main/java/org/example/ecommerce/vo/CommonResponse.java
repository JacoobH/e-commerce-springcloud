package org.example.ecommerce.vo;

import java.io.Serializable;

/**
 * <h1>通用对象定义</h1>
 * {
 *     "code": 0,
 *     "message": "",
 *     "data": {}
 * }
 */
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T Data;

    public CommonResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
