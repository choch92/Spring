package naver.choch92.item.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.choch92.item.domain.Item;

@Repository
public class ItemHibernateDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	// 전체 데이터를 가져오는 메소드
	public List<Item> allItem(){
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Item.class).list();
	}
	// itemid를 가지고 1개의 데이터를 찾아오는 메소드
	public Item getItem(int itemid) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Item.class, itemid);
	}
}
