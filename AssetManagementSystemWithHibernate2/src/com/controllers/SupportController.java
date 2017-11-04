package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
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
import beans.Login;
import beans.RejectedRequest;
import dao.SupportDao;
import com.controllers.AdminController;

@Controller
@SessionAttributes({"username","desig"})
public class SupportController {

	@RequestMapping("/")
	public ModelAndView hello()
	{
		ModelAndView mv=new ModelAndView("home");
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/EmployeeLogin")
	public ModelAndView welcome()
	{
		ModelAndView mv=new ModelAndView("employeeloginpagejsp");
		mv.addObject("welcome","login here");
		return mv;
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/supportstaff")
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView("supportstaff");
		return mv;
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/CheckLogin")
	public ModelAndView checkLogin(@RequestParam String username,@RequestParam String password)//,@RequestParam String designation)
	{
		//System.out.println("designation= "+designation);
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList list=ed.checkLogin(username,password);//,designation);
		if(!list.isEmpty())
		{	
			Iterator it=list.iterator();
			Object o=(Object)it.next();
			if(o instanceof Employee)
			{	
				Employee e=(Employee)o;
				if(e.getStatus()==1)
				{
				String designation=e.getDesignation();
				mv=new ModelAndView(""+designation);
				mv.addObject("username",username);
				mv.addObject("desig", designation);
				return mv;
				}
				else
				{
					mv=new ModelAndView("employeeloginpagejsp");
					mv.addObject("msg"," activate your account first..");
					return mv;
				}
			}
			else if(o instanceof Login){
				Login l=(Login)o;
				String designation=l.getAdmindesignation();
				mv=new ModelAndView(""+designation+"Home");
				mv.addObject("username",username);
				mv.addObject("desig", designation);
				return mv;	
			}
			else {
				mv=new ModelAndView("employeeloginpagejsp");
				mv.addObject("msg","Enter valid details...");
				return mv;
			}
		}
		else {
			mv=new ModelAndView("employeeloginpagejsp");
			mv.addObject("msg","Enter valid details...");
			return mv;
		}
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ViewSupportProfile")
	public ModelAndView viewSupportProfile(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<Employee> list=ed.viewSupportProfile(username);
		if(list!=null)
		{
			mv=new ModelAndView("viewSupportProfile");
			mv.addObject("LIST", list);
		}
		return mv;
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ViewResourceRequestSupport")
	public ModelAndView viewResourceRequestSupport()
	{
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<Asset> list=ed.viewResourceRequestSupport();
		if(list!=null) {
			mv=new ModelAndView("viewResourceRequestSupport");
			mv.addObject("requests",list);
		}
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ViewReportSupport")
	public ModelAndView viewReportSupport()
	{
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<AllocatedAsset> list=ed.viewReportSupport();
		if(list!=null) {
			mv=new ModelAndView("viewReportSupport");
			mv.addObject("report",list);
		}
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/changeEmployeePassword")
	public ModelAndView changePassword() {
		ModelAndView mv=null;
				mv=new ModelAndView("changeEmployeePassword");
				mv.addObject("msg","change password");
	
		return mv;
	}
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ChangeEmpPassword")
	public ModelAndView changeEmpPassword(@RequestParam String userid,@RequestParam String newpassword,HttpSession session)
	{
//		System.out.println(userid);
//		System.out.println(newpassword);
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		String desig=(String)session.getAttribute("desig");
		int x=ed.changePassword(userid,newpassword);
		if(x!=0)
		{
			
			mv=new ModelAndView(""+desig);
			sendNewPassword(userid,newpassword);
			mv.addObject("changepwdmsg","your password has been successfully changed");
		}
		else {
			mv=new ModelAndView(""+desig);
			//sendNewPassword(userid,newpassword);
			mv.addObject("changepwdmsg","your password was not changed .... process failed");
		}
		return mv;
	}
	public void sendNewPassword(String userid,String newpassword) {
		
		String to=userid;
		String sub="change password Information";
		String msg="You have changed your account password and your new password is "+newpassword;
		AdminController ac=new AdminController();
		ac.sendMail(to,sub,msg);
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/UpdateSupportProfile")
	public ModelAndView updateProfile(@ModelAttribute ("Employee") Employee e,HttpSession session) {
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		int x=ed.updateSuppotProfile(e);
				if(x!=0)
				{	String username=(String)session.getAttribute("username");
					ArrayList<Employee> list=ed.viewSupportProfile(username);
					if(list!=null)
					{
					mv=new ModelAndView("viewSupportProfile");
					mv.addObject("LIST",list);
					mv.addObject("updatemsg","updated successfully");
					}
				}
				else {
					mv=new ModelAndView("viewSupportProfile");
					mv.addObject("updatemsg","no employee updated");
				}
	
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ApproveFromSupport")
	public ModelAndView approveFromSupport(@ModelAttribute ("Asset") Asset a,String op,HttpSession session)
	{	
		ArrayList<Asset> list=null;
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
//		System.out.println(a.getAssetname());
//		System.out.println(a.getUserid());
//		System.out.println(a.getAssetid());
		Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String processdate=sf.format(d);
		if(op.equals("approve"))
		{
			int x=ed.approveFromSupport(a,processdate);
			if(x==1)
			{
				String msg="voila!!!!! your request for  \""+a.getAssetname()+ "\" has been approved...";
				//System.out.println(msg);
			sendApproveMail(a.getemailid(),msg);
				list=ed.viewResourceRequestSupport();
				if(list!=null)
				{
					mv=new ModelAndView("viewResourceRequestSupport");
					mv.addObject("requests",list);
					mv.addObject("approvemsg","request approved");
				}
			}
		}
		else {
			String desig=(String)session.getAttribute("desig");
			int x=ed.rejectFromSupport(a,desig,processdate);
			if(x==1) 
			{
					String msg="Sorry your request for \""+a.getAssetname()+"\" has been rejected by support staff...";
					//System.out.println(msg);
					sendApproveMail(a.getemailid(),msg);
					list=ed.viewResourceRequestSupport();
					if(list!=null) 
					{
						mv=new ModelAndView("viewResourceRequestSupport");
						mv.addObject("requests",list);
						mv.addObject("approvemsg","request cancelled for asset "+a.getAssetname());
					}
			}
		}
		return mv;
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------	
	
	public void sendApproveMail(String userid,String msg1) {
		String to=userid;
		String sub="asset request status";
		String msg=msg1;
		AdminController ac=new AdminController();
		ac.sendMail(to,sub,msg);
	}

//---------------------------------------------------------------------------------------------------------
	@RequestMapping("/forgetpassword")
	public ModelAndView forget() {
		ModelAndView mv=new ModelAndView("forgetpass");
		mv.addObject("msg","enter your registered emailid here");
		return mv;
	}
	
	@RequestMapping("/ForgetPass")
	public ModelAndView forgetpass(@RequestParam String userid)
	{
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		String pass=ed.getPassword(userid);
		if(pass!=null) {
		sendOldPassword(userid,pass);
		mv=new ModelAndView("employeeloginpagejsp");
		mv.addObject("forgetpassmsg", "your password has been sent to registered emailid id");
		}
		return mv;
	}
	public void sendOldPassword(String userid,String pass){
		String to=userid;
		String sub="password Information";
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ipadd=addr.getHostAddress();
		String msg="Your password is "+pass+" now log in here http://+"+ipadd+":8080/AssetManagementSystem/";
		AdminController ac=new AdminController();
		ac.sendMail(to,sub,msg);
	}
//---------------------------------------------------------------------------------------------------------
	@RequestMapping("/ViewRejectedRequests")
	public ModelAndView viewRejectedRequests()
	{
		ModelAndView mv=null;
		SupportDao sd=new SupportDao();
		ArrayList<RejectedRequest> list=sd.viewRejectedRequests();
		mv=new ModelAndView("viewRejectedRequests");
		mv.addObject("rejectedrequests", list);
		return mv;
	}
//---------------------------------------------------------------------------------------------------------	
	@RequestMapping("/Activate")
	public ModelAndView activateEmployee(@RequestParam String email)
	{
		ModelAndView mv=null;
		SupportDao sd=new SupportDao();
		int x=sd.activateEmployee(email);
		if(x!=0) {
			mv=new ModelAndView("employeelogonpagejsp");
			mv.addObject("activationmsg","your account has been activated");
		}
		else {
			mv=new ModelAndView("employeelogonpagejsp");
			mv.addObject("activationmsg","your account activation process was suspended!! ");
		}
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------	
	@RequestMapping("/checkoldpassword")
	public void checkoldpassword(@RequestParam String password,HttpSession session,HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email=(String)session.getAttribute("username");
		//System.out.println(email);
		SupportDao sd=new SupportDao();
		String oldpassword=sd.getPassword(email);
		if(oldpassword.equals(password))
		{
			out.println("0");
		}
		else {
			out.println("1");
		}
	}
}
