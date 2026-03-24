package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getBaseCost() {
        return 4;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка <<" + this.getDescription() + ">> изменила местоположение на " + newLocation);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + this.getDescription() + ">> обёрнута в защитную плёнку");
        System.out.println("Посылка <<" + this.getDescription() + ">> упакована");
    }

    @Override
    public String toString() {
        return "Хрупкая посылка: " + this.getDescription();
    }
}
