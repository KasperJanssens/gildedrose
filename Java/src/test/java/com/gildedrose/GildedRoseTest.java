package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.GildedRose.*;
import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {


    @Test
    void testQualityDegradation_normalElement() {
        Item item = new Item("foo", 2, 2);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(1);
        assertThat(item1Updated.sellIn).isEqualTo(1);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(0);
        assertThat(item2Updated.sellIn).isEqualTo(0);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(0);
        assertThat(item3Updated.sellIn).isEqualTo(-1);

        Item item4Updated = GildedRose.updateSingleItem(item3Updated);
        assertThat(item4Updated.quality).isEqualTo(0);
        assertThat(item4Updated.sellIn).isEqualTo(-2);
    }


    @Test
    public void conjuredItem_degradeDoubleAsFastAsNormal () {
        Item item = new Item(CONJURED, 2, 8);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(6);
        assertThat(item1Updated.sellIn).isEqualTo(1);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(4);
        assertThat(item2Updated.sellIn).isEqualTo(0);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(0);
        assertThat(item3Updated.sellIn).isEqualTo(-1);
    }


    @Test
    void testQualityDegradation_normalElementQualityDegradesDoubleAfterSellingDate() {
        Item item = new Item("foo", 0, 4);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(2);
        assertThat(item1Updated.sellIn).isEqualTo(-1);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(0);
        assertThat(item2Updated.sellIn).isEqualTo(-2);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(0);
        assertThat(item3Updated.sellIn).isEqualTo(-3);

        Item item4Updated = GildedRose.updateSingleItem(item3Updated);
        assertThat(item4Updated.quality).isEqualTo(0);
        assertThat(item4Updated.sellIn).isEqualTo(-4);
    }

    @Test
    void testSulfuras_NeverDegradesInQualityOrSellinDate() {
        Item item = new Item(HAND_OF_RAGNAROS, 2, SULFURAS_QUALITY);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated .quality).isEqualTo(SULFURAS_QUALITY);
        assertThat(item1Updated .sellIn).isEqualTo(2);

    }

    @Test
    void testAgedBrie_IncreasesInQualityAsItgetsOlder() {
        Item item = new Item(AGED_BRIE, 6, 2);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(3);
        assertThat(item1Updated.sellIn).isEqualTo(5);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(4);
        assertThat(item2Updated.sellIn).isEqualTo(4);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(5);
        assertThat(item3Updated.sellIn).isEqualTo(3);

        Item item4Updated = GildedRose.updateSingleItem(item3Updated);
        assertThat(item4Updated.quality).isEqualTo(6);
        assertThat(item4Updated.sellIn).isEqualTo(2);
    }

    @Test
    void testAgedBrie_NeverGoesBeyondMaxQuality() {
        Item item = new Item(AGED_BRIE, 6, MAX_QUALITY - 1);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item1Updated.sellIn).isEqualTo(5);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item2Updated.sellIn).isEqualTo(4);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item3Updated.sellIn).isEqualTo(3);

        Item item4Updated = GildedRose.updateSingleItem(item3Updated);
        assertThat(item4Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item4Updated.sellIn).isEqualTo(2);
    }

    @Test
    void backstagePasses_moreThan10DaysLeftNormalIncrease() {
        Item item = new Item(BACKSTAGE_PASS, 13, 20);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(21);
        assertThat(item1Updated.sellIn).isEqualTo(12);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(22);
        assertThat(item2Updated.sellIn).isEqualTo(11);

        Item item3Updated =GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(23);
        assertThat(item3Updated.sellIn).isEqualTo(10);
    }

    @Test
    void backstagePasses_lessThan10DaysButMoreThan5Left() {
        Item item = new Item(BACKSTAGE_PASS, 10, 20);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(22);
        assertThat(item1Updated.sellIn).isEqualTo(9);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(24);
        assertThat(item2Updated.sellIn).isEqualTo(8);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(26);
        assertThat(item3Updated.sellIn).isEqualTo(7);

        Item item4Updated = GildedRose.updateSingleItem(item3Updated );
        assertThat(item4Updated.quality).isEqualTo(28);
        assertThat(item4Updated.sellIn).isEqualTo(6);

        Item item5Updated = GildedRose.updateSingleItem(item4Updated);
        assertThat(item5Updated.quality).isEqualTo(30);
        assertThat(item5Updated.sellIn).isEqualTo(5);
    }

    @Test
    void backstagePasses_lessThan5DaysButMoreThan0Left() {
        Item item = new Item(BACKSTAGE_PASS, 5, 20);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(23);
        assertThat(item1Updated.sellIn).isEqualTo(4);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(26);
        assertThat(item2Updated.sellIn).isEqualTo(3);

        Item item3Updated = GildedRose.updateSingleItem(item2Updated);
        assertThat(item3Updated.quality).isEqualTo(29);
        assertThat(item3Updated.sellIn).isEqualTo(2);

        Item item4Updated = GildedRose.updateSingleItem(item3Updated);
        assertThat(item4Updated.quality).isEqualTo(32);
        assertThat(item4Updated.sellIn).isEqualTo(1);

        Item item5Updated = GildedRose.updateSingleItem(item4Updated);
        assertThat(item5Updated.quality).isEqualTo(35);
        assertThat(item5Updated.sellIn).isEqualTo(0);
    }


    @Test
    void backstagePasses_noMoreThanMaxQuality_lowestSellin() {
        Item item = new Item(BACKSTAGE_PASS, 5, MAX_QUALITY);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item1Updated.sellIn).isEqualTo(4);

    }

    @Test
    void backstagePasses_noMoreThanMaxQuality_mediumSellin() {
        Item item = new Item(BACKSTAGE_PASS, 10, MAX_QUALITY);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item1Updated.sellIn).isEqualTo(9);

    }

    @Test
    void backstagePasses_noMoreThanMaxQuality_highestSellin() {
        Item item = new Item(BACKSTAGE_PASS, 15, MAX_QUALITY);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(MAX_QUALITY);
        assertThat(item1Updated.sellIn).isEqualTo(14);

    }

    @Test
    void backstagePasses_noValueAfterSellinDate() {
        Item item = new Item(BACKSTAGE_PASS, 1, 42);

        Item item1Updated = GildedRose.updateSingleItem(item);
        assertThat(item1Updated.quality).isEqualTo(45);
        assertThat(item1Updated.sellIn).isEqualTo(0);

        Item item2Updated = GildedRose.updateSingleItem(item1Updated);
        assertThat(item2Updated.quality).isEqualTo(0);
        assertThat(item2Updated.sellIn).isEqualTo(-1);

    }


}
