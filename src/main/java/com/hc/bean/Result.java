package com.hc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result<T> {
    private Integer code;    //返回码
    private String msg;    //返回消息
    private T data;    //返回数据
}
