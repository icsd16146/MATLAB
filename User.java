/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdstore;

abstract public class User {

    protected String username;
    protected String password, name, surname;
    protected UserStatus us;

    protected void logIn(String username, String password) {
        if (this.username.matches(username) && this.password.matches(password)) {
            System.out.println("Welcome!");
            this.us = UserStatus.logedIn;
        }
    }

    protected void logOut() {
        this.us = UserStatus.logedOut;
    }
    // protected void signIn(){}

}
