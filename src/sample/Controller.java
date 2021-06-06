package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.model.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Controller {
	private int timeWork = 21600;
	private int var1 = 10;
	private int var2 = 5;
	private int sendTime = 0;
	private int time = 21600;
	private int counter = 10;
	private boolean evmFree = true;
	private boolean service = false;

//	private boolean serviceTerminal1 = false;
//	private boolean serviceTerminal2 = false;
//	private boolean serviceTerminal3 = false;

	private int serviceInput = 0;
	private int zadanieEvmDelay = 0;
	private int serviceEvmInput = 0;

	private boolean terminal = false;
	private boolean superEvmBlock = false;
	private boolean firstTerminalFree = true;
	private boolean secondTerminalFree = true;
	private boolean thirdTerminalFree = true;
//	private boolean evm;

	//	List<>
	private Terminal terminal1;
	private Terminal terminal2;
	private Terminal terminal3;

	private List<Terminal> terminals;
	@FXML
	public Button start;

	@FXML
	public void start(ActionEvent actionEvent) {

//		EVM evm = new EVM();

		// 10 +- 5
//		sendTime = var1 - var2 + (int) (Math.random() * (2 * var2));
		zadanieEvmDelay = countDelayZadanie();

		terminals = new ArrayList<>();

		terminal1 = new Terminal("terminal1");
		terminal2 = new Terminal("terminal2");
		terminal3 = new Terminal("terminal3");

		while (timeWork > 0) {

			int past = time - timeWork;

			if (terminal1.isTerminalFree()) {
				terminal1(past - terminal1.getSecoundsOfWork());
			} else if (terminal1.isWork()) {
				terminal1.addToWorkCounter();
			}

			if (terminal2.isTerminalFree()) {
				terminal2(past - terminal2.getSecoundsOfWork());
			} else if (terminal2.isWork()) {
				terminal2.addToWorkCounter();
			}

			if (terminal3.isTerminalFree()) {
				terminal3(past - terminal3.getSecoundsOfWork());
			} else if (terminal3.isWork()) {
				terminal3.addToWorkCounter();
			}

//			if (past == sendTime || service) {
//				generationAndWork(past);
//			}

			checkOnEvmWork();

//			if (counter == 0) {
//				stopEVM();
//			}

			timeWork--;
		}
		System.out.println(terminal1.getName() + " " + terminal1.getAllStrokiCounter());
		System.out.println(terminal2.getName() + " " + terminal2.getAllStrokiCounter());
		System.out.println(terminal3.getName() + " " + terminal3.getAllStrokiCounter());

		System.out.println(terminal1.getName() + " " + terminal1.getSecoundsOfWork());
		System.out.println(terminal2.getName() + " " + terminal2.getSecoundsOfWork());
		System.out.println(terminal3.getName() + " " + terminal3.getSecoundsOfWork());
	}

	private void generationAndWork(int past) {
		if (evmFree || service) {
			evmFree = false;

			serviceInput++;
			service = true;

			if (serviceInput == 3) {
				evmFree = true;
				serviceInput = 0;
				service = false;
				past = 0;
				terminal = true;
			}

			timeWork -= past;
//			sendTime = var1 - var2 + (int) (Math.random() * (2 * var2));
		}
	}

	private void checkOnEvmWork() {
		if (!terminals.isEmpty()) {
			Terminal terminalToWorkWith = terminals.get(0);
			System.out.println("time: " + (time - timeWork) + " place: evm obrabativaet " + terminalToWorkWith.getName());

			evmFree = false;
			serviceInput++;

			if (serviceInput == 3) {
				terminalToWorkWith.addSecoundsOfWork(3);
				terminalToWorkWith.setWork(true);
				evmFree = true;
				serviceInput = 0;

				terminals.remove(terminalToWorkWith);
				System.out.println("time: " + (time - timeWork) + " place: evm ended work on " + terminalToWorkWith.getName());
			}
		}

	}

	private void stopEVM() {

		superEvmBlock = true;
		evmFree = false;

		serviceEvmInput++;

		if (serviceEvmInput == zadanieEvmDelay) {
			serviceEvmInput = 0;
			superEvmBlock = false;
			evmFree = true;
		}

		time -= 1;

		zadanieEvmDelay = countDelayZadanie();
	}

	public int countDelayZadanie() {
		return 10 - 3 + (int) (Math.random() * (2 * 3));
	}

	private void terminal1(int past) {
		if (past == terminal1.getSendTime()) {
			terminal1.addStorkaToCounter();
			terminal1.addSecoundsOfWork(past);
			terminal1.setTerminalFree(false);
			terminal1.setSendTime(21600);
			terminal1.addCounter();
			System.out.println("time: " + (time - timeWork) + " place: terminal 1 nabral stroku #" + terminal1.getCounter());
			terminals.add(terminal1);
		}
	}

	private void terminal2(int past) {
		if (past == terminal2.getSendTime()) {
			terminal2.addStorkaToCounter();
			terminal2.addSecoundsOfWork(past);
			terminal2.setTerminalFree(false);
			terminal2.setSendTime(21600);
			terminal2.addCounter();
			System.out.println("time: " + (time - timeWork) + " place: terminal 2 nabral stroku #" + terminal2.getCounter());
			terminals.add(terminal2);
		}
	}

	private void terminal3(int past) {
		if (past == terminal3.getSendTime()) {
			terminal3.addStorkaToCounter();
			terminal3.addSecoundsOfWork(past);
			terminal3.setTerminalFree(false);
			terminal3.setSendTime(21600);
			terminal3.addCounter();
			System.out.println("time: " + (time - timeWork) + " place: terminal 3 nabral stroku #" + terminal3.getCounter());
			terminals.add(terminal3);
		}
	}
}
