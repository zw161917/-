//存放电影属性
public class Movie {
  private String xuhao;
  private String mingcheng;
  private String yingcheng;
  private String daoyan;
  private MovieType liexing;
 
public String getXuhao() {
	return xuhao;
}
public void setXuhao(String xuhao) {
	this.xuhao = xuhao;
}
public String getMingcheng() {
	return mingcheng;
}
public void setMingcheng(String mingcheng) {
	this.mingcheng = mingcheng;
}
public String getYingcheng() {
	return yingcheng;
}
public void setYingcheng(String yingcheng) {
	this.yingcheng = yingcheng;
}
public String getDaoyan() {
	return daoyan;
}
public void setDaoyan(String daoyan) {
	this.daoyan = daoyan;
}
public MovieType getLiexing() {
	return liexing;
}
public void setLiexing(MovieType liexing) {
	this.liexing = liexing;
}

public Movie(){
	
}
public Movie(String xuhao, String mingcheng, String yingcheng, String daoyan,
		MovieType liexing) {
	super();
	this.xuhao = xuhao;
	this.mingcheng = mingcheng;
	this.yingcheng = yingcheng;
	this.daoyan = daoyan;
	this.liexing = liexing;
	
}
public String getSi(){
	
	return getXuhao()+getMingcheng()+getYingcheng()+getDaoyan()+getLiexing();
	
}
  
}
