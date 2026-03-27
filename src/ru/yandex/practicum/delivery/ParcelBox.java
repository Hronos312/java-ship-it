package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {

    private int maxWeight;
    private  int currentWeight = 0;
    private ArrayList<T> box = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWeight) {
            System.out.println("Добавление в коробку невозможно: случится перевес");
        } else {
            box.add(parcel);
            currentWeight += parcel.getWeight();
            System.out.println("Посылка добавлена");
        }
    }

    public void getAllParcels() {
        System.out.println("Содержимое коробки: ");
        for (T parcel : box) {
            System.out.println(parcel);
        }
    }

    public ArrayList<T> getBox() {
        return box;
    }
}
