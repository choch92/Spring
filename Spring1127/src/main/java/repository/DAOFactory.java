package repository;

public class DAOFactory {

	// DAO 객체를 생성해서 리턴해주는 메소드
	public static DAO create() {
		return new DAO();
	}
}
