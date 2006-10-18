package pal.plugins.intellij.jumptousage.wrappers;

import com.intellij.psi.PsiReference;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ReferenceCollection extends AbstractCollection<Reference> {
    private ArrayList<Reference> references = new ArrayList<Reference>();
    public static ReferenceCollection EMPTY = new ReferenceCollection(new ArrayList<PsiReference>());

    public ReferenceCollection(Collection<PsiReference> psiReferences) {
        for (PsiReference reference : psiReferences) {
            references.add(new Reference(reference));
        }
    }

    public ReferenceCollection(ReferenceCollection references) {
        this.references.addAll(references);
    }

    public Iterator<Reference> iterator() {
        return references.iterator();
    }

    public boolean addAll(Collection<? extends Reference> references) {
        return this.references.addAll(references);
    }


    public boolean add(Reference reference) {
        return references.add(reference);
    }

    public ReferenceCollection removeDuplicates() {
        ReferenceCollection uniqueReferences = ReferenceCollection.EMPTY;
        for (Reference reference : references) {
            if (!uniqueReferences.contains(reference))
                uniqueReferences.add(reference);
        }
        return uniqueReferences;
    }

    public int size() {
        return references.size();
    }
}
