package naver.choch92.view.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
//가장 외곽의 루트 태그
@XmlRootElement(name="Books")
public class BookReport {
	// 객체 하나의 태그
	@XmlElement(name="Books")
	private List<Book> list;

	public List<Book> getList() {return list;}

	public void setList(List<Book> list) {this.list = list;}
}

