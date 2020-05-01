package com.hc.db;

import com.hc.bean.Dept;

import java.util.ArrayList;
import java.util.List;

public class DB {
    public static List<Dept> depts = new ArrayList<>();

    static {
        depts.add(new Dept(10, "ACCOUNTING", "NEWYORK"));
        depts.add(new Dept(20, "RESEARCH", "DALLAS"));
        depts.add(new Dept(30, "SALES", "CHICAGO"));
        depts.add(new Dept(40, "OPERATIONS", "BOSTON"));
    }

    public static boolean deleteDeptByDeptno(String deptno) {
        for (int i = 0; i < depts.size(); i++) {
            if (depts.get(i).getDeptno() == Integer.parseInt(deptno)) {
                depts.remove(i);
                return true;
            }
        }
        return false;
    }

    public static void select( String params) {
        List<Dept> list = new ArrayList<>();
        for (Dept dept : DB.depts) {
            if (dept.getDname().contains(params)) {
                list.add(dept);
            }
        }
        DB.depts = list;
    }

    public static void update(String deptno, String dname, String loc) {
        Dept dept = new Dept(Integer.parseInt(deptno), dname, loc);
        for (int i = 0; i < DB.depts.size(); i++) {
            if (DB.depts.get(i).getDeptno() == Integer.parseInt(deptno)) {
                DB.depts.set(i, dept);
                break;
            }
        }
    }
    public static  boolean insert(String deptno, String dname, String loc) {
        Dept dept = new Dept(Integer.parseInt(deptno), dname, loc);
        return DB.depts.add(dept);
    }
    public static Object selectById(String deptno) {
        boolean flag = false;
        int i = 0;
        for (; i < DB.depts.size(); i++) {
            if (DB.depts.get(i).getDeptno() == Integer.parseInt(deptno)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return DB.depts.get(i);
        } else {
            return "待候的部门不存在";
        }
    }
}

