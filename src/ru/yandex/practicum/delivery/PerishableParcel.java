package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {

    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getBaseCost() {
        return 3;
    }

    public boolean isExpired(int currentDay) {
        return this.getSendDay() + timeToLive < currentDay;
    }

    @Override
    public String toString() {
        return "Скоропортящаяся посылка: " + this.getDescription();
    }
}
