package my_hibernate_pack.crud;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Controller {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Scanner sc = new Scanner(System.in);
		System.out.println("1=insert   2=fetch_id  3=update  4=delete 5=fetch_all");
		int no = sc.nextInt();

		Instagram instagram = new Instagram();

		switch (no) {
		case 1:
			System.out.println("Insert Id");
			int id = sc.nextInt();
			System.out.println("Insert Name");
			String name = sc.next();
			System.out.println("Insert Gmail");
			String mail = sc.next();

			instagram.setId(id);
			instagram.setName(name);
			instagram.setMail(mail);

			entityTransaction.begin();
			entityManager.persist(instagram);
			entityTransaction.commit();
			System.out.println("data inserted");
			break;
		case 2:
			System.out.println("Enter Id");
			int id2 = sc.nextInt();
			Instagram i2 = entityManager.find(Instagram.class, id2);
			System.out.println(i2.getId() + " " + i2.getName() + " " + i2.getMail());
			break;
		case 3:
			System.out.println("update name");
			String n3 = sc.next();
			System.out.println("update mail");
			String m3 = sc.next();
			System.out.println("enter id");
			int id3 = sc.nextInt();

			Instagram in3 = entityManager.find(Instagram.class, id3);
			in3.setName(n3);
			in3.setMail(m3);
			entityTransaction.begin();
			entityManager.persist(in3);
			entityTransaction.commit();
			System.out.println("data updated");
			break;
		case 4:
//			System.out.println("enter id");
//			int n4=sc.nextInt();
//			Query query=entityManager.createQuery("delete from Instagram where id=2");
			Query query=entityManager.createQuery("delete from Instagram");
//			Instagram in4=entityManager.find(Instagram.class,n4);
			entityTransaction.begin();
			query.executeUpdate();
			entityTransaction.commit();
			System.out.println("data deleted");
			break;
			
		case 5:
			Query query2=entityManager.createQuery("select a from Instagram a");
			List<Instagram> i5=query2.getResultList();
			for (Instagram i6 : i5) {
				System.out.println(i6.getId()+" "+i6.getName()+" "+i6.getMail());
			}
		}
	}

}
