package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import beans.AllocatedAsset;
import beans.Employee;
import dao.AdminDao;
import dao.AdminLoginDao;
import dao.SupportDao;

@Controller
@SessionAttributes("username")
public class AdminController {
	
	@RequestMapping("/AdminLogin")
	public ModelAndView adminlogin() {
		ModelAndView mv=new ModelAndView("adminlogin");
		return mv;
	}
	
//----------------------------------------------------------------------------------------------------	

	@RequestMapping("/checkAdminLogin")
	public ModelAndView checkLogin(@RequestParam String adminid,@RequestParam String adminpass)
		{
		
			ModelAndView mv=null;
			AdminLoginDao ad=new AdminLoginDao();
			int x=ad.checkLogin(adminid,adminpass);
			if(x==1)
			{	
				mv=new ModelAndView("adminHome");
				mv.addObject("username",adminid);
				return mv;
				
			}
			else {
				mv=new ModelAndView("adminlogin");
				mv.addObject("msg","Enter valid details....");
				return mv;
			}
			
		}
	
//----------------------------------------------------------------------------------------------------	

	@RequestMapping("/adminHome")
	public ModelAndView adminhome() {
		ModelAndView mv=new ModelAndView("adminHome");
		return mv;
	}

//----------------------------------------------------------------------------------------------------
	
	@RequestMapping("/CreateEmployee")
	public ModelAndView createemployee() {
		ModelAndView mv=new ModelAndView("createEmployee");
		mv.addObject("msg","enter employee details here");
		return mv;
	}
	
//----------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/AddEmployee")
	public ModelAndView addEmployee(@ModelAttribute ("Employee") Employee e) {
		ModelAndView mv=null;
		AdminDao ad=new AdminDao();
		int y=ad.addEmployee(e);
		if(y!=0)
		{
			mv=new ModelAndView("createEmployee");
			if(e.getDesignation().equals("developer"))
			{
				String mname=ad.getManagerName(e.getManagerid());
				//System.out.println(mname+"mail k upar");
				sendCredentials(e.getEmail(),e.getPassword(),mname);
				mv.addObject("addmsg","employee added successfully");
			}
			else 
			{
				sendCredentials(e.getEmail(),e.getPassword());
				mv.addObject("addmsg","employee added successfully");
			}
		}
		else
		{
			mv=new ModelAndView("createEmployee");
			mv.addObject("addmsg","employee cant be added");
		}
		return mv;
	}

//----------------------------------------------------------------------------------------------------------------
	public void sendMail(String to,String sub,String msg) {
		 final String username = "being.836@gmail.com";//change accordingly
	      final String password = "Shrey@0731";//change accordingly  
	      String from = "being.836@gmail.com";
		String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(sub);

	         // Now set the actual message
	         message.setText(msg);

	         // Send message
	         
	         Transport.send(message);
//	         PrintWriter out= response.getWriter();
//	         out.println("Sent message successfully....");
	         } catch (MessagingException e) {
	    	  System.out.println(e);
	    	  e.printStackTrace();
	    	     }	
	}
//--------------------------------------------------------------------------------------------------
	public void sendCredentials(String email,String password1,String mname){
//		HttpServletResponse response=null;
		  // Recipient's emailid ID needs to be mentioned.
	      String to = email;//request.getParameter("id");//change accordingly
	      String sub="Asset Management Account Creation & Password";
//	      long p=System.currentTimeMillis();//439807598430759083
//	      String pwd=(p+"").substring(7);
	      InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      String ipadd=addr.getHostAddress();
	      String msg="Welcome in our company and your username= "+email+" and password="+password1+" and your manager is :"+mname+" visit us at http://"+ipadd+":8080/AssetManagementSystem/Activate?email="+email;
	      // Sender's emailid ID needs to be mentioned
	     sendMail(to,sub,msg);
	    
}
//-----------------------------------------------------------------------------------------------------
	public void sendCredentials(String email,String password1){
//		HttpServletResponse response=null;
		  // Recipient's emailid ID needs to be mentioned.
	      String to = email;//request.getParameter("id");//change accordingly
	      String sub="Asset Management Account Creation & Password";
//	      long p=System.currentTimeMillis();//439807598430759083
//	      String pwd=(p+"").substring(7);
	      InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      String ipadd=addr.getHostAddress();
	      String msg="Welcome in our company and your username= "+email+" and password="+password1+" visit us at http://"+ipadd+":8080/AssetManagementSystem/Activate?email="+email;
	      // Sender's emailid ID needs to be mentioned
	     sendMail(to,sub,msg);
	    
}
	
