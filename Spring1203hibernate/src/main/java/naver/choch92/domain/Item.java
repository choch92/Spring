package naver.choch92.domain;

import lombok.Data;
//getter, setter, toString, equals, hashcode 메소드 생성
@Data
public class Item {
	private int code;
	private String name;
	private int price;
	private String manufacture;
}
