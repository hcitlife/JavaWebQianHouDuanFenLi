package com.hc.controller;

import com.alibaba.fastjson.JSON;
import com.hc.bean.Result;
import com.hc.db.DB;
import com.hc.db.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/deptController")
public class DeptController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        /**设置响应头允许ajax跨域访问**/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*星号表示所有的异域请求都可以接受，*/
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //设置输出编码
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        Result res = null;
        switch (op) {
            case "list":
                res = list();
                break;
            case "add":
                res = add(request);
                break;
            case "delete":
                res = delete(request);
                break;
            case "showUpdate":
                res = showUpdate(request);
                break;
            case "update":
                update(request, response);
                break;
            case "query":
                res = query(request, response);
                break;
            default:
                break;
        }
        out.write(JSON.toJSONString(res));
        out.flush();
        out.close();
    }

    private Result query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result res = null;
        String params = request.getParameter("params");
        DB.select(params);
        res = ResultUtil.success(DB.depts);
        response.sendRedirect("index.html");
        return res;
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        DB.update(deptno, dname, loc);
        response.sendRedirect("index.html");
    }

    private Result showUpdate(HttpServletRequest request) {
        String deptno = request.getParameter("deptno");
        return ResultUtil.success(DB.selectById(deptno));
    }

    private Result add(HttpServletRequest request) {
        Result res = null;
        String deptno = request.getParameter("deptno").trim();
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        boolean b = DB.insert(deptno, dname, loc);
        if (b) {
            res = ResultUtil.success();
        } else {
            res = ResultUtil.error(503, "部门添加失败");
        }
        return res;
    }


    private Result delete(HttpServletRequest request) {
        Result res = null;
        String deptno = request.getParameter("deptno");
        boolean b = DB.deleteDeptByDeptno(deptno);
        if (b) {
            res = ResultUtil.success();
        } else {
            res = ResultUtil.error(503, "待删除的部门不存在");
        }
        return res;
    }

    private Result list() {
        return ResultUtil.success(DB.depts);
    }

}
