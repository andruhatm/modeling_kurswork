package sample.model;

public class Report {
    private int number;
    private String evmUsage;
    private String operatorLag;
    private int countT1;
    private int countT2;
    private int countT3;
    private int zadanie;

    public Report(int number, String evmUsage, String operatorLag, int countT1, int countT2, int countT3, int zadanieCount) {
        this.number = number;
        this.evmUsage = evmUsage;
        this.operatorLag = operatorLag;
        this.countT1 = countT1;
        this.countT2 = countT2;
        this.countT3 = countT3;
        this.zadanie = zadanieCount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEvmUsage() {
        return evmUsage;
    }

    public void setEvmUsage(String evmUsage) {
        this.evmUsage = evmUsage;
    }

    public String getOperatorLag() {
        return operatorLag;
    }

    public void setOperatorLag(String operatorLag) {
        this.operatorLag = operatorLag;
    }

    public int getCountT1() {
        return countT1;
    }

    public void setCountT1(int countT1) {
        this.countT1 = countT1;
    }

    public int getCountT2() {
        return countT2;
    }

    public void setCountT2(int countT2) {
        this.countT2 = countT2;
    }

    public int getCountT3() {
        return countT3;
    }

    public void setCountT3(int countT3) {
        this.countT3 = countT3;
    }

    public int getZadanie() {
        return zadanie;
    }

    public void setZadanie(int zadanie) {
        this.zadanie = zadanie;
    }

    @Override
    public String toString() {
        return "Report{" +
                "number=" + number +
                ", evmUsage=" + evmUsage +
                ", operatorLag=" + operatorLag +
                ", countT1=" + countT1 +
                ", countT2=" + countT2 +
                ", countT3=" + countT3 +
                ", zadanieCount=" + zadanie +
                '}';
    }
}
