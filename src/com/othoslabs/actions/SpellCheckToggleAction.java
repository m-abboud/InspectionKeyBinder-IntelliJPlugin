package com.othoslabs.actions;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.othoslabs.InspectionToggler;

import java.io.IOException;

public class SpellCheckToggleAction extends AnAction {
    private static final String spellingToolId = "SpellCheckingInspection";

    public SpellCheckToggleAction() {
        super("Toggle Spell Check");
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        PsiFile file = event.getData(PlatformDataKeys.PSI_FILE);

        try {
            InspectionToggler.toggleInspection(spellingToolId, project, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}