package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import beans.AllocatedAsset;
import beans.Asset;
import beans.Employee;
import dto.DBConnector;

public class EmployeeDaoRakesh {
	
//	public Connection start()
//	{
//		Connection con = null;
//		try {
//		Class.forName("com.mysql.jdbc.Driver");
//		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assetmanagement","root","");
//		}catch(SQLException | ClassNotFoundException e)
//		{
//			System.out.println(e);
//			
//		}
//		return con;
//	}
//	
//public int Emplogin(String emailid,String Cpwd,String designation)
//				{
//				int x=0;
//				try {
//					
//							Connection con=start();
//							
//				PreparedStatement pr=con.prepareStatement("select * from employee where emailid=? and password=? and designation=?");
//				pr.setString(1, emailid);
//				pr.setString(2, Cpwd);
//				pr.setString(3, designation);
//				ResultSet rs=pr.executeQuery();
//				if(rs.next())
//				{
//				x=1;
//				}
//				} catch (Exception e) {
//				System.out.println(e);
//				}
//				return x;
//				}
//


				
public ArrayList<Employee> viewEmployeeProfile(String email)
{
	SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
	Session ss=sf.openSession();
	Criteria ct=ss.createCriteria(Employee.class);
	ct.add(Restrictions.eq("email", email));
	ArrayList<Employee> list=(ArrayList<Employee>)ct.list();
	ss.close();
	return list;
//	ArrayList<Employee> list=new ArrayList<>();
//	try {     					       
//	    	Connection con = start();
//	        PreparedStatement ps=con.prepareStatement("select * from employee where emailid=? ");
//	        ps.setString(1, email);
//	        ResultSet rs= ps.executeQuery();					        
//	        
//	        while(rs.next())
//			{
//		    	Employee e=new Employee();
//		    	
//		        e.setName(rs.getString("name"));
//		        e.setMobile(rs.getString("mobile"));
//		        e.setEmail(rs.getString("emailid"));
//		        e.setPassword(rs.getString("password"));							        
//		        list.add(e);							     
//			 }							     
//	     con.close();
//		  }
//	    catch(SQLException ex)
//	        {
//	            System.err.println(ex);
//	        }
//	    return list;
 }



public int updateEmpData(Employee ab)
{ int x=0;
	SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tx=ss.beginTransaction();
	ss.update(ab);
	x=1;
	tx.commit();
	ss.close();
	return x;
//	String hql="update Employee set name=:a,mobile=:b,email=:c,password=:d where email=:e";
//	Query q=ss.createQuery(hql);
//	q.setString("a",ab.getName());
//	q.setString("b",ab.getMobile());
//	q.setString("c", ab.getEmail());
//	q.setString("d",ab.getPassword());
//	q.setString("e",ab.getEmail());
//	System.out.println(ab.toString());
//	Transaction tx=ss.beginTransaction();
//	x=q.executeUpdate();
//	System.out.println(x);
//	tx.commit();
//	ss.close();
//	return x;

}


//public int changePassword(String r,String q)
//{
//	int x=0;
//	try {
//		Connection con = start();
//		PreparedStatement ps=con.prepareStatement("update employee set password=? where emailid=?");
//		ps.setString(1,q);
//		ps.setString(2,r);
//		x=ps.executeUpdate();
//	}catch(SQLException e)
//	{
//		System.out.println(e);
//	}
//	return x;
//}


public int assetRequest(Asset a) {
	int x=0;
	SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
	Session ss=sf.openSession();
	Transaction tt=ss.beginTransaction();
	Object o=ss.save(a);
	if(o!=null)
		x=1;
	tt.commit();
	ss.close();
	return x;
//	try 
//	{
//		Connection con = start();
//	
//		PreparedStatement pr = con.prepareStatement("insert into assetrequest(userid,assetname,requestdate) values(?,?,?)");
//		pr.setString(1,email);
//		pr.setString(2,asset);
//		pr.setString(3, date);
//		x=pr.executeUpdate();		
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}	
//	return x;
	}

public ArrayList<Asset> viewEmpRequest(String email)
{
	SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
	Session ss=sf.openSession();
	Criteria ct=ss.createCriteria(Asset.class);
	ct.add(Restrictions.eq("emailid", email));
	ArrayList<Asset> list=(ArrayList<Asset>)ct.list();
	ss.close();
	return list;
	
//    ArrayList<Asset> list=new ArrayList<>();
//
//    try {     
//       
//    	Connection con = start();    	
//        PreparedStatement ps=con.prepareStatement("select * from assetrequest where userid=?");
//        ps.setString(1, email);
//        ResultSet rs= ps.executeQuery();
//        
//        while(rs.next())
//	     {
//	    	Asset e=new Asset();
//		    	e.setAssetid(rs.getInt("assetrequestid"));
//		    	//System.out.println(rs.getInt("assetrequestid")+"in edr dao");
//		        e.setUserid(rs.getString("userid"));
//		        e.setAssetname(rs.getString("assetname"));
//		        e.setRequestdate(rs.getString("requestdate"));
//		        e.setRequeststatus(rs.getInt("requeststatus"));	
//		        //System.out.println(e);
//	        list.add(e); 
//	    }     
//     con.close();
//	  }
//    catch(SQLException ex)
//        {
//            System.err.println(ex);
//        }
//    return list;
    }

public int deleteRequest(int assetid)
{
	int x=0;
	SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
	Session ss=sf.openSession();
	String hql="delete from Asset where assetrequestid=:aa";
	Query q=ss.createQuery(hql);
	q.setInteger("aa",assetid);
	Transaction tt=ss.beginTransaction();
	x=q.executeUpdate();
	tt.commit();
	ss.close();
	sf.close();
		
//		try {
//		Connection con = start();
//		PreparedStatement ps=con.prepareStatement("delete from assetrequest where assetrequestid=?");
//		ps.setInt(1, assetrequestid);
//		 x=ps.executeUpdate();  
//		  }
//		  catch (SQLException e) {
//			e.printStackTrace();
//		}
			return x;
}

public ArrayList<AllocatedAsset> viewEmployeeAssetR(String userid)
{
	SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
	Session ss=sf.openSession();
	Criteria ct=ss.createCriteria(AllocatedAsset.class);
	ct.add(Restrictions.eq("emailid", userid));
	ArrayList<AllocatedAsset> list=(ArrayList<AllocatedAsset>)ct.list();
	return list;
//	try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from allocatedasset where userid=?");
//			ps.setString(1, userid);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
//				Asset aa=new Asset();
//				aa.setAssetid(rs.getInt("assetrequestid"));
//				aa.setUserid(rs.getString("userid"));
//				aa.setAssetname(rs.getString("assetname"));
//				aa.setDateofallocation(rs.getString("dateofallocation")); 
//				list.add(aa);
//			}
//	
//	}catch(SQLException ex)
//	{System.out.println(ex);}
//	return list;
	
}

}
	

	

