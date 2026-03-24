package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

public class DeliveryCostTest {

    private StandardParcel standardParcel;
    private PerishableParcel perishableParcel;
    private FragileParcel fragileParcel;
    private ParcelBox<StandardParcel> parcelBox;
    private StandardParcel overweightStandardParcel;

    @BeforeEach
    public void beforeEach() {
        standardParcel = new StandardParcel("desc", 20, "address",
                15);
        perishableParcel = new PerishableParcel("desc", 20, "address",
                10, 10);
        fragileParcel = new FragileParcel("desc", 20, "address",
                15);
        parcelBox = new ParcelBox<>(50);
        overweightStandardParcel = new StandardParcel("desc", 60,
                "address", 15);
    }

    @Test
    public void StandardParcelPriceTest() {
        Assertions.assertEquals(40, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void PerishableParcelPriceTest() {
        Assertions.assertEquals(60, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void FragileParcelPriceTest() {
        Assertions.assertEquals(80, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void NotExpiredTest() {
        Assertions.assertTrue(perishableParcel.isExpired(30));
    }

    @Test
    public void ExpiredTest() {
        Assertions.assertFalse(perishableParcel.isExpired(15));
    }

    @Test
    public void OverweightParcelBoxTest() {
        parcelBox.addParcel(overweightStandardParcel);
        Assertions.assertTrue(parcelBox.getBox().isEmpty());
    }

    @Test
    public void UnderweightParcelBoxTest() {
        parcelBox.addParcel(standardParcel);
        Assertions.assertFalse(parcelBox.getBox().isEmpty());
    }



}
