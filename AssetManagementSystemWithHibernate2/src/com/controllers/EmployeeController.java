package com.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import beans.AllocatedAsset;
import beans.Asset;
import beans.Employee;
import dao.EmployeeDaoRakesh;


@Controller
@SessionAttributes({"empemail","managerid"})
public class EmployeeController {
	

//		@RequestMapping("/EmployeeLogin")
//		public ModelAndView first()
//			{
//		
//		ModelAndView mv=new ModelAndView("employeelogin");
//		mv.addObject("msg","Welcome home");
//		
//		return mv;
//		
//			}
	
		@RequestMapping("/employeehome")
		public ModelAndView Goto()
		{
			
			ModelAndView mv=new ModelAndView("developer");
			
			return mv;
			
		}
		
//		@RequestMapping("/checkEmp")
//		public ModelAndView Emplogin(@RequestParam("emailid") String r,@RequestParam("Cpwd") String p,@RequestParam("designation") String s) 
//		{
//			
//			ModelAndView mv = null;
//			EmployeeDaoRakesh l = new EmployeeDaoRakesh();
//			
//			int y=l.Emplogin(r,p,s);
//			if (y==1) {
//				 
//				mv=new ModelAndView("employeehome");
//				mv.addObject("empemail", r);
//				int z=l.addmanager(r);
//				mv.addObject("managerid", z);
//				
//			}
//			else {
//				mv=new ModelAndView("index");
//				mv.addObject("msg","login failed try again");
//			}
//				
//			
//			return mv;
//		}
//		
		
		@RequestMapping("/ViewEmployeeProfile")
		public ModelAndView viewEmployeeProfile(HttpSession session)
	//	
		{

			ModelAndView mv = null;
			String email = (String)session.getAttribute("username");
			EmployeeDaoRakesh ad=new EmployeeDaoRakesh();
			ArrayList<Employee>list=ad.viewEmployeeProfile(email);
		     mv=new ModelAndView("viewEmployeeProfile");  
		     mv.addObject("msg","View successful");
		     mv.addObject("LIST",list);
		     
		    return mv;
		}
		
		
		@RequestMapping("/ManipulateEmpData")
		public ModelAndView manipulateEmpData(@RequestParam String op,@RequestParam String email) {
			ModelAndView mv=null;
			System.out.println(op);
			if(op.equals("update"))
			{
			EmployeeDaoRakesh ad=new EmployeeDaoRakesh();
			ArrayList<Employee> list=ad.viewEmployeeProfile(email);
					if(list!=null)
					{
					mv=new ModelAndView("updateEmployeeProfile");
					mv.addObject("LIST",list);
					}
					else {
						mv=new ModelAndView("");
						mv.addObject("viewEmpdetails","no employee");
					}
			}
			return mv;
		}
		
		@RequestMapping("/UpdateEmpData")
		public ModelAndView updateEmpData(@ModelAttribute ("Employee") Employee e,HttpSession session ) {
			ModelAndView mv=null;
			
			EmployeeDaoRakesh ad=new EmployeeDaoRakesh();
			int x=ad.updateEmpData(e);
			String email = (String)session.getAttribute("username");
			if(x!=0)
			{
					ArrayList<Employee> list=ad.viewEmployeeProfile(email);
					mv=new ModelAndView("viewEmployeeProfile");
					mv.addObject("LIST",list);
					mv.addObject("updatemsg","updated successfully");			
			}
			else 
			{
					ArrayList<Employee> list=ad.viewEmployeeProfile(email);
					mv=new ModelAndView("viewEmployeeProfile");
					mv.addObject("LIST",list);
					mv.addObject("updatemsg","no employee updated");
			}
		
			return mv;
		}
		
		@RequestMapping("/Emplogout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "home";
		}
		
		
//		@RequestMapping("/changeEmpPassword")
//		public ModelAndView changePassword() {
//			ModelAndView mv=null;
//					mv=new ModelAndView("changeEmpPassword");
//					mv.addObject("msg","change password");
//		
//			return mv;
//		}
//
//		@RequestMapping("/ChangeEmpPassword")
//		public ModelAndView changePassword(@RequestParam String emailid,@RequestParam String Cpwd)
//		{
//			ModelAndView mv=null;
//			EmployeeDaoRakesh ad=new EmployeeDaoRakesh();
//			int x=ad.changePassword(emailid,Cpwd);
//			if(x!=0)
//			{
//				mv=new ModelAndView("employeehome");
//				mv.addObject("changepwdmsg","your password has been successfully changed");
//			}
//			return mv;
//		}
//		
		
		@RequestMapping("/CreateReq")
		public ModelAndView adpage() 
		{
			
			
			ModelAndView mv=new ModelAndView("CreateAssetRequest");
			return mv;
		}	
		
		@RequestMapping("/insertReq")
		public ModelAndView insertRequest(@RequestParam("emailid") String emailid,@RequestParam("assetname") String assetname) 
		{   	
			ModelAndView mv=null;
			EmployeeDaoRakesh ad = new EmployeeDaoRakesh();
//			HttpSession ss=request.getSession();
//			String emailid=(String)ss.getAttribute("empemail");
			Date d=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String requestdate=sf.format(d);
			Asset a=new Asset();
			a.setAssetname(assetname);
			a.setemailid(emailid);
			a.setRequestdate(requestdate);
			int y=ad.assetRequest(a);
			if (y!=0)
			{
				mv=new ModelAndView("CreateAssetRequest");	
				mv.addObject("requestmsg","Successfully requested for "+assetname);
			}
			else
			{
				mv=new ModelAndView("CreateAssetRequest");
				mv.addObject("requestmsg","Failed to request for "+assetname);
			}
			
			return mv;
		}	
		
	
		
		@RequestMapping("/ViewEmpRequest")
		public ModelAndView Viewrequest(HttpSession session )
	//	
		{		ModelAndView mv = null;
				String email=(String)session.getAttribute("username");
				//System.out.println(emailid+"helooo employee..");
				
				EmployeeDaoRakesh ad=new EmployeeDaoRakesh();
				ArrayList<Asset>list=ad.viewEmpRequest(email);		    
				mv=new ModelAndView("viewEmpRequest");
				mv.addObject("LIST",list);
				mv.addObject("viewmsg","View successful");
		     
				return mv;	
		}
		
		@RequestMapping("/updateRequest")
		public ModelAndView deleteData(@RequestParam("assetrequestid") int assetid,HttpSession session)
		{	
			System.out.println("Rakeshhhhhhhhhhhhhhhhhhh");
		ModelAndView mv=null;
		EmployeeDaoRakesh ad = new EmployeeDaoRakesh();
		
		int y=ad.deleteRequest(assetid);
		String desig=(String)session.getAttribute("desig");
		if (y!=1)
		{
			 mv=new ModelAndView(""+desig);	
			mv.addObject("deletemsg",assetid+" Deleted");			//model attribute wala puchna hai idahr lagegA
		}
		else {
			mv=new ModelAndView(""+desig);	
			mv.addObject("deletemsg"," Unable to delete "+assetid);
		}
		return mv;
	}
		
		@RequestMapping("/ViewEmployeeAssetR")
		public ModelAndView viewEmployeeAssetR(HttpSession session)
		{
			ModelAndView mv=null;
			String userid=(String)session.getAttribute("username");
			EmployeeDaoRakesh edr=new EmployeeDaoRakesh();
			ArrayList<AllocatedAsset> list=edr.viewEmployeeAssetR(userid);
			if(list!=null) {
				mv=new ModelAndView("viewEmployeeAssetR");
				mv.addObject("LIST",list);
			}
			return mv;
		}
		
}

