package com.example.supermarketcheckout;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class SupermarketCheckoutTest {

    private SupermarketCheckout supermarketCheckout;

    @Before
    public void setup () {
        supermarketCheckout = new SupermarketCheckout();
        Item productA = new Item.Builder("A").itemPrice(50).specialPrice("3 for 130").build();
        Item productB = new Item.Builder("B").itemPrice(30).specialPrice("2 for 45").build();
        Item productC = new Item.Builder("C").itemPrice(20).build();
        Item productD = new Item.Builder("D").itemPrice(10).build();

        supermarketCheckout.addItemStockList(productA);
        supermarketCheckout.addItemStockList(productB);
        supermarketCheckout.addItemStockList(productC);
        supermarketCheckout.addItemStockList(productD);
    }

    @Test
    public void TestOne() {
        assertEquals(10, 5 + 5);
    }

    @Test
    public void retrieveItem () {
        assertEquals("A", supermarketCheckout.getStockList().get(0).getIdentifier());
        assertEquals(50, supermarketCheckout.getStockList().get(0).getItemPrice());
        assertEquals("3 for 130", supermarketCheckout.getStockList().get(0).getSpecialPrice());
    }

    @Test
    public void shouldBeAbleToModifyThePriceOfAnItem () {
        supermarketCheckout.modiftyItemPrice("A", 10);
        assertEquals(10, supermarketCheckout.getStockList().get(0).getItemPrice());
    }

    @Test
    public void shouldBeAbleToModifySpecialPriceOfAnItem () {
        supermarketCheckout.modiftyItemSpecialPrice("A", 3, 100);
        assertEquals("3 for 100", supermarketCheckout.getStockList().get(0).getSpecialPrice());
    }

    @Test
    public void shouldBeAbleToScanItemToBasket () {
        supermarketCheckout.scanItem("A");
        assertEquals("A", supermarketCheckout.getBasketList().get(0));
    }

    @Test
    public void shouldNotScanAnItemToBasketIfItDoesNotExistInStockList () {
        supermarketCheckout.scanItem("Z");
        assertEquals(0, supermarketCheckout.getBasketList().size());
    }

    @Test
    public void priceShouldBeZeroWhenBasketEmpty () {
        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(0, totalPrice);
    }

    @Test
    public void shouldCalculateTotalPriceOfItemsScanned () {
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("B");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(80, totalPrice);
    }

    @Test
    public void shouldCalculateTotalPriceWhenItemsHaveSpecialPrice () {
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(130, totalPrice);
    }

    @Test
    public void combinationOfSpecialAndRegular () {
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("C");
        supermarketCheckout.scanItem("A");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(150, totalPrice);
    }

    @Test
    public void combinationOfTwoSpecialPrices () {
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("B");
        supermarketCheckout.scanItem("B");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(175, totalPrice);
    }

    @Test
    public void twoOfTheSameSpecialPrices () {
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(260, totalPrice);
    }


    @Test
    public void shouldHandleASpecialPriceAndNonSpecialOfTheSameItem () {
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(180, totalPrice);
    }

    @Test
    public void testCalculatingRandomBasket () {
        supermarketCheckout.scanItem("D");
        supermarketCheckout.scanItem("B");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("B");
        supermarketCheckout.scanItem("B");
        supermarketCheckout.scanItem("C");
        supermarketCheckout.scanItem("D");
        supermarketCheckout.scanItem("A");
        supermarketCheckout.scanItem("B");
        supermarketCheckout.scanItem("A");

        int totalPrice = supermarketCheckout.getTotal();

        assertEquals(310, totalPrice);
    }

}