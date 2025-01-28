/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

import fi.tuni.sd.newsapp.enums.ObserverUpdateType;
import java.util.ArrayList;
import java.util.List;

/**
 * The Observable class that serves as the base class for implementing the
 * IObserver design pattern.
 *
 * @author Savidya
 */
public abstract class Observable {

    private final List<IObserver> observers = new ArrayList<>();

    /**
     * Add an observer to the list ( allowing it to be notified of changes).
     *
     * @param observer The observer to be added to the notification list.
     */
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Remove an observer from the list (stopping it from receiving updates).
     *
     * @param observer The observer to be removed from the notification list.
     */
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notify all observers of a state change.
     *
     * @param updateType The update type that has occurred.
     */
    public void notifyObservers(ObserverUpdateType updateType) {
        for (IObserver observer : observers) {
            observer.update(updateType);
        }
    }
}
