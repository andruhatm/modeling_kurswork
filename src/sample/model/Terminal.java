package sample.model;

public class Terminal {

	private String name;
	private int strokaTerminalDelay = 0;
	private boolean terminalFree = true;
	private int secoundsOfWork = 0;
	private boolean work = false;
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

	public int getAllStrokiCounter() {
		return allStrokiCounter;
	}

	public void addToWorkCounter() {
		this.workCounter += 1;
		if (this.getWorkCounter() == 4) {
			this.terminalFree = true;
			this.addSecoundsOfWork(5);
			this.workCounter = 0;
			this.work = false;
			this.setSendTime(generateSendTime());
			System.out.println("new time for " + this.name + " " + this.sendTime);
		}
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

	public void setTime(int time) {
		this.time = time;
	}

	public int getStrokaTerminalDelay() {
		return strokaTerminalDelay;
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
		this.counter += 1;
	}
}
