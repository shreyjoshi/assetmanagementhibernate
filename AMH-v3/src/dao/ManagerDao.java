package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import dto.DBConnector;
import beans.AllocatedAsset;
import beans.Asset;
import beans.Employee;
import beans.RejectedRequest;
import beans.Tansfer;
import beans.TransferredAsset;
//import managerean.AssetsRequest;
public class ManagerDao
{

//	public int ManagerLogIn(String u,String p)
//	{
//		int x=0;
//		try 
//		{
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from employee where emailid=? && password=? && status=1");
//			ps.setString(1,u);
//			ps.setString(2,p);
//			ResultSet rs=ps.executeQuery();
//			if(rs.next())
//			{
//				x=1;
//			}
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
//		return x;
//	}
//	

	public ArrayList<Asset> showViewMyRequest(String user_id)
	{
		int x=0;
	  ArrayList<Asset> list=new ArrayList<>();

	  
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
	    Query q=ss.createQuery("from Asset where requeststatus=:a and emailid IN (select email from Employee where managerid=(select eid from Employee where email=:b))") ;
	    q.setInteger("a", x);
		q.setString("b", user_id);
		Transaction tt=ss.beginTransaction();
		  list=(ArrayList<Asset>)q.list();
	      /*Transaction tt=ss.beginTransaction();
		  for(Asset a:list)
	      {
		    ss.save(a);
	      }
	  */
		  tt.commit();
		  ss.close();
	 /* try 
		{
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from assetrequest where requeststatus=? and userid IN (select emailid from employee where managerid=(select eid from employee where emailid=?))");
			ps.setInt(1, x);
			ps.setString(2, user_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Asset ar=new Asset();
				ar.setAssetid(rs.getInt("assetrequestid"));
				ar.setUserid(rs.getString("userid"));
				ar.setAssetname(rs.getString("assetname"));
				ar.setRequestdate(rs.getString("requestdate"));
				ar.setRequeststatus(rs.getInt("requeststatus"));
				list.add(ar);
			
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
*/		
		return list;
	}


	public int updateStatus(int assetid) 
	{
		int xx=0;
		int x=1;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="update Asset set requeststatus=:aa where assetrequestid=:bb";
		Query query=ss.createQuery(hql);
		query.setInteger("aa",x);
		query.setInteger("bb", assetid);
		Transaction tt=ss.beginTransaction();
		 xx=query.executeUpdate();
		 tt.commit();
		 ss.close();
		 return xx;
//		 try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps1=con.prepareStatement("update assetrequest set requeststatus=? where assetrequestid=?");
//				ps1.setInt(1, x);
//				ps1.setString(2, assetrequestid);
//				
//	     xx=ps1.executeUpdate();
//		  
//			}
//		 catch(Exception e)
//			{
//				System.out.println(e);
//			}
//			 
	}

	public int cancelStatus(Asset a,String dateofrejection)
	{
		int x1=0,x2=0,x=0;
		int requeststatus=2;
		String desig="manager";
		a.setRequeststatus(requeststatus);
		RejectedRequest rr=new RejectedRequest();
			rr.setAssetrequestid(a.getAssetrequestid());
			rr.setAssetname(a.getAssetname());
			rr.setEmailid(a.getemailid());
			rr.setDateofrejection(dateofrejection);
			rr.setRejectedby(desig);
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
//		String hql1="update Asset set requeststatus=:a where assetrequestid=:b";
//		Query q=ss.createQuery(hql1);
//		q.setInteger("a", requeststatus);
//		q.setInteger("b", a.getAssetid());
//		int x1=q.executeUpdate();
		System.out.println(a.toString());
		System.out.println(rr.toString());
		Transaction tt=ss.beginTransaction();
		ss.update(a);
		x1=1;
		tt.commit();
		if(x1>0)
		{
			Transaction tt1=ss.beginTransaction();
			ss.save(rr);
			x2=1;
			tt1.commit();
		}
		if((x1 != 0) && (x2 != 0))
		{
			x=1;
		}
		ss.close();
	  	
	return x;
//		 try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps1=con.prepareStatement("update assetrequest set requeststatus=? where assetrequestid=?");
//				ps1.setInt(1, x);
//				ps1.setString(2, assetrequestid);
//				
//	     xx=ps1.executeUpdate();
//		  
//			}
//		 catch(Exception e)
//			{
//				System.out.println(e);
//			}
//			 

	}


