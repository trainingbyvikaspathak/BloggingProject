/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.Admin;
import com.db.DbConnection;
import java.sql.*;
import com.daos.AdminDao;


public class AdminController extends HttpServlet {

    
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String op = request.getParameter("op");
        if(op!=null&&op.equals("login")){
           String email=request.getParameter("email");
           String password=request.getParameter("password");
           try{
               Connection con =DbConnection.getConnection();
               String sql = "select * from admin where userid=? and password=?";
               PreparedStatement smt =con.prepareCall(sql);
               smt.setString(1,email);
               smt.setString(2,password);
               ResultSet rs=smt.executeQuery();
               if(rs.next()){
                   response.sendRedirect("dashboard.jsp?name="+rs.getString("name"));
               }
               else{
                   out.println("\"<h2><font color='red'>Invalid id or password</font></h2>\"");
               }
               
           }catch(Exception e){
               System.out.println("login Error "+e.getMessage());
           }
            
        }
    }

   
   
}
