package domain;

public class Item {
	private int num;
	private String name;
	private int price;
	private String manufacture;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	@Override
	public String toString() {
		return "Item [num=" + num + ", name=" + name + ", price=" + price + ", manufacture=" + manufacture + "]";
	}
}
