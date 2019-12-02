
public class Ticket {
//µÁ”∞∆±
	private  String[] zhuo;
	private Movie xvhao;
	public String[] getZhuo() {
		return zhuo;
	}
	public void setZhuo(String[] zhuo) {
		this.zhuo = zhuo;
	}
	public Movie getXvhao() {
		return xvhao;
	}
	public void setXvhao(Movie xvhao) {
		this.xvhao = xvhao;
	}
	public Ticket(String[] zhuo, Movie xvhao) {
		super();
		this.zhuo = zhuo;
		this.xvhao = xvhao;
	}
	public Ticket() {
		
	}
	
}
