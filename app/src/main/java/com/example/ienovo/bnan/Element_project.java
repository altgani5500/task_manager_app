package com.example.ienovo.bnan;


public class Element_project {
    private int project_id;
    private String project_name;
    private String project_desc;
    private String project_image;
    private String project_date_start;
    private String project_date_end;


    public Element_project(int id, String title, String shortdesc, String rating, String price, String image) {
        this.project_id = id;
        this.project_name = title;
        this.project_desc = shortdesc;
        this.project_image = rating;
        this.project_date_start = price;
        this.project_date_end = image;



    }

    public int project_id() {
        return project_id;
    }

    public String project_name() { return project_name;
    }

    public String project_desc() {
        return project_desc;
    }

    public String project_image() {
        return project_image;
    }

    public String project_date_start() {
        return project_date_start;
    }

    public  String project_date_end() {
        return project_date_end;
    }



}