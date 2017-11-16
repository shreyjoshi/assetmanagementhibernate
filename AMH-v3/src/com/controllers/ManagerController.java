package com.controllers;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import dao.ManagerDao;
import dao.SupportDao;
import beans.AllocatedAsset;
import beans.Asset;
import beans.Employee;
import beans.Tansfer;
import beans.TransferredAsset;

@Controller
@SessionAttributes("eid")

public class ManagerController 
{

	  
	  @RequestMapping("/manager")
		public ModelAndView home()
		{
			ModelAndView mv=new ModelAndView("manager");
			return mv;
		}

		@RequestMapping("/CreateManagerRequest")	
	    public ModelAndView createRequest() 
	    {
			
	       ModelAndView mv=new ModelAndView("createManagerRequest");
			mv.addObject("msg", "create your request here manager!!");
			return mv;
		}
             

		@RequestMapping("/applyCreateManagerRequest")	
	    public ModelAndView applyCreateManagerRequest(@RequestParam String assetname,HttpSession session) 
	    
	    {
			 ModelAndView mv=null;
			 String emailid=(String)session.getAttribute("username");
			Date d=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String requestdate=sf.format(d);
			 Asset a=new Asset();
			 a.setAssetname(assetname);
			 a.setemailid(emailid);
			 a.setRequestdate(requestdate);
			   ManagerDao md=new ManagerDao();
		   int x=md.applyCreateManagerRequest(a);
			 
			if(x!=0)
			{
				  mv=new ModelAndView("manager");
					mv.addObject("msg", "Applied Succesfully for "+assetname);
			}
			
             			return mv;
		}


		@RequestMapping("/ViewManagerRequest")	
	    public ModelAndView viewManagerRequest(HttpSession session) 
	    {
			 ModelAndView mv=null;
			ManagerDao md=new ManagerDao();
			String user_id=(String)	session.getAttribute("username");
			ArrayList<Asset> list=md.viewMYAsste(user_id);
			
			if(list!=null)
			{
		    mv=new ModelAndView("viewManagerRequest");
			mv.addObject("viewmyrequest", list);
		//	System.out.println(list);
			
		}
			else
			{
				System.out.println("view my request is failed");
				
			}
			return mv;
			}


		@RequestMapping("/MyAssetManager")	
	    public ModelAndView myAssets(HttpSession session) 
	    
	    {
			ModelAndView mv=null;
			String user_id=(String)session.getAttribute("username");
			//System.out.println(user_id+"MAnagerrrrrr");
			ManagerDao md=new ManagerDao();
			ArrayList<AllocatedAsset> list=md.getMyAsset(user_id);
			//System.out.println("SSSSS"+list);
			if(list!=null)
			{
	        mv=new ModelAndView("MyAsset");
			mv.addObject("msg", list);
			mv.addObject("m", "Manager Assests");
			}
			
			
			else
			{
				  mv=new ModelAndView("MyAsset");
					mv.addObject("msg", "No Assets hold");
				
			}
			return mv;
		}
		

		@RequestMapping("/ManagerProfile")	
	    public ModelAndView profile(HttpSession session) 
	    {
		
			String user_id=(String)	session.getAttribute("username");
			//System.out.println("user id is "+user_id );
			ManagerDao md=new ManagerDao();
			ArrayList<Employee> list=md.getManagerProfile(user_id);		
	       ModelAndView mv=new ModelAndView("managerProfile");
			mv.addObject("msg", list);
			//System.out.println(x +"hello");
			return mv;
		}
		@RequestMapping("/Employee_Assets")	
	    public ModelAndView employeeAssets(HttpSession session) 
	    {
			String username=(String)session.getAttribute("username");
			  ModelAndView mv=null;
			ManagerDao md=new ManagerDao();
			ArrayList<AllocatedAsset> list=md.allocatedAssets(username);
			//System.out.println("TTTTTTTT"+list);
			
			if(list!=null)
			{
	       mv=new ModelAndView("Employee_Assets");
			mv.addObject("msg", list);
			mv.addObject("m", "Employee And Assets");
			
		}
			else
			{
				  mv=new ModelAndView("Employee_Assets");
					mv.addObject("msg", "NO Emplyee And Assets");
				
			}
			return mv;
			
	    }
		@RequestMapping("/PendingRequestApproval")	
	    public ModelAndView pendingRequetApproval(HttpSession session) 
	    {
			String user_id=(String)	session.getAttribute("username");
			ModelAndView mv=null;
			ManagerDao md=new ManagerDao();
			ArrayList<Asset> list=md.showViewMyRequest(user_id);
		//	System.out.println("TTTTTTTT"+list)
			   mv=new ModelAndView("PendingRequestApproval");
				mv.addObject("msg", list);
				mv.addObject("m", "Manager Request");
			return mv;       
		//	mv.addObject("msg", "");
			//return mv;
		}
		
