package dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import beans.Employee;
import dto.DBConnector;

public class AdminDao {
//	public int checkLogin(String username,String password)
//	{
//		int x=0;
////		try {
////			DBConnector dbc=new DBConnector();
////			Connection con=dbc.start();
////			PreparedStatement ps=con.prepareStatement("select * from admin where adminid=? and adminpass=?");
////			ps.setString(1, username);
////			ps.setString(2,password);
////			ResultSet rs=ps.executeQuery();
////			while(rs.next())
////			{
////				x=1;
////			}
////			
////		}catch(SQLException e)
////		{System.out.println(e);}
//		return x;
//	}
//	
	public int addEmployee(Employee e)
	{ 
		int x=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tt=ss.beginTransaction();
		Object o=ss.save(e);
		if(o!=null)
			x=1;
		tt.commit();
		ss.close();
		return x;
//		try {
//				DBConnector dbc=new DBConnector();
//				Connection con=dbc.start();
//				PreparedStatement ps=con.prepareStatement("insert into employee(name,mobile,emailid,password,doj,designation,managerid) value(?,?,?,?,?,?,?)");
//				ps.setString(1,e.getName());
//				ps.setString(2, e.getMobile());
//				ps.setString(3,e.getEmail());
//				ps.setString(4, e.getPassword());
//				ps.setString(5,e.getDoj());
//				ps.setString(6,e.getDesignation());
//				ps.setInt(7,e.getManagerid());
//				x=ps.executeUpdate();
//				
//		}catch(SQLException ex)
//		{System.out.println(ex);}
//		return x;
	}
	
	public ArrayList<Employee> viewEmployeeAdmin()
	{
		DBConnector dbc=new DBConnector();
		dbc.start();
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(Employee.class);
		ArrayList<Employee> list=(ArrayList<Employee>)ct.list();
		ss.close();
		return list;
		/*try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from employee");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Employee e=new Employee();
				e.setEid(rs.getInt("eid"));
				e.setName(rs.getString("name"));
				e.setMobile(rs.getString("mobile"));
				e.setEmail(rs.getString("emailid"));
				e.setPassword(rs.getString("password"));
				e.setDoj(rs.getString("doj"));
				e.setStatus(rs.getInt("status"));
				e.setDesignation(rs.getString("designation"));
				e.setManagerid(rs.getInt("managerid"));
				list.add(e);
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;*/
	}
	
	public ArrayList<Employee> viewEmployeeDetailsAdmin(String email)
	{
			SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
			Session ss=sf.openSession();
			Criteria ct=ss.createCriteria(Employee.class);
			ct.add(Restrictions.eq("email", email));
			ArrayList<Employee> list=(ArrayList<Employee>)ct.list();
			ss.close();
			return list;
			/*//ArrayList<Employee> list=new ArrayList<>();
			try {
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("select * from employee where emailid=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Employee e=new Employee();
				e.setName(rs.getString("name"));
				e.setMobile(rs.getString("mobile"));
				e.setEmail(rs.getString("emailid"));
				e.setPassword(rs.getString("password"));
				e.setDoj(rs.getString("doj"));
				e.setStatus(rs.getInt("status"));
				e.setDesignation(rs.getString("designation"));
				e.setManagerid(rs.getInt("managerid"));
				list.add(e);
			}
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;*/
	}
	
	public int deactivate(String email)
	{
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="update Employee set status=:bb where email=:aa";
		Query query=ss.createQuery(hql);
		query.setString("aa",email);
		query.setInteger("bb", 0);
		int x=query.executeUpdate();	
		ss.close();
		/*int x=0;
		try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("update employee set status=0 where emailid=?");
			ps.setString(1, email);
			x=ps.executeUpdate();
	
		}catch(SQLException e)
		{
			System.out.println(e);
		}*/
		return x;
	}
	
	
	
	public int updateEmployeeData(Employee e)
	{ int x=0;
	//System.out.println(e);
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Transaction tt=ss.beginTransaction();
		ss.update(e);
		x=1;
		tt.commit();
		ss.close();
		/*try {
				DBConnector dbc=new DBConnector();
				Connection con=dbc.start();
				String s=e.getEmail();
				PreparedStatement ps=con.prepareStatement("update employee set name=?,mobile=?,emailid=?,password=?,doj=?,designation=?,status=?,managerid=? where emailid=?");
				ps.setString(1,e.getName());
				ps.setString(2, e.getMobile());
				ps.setString(3,e.getEmail());
				ps.setString(4, e.getPassword());
				ps.setString(5,e.getDoj());
				ps.setString(6,e.getDesignation());
				ps.setInt(7, e.getStatus());
				ps.setInt(8,e.getManagerid());
				ps.setString(9,s);
				x=ps.executeUpdate();
				
		}catch(SQLException ex)
		{System.out.println(ex);}*/
		return x;
	}
	
	
	public int changePassword(String adminid,String adminpass)
	{
		int x=0;

		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="update Login set adminpass=:aa where adminid=:bb";
		Query q=ss.createQuery(hql);
		q.setString("aa",adminpass);
		q.setString("bb",adminid);
		Transaction tt=ss.beginTransaction();
		x=q.executeUpdate();
		tt.commit();
		ss.close();
		/*try {
			DBConnector dbc=new DBConnector();
			Connection con=dbc.start();
			PreparedStatement ps=con.prepareStatement("update admin set adminpass=? where adminid=?");
			ps.setString(1, password);
			ps.setString(2,userid);
			x=ps.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println(e);
		}*/
		return x;
	}
	
	public ArrayList<Integer> getData(){
		
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="select eid from Employee where designation=:bb";
		Query q=ss.createQuery(hql);
		q.setString("bb", "manager");		
		ArrayList<Integer> list=(ArrayList<Integer>)q.list();
		//System.out.println(list);	
		ss.close();
		
		//ArrayList<Employee> list=new ArrayList<>();
		/*try {
		DBConnector dbc=new DBConnector();
		Connection con=dbc.start();
		PreparedStatement ps=con.prepareStatement("select eid from employee where designation='manager'");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{	
			Employee e=new Employee();
			e.setManagerid(rs.getInt("eid"));
			list.add(e);
		}
		}catch(SQLException e) {
			System.out.println(e);
		}		*/
		return list;
		
	}
	
	public String getManagerName(int mid)
	{
		String mname="";
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="select name from Employee where managerid=:aa";
		Object o=ss.load(Employee.class,new Integer(mid));
		Employee e=(Employee)o;
		mname=e.getName();
		ss.close();
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select name from employee where eid=?");
//			ps.setInt(1,mid);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next()) {
//				mname=rs.getString("name");
//			}
//		}catch(SQLException e)
//		{System.out.println(e);}
		return mname;
	}

	public int checkEmailExists(String email) {
		int x=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="select eid from Employee where email=:a";
		Query q=ss.createQuery(hql);
		q.setString("a", email);
		Iterator i=q.iterate();
		if(i.hasNext()) {
			x=1;
		}
		// TODO Auto-generated method stub
		return x;
	}
}
