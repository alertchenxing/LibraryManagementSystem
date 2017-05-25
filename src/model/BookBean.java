package model;

public class BookBean {
	String 	bookName;//姓名
	String  bookNumm;//编号
	String  bookWriter;//作者
	String  bookTrans;//译者
	String  bookDate;//出版日期
	String  bookPublishr;//出版社
	String  bookType;//图书类别
	float  bookPrice;//价格
	int  numb;//图书数量
	String  bookphoto;//图书封面
	int  b_numb;//已出借图书数量
	public int getB_numb() {
		return b_numb;
	}
	public void setB_numb(int b_numb) {
		this.b_numb = b_numb;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookNumm() {
		return bookNumm;
	}
	public void setBookNumm(String bookNumm) {
		this.bookNumm = bookNumm;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getBookTrans() {
		return bookTrans;
	}
	public void setBookTrans(String bookTrans) {
		this.bookTrans = bookTrans;
	}
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	public String getBookPublishr() {
		return bookPublishr;
	}
	public void setBookPublishr(String bookPublishr) {
		this.bookPublishr = bookPublishr;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public float getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}
	public String getBookphoto() {
		return bookphoto;
	}
	public void setBookphoto(String bookphoto) {
		this.bookphoto = bookphoto;
	}
	
}
