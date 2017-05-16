package com.othoslabs;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.codeInsight.daemon.HighlightDisplayKey;
import com.intellij.codeInspection.InspectionProfile;
import com.intellij.codeInspection.ex.InspectionProfileImpl;
import com.intellij.codeInspection.ex.InspectionProfileModifiableModel;
import com.intellij.openapi.project.Project;
import com.intellij.profile.codeInspection.InspectionProjectProfileManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class InspectionToggler {
    public static void toggleInspection(String toolId, @NotNull Project project, final PsiFile file) throws IOException {
        if (toolId == null || toolId.isEmpty())
            return;

        if (file == null)
            return;

        modifyAndCommitProjectProfile(modifiableModel -> {
            modifiableModel.setToolEnabled(toolId, !isInspectionEnabled(toolId, file));
        }, project);

        DaemonCodeAnalyzer.getInstance(project).restart();
    }

    public static boolean isInspectionEnabled(@NonNls String shortName, PsiElement context) {
        final InspectionProjectProfileManager profileManager = InspectionProjectProfileManager.getInstance(context.getProject());
        final InspectionProfile profile = profileManager.getCurrentProfile();

        return profile.isToolEnabled(HighlightDisplayKey.find(shortName), context);
    }

    private static void modifyAndCommitProjectProfile(Consumer<InspectionProfileImpl> action, Project project) throws IOException {
        InspectionProjectProfileManager profileManager = InspectionProjectProfileManager.getInstance(project);
        InspectionProfileImpl inspectionProfile = profileManager.getCurrentProfile();

        InspectionProfileModifiableModel model = inspectionProfile.getModifiableModel();
        action.consume(model);
        model.commit();
    }
}
