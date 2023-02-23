package ru.demo.shop.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.io.Serializable;


public class OrderedItemsId implements Serializable {

    private int orderId;
    private int itemId;

    public OrderedItemsId() {
    }

    public OrderedItemsId(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedItemsId that = (OrderedItemsId) o;

        if (orderId != that.orderId) return false;
        return itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + itemId;
        return result;
    }
}
