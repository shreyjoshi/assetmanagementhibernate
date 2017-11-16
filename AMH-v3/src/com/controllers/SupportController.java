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
import beans.Tansfer;
import beans.TransferredAsset;
import dao.SupportDao;
import com.controllers.AdminController;

@Controller
@SessionAttributes({"username","desig"})
public class SupportController {

	@RequestMapping("/")//re-directs to login page on start up 
	public ModelAndView hello()
	{
		ModelAndView mv=new ModelAndView("employeeloginpagejsp");
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/EmployeeLogin")//redirects to login page when /EmployeeLogin is find in url
	public ModelAndView welcome()
	{
		ModelAndView mv=new ModelAndView("employeeloginpagejsp");
		mv.addObject("welcome","login here");
		return mv;
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/supportstaff")//redirects to support staff by 
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView("supportstaff");
		return mv;
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/CheckLogin")// checks the login information entered by the employee
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
				if(e.getStatus()==1)//checks whether user is activated or not 
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
				mv.addObject("msg","Enter valid details...!!");
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
	
	@RequestMapping("/ViewSupportProfile")//used to view support profile
	public ModelAndView viewSupportProfile(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<Employee> list=ed.viewSupportProfile(username);//this method views the support profile
		if(list!=null)
		{
			mv=new ModelAndView("viewSupportProfile");
			mv.addObject("LIST", list);
		}
		return mv;
	}

//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ViewResourceRequestSupport")//all the request for support will be shown to support
	public ModelAndView viewResourceRequestSupport()
	{
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<Asset> list=ed.viewResourceRequestSupport();//thi method extracts all the resource requests to support
		if(list!=null) {
			mv=new ModelAndView("viewResourceRequestSupport");
			mv.addObject("requests",list);
		}
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ViewReportSupport")//here support views all the allocated assets
	public ModelAndView viewReportSupport()
	{
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		ArrayList<AllocatedAsset> list=ed.viewReportSupport();//this method extracts all the allocated asse
		if(list!=null) {
			mv=new ModelAndView("viewReportSupport");
			mv.addObject("report",list);
		}
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/changeEmployeePassword")//this is used to redirect user to changeEmployeePassword.jsp
	public ModelAndView changePassword() {
		ModelAndView mv=null;
				mv=new ModelAndView("changeEmployeePassword");
				mv.addObject("msg","change password");
	
		return mv;
	}
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/ChangeEmpPassword")// this method is used to change the employee password
	public ModelAndView changeEmpPassword(@RequestParam String userid,@RequestParam String password,HttpSession session)
	{
//		System.out.println(userid);
//		System.out.println(newpassword);
		ModelAndView mv=null;
		SupportDao ed=new SupportDao();
		String desig=(String)session.getAttribute("desig");
		int x=ed.changePassword(userid,password);
		if(x!=0)
		{
			
			mv=new ModelAndView(""+desig);
			sendNewPassword(userid,password);
			mv.addObject("changepwdmsg","your password has been successfully changed");
		}
		else {
			mv=new ModelAndView(""+desig);
			//sendNewPassword(userid,newpassword);
			mv.addObject("changepwdmsg","your password was not changed .... process failed");
		}
		return mv;
	}
	public void sendNewPassword(String userid,String newpassword)//this method sends email to the user with the changed password
	{
		String to=userid;
		String sub="change password Information";
		String msg="You have changed your account password and your new password is "+newpassword;
		AdminController ac=new AdminController();
		ac.sendMail(to,sub,msg);
	}
	
//---------------------------------------------------------------------------------------------------------
	
	@RequestMapping("/UpdateSupportProfile")//this is used to update support profile
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
	
	@RequestMapping("/ApproveFromSupport")//this is used to approve the requests of employees for assets
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
	
	public void sendApproveMail(String userid,String msg1) //sends email to user notifying that his/her request has been approved
	{
		String to=userid;
		String sub="asset request status";
		String msg=msg1;
		AdminController ac=new AdminController();
		ac.sendMail(to,sub,msg);
	}

//---------------------------------------------------------------------------------------------------------
	@RequestMapping("/forgetpassword")//redirects the user to forget password page
	public ModelAndView forget() {
		ModelAndView mv=new ModelAndView("forgetpass");
		mv.addObject("msg","enter your registered emailid here");
		return mv;
	}
	
	@RequestMapping("/ForgetPass")//fetchs the old password of user and send it to their emailid
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
	public void sendOldPassword(String userid,String pass)//sends old password to their mail
	{
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
		String msg="Your password is "+pass+" now log in here http://+"+ipadd+":8080/AMH/";
		AdminController ac=new AdminController();
		ac.sendMail(to,sub,msg);
	}
//---------------------------------------------------------------------------------------------------------
	@RequestMapping("/ViewRejectedRequests")//views all the rejected requests of employees for tthe assets
	public ModelAndView viewRejectedRequests()
	{
		ModelAndView mv=null;
		SupportDao sd=new SupportDao();
		ArrayList<RejectedRequest> list=sd.viewRejectedRequests();//fetchs all the data of rejected requests
		mv=new ModelAndView("viewRejectedRequests");
		mv.addObject("rejectedrequests", list);
		return mv;
	}
//---------------------------------------------------------------------------------------------------------	
	@RequestMapping("/Activate")//this is used to activate the user account than only they will be able to login
	public ModelAndView activateEmployee(@RequestParam String email)
	{
		ModelAndView mv=null;
		SupportDao sd=new SupportDao();
		int x=sd.activateEmployee(email);//activates the user 
		if(x!=0) {
			mv=new ModelAndView("employeeloginpagejsp");
			mv.addObject("activationmsg","your account has been activated");
		}
		else {
			mv=new ModelAndView("employeeloginpagejsp");
			mv.addObject("activationmsg","your account activation process was suspended!! ");
		}
		return mv;
	}
	
//---------------------------------------------------------------------------------------------------------	
	@RequestMapping("/checkoldpassword")//checks old password of the user
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
	
//---------------------------------------------------------------------------------------------------------	

	@RequestMapping("/fetchAssetName")//this is used to fetch all the names of assets which are being allocated to an user
	public void fetchAssetName(@RequestParam String fromEmp,HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email=fromEmp;
		//System.out.println(email);
		SupportDao sd=new SupportDao();
		ArrayList<AllocatedAsset> list=sd.fetchAssetName(email);
		if(!list.isEmpty())
		{
			Iterator it=list.iterator();
			while(it.hasNext())
			{
//				System.out.println(it.next());
				String x=(String)it.next();
				//System.out.println("xxxxxxxxx"+x);
				out.println("<option value="+x+">"+x+"</option>");
			}
		}
		else {
			out.println("0");
		}
	}
	
//---------------------------------------------------------------------------------------------------------	

	@RequestMapping("/TransferToEmp")//this is user to create a transfer request 
	public ModelAndView TransferToEmp(@ModelAttribute ("Tansfer") Tansfer tb)
	{
//		String email=fromEmp;
		//System.out.println(email);
		ModelAndView mv=null;
		Date d=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String transferRequestDate=sf.format(d);
		tb.setTransferRequestDate(transferRequestDate);
		SupportDao sd=new SupportDao();
		//System.out.println(tb.getAllocatedid()+" in controller--------");
		int x=sd.transferReq(tb);
		if(x!=0)
		{
			mv=new ModelAndView("assetTransfer");
			mv.addObject("transfermsg","transferred request sent");
			return mv;
		}
		else {
			mv=new ModelAndView("assetTransfer");
			mv.addObject("transfermsg","transferred request was rejected");
			return mv;
		}
	}

//---------------------------------------------------------------------------------------------------------	

		@RequestMapping("/fetchManager")//this is used to fetch manager id of the user who is requesting asset transfer
		public void fetchManager(@RequestParam String fromEmp,HttpServletResponse response)
		{
//			String email=fromEmp;
			//System.out.println(email);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			SupportDao sd=new SupportDao();
			int managerid=sd.fetchManager(fromEmp);
			if(managerid>0)
			{
				out.println(""+managerid);
			}
			else {
				out.println("0");
			}
		}
		
//---------------------------------------------------------------------------------------------------------	

		@RequestMapping("/checkToEmp") //this methods checks whether the employee to whom the asset needs to be transferred
		public void checkToEmp(@RequestParam String toEmp,HttpServletResponse response) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SupportDao sd=new SupportDao();
			int x=sd.checkToEmp(toEmp);
			if(x>0)
			{
				out.println("1");
			}
			else {
				out.println("0");
			}
			
		}

//---------------------------------------------------------------------------------------------------------	
		@RequestMapping("/ViewTransferredHistory")//this is used to view all transferred history
		public ModelAndView viewTransferredHistory(){
			SupportDao sd=new SupportDao();
			ArrayList<TransferredAsset> list=sd.viewTransferredHistory();
			ModelAndView mv=new ModelAndView("viewTransferredHistorySupport");
			mv.addObject("list",list);
			return mv;
		}
}
