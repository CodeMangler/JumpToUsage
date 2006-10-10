package pal.plugins.intellij.jumptousage.ui;

import com.intellij.codeInsight.hint.TooltipController;
import pal.plugins.intellij.jumptousage.wrappers.DataHolder;

public class TooltipPresentation implements Presentable {
    private String toolTipMessage;

    public TooltipPresentation(String toolTipMessage) {
        this.toolTipMessage = toolTipMessage;
    }

    public void present(PresentationLocation location) {
        new TooltipController().showTooltip(DataHolder.getInstance().EDITOR, location, toolTipMessage, false, null);
    }
}