	public int applyCreateManagerRequest(Asset a)
	{
		int status=1;
		int x=0;
		a.setRequeststatus(status);
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tt=ss.beginTransaction();
		Object o=ss.save(a);
		if(o!=null)
			x=1;
		tt.commit();
		ss.close();
		return x;

//		status=1;
//		int xx=0;
//		 try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps1=con.prepareStatement("insert into assetrequest(assetname,userid,requeststatus,requestdate)value(?,?,?,?)");
//			
//				ps1.setString(1, assetname);
//				ps1.setString(2, user_id);
//				ps1.setInt(3, status);
//				ps1.setString(4,date);
//	     xx=ps1.executeUpdate();
//		  
//			}
//		 catch(Exception e)
//			{
//				System.out.println(e);
//			}
//			 
//	  	
//	return x;
	}


	public ArrayList<Employee> getManagerProfile(String userid) 
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(Employee.class);		
		ct.add(Restrictions.eq("email",userid));
		ArrayList<Employee> list=(ArrayList<Employee>)ct.list();
		 ss.close();
//       try 
//		{
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from employee where emailid=?");
//			ps.setString(1,user_id);
//			
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
//			     e=new Employee();
//			    e.setName(rs.getString("name"));
//			    e.setMobile(rs.getString("mobile"));
//			    e.setPassword(rs.getString("password"));
//			   
//			    e.setEmail(rs.getString("emailid"));
//			    e.setStatus(rs.getInt("status"));
//			    e.setDesignation(rs.getString("designation"));
//				
//			}
//		}
//		catch(Exception ee)
//		{
//			System.out.println(ee);
//		}
//		
//	
		return list;
	}


	public int updatManagerProfile(Employee ee,String userid)
	{
		 int x=0;
			SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
			Session ss=sf.openSession();
			Transaction tt=ss.beginTransaction();
			System.out.println(ee.toString());
			ss.update(ee);
			x=1;
			tt.commit();
			ss.close();
//		int xx=0;
//		 try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps1=con.prepareStatement("update employee set name=?,password=?,mobile=? where emailid=?");
//			
//		ps1.setString(1, ee.getName());
//		ps1.setString(2, ee.getPassword());
//		ps1.setString(3, ee.getMobile());
//		ps1.setString(4,userid );	
//				
//	     xx=ps1.executeUpdate();
//		  
//			}
//		 catch(Exception e)
//			{
//				System.out.println(e);
//			}
		return x;

	}


	public ArrayList<AllocatedAsset> allocatedAssets(String username) 
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(AllocatedAsset.class);
		ct.add(Restrictions.ne("emailid",username));
		ct.addOrder(Order.desc("dateofallocation"));
		 ArrayList<AllocatedAsset> list= (ArrayList<AllocatedAsset>)ct.list();
		 ss.close();
	
//		
//		//int x=0;
//		  ArrayList<Asset> list=new ArrayList<>();
//			
//		  try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps=con.prepareStatement("select * from allocatedasset");
//			//	ps.setInt(1, x);
//				ResultSet rs=ps.executeQuery();
//				while(rs.next())
//				{
//					Asset aa=new Asset();
//					
//					aa.setUserid(rs.getString("userid"));
//					aa.setAssetname(rs.getString("assetname"));
//					aa.setAssetid(rs.getInt("assetrequestid"));
//					aa.setDateofallocation(rs.getString("dateofallocation"));
//					list.add(aa);
//				
//				}
//			}
//			catch(Exception e)
//			{
//				System.out.println(e);
//			}
//			
//			
			return list;
		}


	public ArrayList<AllocatedAsset> getMyAsset(String userid)
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(AllocatedAsset.class);
		ct.add(Restrictions.eq("emailid", userid));
		Transaction tt=ss.beginTransaction();
		 ArrayList<AllocatedAsset> list= (ArrayList<AllocatedAsset>)ct.list();
		 tt.commit();
		 ss.close();
	
