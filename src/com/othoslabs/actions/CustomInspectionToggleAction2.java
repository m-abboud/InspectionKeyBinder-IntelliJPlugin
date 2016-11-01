package com.othoslabs.actions;

import com.othoslabs.KeyBindSettings;

/*
    Appearnetley you can't parameterize intellik plugin actions so I'm using these silly seperate classes
    for 1, 2 and 3 custom inspection toggles so forgive it's clumisiness ;_;
 */
public class CustomInspectionToggleAction2 extends CustomInspectionToggleAction {
    public CustomInspectionToggleAction2() {
        super(2);
    }

    protected String getToolId(KeyBindSettings settings) {
        return settings.customToolId2;
    }
}
