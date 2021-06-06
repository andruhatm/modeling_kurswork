package sample.model;

public class Terminal {

	private String name;
	private int strokaTerminalDelay = 0;
	private boolean terminalFree = true;
	private boolean zadanie = false;
	private int zadanieAllCount = 0;
	private int secoundsOfWork = 0;
	private boolean work = false;
	private int countTerm = 3;
	private int counter = 0;
	private int workCounter = 0;
	private int sendTime;
	private int time;
	private int allStrokiCounter = 0;

	public Terminal(String name) {
		this.sendTime = 10 - 5 + (int) (Math.random() * (2 * 5));
		this.name = name;
	}

	public Terminal(int strokaTerminalDelay, boolean terminalFree, boolean serviceTerminal, int counter, int sendTime) {
		sendTime = 10 - 5 + (int) (Math.random() * (2 * 5));
		this.strokaTerminalDelay = strokaTerminalDelay;
		this.terminalFree = terminalFree;
		this.work = serviceTerminal;
		this.counter = counter;
		this.sendTime = sendTime;
	}

	public int getCountTerm() {
		return countTerm;
	}

	public void setCountTerm(int countTerm) {
		this.countTerm = countTerm;
	}

	public int getAllStrokiCounter() {
		return allStrokiCounter;
	}

	public void addToWorkCounter() {
		int count;
		if (counter == 10) {
			this.countTerm = 3;
			count = 38;
		} else {
			count = 5;
		}
//		if (count == 1)
		this.workCounter += 1;
		System.out.println("count of waiting for " + this.getName() + " " + count);
		if (this.getWorkCounter() == count) {
			this.terminalFree = true;
			this.addSecoundsOfWork(count);
			this.workCounter = 0;
			this.setZadanieFalse();
//			this.countTerm = 3;
			//&&
			this.work = false;
			this.setSendTime(generateSendTime());
			if (counter == 10) {
				counter = 0;
			}
			System.out.println("new time for " + this.name + " " + this.sendTime);
		}
	}

	public boolean isZadanie() {
		return zadanie;
	}

	public void setZadanieFalse(){
		this.zadanie = false;
	}

	public void setZadanie(boolean zadanie) {
		this.zadanie = zadanie;
		this.countTerm = 10 - 3 + (int) (Math.random() * (2 * 3));
	}

	public int generateSendTime() {
		return 10 - 5 + (int) (Math.random() * (2 * 5));
	}

	public int getWorkCounter() {
		return workCounter;
	}

	public void addStorkaToCounter() {
		this.allStrokiCounter += 1;
	}

	public boolean isWork() {
		return work;
	}

	public void setWork(boolean work) {
		this.work = work;
	}

	private boolean isSend(int past) {

		return past == sendTime;
	}

	public int getTime() {
		return time;
	}

	public int getZadanieAllCount() {
		return zadanieAllCount;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getStrokaTerminalDelay() {
		return strokaTerminalDelay;
	}

	public void addZadanieCount() {
		this.zadanieAllCount = zadanieAllCount + 1;
	}

	public void setStrokaTerminalDelay(int strokaTerminalDelay) {
		this.strokaTerminalDelay = strokaTerminalDelay;
	}

	public boolean isTerminalFree() {
		return terminalFree;
	}

	public void setTerminalFree(boolean terminalFree) {
		this.terminalFree = terminalFree;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getSendTime() {
		return sendTime;
	}

	public void setSendTime(int sendTime) {
		this.sendTime = sendTime;
	}

	public int getSecoundsOfWork() {
		return secoundsOfWork;
	}

	public String getName() {
		return name;
	}

	public void addSecoundsOfWork(int secoundsOfWork) {
		this.secoundsOfWork += secoundsOfWork;
	}

	public void addCounter() {
		System.out.println("current couunt " + this.counter);
		setCounter(this.counter + 1);
		System.out.println("after " + this.counter);
	}
}