//		ArrayList<Asset> list=new ArrayList<>();	
//		Asset aa=null;
//		
//	       try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps=con.prepareStatement("select * from allocatedasset where userid=?");
//				ps.setString(1,user_id);
//				
//				ResultSet rs=ps.executeQuery();
//				while(rs.next())
//				{
//				     aa=new Asset();
//				     aa.setUserid(rs.getString("userid"));
//				     aa.setAssetname(rs.getString("assetname"));
//				     aa.setAssetid(rs.getInt("assetrequestid"));
//				    aa.setDateofallocation(rs.getString("dateofallocation"));
//				     list.add(aa);
//				}
//			}
//			catch(Exception ee)
//			{
//				System.out.println(ee);
//			}
//			
//		
			return list ;
		}


	public ArrayList<Asset> viewMYAsste(String userid) 
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(Asset.class);
		ct.add(Restrictions.eq("emailid", userid));
		Transaction tt=ss.beginTransaction();
		 ArrayList<Asset> list= (ArrayList<Asset>)ct.list();
		 tt.commit();
		 ss.close();
		 return list;
		
	
//		  ArrayList<Asset> list=new ArrayList<>();
//			
//		  try 
//			{
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps=con.prepareStatement("select * from assetrequest where userid=?");
//			    ps.setString(1, user_id);
//				ResultSet rs=ps.executeQuery();
//				while(rs.next())
//				{
//					Asset aa=new Asset();
//					
//					aa.setUserid(rs.getString("userid"));
//					aa.setAssetid(rs.getInt("assetrequestid"));
//					aa.setAssetname(rs.getString("assetname"));
//					aa.setRequeststatus(rs.getInt("requeststatus"));
//					aa.setRequestdate(rs.getString("requestdate"));
//					
//					list.add(aa);
//				
//				}
//			}
//			catch(Exception e)
//			{
//				System.out.println(e);
//			}
//			
//			
			
		}


	public ArrayList<Tansfer> showEmpRequest(String user_id) {
		int eid=0; 
		ArrayList<Tansfer> list = new ArrayList<>();
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql = "select eid from Employee where email=:a";
		Query q = ss.createQuery(hql);
		q.setString("a", user_id);
		Transaction tt1=ss.beginTransaction();
		Iterator it=q.iterate();
	if(it.hasNext())
	{
		eid=(int) it.next();
		System.out.println(eid+"in daoo");
		Criteria ct=ss.createCriteria(Tansfer.class);
		ct.add(Restrictions.eq("managerid",eid));
		Criterion ct2=Restrictions.eq("status",3);
		Criterion ct3= Restrictions.eq("status",0);
		ct.add(Restrictions.or(ct2,ct3));
		list = (ArrayList<Tansfer>)ct.list();
		tt1.commit();
	}
	
		ss.close();
		return list;
	}
	

	public int approvedTransfer(TransferredAsset td,Tansfer t,int aid) {
		int x=1,z=0,c=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql = "update Tansfer set status=:a where transferReqId=:b";
		Query q = ss.createQuery(hql);
		q.setInteger("a",t.getStatus()+1);
		q.setInteger("b",td.getTransferReqId());
		Transaction tt=ss.beginTransaction();
		int y=q.executeUpdate();
		if(y>0 && t.getStatus()==0)
		{
			ss.save(td);
			z=1;
			if(z>0)
			{
				AllocatedAsset aa=new AllocatedAsset();
				aa.setAllocatedid(aid);
				aa.setAssetname(td.getAssetname());
				aa.setDateofallocation(td.getTransferredDate());
				aa.setEmailid(td.getToEmp());
				ss.saveOrUpdate(aa);
				c=1;
			}
		}
		else {
			c=2;
		}
		tt.commit();
		ss.close();
	return c;
	}


	public int rejectTransfer(Tansfer t) {
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="update Tansfer set status=:a where transferReqId=:b";
		Query q = ss.createQuery(hql);
		q.setInteger("a",2);
		q.setInteger("b",t.getTransferReqId());
		Transaction tt=ss.beginTransaction();
		int y=q.executeUpdate();
		tt.commit();
		ss.close();
		return y;
	}
}
