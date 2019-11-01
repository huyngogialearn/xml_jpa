package jpa.query_data.model;

/**
 * The {@link Order} class contains the data for setting directions of data
 */
public class Order {
    /**
     * decreate
     */
    private int[] orderDes;
    /**
     * increate
     */
    private int[] orderInc;

    public Order() {
    }

    public Order(int[] orderDes, int[] orderInc) {
        this.orderDes = orderDes;
        this.orderInc = orderInc;
    }

    public int[] getOrderDes() {
        return orderDes;
    }

    public void setOrderDes(int[] orderDes) {
        this.orderDes = orderDes;
    }

    public int[] getOrderInc() {
        return orderInc;
    }

    public void setOrderInc(int[] orderInc) {
        this.orderInc = orderInc;
    }
}
