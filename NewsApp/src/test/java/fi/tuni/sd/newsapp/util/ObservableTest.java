/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.sd.newsapp.util;

import fi.tuni.sd.newsapp.enums.ObserverUpdateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * Unit tests for Observable class.
 *
 * @author Savidya
 */
public class ObservableTest {

    private Observable observable;
    private IObserver mockObserver1;
    private IObserver mockObserver2;

    @BeforeEach
    void setUp() {
        observable = new ObservableImpl();
        mockObserver1 = Mockito.mock(IObserver.class);
        mockObserver2 = Mockito.mock(IObserver.class);
    }

    @Test
    void testAddObserver() {
        observable.addObserver(mockObserver1);
        observable.notifyObservers(ObserverUpdateType.COUNTRY_LIST_UPDATED);
        verify(mockObserver1, times(1)).update(ObserverUpdateType.COUNTRY_LIST_UPDATED);
    }

    @Test
    void testRemoveObserver() {
        observable.addObserver(mockObserver1);
        observable.removeObserver(mockObserver1);
        observable.notifyObservers(ObserverUpdateType.COUNTRY_LIST_UPDATED);
        verify(mockObserver1, never()).update(ObserverUpdateType.COUNTRY_LIST_UPDATED);
    }

    @Test
    void testNotifyObservers() {
        observable.addObserver(mockObserver1);
        observable.addObserver(mockObserver2);
        observable.notifyObservers(ObserverUpdateType.COUNTRY_LIST_UPDATED);
        verify(mockObserver1, times(1)).update(ObserverUpdateType.COUNTRY_LIST_UPDATED);
        verify(mockObserver2, times(1)).update(ObserverUpdateType.COUNTRY_LIST_UPDATED);
    }

    @Test
    void testNotifyObserversWithNoObservers() {
        observable.notifyObservers(ObserverUpdateType.COUNTRY_LIST_UPDATED);
        verifyNoInteractions(mockObserver1);
        verifyNoInteractions(mockObserver2);
    }

    public class ObservableImpl extends Observable {
    }

}
