package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    public static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int SULFURAS_QUALITY = 80;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY= 50;
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    void testQualityDegradation_normalElement () {
        Item item = new Item("foo", 2, 2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(1);
        assertThat(item.sellIn).isEqualTo(1);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(0);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-1);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-2);
    }

    @Test
    void testQualityDegradation_normalElementQualityDegradesDoubleAfterSellingDate () {
        Item item = new Item("foo", 0, 4);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(2);
        assertThat(item.sellIn).isEqualTo(-1);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-3);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-4);
    }

    @Test
    void testSulfuras_NeverDegradesInQualityOrSellinDate () {
        Item item = new Item(HAND_OF_RAGNAROS, 2, SULFURAS_QUALITY);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(SULFURAS_QUALITY);
        assertThat(item.sellIn).isEqualTo(2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(SULFURAS_QUALITY);
        assertThat(item.sellIn).isEqualTo(2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(SULFURAS_QUALITY);
        assertThat(item.sellIn).isEqualTo(2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(SULFURAS_QUALITY);
        assertThat(item.sellIn).isEqualTo(2);
    }

    @Test
    void testAgedBrie_IncreasesInQualityAsItgetsOlder () {
        Item item = new Item(AGED_BRIE, 6, 2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(3);
        assertThat(item.sellIn).isEqualTo(5);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(4);
        assertThat(item.sellIn).isEqualTo(4);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(5);
        assertThat(item.sellIn).isEqualTo(3);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(6);
        assertThat(item.sellIn).isEqualTo(2);
    }

    @Test
    void testAgedBrie_NeverGoesBeyondMaxQuality () {
        Item item = new Item(AGED_BRIE, 6, MAX_QUALITY - 1);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(5);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(4);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(3);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(2);
    }

    @Test
    void backstagePasses_moreThan10DaysLeftNormalIncrease () {
        Item item = new Item(BACKSTAGE_PASS, 13, 20);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(21);
        assertThat(item.sellIn).isEqualTo(12);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(22);
        assertThat(item.sellIn).isEqualTo(11);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(23);
        assertThat(item.sellIn).isEqualTo(10);
    }

    @Test
    void backstagePasses_lessThan10DaysButMoreThan5Left () {
        Item item = new Item(BACKSTAGE_PASS, 10, 20);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(22);
        assertThat(item.sellIn).isEqualTo(9);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(24);
        assertThat(item.sellIn).isEqualTo(8);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(26);
        assertThat(item.sellIn).isEqualTo(7);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(28);
        assertThat(item.sellIn).isEqualTo(6);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(30);
        assertThat(item.sellIn).isEqualTo(5);
    }

    @Test
    void backstagePasses_lessThan5DaysButMoreThan0Left () {
        Item item = new Item(BACKSTAGE_PASS, 5, 20);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(23);
        assertThat(item.sellIn).isEqualTo(4);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(26);
        assertThat(item.sellIn).isEqualTo(3);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(29);
        assertThat(item.sellIn).isEqualTo(2);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(32);
        assertThat(item.sellIn).isEqualTo(1);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(35);
        assertThat(item.sellIn).isEqualTo(0);
    }


    @Test
    void backstagePasses_noMoreThanMaxQuality_lowestSellin () {
        Item item = new Item(BACKSTAGE_PASS, 5, MAX_QUALITY);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(4);

    }
    @Test
    void backstagePasses_noMoreThanMaxQuality_mediumSellin () {
        Item item = new Item(BACKSTAGE_PASS, 10, MAX_QUALITY);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(9);

    }

    @Test
    void backstagePasses_noMoreThanMaxQuality_highestSellin () {
        Item item = new Item(BACKSTAGE_PASS, 15, MAX_QUALITY);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(MAX_QUALITY);
        assertThat(item.sellIn).isEqualTo(14);

    }

    @Test
    void backstagePasses_noValueAfterSellinDate () {
        Item item = new Item(BACKSTAGE_PASS, 1, 42);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(45);
        assertThat(item.sellIn).isEqualTo(0);

        GildedRose.updateSingleItem(item);
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-1);

    }

}
