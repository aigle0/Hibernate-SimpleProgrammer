package com.simpleprogrammer;

import java.util.Date;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class Program {

	public static void main(String[] args) {

		System.out.println("hello");

		PopulateSampleData();
		
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.enableFilter("nameFilter").setParameter("name", "o%");//how to use a filter and how to add in your user hbm conf
		session.beginTransaction();
		
		//Query query =session.getNamedQuery("AllGoalAlerts");
		
		
		//Query query = session.createQuery("from GoalAlert");//.setFirstResult(2)
			//	.setMaxResults(1);
		
		//Query query = session.createQuery("select new com.simpleprogrammer.UserTotal(user.name, user.proteinData.total)" +"from User user");
		//List<UserTotal> userTotals = query.list();
		//Criteria criteria = session.createCriteria(User.class);
		/*Criteria criteria = session.createCriteria(User.class)
		.add(Restrictions.or(
			Restrictions.eq("name","Omar"),
			Restrictions.eq("name","Beta")
		)).setProjection(Projections.projectionList()
			.add(Projections.property("name"))
			.add(Projections.property("id"))
		);
		
		
		Criteria criteria2 = session.createCriteria(User.class)
		.createAlias("proteinData","pd")
		.add(Restrictions.or(
			Restrictions.eq("name","Omar"),
			Restrictions.eq("name","beta"),
			Restrictions.eq("name","el")
		)).setProjection(Projections.projectionList()
				.add(Projections.property("pd.total"))
				.add(Projections.property("pd.id")));
		
		List<Object[]> results = criteria2.list();
		for(Object[] result : results){
			for(Object o : result){
				System.out.println(o.toString());
			}
			
		}*/
		
		/*Criteria criteria = session.createCriteria(User.class);
		List<User> users = criteria.list();
		for(User user : users){
			System.out.println(user.getName());
		}
		
		Query query = session.createQuery("update ProteinData pd set pd.total=1000"); 
		query.executeUpdate();	*/	
		
		/*Criteria criteria = session.createCriteria(User.class);

		ScrollableResults users = criteria.setCacheMode(CacheMode.IGNORE).scroll();
		int count =0;
		while(users.next()){
			User user = (User) users.get(0);
			user.setProteinData(new ProteinData());
			session.save(user);
			if(++count % 10 == 0){
				session.flush();
				session.clear();
			}
			System.out.println(user.getName());
		}*/
		
		//Query query = session.createSQLQuery("SELECT * FROM Users").addEntity(User.class);
		Query query = session.createQuery("FROM User");

		List<User> users = query.list();
		for(User user : users){
			System.out.println(user.getName());
		}
		
		User u = (User) session.load(User.class, 1);
		System.out.println(u.getName());
		
		session.getTransaction().commit();
		session.close();
		
		HibernateUtilities.getSessionFactory().close();
	}
	
	private static void PopulateSampleData() {

		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		User omar = CreateUser("Omar",500,50,"Good Time", "Go ahead!");
		session.save(omar);
		
		User beta = CreateUser("beta",300,20,"Job Time");
		session.save(beta);
		
		User el = CreateUser("el",250,200,"Just Do it!");
		session.save(el);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	private static User CreateUser(String name, int goal, int total, String ... alerts) {

		User user = new User();
		user.setName(name);
		user.getProteinData().setGoal(goal);
		user.addHistory(new UserHistory(new Date(), "Set goal to " + goal));
		user.getProteinData().setTotal(total);
		user.addHistory(new UserHistory(new Date(), "Set total to " + total));
		for(String alert: alerts){
			user.getGoalAlerts().add(new GoalAlert(alert));
		}
		return user;
	}
	/*public static void main(String[] args) {

		System.out.println("hello");
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		
		User user = new User();
		user.setName("Omar");
		user.addHistory(new UserHistory(new Date(),"Set name to Omar"));
		user.getProteinData().setGoal(550);
		user.addHistory(new UserHistory(new Date(),"Set the goal to 550"));
		
		user.getGoalAlerts().add(new GoalAlert("Congratulations Ow… !"));
		user.getGoalAlerts().add(new GoalAlert("Congras IWi !"));

		
		session.save(user);
		
		session.getTransaction().commit();
		
		session.beginTransaction();
		
		User loadedUser = (User) session.load(User.class, 1);
		System.out.println(loadedUser.getName());
		System.out.println(loadedUser.getProteinData().getGoal());
		for(UserHistory history : loadedUser.getHistory()){
			System.out.println(history.getEntrytime().toString()+"  "+ history.getEntry());
		}
		
		loadedUser.getProteinData().setTotal(loadedUser.getProteinData().getTotal() + 150);
		loadedUser.addHistory(new UserHistory(new Date(),"Added 150 protein"));
		System.out.println(loadedUser.getProteinData().getTotal());
		
		user.setProteinData(new ProteinData());
		
		session.save(loadedUser);
		session.getTransaction().commit();
		
		session.close();
		HibernateUtilities.getSessionFactory().close();
	}*/

	

	

}
