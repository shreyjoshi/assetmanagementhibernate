package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import beans.AllocatedAsset;
import beans.Asset;
import beans.Employee;
import beans.Login;
import beans.RejectedRequest;
import beans.Tansfer;
import beans.TransferredAsset;
@SuppressWarnings("unchecked")
public class SupportDao {
	
	
	public ArrayList checkLogin(String username,String password)//,String designation)
	{
		ArrayList list=new ArrayList();
		int x=0,status=1;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(Employee.class);
		ct.add(Restrictions.eq("email", username));
		ct.add(Restrictions.eq("password",password));
		//ct.add(Restrictions.eq("designation",designation));
		//ct.add(Restrictions.eq("status",status));
		list=(ArrayList)ct.list();
		if(list.isEmpty())
		{
			Criteria ct1=ss.createCriteria(Login.class);
			ct1.add(Restrictions.eq("adminid",username));
			ct1.add(Restrictions.eq("adminpass", password));
			list=(ArrayList)ct1.list();
		}
		System.out.println(list);
		return list;
//		int x=0;
//		try {
//		DBConnector dbc=new DBConnector();
//		Connection con=dbc.start();
//		PreparedStatement ps=con.prepareStatement("select * from employee where emailid=? and password=? and designation=? and status=1"); 
//		ps.setString(1, username);
//		ps.setString(2,password);
//		ps.setString(3, designation);
//		ResultSet rs=ps.executeQuery();
//		while(rs.next())
//		{
//			x=1;
//		}
//		}catch(SQLException e)
//		{System.out.println(e);}
		
	}
	
	public ArrayList<Employee> viewSupportProfile(String username) {
		
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(Employee.class);		
		ct.add(Restrictions.eq("email",username));
		Transaction tt=ss.beginTransaction();
		ArrayList<Employee> list=(ArrayList<Employee>)ct.list();
		tt.commit();
		 ss.close();
		 return list;
		 
//		ArrayList<Employee> list=new ArrayList<>();
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from employee where emailid=?");
//			ps.setString(1, username);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
//				Employee e=new Employee();
//				e.setName(rs.getString("name"));
//				e.setMobile(rs.getString("mobile"));
//				e.setEmail(rs.getString("emailid"));
//				e.setPassword(rs.getString("password"));
//				e.setDesignation(rs.getString("designation"));
//				list.add(e);
//			}
//		}catch(SQLException e)
//		{System.out.println(e);}
//		return list;
	}
	
	public ArrayList<Asset> viewResourceRequestSupport()
	{
		int x=1;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="from Asset where requeststatus=:a";
		Query q=ss.createQuery(hql);
		q.setInteger("a", x);
		Transaction tt=ss.beginTransaction();
		ArrayList<Asset> list=(ArrayList<Asset>)q.list();
		tt.commit();
		ss.close();
		return list;
//		ArrayList<Asset> list=new ArrayList<>();
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from assetrequest where requeststatus=1");
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
//				Asset a=new Asset();
//				a.setUserid(rs.getString("userid"));
//				a.setAssetname(rs.getString("assetname")); 
//				a.setAssetid(rs.getInt("assetrequestid"));
//				a.setRequestdate(rs.getString("requestdate"));
//				a.setRequeststatus(rs.getInt("requeststatus"));
//				list.add(a);	
//			}
//		}catch(SQLException e)
//		{System.out.println(e);}
//		return list;
	}
	
	public ArrayList<AllocatedAsset> viewReportSupport(){
		
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="from AllocatedAsset";
		Query q=ss.createQuery(hql);
		Transaction tt=ss.beginTransaction();
		ArrayList<AllocatedAsset> list=(ArrayList<AllocatedAsset>)q.list();
		tt.commit();
		ss.close();
		return list;
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from allocatedasset");
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
//				Asset a=new Asset();
//				a.setAssetid(rs.getInt("assetrequestid"));
//				a.setUserid(rs.getString("userid"));
//				a.setAssetname(rs.getString("assetname"));
//				a.setDateofallocation(rs.getString("dateofallocation"));
//				list.add(a);
//			}
//		}catch(SQLException e)
//		{System.out.println(e);}
//		return list;
	}
	
