package com.gildedrose;

class GildedRose {
    public static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int SULFURAS_QUALITY = 80;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY = 50;
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

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
        if(isSulfuras(item)){
            return;
        }

        if (isNormalItem(item)) {
            item.sellIn = item.sellIn - 1;
            if (item.sellIn <0) {
                item.quality = Math.max(0, item.quality - 2);
            } else {
                item.quality = Math.max(0, item.quality - 1);
            }
        }

        if (isAgedBrie(item)) {
            item.sellIn = item.sellIn - 1;

            if (item.quality < 50) {
                item.quality = item.quality + 1;

            }

            if (item.sellIn < 0) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        if (isBackstagePass(item)) {
            item.sellIn = item.sellIn - 1;

            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.sellIn < 10) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 5) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        }

    }

}