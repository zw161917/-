import java.util.ArrayList;





public class Test {
public static void main(String[] args) {
	Movie movie1 = new Movie("01","罗曼假日","ying","导演",MovieType.ganqing);
	Movie movie2 = new Movie("02","战狼","ying","导演",MovieType.dongzuo);
	Movie movie3 = new Movie("03","午夜凶铃","ying","导演",MovieType.kongbu);
	
	ScheduleItem sched1 = new ScheduleItem(movie1,"14:30");
	ScheduleItem sched2 = new ScheduleItem(movie2,"14:30");
	ScheduleItem sched3 = new ScheduleItem(movie3,"14:30");
	ArrayList<ScheduleItem> liar = new ArrayList<ScheduleItem>();
	liar.add(sched1);
	liar.add(sched2);
	liar.add(sched3);
	System.out.println("场次"+"\t电影名"+"\t主演"+"\t导演"+"\t电影类型"+"\t播放时间");
	for (ScheduleItem scheduleItem : liar) {
		System.out.println(scheduleItem.getXvhao().getXuhao()+"\t"+scheduleItem.getXvhao().getMingcheng()+
				"\t"+scheduleItem.getXvhao().getYingcheng()+"\t"+scheduleItem.getXvhao().getDaoyan()+
				"\t"+scheduleItem.getXvhao().getLiexing()+"\t"+scheduleItem.getShijian());
	}
	
	}
}
