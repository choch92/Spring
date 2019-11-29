package vo;

public class Item {
	private int itemid;
	private String itemname;
	private int itemprice;
	private String itemmanufacture;
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getItemprice() {
		return itemprice;
	}
	public void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	public String getItemmanufacture() {
		return itemmanufacture;
	}
	public void setItemmanufacture(String itemmanufacture) {
		this.itemmanufacture = itemmanufacture;
	}
	@Override
	public String toString() {
		return "Item [itemid=" + itemid + ", itemname=" + itemname + ", itemprice=" + itemprice + ", itemmanufacture="
				+ itemmanufacture + "]";
	}
}
