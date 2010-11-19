package pal.plugins.intellij.jumptousage.finders;

import pal.plugins.intellij.jumptousage.wrappers.ReferenceCollection;

public interface PsiElementUsageFinder {
    ReferenceCollection findUsages();

    PsiElementUsageFinder setNext(PsiElementUsageFinder nextFinder);
}
