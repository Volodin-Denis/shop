package ru.demo.shop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ordered_items")
@IdClass(OrderedItemsId.class)
public class OrderedItem {

    @Id
    @Column(name = "order_id")
    private int orderId;
    @Id
    @Column(name = "item_id")
    private int itemId;

    @ManyToOne
    @JoinColumn(name = "item_id",referencedColumnName = "id", insertable=false, updatable=false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id", insertable=false, updatable=false)
    private Order order;

    @Column(name = "item_count")
    private int itemQuantity;

    public OrderedItem() {
    }

    public OrderedItem(Order order, Item item , int itemQuantity) {
        this.orderId = order.getId();
        this.order = order;
        this.itemId = item.getId();
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Item getItem(){
        return item;
    }
}
