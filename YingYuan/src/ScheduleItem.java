
public class ScheduleItem {
private String shijian;
private Movie xvhao;
public String getShijian() {
	return shijian;
}
public void setShijian(String shijian) {
	this.shijian = shijian;
}

public Movie getXvhao() {
	return xvhao;
}
public void setXvhao(Movie xvhao) {
	this.xvhao = xvhao;
}
public ScheduleItem(){
	
}
public ScheduleItem( Movie xvhao,String shijian) {
	super();
	this.shijian = shijian;
	this.xvhao = xvhao;
}

}
