package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.export.Export;
import sample.model.Report;
import sample.model.Terminal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller extends Export {

	@FXML
	public TextField termOtvet1;
	@FXML
	public TextField evmReshenie3;
	@FXML
	public TextField termOtvet2;
	@FXML
	public TextField termOtvet3;
	@FXML
	public TextField evmReshenie1;
	@FXML
	public TextField evmReshenie2;
	@FXML
	public TextField termOtvet4;
	@FXML
	public TextField time1;
	@FXML
	public TextField zadanieCount;
	@FXML
	public TextField analize;
	@FXML
	public TableView table;
	@FXML
	public MenuItem word;
	@FXML
	public MenuItem excel;

	private ObservableList<Report> reports = FXCollections.observableArrayList();

	@FXML
	public TableColumn<Report, Integer> number;
	@FXML
	public TableColumn<Report, String> evmUsage;
	@FXML
	public TableColumn<Report, String> operatorLag;
	@FXML
	public TableColumn<Report, Integer> countT1;
	@FXML
	public TableColumn<Report, Integer> countT2;
	@FXML
	public TableColumn<Report, Integer> countT3;
	@FXML
	public TableColumn<Report, Integer> zadanie;

	private int countReport = 0;
	private int timeWork;
	private int time;

	String prostoy;
	String evmLoad;
	private int zadanieCount1;

	private int noNullEntries = 0;
	private int allEntries = 0;
	private int evmSecondsWork = 0;

	private int serviceInput = 0;
	private int countZadanie;

	private Terminal terminal1;
	private Terminal terminal2;
	private Terminal terminal3;

	private List<Terminal> terminals;
	@FXML
	public Button start;

	@FXML
	public void start(ActionEvent actionEvent) {

		countZadanie = Integer.parseInt(zadanieCount.getText());
		time = Integer.parseInt(time1.getText());
		timeWork = time;

		terminals = new ArrayList<>();

		terminal1 = new Terminal(
						"terminal1",
						Integer.parseInt(evmReshenie3.getText()),
						Integer.parseInt(termOtvet1.getText()),
						Integer.parseInt(termOtvet2.getText()),
						Integer.parseInt(evmReshenie1.getText()),
						Integer.parseInt(evmReshenie2.getText()),
						Integer.parseInt(termOtvet4.getText()),
						Integer.parseInt(analize.getText()),
						Integer.parseInt(termOtvet3.getText()),
						Integer.parseInt(zadanieCount.getText())
		);
		terminal2 = new Terminal(
						"terminal2",
						Integer.parseInt(evmReshenie3.getText()),
						Integer.parseInt(termOtvet1.getText()),
						Integer.parseInt(termOtvet2.getText()),
						Integer.parseInt(evmReshenie1.getText()),
						Integer.parseInt(evmReshenie2.getText()),
						Integer.parseInt(termOtvet4.getText()),
						Integer.parseInt(analize.getText()),
						Integer.parseInt(termOtvet3.getText()),
						Integer.parseInt(zadanieCount.getText())
		);
		terminal3 = new Terminal(
						"terminal3",
						Integer.parseInt(evmReshenie3.getText()),
						Integer.parseInt(termOtvet1.getText()),
						Integer.parseInt(termOtvet2.getText()),
						Integer.parseInt(evmReshenie1.getText()),
						Integer.parseInt(evmReshenie2.getText()),
						Integer.parseInt(termOtvet4.getText()),
						Integer.parseInt(analize.getText()),
						Integer.parseInt(termOtvet3.getText()),
						Integer.parseInt(zadanieCount.getText())
		);

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
			checkOnEvmWork();
			timeWork--;
		}
		System.out.println(terminal1.getName() + " all stroki " + terminal1.getAllStrokiCounter());
		System.out.println(terminal2.getName() + " all stroki " + terminal2.getAllStrokiCounter());
		System.out.println(terminal3.getName() + " all stroki " + terminal3.getAllStrokiCounter());

		System.out.println(terminal1.getName() + " " + terminal1.getSecoundsOfWork());
		System.out.println(terminal2.getName() + " " + terminal2.getSecoundsOfWork());
		System.out.println(terminal3.getName() + " " + terminal3.getSecoundsOfWork());

		System.out.println(terminal1.getName() + " " + terminal1.getZadanieAllCount());
		System.out.println(terminal2.getName() + " " + terminal2.getZadanieAllCount());
		System.out.println(terminal3.getName() + " " + terminal3.getZadanieAllCount());

		this.zadanieCount1 = terminal1.getZadanieAllCount() + terminal2.getZadanieAllCount() + terminal3.getZadanieAllCount();

		System.out.println(evmSecondsWork);
		DecimalFormat df = new DecimalFormat("#.###");
		this.evmLoad = df.format(((double) (this.evmSecondsWork) / this.time));
		this.prostoy = df.format(((double) this.noNullEntries / this.allEntries));
		System.out.println("evm work load " + evmLoad);
		System.out.println("prostoy proektirovshika " + prostoy);

		countReport++;
		report();
	}

	private void checkOnEvmWork() {
		if (!terminals.isEmpty()) {
			if (terminals.size() > 1) {
				this.noNullEntries += 1;
			}
			this.allEntries += 1;

			Terminal terminalToWorkWith = terminals.stream().findFirst().get();

			System.out.println("time: " + (time - timeWork) + " place: evm obrabativaet " + terminalToWorkWith.getName() + " with " + terminalToWorkWith.getCountTerm());

			int workTime = terminalToWorkWith.getCountTerm();
			serviceInput++;

			if (serviceInput == terminalToWorkWith.getCountTerm()) {
				terminalToWorkWith.addSecoundsOfWork(terminalToWorkWith.getCountTerm());
				terminalToWorkWith.setWork(true);
				serviceInput = 0;

				terminals.remove(0);
				if (!terminals.isEmpty()) {
					for (Terminal t : terminals) {
						t.addSecoundsOfWork(workTime);
					}
				}
				System.out.println("time: " + (time - timeWork) + " place: evm ended work on " + terminalToWorkWith.getName());
			}
		}
	}

	private void terminal1(int past) {
		System.out.println("past " + past + " terminal1 sendTime " + terminal1.getSendTime());
		if (past == terminal1.getSendTime()) {
			//all stroki by terminal counter
			terminal1.addStorkaToCounter();
			//seconds of work terminal all
			terminal1.addSecoundsOfWork(past);
			//terminal free
			terminal1.setTerminalFree(false);
			terminal1.setSendTime(this.time);
			//counter 0 to 10
			terminal1.addCounter();
			if (terminal1.getCounter() == countZadanie) {
				System.out.println("COUNTER IS 10 HERE terminal1");
				terminal1.addZadanieCount();
				terminal1.setZadanie(true);
			}
			this.evmSecondsWork += terminal1.getCountTerm();
			System.out.println("time: " + (time - timeWork) + " place: terminal 1 nabral stroku #" + terminal1.getCounter());
			terminals.add(terminal1);
		}
	}

	private void terminal2(int past) {
		System.out.println("past " + past + " terminal2 sendTime " + terminal2.getSendTime());
		if (past == terminal2.getSendTime()) {
			terminal2.addStorkaToCounter();
			terminal2.addSecoundsOfWork(past);
			terminal2.setTerminalFree(false);
			terminal2.setSendTime(this.time);
			terminal2.addCounter();
			if (terminal2.getCounter() == countZadanie) {
				System.out.println("COUNTER IS 10 HERE terminal2");
				terminal2.addZadanieCount();
				terminal2.setZadanie(true);
			}
			this.evmSecondsWork += terminal2.getCountTerm();
			System.out.println("time: " + (time - timeWork) + " place: terminal 2 nabral stroku #" + terminal2.getCounter());
			terminals.add(terminal2);
		}
	}

	private void terminal3(int past) {
		System.out.println("past " + past + " terminal3 sendTime " + terminal3.getSendTime());
		if (past == terminal3.getSendTime()) {
			terminal3.addStorkaToCounter();
			terminal3.addSecoundsOfWork(past);
			terminal3.setTerminalFree(false);
			terminal3.setSendTime(this.time);
			terminal3.addCounter();
			if (terminal3.getCounter() == countZadanie) {
				System.out.println("COUNTER IS 10 HERE terminal3");
				terminal3.addZadanieCount();
				terminal3.setZadanie(true);
			}
			this.evmSecondsWork += terminal3.getCountTerm();
			System.out.println("time: " + (time - timeWork) + " place: terminal 3 nabral stroku #" + terminal3.getCounter());
			terminals.add(terminal3);
		}
	}

	public void clear() {
		serviceInput = 0;
		terminals.clear();
		countZadanie = Integer.parseInt(zadanieCount.getText());
		time = Integer.parseInt(time1.getText());
		timeWork = time;
		evmSecondsWork = 0;
		noNullEntries = 0;
		allEntries = 0;
	}

	private void report() {
		reports.add(new Report(countReport, this.evmLoad, this.prostoy, terminal1.getAllStrokiCounter(), terminal2.getAllStrokiCounter(), terminal3.getAllStrokiCounter(), this.zadanieCount1));

		number.setCellValueFactory(new PropertyValueFactory<Report, Integer>("number"));
		evmUsage.setCellValueFactory(new PropertyValueFactory<Report, String>("evmUsage"));
		operatorLag.setCellValueFactory(new PropertyValueFactory<Report, String>("operatorLag"));
		countT1.setCellValueFactory(new PropertyValueFactory<Report, Integer>("countT1"));
		countT2.setCellValueFactory(new PropertyValueFactory<Report, Integer>("countT2"));
		countT3.setCellValueFactory(new PropertyValueFactory<Report, Integer>("countT3"));
		zadanie.setCellValueFactory(new PropertyValueFactory<Report, Integer>("zadanie"));

		table.setItems(reports);
		clear();
	}

	public void exportWord(ActionEvent actionEvent) throws IOException {
		reportWord(reports);
	}

	public void exportExcel(ActionEvent actionEvent) {
		report(reports);
	}
}
