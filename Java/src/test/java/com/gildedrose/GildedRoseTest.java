package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    String sulfuras = "Sulfuras";

//    @Test
//    void foo() {
//        Item[] items = new Item[] { new Item("foo", 0, 0) };
//        GildedRose app = new GildedRose(items);
//        app.updateQuality();
//        assertEquals("fixme", app.items[0].name);
//    }

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

}
