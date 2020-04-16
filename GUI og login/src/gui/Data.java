/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Tobias
 */
public class Data {
    String[][] logins = {
        {"Pob","1234","Student","3E"},
        {"Jil","2345","Student","3A"},
        {"Has","3456","Student","3B"},
        {"Dav","4321","Teacher","Math"},
        {"Jon","5432","Teacher","Programming"}
    };
    ArrayList<String> Login = new ArrayList(Arrays.asList(logins));
    String[] loginTemplate = {
        "Username", "Password", "status", "ID"
        };
    
}
