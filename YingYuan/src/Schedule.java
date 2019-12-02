
import java.io.Serializable;
import java.util.List;


public class Schedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ScheduleItem> scheduleItem;
	
	public List<ScheduleItem> getScheduleItem() {
		return scheduleItem;
	}

	public void setScheduleItem(List<ScheduleItem> scheduleItem) {
		this.scheduleItem = scheduleItem;
	}

	
}
