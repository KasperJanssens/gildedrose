package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    public static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int SULFURAS_QUALITY = 80;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY= 50;

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

}
