package naver.choch92.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import naver.choch92.domain.Item;

//Bean(객체)을 자동으로 생성해주는 어노테이션
@Repository
public class ItemDao {
	// 동일한 자료형의 bean이 있으면 자동으로 대입됩니다.
	@Autowired
	private SessionFactory sessionFactory;
	
	// 데이터 삽입하는 메소드
	public void insert(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.save(item);
	}
	// 데이터 수정하는 메소드
	public void update(Item item) {
		Session session = sessionFactory.getCurrentSession();
		session.update(item);
	}
	// 데이터 삭제하는 메소드
	public void delete(Item item) {
		// SQL 실행 객체 만들기
		Session session = sessionFactory.getCurrentSession();
		session.delete(item);
	}
	// 전체 데이터 가져오는 메소드
	public List<Item> allItem(){
		Session session = sessionFactory.getCurrentSession();
		// SQL을 이용해서 select 사용
		/*
		List<Item> list = (List<Item>)session.createSQLQuery(
				"select * from item").addEntity(Item.class).list();
		*/
		// SQL을 이용하지 않고 데이터 가져오기
		/*
		List<Item> list = session.createCriteria(Item.class).list();
		*/
		// Hibernate Query(HQL)를 이용한 데이터 가져오기
		List<Item> list = (List<Item>)session.createQuery("from Item").list();
			return list;
		}
			
	// 데이터 1개를 가져오는 메소드
	public Item getItem(int code) {
		Session session = sessionFactory.getCurrentSession();
		// 기본키를 가지고 가져오는 경우
		// Item item = session.get(Item.class, code);
		
		// name을 가지고 찾아오기 : sql 이용
		/*
		List<Item> list = 
				(List<Item>)session.createSQLQuery("select * from item where name=:name")
				.addEntity(Item.class).setString("name", "단감").list();
		Item item = null;
		if(list != null && list.size() > 0) {
			item = list.get(0);
		}
		*/
				
		// name을 가지고 조회 : HQL 이용
		// 테이블 이름에 별명을 반드시 사용
		// 파라미터에 물음표 설정이 안됨
		Query query = session.createQuery("from Item as item where item.name = :name");
		query.setString("name", "단감");
		List<Item> list = (List<Item>)query.list();
		Item item = null;
		if(list != null && list.size() >0) {
			item = list.get(0);
		}
		return item;
	}
}
