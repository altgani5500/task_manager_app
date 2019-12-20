package com.example.ienovo.bnan;


public class ProductMain2 {

    private int mssages_id;
    private String mssages_desc;
    private String mssages_date;
    private String mssages_time;
    private String mssages_type;
    private String mssages_done;

    private String employs_id;
    private String employs_name;
    private String employs_image;
    private String employs_email;
    private String employs_prunch;
    private String jobs_name;
    private String images_name;

    public ProductMain2(int id, String title, String shortdesc, String rating, String price, String image, String image1, String image2,
                        String image3, String image4, String image5, String image6, String image7) {
        this.mssages_id = id;
        this.mssages_desc = title;
        this.mssages_date = shortdesc;
        this.mssages_time = rating;
        this.mssages_type = price;
        this.mssages_done = image;

        this.employs_id = image1;
        this.employs_name = image2;
        this.employs_image = image3;
        this.employs_email = image4;
        this.employs_prunch = image5;
        this.jobs_name = image6;
        this.images_name = image7;


    }

    public int getId() {
        return mssages_id;
    }

    public String getTitle() { return mssages_desc;
    }

    public String getShortdesc() {
        return mssages_date;
    }

    public String getRating() {
        return mssages_time;
    }

    public String getPrice() {
        return mssages_type;
    }
    public  String getImage() {
        return mssages_done;
    }

    public  String getImage1() {
        return employs_id;
    }
    public  String getImage2() {
        return employs_name;
    }
    public  String getImage3() {
        return employs_image;
    }
    public  String getImage4() {
        return employs_email;
    }
    public  String getImage5() {
        return employs_prunch;
    }


    public  String getImage6() {
        return jobs_name;
    }
    public  String getImage7() {
        return images_name;
    }

}