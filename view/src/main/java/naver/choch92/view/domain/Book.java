package naver.choch92.view.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
//title 과 author 순으로 출력
//여기서 제외되면 데이터가 있어도 출력되지 않습니다.
@XmlType(name="", propOrder= {"title", "author"})
public class Book {
	private String title;
	private String author;
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + "]";
	}
}
