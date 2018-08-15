package com.example.supermarketcheckout;

public class Item {
    private String itemIdentifier;
    private int itemPrice;
    private String specialPrice;


    public static class Builder {
        private String itemIdentifier;
        private int itemPrice;
        private String specialPrice = null;

        public Builder(String identifier) {
            this.itemIdentifier = identifier;
        }

        public Builder itemPrice(int price) {
            this.itemPrice = price;
            return this;
        }

        public Builder specialPrice(String price) {
            this.specialPrice = price;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    private Item(Builder b) {
        this.itemIdentifier = b.itemIdentifier;
        this.itemPrice = b.itemPrice;
        this.specialPrice = b.specialPrice;
    }

    public String getIdentifier() {
        return this.itemIdentifier;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public String getSpecialPrice() {
        return this.specialPrice;
    }

    public void updatePrice(int newPrice) {
        this.itemPrice = newPrice;
    }

    public void updateSpecialPrice(String newSpecialPrice) {
        this.specialPrice = newSpecialPrice;
    }

}
