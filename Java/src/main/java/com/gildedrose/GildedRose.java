package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int SULFURAS_QUALITY = 80;
    public static final String AGED_BRIE = "Aged Brie";
    public static final String CONJURED = "Conjured";
    public static final int MAX_QUALITY = 50;
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        items = Arrays.stream(items).map(GildedRose::updateSingleItem).toArray(Item[]::new);
    }

    public static boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    public static boolean isSulfuras(Item item) {
        return item.name.equals(HAND_OF_RAGNAROS);
    }
    public static boolean isConjuredItem(Item item) {
        return item.name.equals(CONJURED);
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals(BACKSTAGE_PASS);
    }


    public static Item updateSingleItem(Item item) {
        int newSellIn = item.sellIn - 1;
        if (isAgedBrie(item)) {
            int newQuality = calculateNewQualityBrie(newSellIn, item.quality);
            return new Item(item.name, newSellIn, newQuality);
        }

        if (isBackstagePass(item)) {
            int newQuality = calculateNewQualityBackstagePass(newSellIn, item.quality);
            return new Item(item.name, newSellIn, newQuality);
        }
        if(isConjuredItem(item)){
            int newQuality = calculateNewQualityConjuredItem(newSellIn, item.quality);
            return new Item(item.name, newSellIn, newQuality);
        }
        if(isSulfuras(item)) {
            return item;
        }

        int newQuality = calculateNewQualityNormalItem(newSellIn, item.quality);
        return new Item(item.name, newSellIn, newQuality);

    }

    private static int calculateNewQualityBackstagePass(int newSellIn, int oldQuality) {
        if (newSellIn < 0) {
            return MIN_QUALITY;
        }
        if (newSellIn < 5) {
            return Math.min(MAX_QUALITY, oldQuality + 3);
        }
        if (newSellIn < 10) {
            return Math.min(MAX_QUALITY, oldQuality + 2);
        }
        return Math.min(MAX_QUALITY, oldQuality + 1);
    }

    private static int calculateNewQualityBrie(int newSellIn, int oldQuality) {
        if (newSellIn < 0) {
            return Math.min(MAX_QUALITY, oldQuality + 2);
        } else {
            return Math.min(MAX_QUALITY, oldQuality + 1);
        }
    }

    private static int calculateNewQualityNormalItem(int newSellIn, int oldQuality) {
        if (newSellIn < 0) {
            return Math.max(MIN_QUALITY, oldQuality - 2);
        } else {
            return Math.max(MIN_QUALITY, oldQuality - 1);
        }
    }

    private static int calculateNewQualityConjuredItem(int newSellIn, int oldQuality) {
        if (newSellIn < 0) {
            return Math.max(MIN_QUALITY, oldQuality - 4);
        } else {
            return Math.max(MIN_QUALITY, oldQuality - 2);
        }
    }

}