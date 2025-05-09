package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void testAgedBrieIncreasesInQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(21, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testBackstagePassesIncreaseInQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(21, items[0].quality);
        assertEquals(14, items[0].sellIn);
    }

    @Test
    void testBackstagePassesIncreaseBy2WhenSellIn10OrLess() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(22, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testBackstagePassesIncreaseBy3WhenSellIn5OrLess() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(23, items[0].quality);
        assertEquals(4, items[0].sellIn);
    }

    @Test
    void testBackstagePassesDropToZeroAfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void testSulfurasNeverDecreasesInQualityOrSellIn() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(80, items[0].quality);
        assertEquals(0, items[0].sellIn);
    }

    @Test
    void testGenericItemDecreasesInQualityAndSellIn() {
        Item[] items = new Item[] { new Item("Generic Item", 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(19, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testGenericItemDecreasesTwiceAsFastAfterSellIn() {
        Item[] items = new Item[] { new Item("Generic Item", 0, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(18, items[0].quality);
        assertEquals(-1, items[0].sellIn);
    }

    @Test
    void testQualityNeverGoesNegative() {
        Item[] items = new Item[] { new Item("Generic Item", 10, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testQualityNeverExceeds50() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }
}
