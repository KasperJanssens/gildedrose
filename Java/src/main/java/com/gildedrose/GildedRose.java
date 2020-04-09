package com.gildedrose;

class GildedRose {
    public static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int SULFURAS_QUALITY = 80;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY = 50;
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }


    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateSingleItem(items[i]);
        }
    }

    public static boolean isNormalItem(Item item) {
        return !(item.name.equals(AGED_BRIE) || item.name.equals(HAND_OF_RAGNAROS) || item.name.equals(BACKSTAGE_PASS));
    }

    public static boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    public static boolean isSulfuras(Item item) {
        return item.name.equals(HAND_OF_RAGNAROS);
    }

    private static boolean isBackstagePass(Item item) {
        return item.name.equals(BACKSTAGE_PASS);
    }


    public static void updateSingleItem(Item item) {
        if (isSulfuras(item)) {
            return;
        }

        if (isNormalItem(item)) {
            item.sellIn = item.sellIn - 1;
            int newQuality = handleNormalItem(item.sellIn, item.quality);
            item.quality = newQuality;
        }

        if (isAgedBrie(item)) {
            item.sellIn = item.sellIn - 1;
            int newQuality = handleBrie(item.sellIn, item.quality);
            item.quality = newQuality;
        }

        if (isBackstagePass(item)) {
            item.sellIn = item.sellIn - 1;
            int newQuality = handleBackstagePass(item.sellIn, item.quality);
            item.quality = newQuality;
        }

    }

    private static int handleBackstagePass(int newSellIn, int oldQuality) {
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

    private static int handleBrie(int newSellIn, int oldQuality) {
        if (newSellIn < 0) {
            return Math.min(MAX_QUALITY, oldQuality + 2);
        } else {
            return Math.min(MAX_QUALITY, oldQuality + 1);
        }
    }

    private static int handleNormalItem(int newSellIn, int oldQuality) {
        if (newSellIn < 0) {
            return Math.max(MIN_QUALITY, oldQuality - 2);
        } else {
            return Math.max(MIN_QUALITY, oldQuality - 1);
        }
    }

}