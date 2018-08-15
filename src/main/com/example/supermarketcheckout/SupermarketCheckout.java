//http://codekata.com/kata/kata09-back-to-the-checkout/
package com.example.supermarketcheckout;

import java.util.*;

public class SupermarketCheckout {


    ArrayList<Item> stockList = new ArrayList<Item>();

    ArrayList<String> basketList = new ArrayList<String>();


    public ArrayList<Item> getStockList() {
        return stockList;
    }


    public void addItemToStockList(Item item) {
        stockList.add(item);
    }

    public void modifyItemPrice(String productIdentifier, int newPrice) {
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

    public void modifyItemSpecialPrice(String productIdentifier, int quantityOfDeal, int specialPrice) {
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
        Set<String> unique = new HashSet<>(basketList); //pass unique items in basketList to HashSet

        for (String key : unique) {
            totalCost += calculateTotalValueOfBasket(key);
        }

        return totalCost;
    }

    private int calculateTotalValueOfBasket(String key) {
        int basketTotal = 0;
        int numberOfItemsInBasket = Collections.frequency(basketList, key);
        Item item = returnItemByIdentifier(key);

        if (itemHasASpecialPrice(item)) {
            basketTotal += calculateCostOfItemsEligibleForSpecialPrice(numberOfItemsInBasket, item);
        } else {
            basketTotal += calculateRegularItems(numberOfItemsInBasket, item);
        }

        return basketTotal;
    }

    private boolean itemHasASpecialPrice(Item item) {
        return item.getSpecialPrice() != null;
    }

    private int calculateCostOfItemsEligibleForSpecialPrice(int numberOfItemsInBasket, Item item) {
        int numberOfNonSpecialPrices = numberOfItemsInBasket;
        int quantityOfDeal;
        int specialPrice;
        int numberOfSpecialPrices;
        String[] specialDealParts = item.getSpecialPrice().split(" for ");
        quantityOfDeal = Integer.parseInt(specialDealParts[0]);
        specialPrice = Integer.parseInt(specialDealParts[1]);
        numberOfSpecialPrices = numberOfItemsInBasket/quantityOfDeal;
        numberOfNonSpecialPrices = numberOfNonSpecialPrices - numberOfSpecialPrices * quantityOfDeal;

        return (numberOfSpecialPrices * specialPrice) + (numberOfNonSpecialPrices * item.getItemPrice());
    }

    private int calculateRegularItems(int numberOfItemsInBasket, Item item) {
        return item.getItemPrice() * numberOfItemsInBasket;
    }
}