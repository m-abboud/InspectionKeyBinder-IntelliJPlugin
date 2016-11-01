package com.othoslabs;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;

@SuppressWarnings("unused")
@State(name = "InspectionKeyBindSettings", storages = @Storage(StoragePathMacros.WORKSPACE_FILE))
public class KeyBindSettings implements PersistentStateComponent<KeyBindSettings> {
    public String customToolId1;
    public String customToolId2;
    public String customToolId3;

    public KeyBindSettings getState() {
        return this;
    }

    public void loadState(KeyBindSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public static KeyBindSettings getInstance(Project project) {
        return ServiceManager.getService(project, KeyBindSettings.class);
    }
}

