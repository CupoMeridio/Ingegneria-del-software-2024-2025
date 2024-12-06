/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.unisa.diem.team02.contactsbook.model;

/**
 *
 * @author anuar
 */
public interface UserInteractionDataInterface {
    
    public abstract void insertContact();
    public abstract void deleteContact();
    public abstract Contact getContact();
    public abstract void modifyContact();
    
}