//----------------------------------------------------------------------------------------------------	
	@RequestMapping("/ViewEmployee")
	public ModelAndView viewEmployeeAdmin() {
		ModelAndView mv=null;
		AdminDao ad=new AdminDao();
		ArrayList<Employee> list=ad.viewEmployeeAdmin();
		if(list!=null)
		{
		mv=new ModelAndView("viewEmployeeAdmin");
		mv.addObject("view",list);
		}
		else {
			mv=new ModelAndView("createEmployee");
			mv.addObject("view","no employee");
		}
		return mv;
	}

//----------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/FetchManager")
	public ModelAndView fetchManager(HttpServletResponse response)
	{	ModelAndView mv=null;
		AdminDao ad=new AdminDao();
		ArrayList<Integer> list =ad.getData();
		mv=new ModelAndView("getManager");
		mv.addObject("mid",list);
		return mv;
//		PrintWriter out=response.getWriter();
//			System.out.println(list);
//			out.println("<option>""</option>");
	}
	
//----------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/ManipulateData")
	public ModelAndView manipulateDataAdmin(@RequestParam String op,@RequestParam String email) {
		ModelAndView mv=null;
		//System.out.println("heelo in manipulate data");
		if(op.equals("update"))
		{
		AdminDao ad=new AdminDao();
		ArrayList<Employee> list=ad.viewEmployeeDetailsAdmin(email);
				if(list!=null)
				{
				mv=new ModelAndView("viewEmployeeDetailsAdmin");
				mv.addObject("viewdetails",list);
				}
				else {
					mv=new ModelAndView("createEmployee");
					mv.addObject("viewdetails","no employee");
				}
		}
		else{
			AdminDao ad=new AdminDao();
			int x=ad.deactivate(email);
			if(x==1)
			{
				mv=new ModelAndView("viewEmployeeAdmin");
				mv.addObject("deactivated","user "+email+"deactivated");
				
			}
		}
		return mv;
	}
	
//----------------------------------------------------------------------------------------------------	

	@RequestMapping("/UpdateData")
	public ModelAndView updateData(@ModelAttribute ("Employee") Employee e) {
		ModelAndView mv=null;
		AdminDao ad=new AdminDao();
		int x=ad.updateEmployeeData(e);
				if(x==1)
				{
					ArrayList<Employee> list=ad.viewEmployeeAdmin();
					if(list!=null)
					{
					mv=new ModelAndView("viewEmployeeAdmin");
					mv.addObject("view",list);
					mv.addObject("updatemsg","updated successfully");
					}
				}
				else {
					mv=new ModelAndView("viewEmployeeAdmin");
					mv.addObject("updatemsg","no employee updated");
				}
	
		return mv;
	}
	
//----------------------------------------------------------------------------------------------------	

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
	
//----------------------------------------------------------------------------------------------------	
	
	@RequestMapping("/changePassword")
	public ModelAndView changePassword() {
		ModelAndView mv=null;
				mv=new ModelAndView("changeadminPassword");
				mv.addObject("msg","change password");
	
		return mv;
	}

//----------------------------------------------------------------------------------------------------	

	@RequestMapping("/ChangeAdminPassword")
	public ModelAndView changePassword(@RequestParam String adminid,@RequestParam String adminpass)
	{
		ModelAndView mv=null;
		AdminDao ad=new AdminDao();
		int x=ad.changePassword(adminid,adminpass);
		if(x!=0)
		{
			mv=new ModelAndView("adminHome");
			mv.addObject("changepwdmsg","your password has been successfully changed");
		}
		return mv;
	}
	

//----------------------------------------------------------------------------------------------------	
		
	@RequestMapping("/ViewReportAdmin")
	public ModelAndView viewReportAdmin()
	{
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<AllocatedAsset> list=ed.viewReportSupport();
		if(list!=null) {
			mv=new ModelAndView("viewReportAdmin");
			mv.addObject("report",list);
		}
		return mv;
	}
	
//----------------------------------------------------------------------------------------------------	
	@RequestMapping("/checkoldpass")
	public void checkoldpass(@RequestParam String adminpass,HttpSession session,HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ss=(String)session.getAttribute("username");
		AdminLoginDao ado=new AdminLoginDao();
		String x=ado.checkoldpass(ss);
		if(x.matches(adminpass))
		{
			out.println("0");
		}
		else {
			out.println("1");
		}
	}
	
//----------------------------------------------------------------------------------------------------	
	@RequestMapping("/checkEmailExist")
	public void checkEmailExists(@RequestParam String email,HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdminDao ado=new AdminDao();
		int x=ado.checkEmailExists(email);
		if(x!=0)
		{
			out.println("1");
		}
		else {
			out.print("0");
		}
		
	}
}
