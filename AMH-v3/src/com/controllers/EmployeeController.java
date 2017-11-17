package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.http.*;
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
import beans.Tansfer;
import dao.EmployeeDaoRakesh;
import dao.SupportDao;


@Controller
@SessionAttributes({"empemail","managerid"})
public class EmployeeController {
	
	
		@RequestMapping("/employeehome")
		public ModelAndView Goto()
		{
			
			ModelAndView mv=new ModelAndView("developer");
			
			return mv;
			
		}
		
		@RequestMapping("/developer")
		public ModelAndView developer()
		{
			
			ModelAndView mv=new ModelAndView("developer");
			
			return mv;
			
		}
		
		
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
		
		@RequestMapping("/Fetchassetid")
		public void fetchassetid(@RequestParam String assetname,@RequestParam String emailid,HttpServletResponse response)
		{
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			EmployeeDaoRakesh ed=new EmployeeDaoRakesh();
			int aid=ed.fetchassetid(assetname,emailid);
			System.out.println(aid);
			if(aid>0)
			{
				out.println(""+aid);
			}
			else {
				out.println("0");
			}
			
		}
		
		@RequestMapping("/ViewTransferRequest")
		public ModelAndView viewTransferRequest(HttpSession session)
		{
				ModelAndView mv= null;
			String username=(String)session.getAttribute("username");
			EmployeeDaoRakesh ed=new EmployeeDaoRakesh();
			int status=4;
			ArrayList<Tansfer> list=ed.viewTransferRequest(username,status);
			if(!list.isEmpty()) {
				mv=new ModelAndView("viewTransferRequestE");
				mv.addObject("LIST",list);
			}
			
			return mv;
			
			
		}
		
		@RequestMapping("/ViewAssetandRequest")
		public ModelAndView viewAssetandRequest(@ModelAttribute ("Tansfer") Tansfer tb,HttpSession session)
		{
				ModelAndView mv= null;
				String username=(String)session.getAttribute("username");
				String desig=(String)session.getAttribute("desig");
				SupportDao sd=new SupportDao();
				int mid=sd.fetchManager(username);
				Date d=new Date();
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
				String transferRequestDate=sf.format(d);
				
				tb.setTransferRequestDate(transferRequestDate);
				tb.setToEmp(username);
				tb.setManagerid(mid);
				if(desig.equals("developer"))
				{
				tb.setStatus(3);
				}else if(desig.equals("manager"))
				{
					tb.setStatus(4);
				}else {
					tb.setStatus(5);
				}		
			int x=sd.transferReq(tb);
			if(x!=0)
			{
				mv=new ModelAndView(""+desig);
				mv.addObject("transfermsg","transferred request sent");
				
			}
			else {
				mv=new ModelAndView(""+desig);
				mv.addObject("transfermsg","transferred request was rejected");
				
			}
			return mv;
		
		}
		
		@RequestMapping("/AssetTransfer")	
	    public ModelAndView assetTransfer(HttpSession session) 
	    {
	       ModelAndView mv=new ModelAndView("assetTransfer");
	       String username=(String)session.getAttribute("username");
	       EmployeeDaoRakesh ed=new EmployeeDaoRakesh();
	       int status=4;
	       ArrayList<Tansfer> list=ed.viewTransferRequest(username,status);
	       int status1=0;
	       ArrayList<Tansfer> list1=ed.viewTransferRequest(username,status1);
	       int status2=3;
	       ArrayList<Tansfer> list2=ed.viewTransferRequest2(username,status2);
			mv.addObject("List", list);
			mv.addObject("List1",list1);
			mv.addObject("List2",list2);
			return mv;
		}
		
		@RequestMapping("/AskManager")	
	    public ModelAndView askManager(HttpSession session,@RequestParam int transferReqId) 
	    {
	      
	       String username=(String)session.getAttribute("username");
	       EmployeeDaoRakesh ed=new EmployeeDaoRakesh();
	       int y=ed.askManager(username,transferReqId);
	       if(y>0)
	       {
	       int status=4;
	       ModelAndView mv=new ModelAndView("assetTransfer");
	       ArrayList<Tansfer> list=ed.viewTransferRequest(username,status);
			mv.addObject("List", list);
		       int status1=0;
		       ArrayList<Tansfer> list1=ed.viewTransferRequest(username,status1);
		       int status2=3;
		       ArrayList<Tansfer> list2=ed.viewTransferRequest2(username,status2);

		       mv.addObject("List1",list1);
				mv.addObject("List2",list2);
				
			mv.addObject("askmsg","ask request approved");
			return mv;
	       }
	       else {
	    	   int status=1;
		       ModelAndView mv=new ModelAndView("assetTransfer");
		       ArrayList<Tansfer> list=ed.viewTransferRequest(username,status);
				mv.addObject("List", list);
				 int status1=0;
			       ArrayList<Tansfer> list1=ed.viewTransferRequest(username,status1);
			       int status2=3;
			       ArrayList<Tansfer> list2=ed.viewTransferRequest2(username,status2);

			       mv.addObject("List1",list1);
					mv.addObject("List2",list2);

				mv.addObject("askmsg","ask request rejected");
				return mv;
	       }
		}
}




