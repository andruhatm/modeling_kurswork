package sample.model;

import java.util.List;

public class EVM {

	private int delay1 = 3;
	private int delay2 = countDelayZadanie();
	private boolean free;
//	private List<Integer>

	public EVM() {
		free = true;
	}

	public int countDelayZadanie(){
		return 10 - 3 + (int) (Math.random() * (2 * 3));
	}

	public int getDelay1() {
		return delay1;
	}

	public int getDelay2() {
		return delay2;
	}
}
