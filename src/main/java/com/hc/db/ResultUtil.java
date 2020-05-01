package com.hc.db;

import com.hc.bean.Result;

public class ResultUtil {
    public static Result success(Object obj) { //操作成功的处理方法
        Result<Object> result = new Result<>();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(obj);
        return result;
    }

    public static Result success() { //操作成功的处理方法，不返回任何消息数据
        return success(null);
    }

    public static Result error(Integer code, String msg, Object obj) {//操作失败处理方法
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static Result error(Integer code, String msg) {
        return error(code, msg, null);
    }
}
