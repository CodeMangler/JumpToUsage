package pal.plugins.intellij.jumptousage.finders;

import com.intellij.psi.PsiElement;
import com.intellij.psi.search.searches.ReferencesSearch;
import pal.plugins.intellij.jumptousage.wrappers.ReferenceCollection;

public class DefaultUsageFinder extends AbstractUsageFinder {

    public DefaultUsageFinder(PsiElement psiElement) {
        super(psiElement);
    }

    protected ReferenceCollection findCurrentElementUsages() {
        ReferencesSearch.SearchParameters searchParameters = new ReferencesSearch.SearchParameters(this.psiElement, this.psiElement.getUseScope(), true);
        return new ReferenceCollection(ReferencesSearch.INSTANCE.createQuery(searchParameters).findAll());
    }

}