	public ArrayList<RejectedRequest> viewRejectedRequests()
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="from RejectedRequest";
		Query q=ss.createQuery(hql);
		Transaction tt=ss.beginTransaction();
		ArrayList<RejectedRequest> list=(ArrayList<RejectedRequest>)q.list();
		tt.commit();
		ss.close();
		return list;
	}
	public int changePassword(String username,String password)
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="update Employee set password=:bb where email=:aa";
		Query query=ss.createQuery(hql);
		query.setString("aa",username);
		query.setString("bb", password);
		Transaction tt=ss.beginTransaction();
		int x=query.executeUpdate();	
		tt.commit();
		ss.close();
		return x;
//		int x=0;
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("update employee set password=? where emailid=?");
//			ps.setString(1, password);
//			ps.setString(2,username);
//			x=ps.executeUpdate();
//		}catch(SQLException e)
//		{
//			System.out.println(e);
//		}
//		return x;
	}
	
	public int updateSuppotProfile(Employee e)
	{ 
		int x=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tt=ss.beginTransaction();
		System.out.println(e.toString());
		ss.update(e);
		x=1;
		tt.commit();
		ss.close();
//		int x=0;
//		try {
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				String s=e.getEmail();
//				PreparedStatement ps=con.prepareStatement("update employee set name=?,mobile=?,emailid=?,password=?,designation=? where emailid=?");
//				ps.setString(1,e.getName());
//				ps.setString(2, e.getMobile());
//				ps.setString(3,e.getEmail());
//				ps.setString(4, e.getPassword());
//				ps.setString(5,e.getDesignation());
//				ps.setString(6,s);
//				x=ps.executeUpdate();
//				
//		}catch(SQLException ex)
//		{System.out.println(ex);}
		return x;
	}
	
	public int approveFromSupport(Asset a,String processdate)
	{
		int x2=0,x=0;
		int requeststatus=3;
		a.setRequeststatus(requeststatus);
		
		AllocatedAsset aad=new AllocatedAsset();
			aad.setAssetname(a.getAssetname());
			aad.setDateofallocation(processdate);
			aad.setEmailid(a.getemailid());
			
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tt=ss.beginTransaction();
		
		System.out.println(aad.toString());
		System.out.println(a.toString());
//			String hql1="update Asset set requeststatus=:a where assetrequestid=:b";
//			Query q=ss.createQuery(hql1);
//				q.setInteger("a", requeststatus);
//				q.setInteger("b", a.getAssetid());
//				int x1=q.executeUpdate();
		ss.update(a);
		int x1=1;
		tt.commit();
		if(x1>0)
		{
			Transaction tt1=ss.beginTransaction();
			ss.save(aad);
			x2=1;
			tt1.commit();
		}	
		if((x1 != 0) && (x2 != 0))
		{
			x=1;
		}
		
		ss.close();
		
//		try {
//				int y=a.getAssetid();
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps1=con.prepareStatement("update assetrequest set requeststatus=3 where assetrequestid=?");
//				ps1.setInt(1, y);
//				PreparedStatement ps2=con.prepareStatement("insert into allocatedasset(assetrequestid,userid,assetname) value(?,?,?) ");
//				ps2.setInt(1,y);
//				ps2.setString(2,a.getUserid());
//				ps2.setString(3,a.getAssetname());
//				con.setAutoCommit(false);
//				int z1=ps1.executeUpdate();
//				int z2=ps2.executeUpdate();
//				con.commit();
//				if((z1 != 0) && (z2 != 0))
//				{
//					x=1;
//				}
//			
//		}catch(SQLException e) {
//			System.out.println(e);
//		}
		return x;
	}
	
	
	public int rejectFromSupport(Asset a,String desig,String processdate)
	{
		
		int x2=0,x=0;
		int requeststatus=4;
		a.setRequeststatus(requeststatus);
		RejectedRequest rr=new RejectedRequest();
			rr.setAssetrequestid(a.getAssetrequestid());
			rr.setAssetname(a.getAssetname());
			rr.setEmailid(a.getemailid());
			rr.setDateofrejection(processdate);
			rr.setRejectedby(desig);
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
//		String hql1="update Asset set requeststatus=:a where assetrequestid=:b";
//		Query q=ss.createQuery(hql1);
//		q.setInteger("a", requeststatus);
//		q.setInteger("b", a.getAssetid());
//		int x1=q.executeUpdate();
//		System.out.println(a.toString());
//		System.out.println(rr.toString());
		ss.update(a);
		int x1=1;
		if(x1>0)
		{
			Transaction tt=ss.beginTransaction();
			ss.save(rr);
			x2=1;
			tt.commit();
		}
		if((x1 != 0) && (x2 != 0))
		{
			x=1;
		}
		ss.close();
//		int x=0;
//		try {
//				int y=a.getAssetid();
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps1=con.prepareStatement("update assetrequest set requeststatus=4 where assetrequestid=?");
//				ps1.setInt(1, y);
//				PreparedStatement ps2=con.prepareStatement("insert into rejectedrequest(assetrequestid,userid,assetname,rejectedby) value(?,?,?,?) ");
//				ps2.setInt(1,y);
//				ps2.setString(2,a.getUserid());
//				ps2.setString(3,a.getAssetname());
//				ps2.setString(4,desig);
//				con.setAutoCommit(false);
//				int z1=ps1.executeUpdate();
//				int z2=ps2.executeUpdate();
//				con.commit();
//				if((z1 != 0) && (z2 != 0))
//				{
//					x=1;
//				}
//			
//		}catch(SQLException e) {
//			System.out.println(e);
//		}
		return x;
	}
	
	public String getPassword(String userid) {
		String pass="";
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction  tt=ss.beginTransaction();
		String hql="select password from Employee where email=:aa";
		Query q=ss.createQuery(hql);//(Employee.class,new String(userid));
		q.setString("aa",userid);
		Iterator it=q.iterate();
		if(it.hasNext())
		{
			//Employee e=(Employee)
			pass=(String) it.next();//e.getPassword();
			//System.out.println(pass);
		}
		tt.commit();
		ss.close();
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select password from employee where emailid=?");
//			ps.setString(1, userid);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()) {
//				pass=rs.getString("password");
//			}
//		}catch(SQLException e) {
//			System.out.println(e);
//		}
		return pass;
	}

	public int activateEmployee(String email)
	{
		int x=0,status=1;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="update Employee set status=:bb where email=:aa";
		Query query=ss.createQuery(hql);
		query.setString("aa",email);
		query.setInteger("bb", status);
		Transaction tt=ss.beginTransaction();
		x=query.executeUpdate();	
		tt.commit();
		ss.close();
		return x;
	}
	
	public ArrayList<AllocatedAsset> fetchAssetName(String email){
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="select assetname from AllocatedAsset where emailid=:a";
		Query query=ss.createQuery(hql);
		query.setString("a",email);
		Transaction tt=ss.beginTransaction();
		ArrayList<AllocatedAsset> list=(ArrayList<AllocatedAsset>)query.list();	
		tt.commit();
		ss.close();
		return list;
	}
	
	public int fetchManager(String fromEmp)
	{
		int managerid=0;	
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction  tt=ss.beginTransaction();
		String hql="select managerid from Employee where email=:aa";
		Query q=ss.createQuery(hql);//(Employee.class,new String(userid));
		q.setString("aa",fromEmp);
		Iterator it=q.iterate();
		if(it.hasNext())
		{
			//Employee e=(Employee)
			managerid=(Integer) it.next();//e.getPassword();
			//System.out.println(pass);
		}
		tt.commit();
		ss.close();
		return managerid;
	}

	public int checkToEmp(String toEmp) {
		int x=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction  tt=ss.beginTransaction();
		String hql="select email from Employee where email=:aa";
		Query q=ss.createQuery(hql);//(Employee.class,new String(userid));
		q.setString("aa",toEmp);
		Iterator it=q.iterate();
		if(it.hasNext())
		{
			if(toEmp.equals(it.next())) {
				x=1;
				System.out.println(x);
			}
		}
		tt.commit();
		ss.close();
		return x;
	}

	public int transferReq(Tansfer tb) {
		
		int x=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction  tt=ss.beginTransaction();
		ss.save(tb);
		x=1;
		tt.commit();
		ss.close();
		return x;
	}

	public ArrayList<TransferredAsset> viewTransferredHistory() {
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(TransferredAsset.class);
		ct.addOrder(Order.desc("transferredDate"));
		 ArrayList<TransferredAsset> list= (ArrayList<TransferredAsset>)ct.list();
		 ss.close();
		return list;
	}
	
	
	
}
