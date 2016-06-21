package object1;

import java.util.ArrayList;
import java.util.List;

public class ChickenObserver {

	List<Task> tasks = new ArrayList<Task>();

	public void registerTask(Task task) {
		tasks.add(task);
	}

	public void notifyUpdate(Chicken chicken) {
		for (Task task : tasks) {
			task.update(chicken);
		}
	}
}
