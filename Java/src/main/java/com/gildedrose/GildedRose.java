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
        if (!(item.name.equals(AGED_BRIE) || item.name.equals(HAND_OF_RAGNAROS) || item.name.equals(BACKSTAGE_PASS))) {
            return true;
        }
        return false;
    }


    public static void updateSingleItem(Item item) {
        if (isNormalItem(item)) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
            item.sellIn = item.sellIn - 1;
            if (item.sellIn < 0) {
                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
            }
        }


        if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASS)) {
            // Brie and backstage pass
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals(BACKSTAGE_PASS)) {
//                    Backstage pass
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1;
                        }
                    }
                }
            }
        }

        if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASS)) {
//            brie, pass
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (item.name.equals(BACKSTAGE_PASS)) {
                item.quality = 0;
            }
            if (item.name.equals(AGED_BRIE)) {
//brie
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }
}