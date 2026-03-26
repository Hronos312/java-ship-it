package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(100);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(100);
    private static List<Trackable> trackableParcels = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.next());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    getReports();
                    break;
                case 5:
                    getAllParcelsFromBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Посмотреть местонахождение посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        System.out.println("Ввыберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        System.out.println("Введите цифру: ");
        int type = scanner.nextInt();
        System.out.println("Введите описание посылки: ");
        String description = scanner.next();
        System.out.println("Введите вес: ");
        int weight = scanner.nextInt();
        System.out.println("Введите адрес доставки:");
        String deliveryAddress = scanner.next();
        System.out.println("Введите день доставки:");
        int sendDay = scanner.nextInt();
        switch (type) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                standardParcelBox.addParcel(standardParcel);
                allParcels.add(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                fragileParcelBox.addParcel(fragileParcel);
                trackableParcels.add(fragileParcel);
                allParcels.add(fragileParcel);
                break;
            case 3:
                System.out.println("Введите срок, за который посылка испортится:");
                int timeToLive = scanner.nextInt();
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay,
                        timeToLive);
                perishableParcelBox.addParcel(perishableParcel);
                allParcels.add(perishableParcel);
                break;
            default:
                System.out.println("Вы ввели неправильные данные");
                break;
        }
    }

    private static  void getReports() {
        System.out.println("Введите местонахождение для посылок:");
        String newLocation = scanner.next();
        for (Trackable parcel : trackableParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void getAllParcelsFromBox() {
        System.out.println("Введите тип посылок:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Хрупкая");
        System.out.println("3 - Скоропортящаяся");
        System.out.println("Введите цифру: ");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                standardParcelBox.getAllParcels();
                break;
            case 2:
                fragileParcelBox.getAllParcels();
                break;
            case 3:
                perishableParcelBox.getAllParcels();
                break;
            default:
                System.out.println("Такого типа посылок нет");
                break;
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        // Пройти по allParcels, вызвать packageItem() и deliver()
    }

    private static void calculateCosts() {
        int costOfAll = 0;
        for (Parcel parcel : allParcels) {
            costOfAll += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + costOfAll);
        // Посчитать общую стоимость всех доставок и вывести на экран
    }

}

