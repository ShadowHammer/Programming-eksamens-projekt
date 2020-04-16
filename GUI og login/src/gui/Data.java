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
        {"Pob","1234","Student","3107"},
        {"Jil","2345","Student","6512"},
        {"Has","3456","Student","9648"},
        {"Dav","4321","Teacher","2310"},
        {"Jon","5432","Teacher","0308"}
    };
    ArrayList<String> Login = new ArrayList(Arrays.asList(logins));
    String[] loginTemplate = {
        "Username", "Password", "status", "ID"
        };
    String[][] classes = {
        {"3107","3E"},
        {"6512","3A"},
        {"9648","3B"}
    };
}
