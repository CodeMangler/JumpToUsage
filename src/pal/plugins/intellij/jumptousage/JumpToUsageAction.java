package pal.plugins.intellij.jumptousage;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import pal.plugins.intellij.jumptousage.finders.PsiElementUsageFinderFactory;
import pal.plugins.intellij.jumptousage.ui.PresentationLocation;
import pal.plugins.intellij.jumptousage.ui.ReferencePresentation;
import pal.plugins.intellij.jumptousage.util.ReferenceNavigator;
import pal.plugins.intellij.jumptousage.wrappers.DataHolder;
import pal.plugins.intellij.jumptousage.wrappers.ReferenceCollection;

public class JumpToUsageAction extends AnAction {
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabled(e.getDataContext().getData(DataConstants.EDITOR) != null);
    }

    public void actionPerformed(AnActionEvent e) {
        DataHolder.getInstance().initDataHolder(e.getDataContext());

        final PsiElement psiElement = DataHolder.getInstance().PSI_ELEMENT;
        if (!(psiElement instanceof PsiNamedElement)) return;

        if (DataHolder.getInstance().EDITOR == null) return;
        final ReferenceCollection references = ReferenceCollection.EMPTY;

        String progressBarTitle = "Searching for usages of '" + ((PsiNamedElement) psiElement).getName() + "'  (in scope '" + psiElement.getUseScope() + "')";
        boolean searchWasNotCancelled = ProgressManager.getInstance().runProcessWithProgressSynchronously(new Runnable() {
            public void run() {
                references.addAll(PsiElementUsageFinderFactory.getUsageFinder(psiElement).findUsages());
            }
        }, progressBarTitle, true, DataHolder.getInstance().PROJECT);

        if (searchWasNotCancelled)
            presentUsages(psiElement, references);
        else
            System.gc();

        references.clear();
    }

    private void presentUsages(final PsiElement psiElement, final ReferenceCollection references) {
        ApplicationManager.getApplication().runReadAction(new Runnable() {
            public void run() {
                if (references.size() == 1) {
                    new ReferenceNavigator(references.iterator().next()).navigateToReference();
                } else {
                    PresentationLocation presentationLocation = new PresentationLocation(PresentationLocation.currentCaretLocation());
                    presentationLocation.translate(0, DataHolder.getInstance().EDITOR.getLineHeight());
                    new ReferencePresentation(references, psiElement).present(presentationLocation);
                }
            }
        });
    }
}
