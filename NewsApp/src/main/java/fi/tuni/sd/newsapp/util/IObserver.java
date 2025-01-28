/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

import fi.tuni.sd.newsapp.enums.ObserverUpdateType;

/**
 * The IObserver interface that defines observer objects in the Observer design
 pattern.
 *
 * @author Savidya
 */
public interface IObserver {

    /**
     * Called when when the observable object changes state.
     *
     * @param updateType The type of update occurred.
     */
    void update(ObserverUpdateType updateType);
}