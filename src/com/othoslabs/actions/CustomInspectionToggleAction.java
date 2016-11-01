package com.othoslabs.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.othoslabs.InspectionToggler;
import com.othoslabs.KeyBindSettings;

import java.io.IOException;

/*
    Appearnetley you can't parameterize intellik plugin actions so I'm using these silly seperate classes
    for 1, 2 and 3 custom inspection toggles so forgive it's clumisiness ;_;
 */
public class CustomInspectionToggleAction extends AnAction {
    public CustomInspectionToggleAction() {
        super("Custom Toggle Inspection #1");
    }
    
    public CustomInspectionToggleAction(int number) {
        super("Custom Toggle Inspection #" + number);
    }

    protected String getToolId(KeyBindSettings settings) {
        return settings.customToolId1;

    }
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        PsiFile file = event.getData(PlatformDataKeys.PSI_FILE);
        KeyBindSettings settings = KeyBindSettings.getInstance(project);
        String toolId = getToolId(settings);
        
        try {
            InspectionToggler.toggleInspection(toolId, project, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}