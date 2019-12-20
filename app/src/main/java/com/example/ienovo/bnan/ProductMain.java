package com.example.ienovo.bnan;


public class ProductMain {
    private int id;
    private String name;
    private String image;
    private String email;
    private String prunch;
    private String stutus;



    public ProductMain(int id, String title, String shortdesc, String rating, String price,String stutus) {
        this.id = id;
        this.name = title;
        this.image = shortdesc;
        this.email = rating;
        this.prunch = price;
        this.stutus = stutus;


    }

    public int employ_id() {
        return id;
    }

    public String employ_name() { return name;
    }

    public String employ_image() {
        return image;
    }

    public String employ_email() {
        return email;
    }

    public String employ_prunch() {
        return prunch;
    }
    public String employ_stutus() {
        return stutus;
    }


}