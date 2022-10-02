package lk.ijse.pos.dto;

import java.io.Serializable;

public class ItemCountDTO implements Serializable {
    private String itemCode;
    private int qty;

    public ItemCountDTO() {
    }

    public ItemCountDTO(String itemCode, int qty) {
        this.itemCode = itemCode;
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                '}';
    }
}
