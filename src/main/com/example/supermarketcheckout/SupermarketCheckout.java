//http://codekata.com/kata/kata09-back-to-the-checkout/
package com.example.supermarketcheckout;

import java.util.*;

public class SupermarketCheckout {


    ArrayList<Item> stockList = new ArrayList<Item>();

    ArrayList<String> basketList = new ArrayList<String>();


    public ArrayList<Item> getStockList() {
        return stockList;
    }


    public void addItemStockList(Item item) {
        stockList.add(item);
    }

    public void modiftyItemPrice(String productIdentifier, int newPrice) {
        returnItemByIdentifier(productIdentifier).updatePrice(newPrice);
    }

    private Item returnItemByIdentifier(String productIdentifier) {
        for(Item item : stockList) {
            if(item.getIdentifier().equals(productIdentifier)) {
                return item;
            }
        }
        return null;
    }

    public void modiftyItemSpecialPrice(String productIdentifier, int quantityOfDeal, int specialPrice) {
        String newSpecialPrice = (Integer.toString(quantityOfDeal)) + " for " + (Integer.toString(specialPrice));
        returnItemByIdentifier(productIdentifier).updateSpecialPrice(newSpecialPrice);
    }

    public void scanItem(String productIdentifier) {
        if(itemExistsInStockList(productIdentifier)){
            basketList.add(productIdentifier);
        }
    }

    private boolean itemExistsInStockList(String productIdentifier) {
        return returnItemByIdentifier(productIdentifier) != null;
    }

    public ArrayList<String> getBasketList() {
        return basketList;
    }

    public int getTotal() {
        int totalCost = 0;
        Set<String> unique = new HashSet<String>(basketList);

        for (String key : unique) {
            totalCost = calculateTotalValueOfBasket(totalCost, key);
        }

        return  totalCost;
    }

    private int calculateTotalValueOfBasket(int totalCost, String key) {
        int quantityOfDeal;
        int specialPrice;
        int numberOfItemsInBasket = Collections.frequency(basketList, key);
        int numberOfSpecialPrices = 0;
        int numberOfNonSpecialPrices = numberOfItemsInBasket;
        Item item = returnItemByIdentifier(key);

        if (item.getSpecialPrice() != null) {
            String[] specialDealParts = item.getSpecialPrice().split(" for ");
            quantityOfDeal = Integer.parseInt(specialDealParts[0]);
            specialPrice = Integer.parseInt(specialDealParts[1]);
            numberOfSpecialPrices = numberOfItemsInBasket/quantityOfDeal;
            numberOfNonSpecialPrices = numberOfNonSpecialPrices - numberOfSpecialPrices * quantityOfDeal;

            totalCost += numberOfSpecialPrices * specialPrice;
            totalCost += numberOfNonSpecialPrices * item.getItemPrice();

        } else {
            totalCost += item.getItemPrice() * numberOfItemsInBasket;
        }

        return totalCost;
    }
}