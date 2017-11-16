package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import beans.Employee;
import beans.Login;
import dto.DBConnector;

public class AdminLoginDao {
	public int checkLogin(String username,String password)
	{
		int x=0;
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		Criteria ct=ss.createCriteria(Login.class);
		ct.add(Restrictions.eq("adminid",username));
		ct.add(Restrictions.eq("adminpass", password));
		List list=ct.list();
		if(!list.isEmpty())
			x=1;
		return x;
//		try {
//			DBConnector dbc=new DBConnector();
//			Connection con=dbc.start();
//			PreparedStatement ps=con.prepareStatement("select * from admin where adminid=? and adminpass=?");
//			ps.setString(1, username);
//			ps.setString(2,password);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
//				x=1;
//			}
//			
//		}catch(SQLException e)
//		{System.out.println(e);}
//		return x;
	}

	public String checkoldpass(String adminid) {
		String x="";
		SessionFactory sf=new AnnotationConfiguration().configure().buildSessionFactory();
		Session ss=sf.openSession();
		String hql="select adminpass from  Login where adminid=:a";
		Object o=ss.load(Login.class,new String(adminid));
		Login l=(Login)o;
		x=l.getAdminpass();
		return x;
	}
	
}
