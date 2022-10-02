package lk.ijse.pos.view.tdm;

public class IncomeTM implements Comparable<IncomeTM>{
    private String custId;
    private double total;

    public IncomeTM() {
    }

    public IncomeTM(String custId, double total) {
        this.custId = custId;
        this.total = total;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "IncomeTM{" +
                "custId='" + custId + '\'' +
                ", total=" + total +
                '}';
    }

    @Override
    public int compareTo(IncomeTM o) {
        return custId.compareTo(o.getCustId());
    }
}
