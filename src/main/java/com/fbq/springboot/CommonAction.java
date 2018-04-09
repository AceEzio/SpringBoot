/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.fbq.springboot;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @author 01
 * @version V1.0
 * @since 2017-06-20 21:48
 */
@RestController
@Transactional
public class CommonAction {

    @Resource
    private SessionFactory sessionFactory;

    @RequestMapping("test")
    public void test(HttpServletResponse response) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from user");
        List list = sqlQuery.list();
        System.out.printf(list.size() + "");
        try {
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"msg\":\"调用成功\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
