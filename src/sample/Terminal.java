package sample;

public class Terminal {

	private final String name;
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
	private int defaultCountTerm;
	private int mean;
	private int mean1;
	private int std;
	private int std1;
	private int analyze1;
	private int analyze2;
	private int termOtvet;
	private int zadanieCount;

	public Terminal(String name, int countTerm, int mean, int std, int mean1, int std1, int analyze1, int analyze2, int termOtvet, int zadanieCount) {
		this.countTerm = countTerm;
		this.defaultCountTerm = countTerm;
		this.mean1 = mean1;
		this.mean = mean;
		this.termOtvet = termOtvet;
		this.zadanieCount = zadanieCount;
		this.std = std;
		this.std1 = std1;
		this.analyze1 = analyze1;
		this.analyze2 = analyze2;
		this.sendTime = this.mean - this.std + (int) (Math.random() * (2 * this.std));
		this.name = name;
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
		if (counter == zadanieCount) {
			this.countTerm = this.defaultCountTerm;
			count = analyze1 + analyze2;
		} else {
			count = this.termOtvet;
		}
		this.workCounter += 1;
		System.out.println("count of waiting for " + this.getName() + " " + count);
		if (this.getWorkCounter() == count) {
			this.terminalFree = true;
			this.addSecoundsOfWork(count);
			this.workCounter = 0;
			this.setZadanieFalse();
			this.work = false;
			this.setSendTime(generateSendTime());
			if (counter == zadanieCount) {
				counter = 0;
			}
			System.out.println("new time for " + this.name + " " + this.sendTime);
		}
	}

	public boolean isZadanie() {
		return zadanie;
	}

	public void setZadanieFalse() {
		this.zadanie = false;
	}

	public void setZadanie(boolean zadanie) {
		this.zadanie = zadanie;
		this.countTerm = mean1 - std1 + (int) (Math.random() * (2 * std1));
	}

	public int generateSendTime() {
		return this.mean - this.std + (int) (Math.random() * (2 * this.std));
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
