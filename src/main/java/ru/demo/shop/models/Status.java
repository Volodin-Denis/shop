package ru.demo.shop.models;

public enum Status {
    IN_CART{public String getName(){return "В корзине";}},
    NOT_VERIFIED{public String getName(){return "Обрабатывается";}},
    IN_TRANSIT{public String getName(){return "В пути";}},
    AT_PICKUP{public String getName(){return "В пункте самовывоза";}},
    DELIVERED{public String getName(){return "Доставлен";}},
    CANCELED{public String getName(){return "Отменен";}}
}
