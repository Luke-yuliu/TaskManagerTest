package com.taskmgat.domainobject;

public class AppEnv {

        private static final String Dev = "http://localhost:4200" ;


        public static String getDevUrl(){
            return Dev;
        }
        public static String getDevHomeUrl(){return Dev + "/nav/home";}
        public static String getDevTaskPage(){return Dev + "/nav/all-tasks";}
        public static String getDevImportantTaskPage(){return Dev + "nav/important-tasks";}

}
