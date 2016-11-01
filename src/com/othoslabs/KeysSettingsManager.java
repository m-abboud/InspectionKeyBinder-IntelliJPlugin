package com.othoslabs;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class KeysSettingsManager implements SearchableConfigurable, Configurable.NoScroll {
    private KeysSettingsPane settingsPane;
    private final KeyBindSettings settings;
    private final Project project;

    public KeysSettingsManager(Project project, KeyBindSettings settings) {
        this.project = project;
        this.settings = settings;
    }

    @Nls
    public String getDisplayName() {
        return "Inspection Key Binder";
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        return "reference.settings.ide.settings.spelling";
    }

    @NotNull
    public String getId() {
        return getHelpTopic();
    }

    @Nullable
    public Runnable enableSearch(String s) {
        return null;
    }

    public JComponent createComponent() {
        if (settingsPane == null)
            settingsPane = new KeysSettingsPane(settings, project);

        return settingsPane.getPane();
    }

    public boolean isModified() {
        return settingsPane == null || settingsPane.isModified();
    }

    public void apply() throws ConfigurationException {
        if (settingsPane != null)
            settingsPane.apply();
    }

    public void reset() {
        if (settingsPane != null)
            settingsPane.reset();
    }

    public void disposeUIResources() {
        settingsPane = null;
    }
}