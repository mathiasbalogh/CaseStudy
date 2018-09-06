package com.target.case_study.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private Integer id;

    private String name;

    private ProductPrice current_price;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Item item;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameFromProductDescription() {
        String name = this.getItem().product_description.getTitle();
        if (name != null) {
            this.name = name;
        }
    }

    public ProductPrice getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(ProductPrice current_price) {
        this.current_price = current_price;
    }

    @JsonIgnore
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public static class Item {

        private ProductDescription product_description;


        public ProductDescription getProduct_description() {
            return product_description;
        }

        public void setProduct_description(ProductDescription product_description) {
            this.product_description = product_description;
        }

        public static class ProductDescription {
            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }

    }

}
