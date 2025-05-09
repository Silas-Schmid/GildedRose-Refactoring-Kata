package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isSulfuras(item)) {
                continue;
            }

            updateSellIn(item);
            updateQualityForItem(item);
        }
    }

    private void updateSellIn(Item item) {
        item.sellIn -= 1;
    }

    private void updateQualityForItem(Item item) {
        if (isAgedBrie(item)) {
            updateAgedBrieQuality(item);
        } else if (isBackstagePass(item)) {
            updateBackstagePassQuality(item);
        } else {
            updateGenericItemQuality(item);
        }

        if (item.sellIn < 0) {
            handleExpiredItem(item);
        }
    }

    private void updateAgedBrieQuality(Item item) {
        increaseQuality(item);
    }

    private void updateBackstagePassQuality(Item item) {
        increaseQuality(item);

        if (item.sellIn < 10) {
            increaseQuality(item);
        }

        if (item.sellIn < 5) {
            increaseQuality(item);
        }
    }

    private void updateGenericItemQuality(Item item) {
        decreaseQuality(item);
    }

    private void handleExpiredItem(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePass(item)) {
            item.quality = 0;
        } else {
            decreaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.name);
    }

    private boolean isBackstagePass(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.name);
    }

    private boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.name);
    }
}