		@RequestMapping("/updateStatus")	
	    public ModelAndView updateSatatus(@ModelAttribute("Asset")Asset a,String op,HttpSession session) 
	    {
			  ModelAndView mv=null;
			  ManagerDao md=null;
			  String user_id=(String)session.getAttribute("username");
			  if(op.equalsIgnoreCase("approve"))
				{			 
			    	md=new ManagerDao();
			    	int assetid=a.getAssetrequestid();
			    	int x=md.updateStatus(assetid);
				
					  if(x!=0)
					  {
						  ArrayList<Asset> list=md.showViewMyRequest(user_id);
						  mv=new ModelAndView("PendingRequestApproval");
						  mv.addObject("pendingrequests", "the request has been succesfully approved");
						  mv.addObject("msg",list);
					  }
					  else
						{
						  ArrayList<Asset> list=md.showViewMyRequest(user_id);
						  mv=new ModelAndView("PendingRequestApproval");
						  mv.addObject("pendingrequests", "the request was not succesfully approved");
						  mv.addObject("msg",list);
						}
				}
			  else if(op.equalsIgnoreCase("reject"))
				  {
					   md=new ManagerDao();
					   Date d=new Date();
					   SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
					   String dateofrejection=sf.format(d);
					   System.out.println(dateofrejection);
					   int x=md.cancelStatus(a,dateofrejection);
					   
					   if(x!=0)
					   {
			                ArrayList<Asset> list=md.showViewMyRequest(user_id);							
					        mv=new ModelAndView("PendingRequestApproval");
							mv.addObject("pendingrequests", "the request has been cancelled");
							mv.addObject("msg",list);
					   }
					   else 
					   {
						   ArrayList<Asset> list=md.showViewMyRequest(user_id);							
					       mv=new ModelAndView("PendingRequestApproval");
					       mv.addObject("pendingrequests", "the request wasn't cancelled");
					       mv.addObject("msg",list);
					   }
				} 
			return mv;
			}
		
		@RequestMapping("/updateManagerProfile")	
	    public ModelAndView updateManagerProfile(@ModelAttribute ("Employee") Employee e,HttpSession session) 
	    {
			 ModelAndView mv=null;
			String user_id=(String)	session.getAttribute("username");
	
			ManagerDao md=new ManagerDao();
			int x=md.updatManagerProfile(e,user_id);
			if(x!=0)
			{
				ArrayList<Employee> list=md.getManagerProfile(user_id);
				  mv=new ModelAndView("managerProfile");
					mv.addObject("msg", list);
					mv.addObject("m", "data updated");
			}
			else
			{
				System.out.println("data note updatd");
			}
			return mv;
		}
		
		
		
		@RequestMapping("/GoTOHome")	
	    public ModelAndView GoTOHome() 
	    {
		
		 ModelAndView mv=new ModelAndView("manager");
		
			return mv;
	    }
		@RequestMapping("/PendingTransferRequests")
		public ModelAndView pendingTransferRequests(HttpSession session)
		{

			 	ModelAndView mv=null;
				String user_id=(String)	session.getAttribute("username");
		
			ManagerDao md=new ManagerDao();
			ArrayList<Tansfer> list=md.showEmpRequest(user_id);
			 mv=new ModelAndView("viewTransferRequestM");
			 mv.addObject("list",list);
			 
		
			 return mv;
		}
		
		@RequestMapping("/ManipulateTransferRequests")
		public ModelAndView ManipulateTransferRequests(HttpSession session,@RequestParam String op,@ModelAttribute ("Tansfer") Tansfer t)
		{
			ModelAndView mv=null;
			Date d=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String date=sf.format(d);
				if(op.equals("approved"))
				{

				  TransferredAsset td=new TransferredAsset();
				  td.setAssetname(t.getAssetname());
				  td.setFromEmp(t.getFromEmp());
				  td.setManagerid(t.getManagerid());
				  td.setToEmp(t.getToEmp());
				  td.setTransferReqId(t.getTransferReqId());
				  td.setTransferredDate(date);
				  
				  ManagerDao md = new ManagerDao();
				  int x=md.approvedTransfer(td,t,t.getAllocatedid());
				  if(x==1||x==2) 
				  {	
					String user_id=(String)	session.getAttribute("username");
					ArrayList<Tansfer> list=md.showEmpRequest(user_id);
					 mv=new ModelAndView("viewTransferRequestM");
					 mv.addObject("list",list);
					 mv.addObject("approvalmsg","request of "+t.getFromEmp()+" has been approved");
				  }
				  else {
					  String user_id=(String)	session.getAttribute("username");
						ArrayList<Tansfer> list=md.showEmpRequest(user_id);
						 mv=new ModelAndView("viewTransferRequestM");
						 mv.addObject("list",list);
						 mv.addObject("approvalmsg","request of "+t.getFromEmp()+" has failed");
				  }
					}else if(t.getStatus()==3) {
						
					
				}else {
					ManagerDao md=new ManagerDao();
					int x=md.rejectTransfer(t);
					if(x>0)
					{
						 String user_id=(String)	session.getAttribute("username");
						 ArrayList<Tansfer> list=md.showEmpRequest(user_id);
						 mv=new ModelAndView("viewTransferRequestM");
						 mv.addObject("list",list);
						 mv.addObject("approvalmsg","request of "+t.getFromEmp()+" has rejected by you");
					}
					else {
						String user_id=(String)	session.getAttribute("username");
						 ArrayList<Tansfer> list=md.showEmpRequest(user_id);
						 mv=new ModelAndView("viewTransferRequestM");
						 mv.addObject("list",list);
						 mv.addObject("approvalmsg","operation on request of "+t.getFromEmp()+" was not performed");
					}
				}
			 	
				
			 return mv;
		}
		
}
