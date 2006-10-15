package pal.plugins.intellij.jumptousage;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import pal.plugins.intellij.jumptousage.finders.PsiElementUsageFinderFactory;
import pal.plugins.intellij.jumptousage.ui.PresentationLocation;
import pal.plugins.intellij.jumptousage.ui.ReferencePresentation;
import pal.plugins.intellij.jumptousage.util.ReferenceNavigator;
import pal.plugins.intellij.jumptousage.wrappers.DataHolder;
import pal.plugins.intellij.jumptousage.wrappers.ReferenceCollection;

public class JumpToUsageAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        PsiElement psiElement = (PsiElement) e.getDataContext().getData(DataConstants.PSI_ELEMENT);
        if (!(psiElement instanceof PsiNamedElement)) return;

        DataHolder.getInstance().initDataHolder(e.getDataContext());

        ReferenceCollection references = PsiElementUsageFinderFactory.getUsageFinder(psiElement).findUsages();
        if (references.size() == 1) {
            new ReferenceNavigator(references.iterator().next()).navigateToReference();
        } else {
            PresentationLocation presentationLocation = new PresentationLocation(PresentationLocation.currentCaretLocation());
            new ReferencePresentation(references, psiElement).present(presentationLocation);
        }
    }
}
