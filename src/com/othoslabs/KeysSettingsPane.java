package com.othoslabs;


import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import javax.swing.*;

public class KeysSettingsPane implements Disposable {
    private JPanel root;
    private JTextField customToolId1;
    private JTextField customToolId2;
    private JTextField customToolId3;

    private final KeyBindSettings settings;

    public KeysSettingsPane(KeyBindSettings settings, final Project project) {
        this.settings = settings;

        // forgive my laziness
        customToolId1.setText(settings.customToolId1);
        customToolId2.setText(settings.customToolId2);
        customToolId3.setText(settings.customToolId3);
    }

    public JComponent getPane() {
        return root;
    }

    public boolean isModified() {
        return true;
    }

    public void apply() throws ConfigurationException {
        // forgive my laziness
        settings.customToolId1 = customToolId1.getText();
        settings.customToolId2 = customToolId2.getText();
        settings.customToolId3 = customToolId3.getText();
    }

    public void reset() {
        // forgive my laziness
    }

    public void dispose() {
    }

    private void createUIComponents() {

    }
}