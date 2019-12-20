package com.example.ienovo.bnan;


public class Element_jobs {
    private int job_id;
    private String job_name;
    private String job_desc;
    private String job_image;
//    private String project_date_start;
//    private String project_date_end;


    public Element_jobs(int id, String title, String shortdesc, String rating) {
        this.job_id = id;
        this.job_name = title;
        this.job_desc = shortdesc;
        this.job_image = rating;
//        this.project_date_start = price;
//        this.project_date_end = image;



    }

    public int job_id() {
        return job_id;
    }

    public String job_name() { return job_name;
    }

    public String job_desc() {
        return job_desc;
    }

    public String job_image() {
        return job_image;
    }

//    public String project_date_start() {
//        return project_date_start;
//    }
//
//    public  String project_date_end() {
//        return project_date_end;
//    }



}