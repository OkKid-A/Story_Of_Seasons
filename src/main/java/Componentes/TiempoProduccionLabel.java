package Componentes;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TiempoProduccionLabel extends JLabel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String value;

    public TiempoProduccionLabel() {
        super();
        addPropertyListener();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(listener);
    }

    private void addPropertyListener(){
        redundar().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                redundar().setText((String) evt.getNewValue());
            }
        });
    }

    public void setValue(String newValue){
        String oldValue = value;
        String value = newValue;
        support.firePropertyChange("Tiempo",oldValue,newValue);
    }

    private JLabel redundar(){
        return this;
    }
}
