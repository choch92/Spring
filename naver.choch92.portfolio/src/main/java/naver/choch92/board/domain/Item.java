package naver.choch92.board.domain;

public class Item {
	private Integer itemid;
	private String itemname;
	private Integer price;
	private String description;
	private String pictureurl;
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	@Override
	public String toString() {
		return "Item [itemid=" + itemid + ", itemname=" + itemname + ", price=" + price + ", description=" + description
				+ ", pictureurl=" + pictureurl + "]";
	}	
}
