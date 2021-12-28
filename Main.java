package com.company;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Main {
    private static String append;
    public static void main(String[] args){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        Scanner sc = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("1. Search an ID");
        System.out.println("2. View a Specific Page");
        int choice = sc.nextInt();
        switch (choice){
            case 1 : {
                System.out.println("Enter ID to be searched");
                int id = sc.nextInt();
                append="id="+id;
                break;
            }
            case 2 : {
                System.out.println("Enter items per page");
                int perpage = sc.nextInt();
                append="per_page="+perpage;
                System.out.println("Enter page number");
                int pageno = sc.nextInt();
                append+="&page="+pageno;
                break;
            }
            default:{
                System.out.println("Incorrect Choice");
            }
        }


        try {
            URL url1 = new URL("https://reqres.in/api/users");
            URL url2 = new URL(url1.getProtocol(), url1.getHost(), url1.getPort(), url1.getPath() + "?" + append, null);
            //System.out.println((url2));
            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            // System.out.println(status);

            if (status>299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            }
            System.out.println("Status : "+status);
            System.out.println(responseContent.toString());
            connection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
