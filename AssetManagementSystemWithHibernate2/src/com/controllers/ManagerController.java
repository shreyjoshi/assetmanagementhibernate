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
import dao.ManagerDao;
import beans.AllocatedAsset;
import beans.Asset;
import beans.Employee;

@Controller
@SessionAttributes("eid")

public class ManagerController 
{
//	@RequestMapping("/")	
//    public ModelAndView welcome() 
//    {
//       ModelAndView mv=new ModelAndView("index");
//		mv.addObject("msg", "welcome Spring MVC");
//		return mv;
//	}

//	  @RequestMapping("/ManagaerLogin")
//	  public ModelAndView hello(@RequestParam String eid, @RequestParam String pwd) 
//		 
//  {
//		
//		   ModelAndView mv=null;
//		   ManagerDao md=new ManagerDao();
//		  
//		   int x= md.ManagerLogIn(eid, pwd);
//		   if(x!=0)
//		   
//		   {
//			  
//			 mv=new ModelAndView("ManagerHome");
//			 mv.addObject("eid",eid);
//			  
//			  
//		   }
//		   
//		  else
//		    {
//			  mv=new ModelAndView("index");
//			  mv.addObject("msg","login failed, try againe");
//			  
//		    }
//		  
//		  return  mv;
//		  
//   }
	  
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
		
		@RequestMapping("/AssetsTransfor")	
	    public ModelAndView assetTransfer() 
	    {
	       ModelAndView mv=new ModelAndView("AssetsTransfor");
			mv.addObject("msg", "");
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
	    public ModelAndView employeeAssets() 
	    {
			
			  ModelAndView mv=null;
			ManagerDao md=new ManagerDao();
			ArrayList<AllocatedAsset> list=md.allocatedAssets();
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
		
//		@RequestMapping("/Logout")	
//	    public ModelAndView Logout(HttpSession session)
//	    
//	    {
//		               
//			session.invalidate();	
//	        ModelAndView mv=new ModelAndView("home");
//		
//			return mv;
//		}
		
		
		
		
}
